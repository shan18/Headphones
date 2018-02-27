package com.shan.headphones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this, HeadsetPlugService.class);
        startService(intent);
        finish();
    }

    public void onResume() {
        Intent intent = new Intent(MainActivity.this, HeadsetPlugService.class);
        startService(intent);
        super.onResume();
    }
}
