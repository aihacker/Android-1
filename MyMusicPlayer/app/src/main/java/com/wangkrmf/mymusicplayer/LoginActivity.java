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

                //?????????????????????????????????
                String inputname=editText_1.getText().toString();
                String inputpwd=editText_2.getText().toString();
                //?????????????????????????????????
                if(inputname.equals("")||inputpwd.equals("")){//????????????????????????
                    Toast.makeText(LoginActivity.this,"??????????????????????????????",Toast.LENGTH_SHORT).show();
                }else{//???????????????????????????????????????????????????????????????
                    // ??????????????????????????????????????????????????????
                    Cursor cursor =db.rawQuery("select * from tb_userinfo where name=? and pwd=?",new String[]{inputname,inputpwd});
                    //????????????????????????????????????
                    if (cursor.moveToNext()){//????????????
                        String getname=cursor.getString(cursor.getColumnIndex("name"));
                        String getpwd=cursor.getString(cursor.getColumnIndex("pwd"));
                        if(inputname.equalsIgnoreCase(getname)&&inputpwd.equalsIgnoreCase(getpwd)){
                            SharedPreferences.Editor editor=getSharedPreferences("userinfo",0).edit();
                            editor.putString("username",inputname);
                            editor.putString("userpwd",inputpwd);
                            editor.commit();
                            Toast.makeText(LoginActivity.this,"????????????WRMusic",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }else{//????????????????????????
                        Toast.makeText(LoginActivity.this,"??????????????????????????????????????????",Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);//??????AlertDialog?????????
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle("????????????");
                builder.setMessage("???????????????????");
                builder.setPositiveButton("??????",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LoginActivity.this,"????????????MRMusic",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
            }
        });
    }
}