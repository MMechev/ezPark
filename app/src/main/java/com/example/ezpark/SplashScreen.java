package com.example.ezpark;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent=new Intent(SplashScreen.this, LoginRegisterActivity.class);
            startActivity(intent);
            finish();
        },3000);
    }
}
