package com.wangkrmf.mymusicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Music_frag2 extends Fragment {
    private View view;
    public String[] video_name={"华晨宇——烟火里的尘埃", "毛不易——火花", "孙思怡——蜕变", "薛之谦——彩券"};
    public static int[] video_icons={R.drawable.video0, R.drawable.video1, R.drawable.video2, R.drawable.video3};
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.video_list,null);
        ListView listView=view.findViewById(R.id.lv2);
        MyBaseAdapter adapter=new MyBaseAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Music_frag2.this.getContext(),VideoActivity.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                intent.putExtra("name",video_name[position]);
                intent.putExtra("position",String.valueOf(position));
                startActivity(intent);
            }
        });
        return view;

    }
    class MyBaseAdapter extends BaseAdapter{
        @Override
        public int getCount(){return  video_name.length;}
        @Override
        public Object getItem(int i){return video_name[i];}
        @Override
        public long getItemId(int i){return i;}
        @Override
        public View getView(int i ,View convertView, ViewGroup parent) {
            View view=View.inflate(Music_frag2.this.getContext(),R.layout.video_item,null);
            TextView vid_name=view.findViewById(R.id.video_name);
            ImageView vid_pic=view.findViewById(R.id.video_pic);
            vid_name.setText(video_name[i]);
            vid_pic.setImageResource(video_icons[i]);
            return view;
        }
    }
}
