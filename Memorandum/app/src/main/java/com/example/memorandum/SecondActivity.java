package com.example.memorandum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.memorandum.adapter.MemoAdapter;
import com.example.memorandum.bean.MemoBean;

import java.util.ArrayList;
import java.util.List;

import db.MyDbHelper;

public class SecondActivity extends AppCompatActivity {
    RecyclerView recy_view;
    MyDbHelper mhelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recy_view=findViewById(R.id.recy_view);
        mhelper=new MyDbHelper(SecondActivity.this);
        db=mhelper.getWritableDatabase();
        recy_Display();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.delete_item:
                //“删除”
                break;
            case R.id.modify_item:
                //“修改”
                break;
            case R.id.exit_item:
                AlertDialog.Builder builder=new AlertDialog.Builder(SecondActivity.this);//创建AlertDialog构造器
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle("温馨提示");
                builder.setMessage("确定要退出吗?");
                builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SecondActivity.this,"成功退出《美添1记》",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
                break;
            default:
        }
        return true;
    }

    private void recy_Display() {
        List<MemoBean> arr=new ArrayList();
        Cursor cursor=db.rawQuery("select * from tb_memory",null);
        while (cursor.moveToNext()){
            String mytitle=cursor.getString(cursor.getColumnIndex("title"));
            String mycontent=cursor.getString(cursor.getColumnIndex("content"));
            String myimgpath=cursor.getString(cursor.getColumnIndex("imgpath"));
            String mymtime=cursor.getString(cursor.getColumnIndex("mtime"));
            MemoBean memoBean=new MemoBean(mytitle,mycontent,myimgpath,mymtime);
            arr.add(memoBean);
        }
        cursor.close();

        MemoAdapter adapter=new MemoAdapter(SecondActivity.this,arr);
        StaggeredGridLayoutManager st=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recy_view.setLayoutManager(st);

        recy_view.setAdapter(adapter);
    }
}
