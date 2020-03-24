package com.example.apkanameintelefon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class AfterLoginActivity extends AppCompatActivity {
    private String token;
    private Button wyloguj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_after_login);
        token= getSharedPreferences("sharedPrefs",MODE_PRIVATE).getString("token",null);
        wyloguj = (Button) findViewById(R.id.wyloguj);
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
    public void wyloguj(View v){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("token").commit();
        Intent a = new Intent(this,LoginActivity.class);
        startActivity(a);
        finish();
    }

}
