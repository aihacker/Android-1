package com.example.memorandum.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.memorandum.R;
import com.example.memorandum.bean.MemoBean;

import java.util.List;
import java.util.Random;

import db.MyDbHelper;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {
    private Context mcontext;
    private List<MemoBean> arr1;
    private MyDbHelper mhelper1;
    private SQLiteDatabase db;

    public MemoAdapter(Context mcontext, List<MemoBean> arr1) {
        this.mcontext = mcontext;
        this.arr1 = arr1;
    }

    @NonNull
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.recy_item,parent,false);
        ViewHolder mholder=new ViewHolder(view);
        return mholder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder mholder, final int i) {
        MemoBean memoBean=arr1.get(i);
        mholder.item_title.setText(memoBean.getTitle());
        mholder.item_content.setText(memoBean.getContent());
        mholder.item_time.setText(memoBean.getTime());
        Glide.with(mcontext).load(memoBean.getImgpath()).into(mholder.item_img);

        Random random = new Random();
        int randcolor = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int color= Color.rgb(220,226,227);
        GradientDrawable gradientDrawable1 = new GradientDrawable();
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable1.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable1.setCornerRadius(10f);
        gradientDrawable1.setColor(color);
        gradientDrawable2.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable2.setCornerRadius(10f);
        gradientDrawable2.setColor(randcolor);
        mholder.item_layout.setBackground(gradientDrawable1);
        mholder.textView_2.setBackground(gradientDrawable2);

        mholder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(mcontext);
                dialog.setMessage("确定删除吗？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int abc) {
                        mhelper1= new MyDbHelper(mcontext);
                        db=mhelper1.getWritableDatabase();
                        db.delete("tb_memory","title=?",new String[]{arr1.get(i).getTitle()});
                        arr1.remove(i);
                        notifyItemRemoved(i);
                        dialogInterface.dismiss();
                    }
                });
                dialog.setNegativeButton("取消",null);
                dialog.setCancelable(false);
                dialog.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_title,item_content,item_time,textView_2;
        ImageView item_img;
        LinearLayout item_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_title=itemView.findViewById(R.id.item_title);
            item_content=itemView.findViewById(R.id.item_content);
            item_img=itemView.findViewById(R.id.item_image);
            item_time=itemView.findViewById(R.id.item_time);
            item_layout=itemView.findViewById(R.id.item_layout);
            textView_2=itemView.findViewById(R.id.textView_2);
        }
    }
}
