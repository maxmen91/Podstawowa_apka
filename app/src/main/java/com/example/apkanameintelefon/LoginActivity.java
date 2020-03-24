package com.example.apkanameintelefon;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private ViewGroup layout1;
    private EditText email;
    private EditText password;
    private TextView witam;
    private TextView hint;
    private TextView blad;
    private ColorDrawable viewColor;
    private Button przycisk;
    private TextView zarejestruj;
    private LinearLayout transition_container;

    public static String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        first_layout();

        // Wygaszenie hintu
        TransitionManager.beginDelayedTransition(layout1);
        hint.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(layout1);
                hint.setVisibility(View.GONE);
            }
        },1500);
    }

    private void first_layout(){
        //Inicjalizacja widgetów do obiektów
         layout1 = (ViewGroup) findViewById(R.id.Layout);
         email = (EditText) layout1.findViewById(R.id.email);
         password = (EditText) layout1.findViewById(R.id.password);
         witam = (TextView) layout1.findViewById(R.id.zaloguj);
         viewColor = (ColorDrawable) layout1.getBackground();
         przycisk = (Button) findViewById(R.id.przycisk);
         hint = (TextView) findViewById(R.id.hint);
         zarejestruj = (TextView) findViewById(R.id.Zarejestruj);
         transition_container = (LinearLayout) findViewById(R.id.transitions_container);
         blad = (TextView) findViewById(R.id.blad);
        //Setowanie
        email.setBackgroundColor(Color.TRANSPARENT);
        password.setBackground(null);
        layout1.setClickable(true);
        witam.setClickable(true);
        transition_container.requestFocus();

        //Listener OnLongClick layoutu pierwszego
        layout1.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                System.out.println("longerclickon_layout!!");
                if(viewColor.getColor()==Color.WHITE) { // jesli jest czarno
                    zarejestruj.setTextColor(Color.WHITE);
                    zarejestruj.setText(R.string.zarejestruj_in);
                    email.setHintTextColor(Color.WHITE);
                    email.setTextColor(Color.WHITE);
                    password.setHintTextColor(Color.WHITE);
                    password.setTextColor(Color.WHITE);
                    layout1.setBackgroundColor(Color.BLACK);
                    witam.setTextColor(Color.WHITE);
                    hint.setTextColor(Color.WHITE);

                }
                else{  // jesli jest bialo
                    zarejestruj.setTextColor(Color.BLACK);
                    zarejestruj.setText(R.string.zarejestruj);
                    email.setHintTextColor(Color.BLACK);
                    email.setTextColor(Color.BLACK);
                    password.setHintTextColor(Color.BLACK);
                    password.setTextColor(Color.BLACK);
                    layout1.setBackgroundColor(Color.WHITE);
                    witam.setTextColor(Color.BLACK);
                    hint.setTextColor(Color.BLACK);
                }
                return true;
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                /*if (!hasFocus){
                    System.out.println("Focus losed");
                }
                else{
                    System.out.println("mam fokus");
                }
                 */
            }
        });
//        email.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
//            @Override
//            public void onSystemUiVisibilityChange(int visibility) {
//                System.out.println(visibility);
//            }
//        });
        //Listener relativelayouta!


    }
    public void witamonclick(View v){
        boolean visible;
        TransitionManager.beginDelayedTransition(layout1);
        if(email.getVisibility()==View.GONE){
            visible=true;
            zarejestruj.setVisibility(View.GONE);
        }
        else{
            visible=false;
            zarejestruj.setVisibility(View.VISIBLE);
        }
        email.setVisibility(visible ? View.VISIBLE : View.GONE);
        password.setVisibility(visible ? View.VISIBLE : View.GONE);
        przycisk.setVisibility(visible ? View.VISIBLE: View.GONE);
    }
    public void logowanie(View v){
        String emajl =  email.getText().toString();
        String pasik =  password.getText().toString();
        Logintoaccount(emajl,pasik);
    }
    public void hints(View v){
        if(hint.getVisibility()==View.GONE) {
            TransitionManager.beginDelayedTransition(layout1);
            hint.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    TransitionManager.beginDelayedTransition(layout1);
                    hint.setVisibility(View.GONE);
                }
            }, 1500);
        }
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void Logintoaccount(String emailorname, String passik){
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String bodystring;
        if(emailorname.matches("^(.+)@(.+)$")) bodystring = String.format("{\"email\":\"%s\",\"password\":\"%s\"}",emailorname,passik);
        else bodystring = String.format("{\"username\":\"%s\",\"password\":\"%s\"}",emailorname,passik);
        RequestBody body = RequestBody.create(bodystring,JSON);
        Request request = new Request.Builder()
                    .url("http://10.0.2.2:8080/apka/test/login")
                    .post(body)
                    .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    String token = response.body().string();
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token",token).commit();
                    Afterloginintent();
                }
                else runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        blad.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }
    public void Afterloginintent(){
        Intent loginos = new Intent(this ,AfterLoginActivity.class);
        startActivity(loginos);
        finish();
    }
    public void RegisterIntent(View v){
        Intent register = new Intent(this,RegisterActivity.class);
        startActivity(register);
        finish();
    }

}
