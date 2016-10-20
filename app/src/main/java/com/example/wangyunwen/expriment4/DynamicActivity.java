package com.example.wangyunwen.expriment4;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DynamicActivity extends AppCompatActivity {
    private final String DYNAMICACTION = "dynamic_receiver";
    private boolean unregistered = true;
    private DynamicReceiver dynamicReceiver = new DynamicReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        final Button broadcast = (Button) findViewById(R.id.broadcast);
        Button send = (Button) findViewById(R.id.send);

        broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(unregistered) {
                    IntentFilter dynamic_filter = new IntentFilter();
                    dynamic_filter.addAction(DYNAMICACTION);
                    registerReceiver(dynamicReceiver, dynamic_filter);
                    unregistered = false;
                    broadcast.setText("Unrigister Broadcast");
                } else {
                    unregisterReceiver(dynamicReceiver);
                    unregistered = true;
                    broadcast.setText("Rigister Broadcast");
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(unregistered) {
                    Toast.makeText(DynamicActivity.this, "The receiver hasn't been registered yet",Toast.LENGTH_SHORT).show();
                } else {
                    EditText editText = (EditText) findViewById(R.id.msg);
                    String msg = editText.getText().toString();
                    Intent intentd = new Intent(DYNAMICACTION);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", msg);
                    intentd.putExtras(bundle);
                    sendBroadcast(intentd);
                }
            }
        });
    }
}
