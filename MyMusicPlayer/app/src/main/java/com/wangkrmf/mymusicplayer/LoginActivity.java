package com.wangkrmf.mymusicplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wangkrmf.mymusicplayer.db.MyDBHelder;

public class LoginActivity extends AppCompatActivity {
    Button btn_1,btn_2,btn_newregister;
    EditText editText_1,editText_2;
    MyDBHelder mhelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        btnloginonClick();
        btnNewRegister();
        btndestroyonClick();
    }
    private void initView() {
        btn_1 = findViewById(R.id.button_1);
        btn_2 = findViewById(R.id.button_2);
        btn_newregister=findViewById(R.id.bt_newregister_lg);
        editText_1 = findViewById(R.id.editText_1);
        editText_2 = findViewById(R.id.editText_2);
        mhelper=new MyDBHelder(LoginActivity.this);
        db=mhelper.getWritableDatabase();
    }
    private void btnloginonClick() {
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*SharedPreferences.Editor editor=getSharedPreferences("myfile",0).edit();
                editor.putString("name",editText_1.getText().toString());
                editor.putString("pwd",editText_2.getText().toString());
                editor.putBoolean("st",checkBox_reme.isChecked());
                editor.commit();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();*/

                //获取输入的用户名和密码
                String inputname=editText_1.getText().toString();
                String inputpwd=editText_2.getText().toString();
                //对用户名和密码进行判断
                if(inputname.equals("")||inputpwd.equals("")){//用户名或密码为空
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }else{//用户名或密码不为空时，对输入的正确性判断。
                    // 根据输入的用户名和密码从数据库中查询
                    Cursor cursor =db.rawQuery("select * from tb_userinfo where name=? and pwd=?",new String[]{inputname,inputpwd});
                    //根据查询到的结果进行判断
                    if (cursor.moveToNext()){//查询到时
                        String getname=cursor.getString(cursor.getColumnIndex("name"));
                        String getpwd=cursor.getString(cursor.getColumnIndex("pwd"));
                        if(inputname.equalsIgnoreCase(getname)&&inputpwd.equalsIgnoreCase(getpwd)){
                            SharedPreferences.Editor editor=getSharedPreferences("userinfo",0).edit();
                            editor.putString("username",inputname);
                            editor.putString("userpwd",inputpwd);
                            editor.commit();
                            Toast.makeText(LoginActivity.this,"欢迎进入WRMusic",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }else{//没有查询到结果时
                        Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                        editText_1.setText("");
                        editText_2.setText("");
                    }
                }

            }
        });
    }

    private void btnNewRegister() {
        btn_newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void btndestroyonClick() {
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);//创建AlertDialog构造器
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle("温馨提示");
                builder.setMessage("确定要退出吗?");
                builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LoginActivity.this,"成功退出MRMusic",Toast.LENGTH_SHORT).show();
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