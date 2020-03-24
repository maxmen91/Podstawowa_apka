package com.example.apkanameintelefon;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = getSharedPreferences("sharedPrefs",MODE_PRIVATE).getString("token",null);
        if(token!=null) Afterloginintent();
        else Loginintent();
    }
    public void Loginintent(){
        Intent loginos = new Intent(this ,LoginActivity.class);
        startActivity(loginos);
        finish();
    }
    public void Afterloginintent(){
        Intent loginos = new Intent(this ,AfterLoginActivity.class);
        startActivity(loginos);
        finish();
    }
}
