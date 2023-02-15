package com.example.angel.usbmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {
    Button btnNoticias, btnChat, btnHorario, btnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnNoticias = (Button) findViewById(R.id.btnNoticias);
        btnChat     = (Button) findViewById(R.id.btnChat);
        btnHorario  = (Button) findViewById(R.id.btnHorario);
        btnMapa     = (Button) findViewById(R.id.btnMapa);


        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,MainActivity2.class));
            }
        });
    }
}