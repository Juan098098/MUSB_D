package co.edu.usbbog.musb;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread.start();
    }

    Thread thread = new Thread() {
        public void run () {
            try {
                int tiempo = 0;
                while(tiempo < 5000){
                    sleep(100);
                    tiempo = tiempo + 100;
                }
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                finish();
            }
        }
    };
}