package com.wangkrmf.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity2 extends AppCompatActivity {

    TextView text_getname, text_getbirth, text_getstar;
    ImageView image_star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        text_getname = findViewById(R.id.textView_getname);
        text_getbirth = findViewById(R.id.textView_getbirth);
        text_getstar = findViewById(R.id.textView_star);
        image_star = findViewById(R.id.imageView);

        String str = getIntent().getStringExtra("name");
        int y = getIntent().getIntExtra("year", 0);
        int m = getIntent().getIntExtra("month", 0);
        int d = getIntent().getIntExtra("day", 0);

        text_getname.setText("你好,"+str);
        text_getbirth.setText("你的出生日期为:"+y+"年"+(m+1)+"月"+d+"日");

        int[] image = {R.drawable.baiyang, R.drawable.jinniu, R.drawable.shuangzi,
                R.drawable.juxie, R.drawable.shizi, R.drawable.chunv,
                R.drawable.tiancheng, R.drawable.tianxie, R.drawable.sheshou,
                R.drawable.mojie, R.drawable.shuiping, R.drawable.shuangyu};

        int[] star = {R.string.白羊座, R.string.金牛座, R.string.双子座,
                R.string.巨蟹座, R.string.狮子座, R.string.处女座,
                R.string.天枰座, R.string.天蝎座, R.string.射手座,
                R.string.摩羯座, R.string.水瓶座, R.string.双鱼座};

        int i = find(m, d);
        image_star.setImageResource(image[i]);
        text_getstar.setText(star[i]);
    }

    private int find(int m, int d) {
        int i=0;
        if(m == 3 && d >= 21 || m == 4 && d <= 19)
            i = 0;
        else if(m == 4 && d >= 20 || m == 5 && d <= 20)
            i = 1;
        else if(m == 5 && d >= 21 || m == 6 && d <= 21)
            i = 2;
        else if(m == 6 && d >= 22 || m == 7 && d <= 22)
            i = 3;
        else if(m == 7 && d >= 23 || m == 8 && d <= 22)
            i = 4;
        else if(m == 8 && d >= 23 || m == 9 && d <= 22)
            i = 5;
        else if(m == 9 && d >= 23 || m == 10 && d <= 23)
            i = 6;
        else if(m == 10 && d >= 24 || m == 11 && d <= 22)
            i = 7;
        else if(m == 11 && d >= 23 || m == 12 && d <= 21)
            i = 8;
        else if(m == 12 && d >= 22 || m == 1 && d <= 19)
            i = 9;
        else if(m == 1 && d >= 20 || m == 2 && d <= 18)
            i = 10;
        else if(m == 2 && d >= 19 || m == 3 && d <= 20)
            i = 6;
        return i + 1;
    }
}