package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.routerbase.annotation.Router;

@Router(path="/app/index")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
