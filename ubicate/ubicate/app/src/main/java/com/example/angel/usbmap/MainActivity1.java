package com.example.angel.usbmap;

        import android.Manifest;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.WindowManager;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.GroundOverlayOptions;

        import java.sql.SQLOutput;

        import static com.example.angel.usbmap.R.id.origen;
        import static com.example.angel.usbmap.R.id.textView;

public class MainActivity1 extends Activity{

    TextView textView;
    FloatingActionButton button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main1);

        textView = (TextView) findViewById(R.id.textView);
        button = (FloatingActionButton) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity1.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    textView.setText("¡ Bienvenid@ !");
                    ActivityCompat.requestPermissions(MainActivity1.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1);
                } else {
                    button.setVisibility(View.INVISIBLE);
                    textView.setText("Cargando ...");
                    thread.start();
                }
            }
        });

        if (ActivityCompat.checkSelfPermission(MainActivity1.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            textView.setText("¡ Bienvenid@ !");
            ActivityCompat.requestPermissions(MainActivity1.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else {
            button.setVisibility(View.INVISIBLE);
            textView.setText("Cargando ...");
            thread.start();
        }
    }

    Thread thread = new Thread() {
        public void run () {
            try {
                int tiempo = 0;
                while(tiempo < 5000){
                    sleep(100);
                    tiempo = tiempo + 100;
                }
                startActivity(new Intent(MainActivity1.this,MainActivity2.class));
            } catch (Exception e) {e.printStackTrace();} finally {finish();}
        }
    };
}