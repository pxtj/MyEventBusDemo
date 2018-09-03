package com.example.porify.myeventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.porify.myeventbus.eventbus.EventBus;
import com.example.porify.myeventbus.eventbus.Monkey;

public class SecondActivity extends Activity {
    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnSecond = findViewById(R.id.btn_click);
        btnSecond.setOnClickListener(v -> {

            new Thread(() -> {
                        Log.d(TAG, "run: wrp, thread: " + Thread.currentThread().getName());
                        EventBus.getDefault().post(new Monkey("Porify from subThread", "123456"));
            }).start();
//            Log.d(TAG, "onClick: wrp, thread: " + Thread.currentThread().getName());
//            EventBus.getDefault().post(new Monkey("Porify from main", "123456"));

            startActivity(new Intent(SecondActivity.this, MainActivity.class));
        });

    }
}
