package com.example.wangyunwen.expriment4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button static_b = (Button) findViewById(R.id.static_broadcast);
        Button dynamic_b = (Button) findViewById(R.id.dynamic_broadcast);

        static_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StaticActicity.class);
                startActivity(intent);
            }
        });

        dynamic_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, DynamicActivity.class);
                startActivity(intent1);
            }
        });
    }
}
