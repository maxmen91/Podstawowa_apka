package com.example.apkanameintelefon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ybs.passwordstrengthmeter.PasswordStrength;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText username;
    private EditText passw;
    private ProgressBar progressBar;
    private TextView passwstr;
    private TextView passwstrind;
    private TextView succesful;
    private ConstraintLayout layout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        //FindWidgets
        email = (EditText) findViewById(R.id.emailr);
        username = (EditText) findViewById(R.id.usernamer);
        passw = (EditText) findViewById(R.id.passwordr);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        passwstr = (TextView) findViewById(R.id.passwstr);
        passwstrind = (TextView) findViewById(R.id.strength_indicator);
        succesful = (TextView) findViewById(R.id.successful);
        layout1 = (ConstraintLayout) findViewById(R.id.registerlayout);
        //listener emailu
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() && !TextUtils.isEmpty(email.getText().toString())) email.setError("Email musi być poprawny");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //listener passwordu
        passw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updatePasswordStrengthView(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void updatePasswordStrengthView(String password){
        if(passwstrind.getVisibility() !=TextView.VISIBLE){
            return;
        }
        if(password.isEmpty()){
            passwstrind.setText("");
            progressBar.setProgress(0);
            return;
        }
        PasswordStrength str = PasswordStrength.calculateStrength(password);
        switch(str.getText(this).toString()){
            case "Weak":
                passwstrind.setText("Słabe");
                break;
            case "Medium":
                passwstrind.setText("Średnie");
                break;
            case "Strong":
                passwstrind.setText("Mocne");
                break;
            default:
                passwstrind.setText("Plus Ultra");
                break;
        }
        passwstrind.setTextColor(str.getColor());

        progressBar.getProgressDrawable().setColorFilter(str.getColor(), android.graphics.PorterDuff.Mode.SRC_IN);
        if (str.getText(this).equals("Słabe")) {
            progressBar.setProgress(25);
        } else if (str.getText(this).equals("Średnie")) {
            progressBar.setProgress(50);
        } else if (str.getText(this).equals("Mocne")) {
            progressBar.setProgress(75);
        } else {
            progressBar.setProgress(100);
        }
    }

    public void Register(View v){
        if(passw.getText().toString().matches("^(?=.*[A-Z])(?=.*[0-9].*[0-9]).{10,}$")) {
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(String.format("{\"email\":\"%s\",\"password\":\"%s\",\"username\":\"%s\"}", email.getText().toString(), passw.getText().toString(), username.getText().toString()), JSON);
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:8080/apka/test/register")
                    .post(body)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    System.out.println("jebać disa");
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String zwrot = response.body().string();
                    System.out.println(zwrot);
                    if (response.isSuccessful() && response.code() != 417) {
                        Loginintent();
                    }
                    if (zwrot.contains("user")) {
                        System.out.println("user");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                username.setError("Taki użytkownik już istnieje");
                            }
                        });
                    }
                    if (zwrot.contains("email")) {
                        System.out.println("email");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                email.setError("Taki email już istnieje");
                            }
                        });
                    }
                }
            });
        }
        else passw.setError("Hasło nie spełnia wymagań!");
    }
    public void Loginintent(){
        Intent loginos = new Intent(this ,LoginActivity.class);
        startActivity(loginos);
        finish();
    }
}

