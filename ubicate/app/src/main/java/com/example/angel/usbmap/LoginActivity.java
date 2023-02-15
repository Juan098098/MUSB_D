package com.example.angel.usbmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends Activity {
    ImageView ivUser, ivPass;
    Button btnLogin;
    TextView tvForgot, tvSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ivUser   = (ImageView) findViewById(R.id.ivUser);
        ivPass   = (ImageView) findViewById(R.id.ivPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvForgot = (TextView) findViewById(R.id.tvForgot);
        tvSign   = (TextView) findViewById(R.id.tvSign);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MenuActivity.class));
            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPassActivity.class));
            }
        });

        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }
}