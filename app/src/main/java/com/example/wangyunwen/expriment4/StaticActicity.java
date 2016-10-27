package com.example.wangyunwen.expriment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StaticActicity extends AppCompatActivity {
    List<fruit> list = new ArrayList<fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);

        putData();

        ListView listView = (ListView) findViewById(R.id.static_list);
        MyAdapter myAdapter = new MyAdapter(this, list);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent("static_receiver");
                Bundle bundle = new Bundle();
                bundle.putString("name", list.get(i).getName());
                bundle.putInt("src", list.get(i).getSrc());
                intent.putExtras(bundle);
                sendBroadcast(intent);

                // widget
                Intent intent1 = new Intent("static_widget");
                intent1.putExtras(bundle);
                sendBroadcast(intent1);
            }
        });
    }
     void putData() {
         list.add(new fruit("Apple", "apple"));
         list.add(new fruit("Banana", "banana"));
         list.add(new fruit("Cherry", "cherry"));
         list.add(new fruit("Coco", "coco"));
         list.add(new fruit("Kiwi", "kiwi"));
         list.add(new fruit("Orange", "orange"));
         list.add(new fruit("Pear", "pear"));
         list.add(new fruit("Strawberry", "strawberry"));
         list.add(new fruit("Watermalon", "watermelon"));
     }
}
