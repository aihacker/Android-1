package com.wangkrmf.project2;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/*public class FruitAdapter extends ArrayAdapter<Fruit> {

    public FruitAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {

            view = LayoutInflater.from(getContext()).inflate(R.layout.fruit_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitimage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitname = view.findViewById(R.id.fruit_name);
            viewHolder.fruitprice = view.findViewById(R.id.fruit_price);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.fruitimage.setImageResource(fruit.getImageID());
        viewHolder.fruitname.setText(fruit.getName());
        viewHolder.fruitprice.setText(fruit.getPrice());
        return view;
    }

    private class ViewHolder {
        ImageView fruitimage;
        TextView fruitname;
        TextView fruitprice;
    }
}*/

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitlist;
    public FruitAdapter(List<Fruit> fruitList) {
        mFruitlist = fruitList;
    }

    @NonNull
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        ViewHolder holder= new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitlist.get(position);
                Toast.makeText(view.getContext(),"您点击的布局为: " + fruit.getName(), Toast.LENGTH_LONG).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitlist.get(position);
                Toast.makeText(view.getContext(),"您点击的图片为: " + fruit.getName(), Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = mFruitlist.get(position);
        holder.fruitImage.setImageResource(fruit.getImageID());
        holder.fruitName.setText(fruit.getName());
        holder.fruitPrice.setText(fruit.getPrice());
    }

    @Override
    public int getItemCount() {
        return mFruitlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        TextView fruitPrice;
        public ViewHolder(@NonNull View view) {
            super(view);
            fruitView = view;
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
            fruitPrice = view.findViewById(R.id.fruit_price);
        }
    }
}