package com.wangkrmf.project2;

import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //1.定义对象
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2.绑定控件
        recyclerView = findViewById(R.id.recycler_view);

        //3.准备数据
        List<Fruit> fruitList = new ArrayList<>();
        for (int i = 0; i < 3; i++ ){
            Fruit pineapple = new Fruit(R.drawable.pineapple, "菠萝", "16.9元/KG");
            fruitList.add(pineapple);
            Fruit mango = new Fruit(R.drawable.mango, "芒果", "29.9元/KG");
            fruitList.add(mango);
            Fruit pomegranate = new Fruit(R.drawable.pomegranate, "石榴", "15元/KG");
            fruitList.add(pomegranate);
            Fruit grape = new Fruit(R.drawable.grape, "葡萄", "19.9元/KG");
            fruitList.add(grape);
            Fruit apple = new Fruit(R.drawable.apple, "苹果", "20元/KG");
            fruitList.add(apple);
            Fruit orange = new Fruit(R.drawable.orange, "橙子", "18.8元/KG");
            fruitList.add(orange);
            Fruit watermelon = new Fruit(R.drawable.watermelon, "西瓜", "28.8元/KG");
            fruitList.add(watermelon);
        }

        FruitAdapter adapter = new FruitAdapter(fruitList);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        /*//6.为列表中选中的项添加单击相应事件
        recyclerView.setOnItemClickListener(new  AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, "您选择的水果是: " + fruit.getName(), Toast.LENGTH_LONG).show();
            }
        });*/

    }
}