package com.example.porify.myeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.porify.myeventbus.eventbus.EventBus;
import com.example.porify.myeventbus.eventbus.Monkey;
import com.example.porify.myeventbus.eventbus.Subscribe;
import com.example.porify.myeventbus.eventbus.ThreadMode;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        button = findViewById(R.id.btn_to_second);
        button.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SecondActivity.class)));
        textView = findViewById(R.id.tv_receive);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void aliAndroid(final Monkey monkey) {
        Log.d(TAG, "aliAndroid: wrp, thread name: " + Thread.currentThread().getName());
        new Handler(Looper.getMainLooper()).post(() -> textView.setText(monkey.toString()));
        Log.d(TAG, "aliAndroid: wrp, monkey: " + monkey);
    }
}
