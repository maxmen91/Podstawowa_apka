package com.example.apkanameintelefon;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    ViewGroup layout1;
    EditText email;
    EditText password;
    TextView witam;
    TextView hint;
    ColorDrawable viewColor;
    Button przycisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        first_layout();
        hints(layout1);
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
        //Setowanie
        email.setBackgroundColor(Color.TRANSPARENT);
        password.setBackground(null);
        layout1.setClickable(true);
        witam.setClickable(true);


        //Listener OnLongClick layoutu pierwszego
        layout1.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                System.out.println("longerclickon_layout!!");
                if(viewColor.getColor()==Color.WHITE) {
                    email.setHintTextColor(Color.WHITE);
                    email.setTextColor(Color.WHITE);
                    password.setHintTextColor(Color.WHITE);
                    password.setTextColor(Color.WHITE);
                    layout1.setBackgroundColor(Color.BLACK);
                    witam.setTextColor(Color.WHITE);
                    hint.setTextColor(Color.WHITE);

                }
                else{
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

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() && !TextUtils.isEmpty(email.getText().toString())) email.setError("Email musi być poprawny");
                System.out.println(email.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void witamonclick(View v){
        boolean visible;
        if(email.getVisibility()==View.GONE) visible=true;
        else visible=false;
        TransitionManager.beginDelayedTransition(layout1);
        email.setVisibility(visible ? View.VISIBLE : View.GONE);
        password.setVisibility(visible ? View.VISIBLE : View.GONE);
        przycisk.setVisibility(visible ? View.VISIBLE: View.GONE);
    }
    public void logowanie(View v){
        String emajl =  email.getText().toString();
        String pasik =  password.getText().toString();
        Intent loginos = new Intent(this, AfterLoginActivity.class);
        startActivity(loginos);
        finish();
    }
    public void hints(View v){
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
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
