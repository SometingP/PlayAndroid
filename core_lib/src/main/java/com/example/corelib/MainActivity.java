package com.example.corelib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.corelib.net.rx.RxjavaClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
