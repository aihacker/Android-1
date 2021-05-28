package com.example.memorandum;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.format.Time;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.UriUtils;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

import db.MyDbHelper;

public class ThirdActivity extends AppCompatActivity {

    EditText edit_title,edit_content;
    Button btn_camera,btn_photo,btn_save;
    ImageView img_preview;
    String tmp_path,disp_path;
    MyDbHelper mhelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            StrictMode.VmPolicy.Builder builder=new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        inintView();
        btnOnClick();
        btnSave();
    }

    private void inintView() {
        edit_title=findViewById(R.id.editText_title);
        edit_content=findViewById(R.id.editText_content);
        btn_camera=findViewById(R.id.button_camera);
        btn_photo=findViewById(R.id.button_photo);
        img_preview=findViewById(R.id.imageView_preview);
        btn_save=findViewById(R.id.button_save);
        mhelper=new MyDbHelper(ThirdActivity.this);
        db=mhelper.getWritableDatabase();
    }

    private void btnOnClick() {
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Time time=new Time();
                time.setToNow();
                String randtime=time.year+(time.month+1)+time.monthDay+time.hour+time.minute+time.second+"";
                tmp_path= Environment.getExternalStorageDirectory()+"/image"+ randtime+".jpg";
                File imgfile=new File(tmp_path);
                try {
                    imgfile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgfile));
                startActivityForResult(intent,11);
            }
        });

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent,22);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 11:
                if (resultCode == RESULT_OK) {
                    disp_path = tmp_path;
                    Glide.with(ThirdActivity.this).load(disp_path).into(img_preview);
                }
                break;
            case 22:
                Uri imageuri = data.getData();
                if (imageuri == null) {
                    return;
                }
                disp_path = UriUtils.uri2File(imageuri).getPath();
                Glide.with(ThirdActivity.this).load(disp_path).into(img_preview);
                break;
            default:
                break;
        }
    }

    private void btnSave() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Time time=new Time();
                time.setToNow();
                ContentValues contentValues=new ContentValues();
                contentValues.put("title",edit_title.getText().toString());
                contentValues.put("content",edit_content.getText().toString());
                contentValues.put("imgpath",disp_path);
                contentValues.put("mtime",time.year+"/"+(time.month+1)+"/"+time.monthDay);
                db.insert("tb_memory",null,contentValues);
                Toast.makeText(ThirdActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}