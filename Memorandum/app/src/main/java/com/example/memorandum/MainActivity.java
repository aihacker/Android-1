package com.example.memorandum;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_1,btn_2;
    EditText editText_1,editText_2;
    CheckBox checkBox_reme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        btnloginonClick();
        displayinfo();
        btndestroyonClick();
    }

    private void initView() {
        btn_1=findViewById(R.id.button_1);
        btn_2=findViewById(R.id.button_2);
        editText_1=findViewById(R.id.editText_1);
        editText_2=findViewById(R.id.editText_2);
        checkBox_reme=findViewById(R.id.checkBox_reme);
    }

    private void btnloginonClick() {
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"欢迎进入《美添1记》",Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor=getSharedPreferences("myfile",0).edit();
                editor.putString("name",editText_1.getText().toString());
                editor.putString("pwd",editText_2.getText().toString());
                editor.putBoolean("st",checkBox_reme.isChecked());
                editor.commit();

                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void displayinfo() {
        String myname=getSharedPreferences("myfile",0).getString("name","");
        String mypwd=getSharedPreferences("myfile",0).getString("pwd","");
        Boolean myst=getSharedPreferences("myfile",0).getBoolean("st",false);
        if(myst==true){
            editText_1.setText(myname);
            editText_2.setText(mypwd);
            checkBox_reme.setChecked(true);
        }else{
            editText_1.setText("");
            editText_2.setText("");
            checkBox_reme.setChecked(false);
        }
    }

    private void btndestroyonClick() {
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);//创建AlertDialog构造器
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle("温馨提示");
                builder.setMessage("确定要退出吗?");
                builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"成功退出《美添1记》",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });
    }
}
