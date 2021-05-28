package com.wangkrmf.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //1.定义对象
    Button button;
    EditText editText;
    DatePicker datePicker;



    ImageView imageView_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定控件
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        datePicker = findViewById(R.id.datePicker_birth);

        //页面跳转,按钮单击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity2.class);
                intent.putExtra("name", editText.getText().toString());
                intent.putExtra("year", datePicker.getYear());
                intent.putExtra("month", datePicker.getMonth());
                intent.putExtra("day", datePicker.getDayOfMonth());
                startActivity(intent);
            }
        });
    }
}