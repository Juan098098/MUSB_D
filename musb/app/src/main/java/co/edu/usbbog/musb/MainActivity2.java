package co.edu.usbbog.musb;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity2 extends AppCompatActivity implements OnMapReadyCallback {

    int ands = (int) 27.5, alds = (int) 113.5,
            andb = (int) 17.75, aldb = (int) 63.5,
            anps = (int) 17.75, alps = (int) 63.5,
            ango = 20, algo = (int) 68.75;

    double inf = 4.750349, sup = 4.752828,
            izq = -74.030596, der = -74.029087;

    LatLng dbsi = new LatLng(4.75091536, -74.03014160);
    LatLng dbid = new LatLng(4.75042086, -74.03000783);
    LatLng pssi = new LatLng(4.75112953, -74.02993138);
    LatLng psid = new LatLng(4.75063403, -74.02979794);
    LatLng dssi = new LatLng(4.75164074, -74.03047319);
    LatLng dsid = new LatLng(4.75062935, -74.03031561);
    LatLng gosi = new LatLng(4.75135707, -74.02971748);
    LatLng goid = new LatLng(4.75076166, -74.02954280);

    int aux;
    int check;
    boolean tds,
    tdb, tps, tgo;
    float bool = 1;
    Marker marker;
    GoogleMap mMap;
    EditText origen;
    ToggleButton t1;
    ToggleButton t2;
    ToggleButton t3;
    ToggleButton t4;
    ToggleButton t5;
    Boolean b = false;
    EditText destino;
    GroundOverlay usb;
    Location location;
    Polyline polyline;
    GroundOverlay mar;
    AlertDialog alert = null;
    GroundOverlay imageOverlay;
    LocationManager locationManager;
    LocationListener locationListener;
    LatLng[] huellas = new LatLng[20];//-y+,+x-
    GroundOverlay mark[] = new GroundOverlay[20];
    LatLng root = new LatLng(4.7514800, -74.029850);
    LatLng ds = new LatLng(4.751139000, -74.0303985);
    LatLng db = new LatLng(4.750670000, -74.0300625);
    LatLng ps = new LatLng(4.750885000, -74.0298525);
    LatLng gu = new LatLng(4.751067500, -74.0296275);

    LatLng dbp = new LatLng(4.7508300, -74.0301600);
    LatLng psp = new LatLng(4.7510485, -74.0299250);
    LatLng gup = new LatLng(4.7510485, -74.0297050);
    LatLng hnp = new LatLng(4.7525044, -74.0304075);
    LatLng plp = new LatLng(4.7520053, -74.0302580);
    LatLng cpp = new LatLng(4.7504091, -74.0303685);
    LatLng prp = new LatLng(4.7505207, -74.0305700);

    LatLng ds1 = new LatLng(4.7509265, -74.0303132);
    LatLng ds2 = new LatLng(4.7510485, -74.0303132);
    LatLng ds3 = new LatLng(4.7511620, -74.0303132);
    LatLng ds4 = new LatLng(4.7512965, -74.0303132);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main2);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS está desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert = builder.create();

        origen = (EditText) findViewById(R.id.origen);
        destino = (EditText) findViewById(R.id.destino);

        t5 = (ToggleButton) findViewById(R.id.toggleButton4);
        t4 = (ToggleButton) findViewById(R.id.toggleButton5);
        t3 = (ToggleButton) findViewById(R.id.toggleButton6);
        t2 = (ToggleButton) findViewById(R.id.toggleButton7);
        t1 = (ToggleButton) findViewById(R.id.toggleButton8);

        t1.setVisibility(View.INVISIBLE);t2.setVisibility(View.INVISIBLE);t3.setVisibility(View.INVISIBLE);t4.setVisibility(View.INVISIBLE);t5.setVisibility(View.INVISIBLE);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (polyline != null) polyline.remove();
                if (imageOverlay != null) imageOverlay.remove();
                if (mar!=null)mar.remove();
                if (tdb==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.db1)).position(db, andb, aldb));
                else if(tps==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ps1)).position(ps, anps, alps));
                else if(tds==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ds1)).position(ds, ands, alds));
                else if(tgo==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.go1)).position(gu, ango, algo));
                if (imageOverlay != null)imageOverlay.setZIndex(1);
                t1.setChecked(true);t2.setChecked(false);t3.setChecked(false);t4.setChecked(false);t5.setChecked(false);check=1;
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (polyline != null) polyline.remove();
                if (imageOverlay != null) imageOverlay.remove();
                if (mar!=null)mar.remove();
                if (tdb==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.db2)).position(db, andb, aldb));
                else if(tps==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ps2)).position(ps, anps, alps));
                else if(tds==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ds2)).position(ds, ands, alds));
                else if(tgo==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.go2)).position(gu, ango, algo));
                if (imageOverlay != null)imageOverlay.setZIndex(1);
                t1.setChecked(false);t2.setChecked(true);t3.setChecked(false);t4.setChecked(false);t5.setChecked(false);check=2;
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (polyline != null) polyline.remove();
                if (imageOverlay != null) imageOverlay.remove();
                if (mar!=null)mar.remove();
                if (tdb==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.db3)).position(db, andb, aldb));
                else if(tps==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ps3)).position(ps, anps, alps));
                else if(tds==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ds3)).position(ds, ands, alds));
                else if(tgo==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.go3)).position(gu, ango, algo));
                if (imageOverlay != null)imageOverlay.setZIndex(1);
                t1.setChecked(false);t2.setChecked(false);t3.setChecked(true);t4.setChecked(false);t5.setChecked(false);check=3;
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (polyline != null) polyline.remove();
                if (imageOverlay != null) imageOverlay.remove();
                if (mar!=null)mar.remove();
                if (tdb==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.db4)).position(db, andb, aldb));
                else if(tps==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ps4)).position(ps, anps, alps));
                else if(tds==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ds4)).position(ds, ands, alds));
                else if(tgo==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.go4)).position(gu, ango, algo));
                if (imageOverlay != null)imageOverlay.setZIndex(1);
                t1.setChecked(false);t2.setChecked(false);t3.setChecked(false);t4.setChecked(true);t5.setChecked(false);check=4;
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (polyline != null) polyline.remove();
                if (imageOverlay != null) imageOverlay.remove();
                if (mar!=null)mar.remove();
                if(tds==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ds5)).position(ds, ands, alds));
                else if(tgo==true)imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.go5)).position(gu, ango, algo));
                if (imageOverlay != null)imageOverlay.setZIndex(1);
                t1.setChecked(false);t2.setChecked(false);t3.setChecked(false);t4.setChecked(false);t5.setChecked(true);check=5;
            }
        });

        Button ch = (Button) findViewById(R.id.button0);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(origen.getText());
                origen.setText(destino.getText());
                destino.setText(s);
            }
        });

        origen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "¿De donde quieres comenzar?", Toast.LENGTH_LONG).show();
            }
        });

        destino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "¿A donde quieres ir?", Toast.LENGTH_LONG).show();
            }
        });

        Button go = (Button) findViewById(R.id.button1);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });

        String[] places = new String[]{"PR PORTERIA", "PL POLIDEPORTIVO", "HN HANGAR", "CP CAPILLA", "RS RESTAURANTE",

                "DS 1 D DUNS SCOTO FRAY DARIO CORREA G.", "DS 1 A DUNS SCOTO FRAY JUAN DE ANAYA P.", "DS 103 DUNS SCOTO",
                "DS 200 DUNS SCOTO CENTRO DE COPIADO", "DS 201 DUNS SCOTO PAPELERÍA", "DS 202 DUNS SCOTO", "DS 203 DUNS SCOTO", "DS 204 DUNS SCOTO", "DS 205 DUNS SCOTO AULA DE DIBUJO", "DS 206 DUNS SCOTO BIENESTAR INSTITUCIONAL", "DS 207 DUNS SCOTO AUDIOVISUALES",
                "DS 3 B DUNS SCOTO BIBLIOTECA FRAY ALBERTO MONTEALEGRE GONZALES", "DS 301 DUNS SCOTO", "DS 302 DUNS SCOTO", "DS 303 DUNS SCOTO", "DS 304 DUNS SCOTO", "DS 305 DUNS SCOTO", "DS 306 DUNS SCOTO", "DS 307 DUNS SCOTO", "DS 308 DUNS SCOTO", "DS 309 DUNS SCOTO", "DS 310 DUNS SCOTO", "DS 311 DUNS SCOTO BIENESTAR INSTITUCIONAL PSICOLOGÍA", "DS 312 DUNS SCOTO REVISTA FRANCISCANUM", "DS 313 DUNS SCOTO", "DS 314 DUNS SCOTO", "DS 315 DUNS SCOTO", "DS 316 DUNS SCOTO", "DS 317 DUNS SCOTO", "DS 318 DUNS SCOTO",
                "DS 4 W DUNS SCOTO FRAY FELIX A. WILCHES", "DS 400 DUNS SCOTO RELACIONES INTER INSTITUCIONALES", "DS 401 DUNS SCOTO", "DS 402 DUNS SCOTO", "DS 403 DUNS SCOTO", "DS 404 DUNS SCOTO", "DS 405 DUNS SCOTO", "DS 406 DUNS SCOTO", "DS 407 DUNS SCOTO", "DS 408 DUNS SCOTO", "DS 409 DUNS SCOTO", "DS 410 DUNS SCOTO", "DS 411 DUNS SCOTO PUBLICACIONES", "DS 412 DUNS SCOTO COMUNICACIONES Y PROTOCOLO", "DS 413 DUNS SCOTO PUBLICACIONES", "DS 414 DUNS SCOTO", "DS 415 DUNS SCOTO", "DS 416 DUNS SCOTO", "DS 417 DUNS SCOTO", "DS 418 DUNS SCOTO", "DS 419 DUNS SCOTO", "DS 4 C DUNS SCOTO FRAY ARTURO CARLOS DELGADO Z.",
                "DS 500 DUNS SCOTO PLANEACIÓN", "DS 501 DUNS SCOTO SALA DE JUNTAS", "DS 502 DUNS SCOTO PASTORAL UNIVERSITARIA", "DS 503 DUNS SCOTO INVESTIGACIONES", "DS 504 DUNS SCOTO DIRECCIÓN DE PLANEACIÓN", "DS 505 DUNS SCOTO DIRECCIÓN INVESTIGACIONES", "DS 506 DUNS SCOTO RELACIONES INTERINSTITUCIONALES", "DS 507 DUNS SCOTO BIENESTAR INSTITUCIONAL", "DS 508 DUNS SCOTO DIRECCIÓN ITER", "DS 509 DUNS SCOTO ITER", "DS 510 DUNS SCOTO", "DS 511 DUNS SCOTO PLANEACIÓN", "DS 512 DUNS SCOTO EDITORIAL BONAVENTURIANA", "DS 513 DUNS SCOTO INNOVACIÓN Y TRANSFERENCIA DEL CONOCIMIENTO", "DS 514 DUNS SCOTO", "DS 515 DUNS SCOTO", "DS 516 DUNS SCOTO", "DS 517 DUNS SCOTO", "DS 518 DUNS SCOTO", "DS 519 DUNS SCOTO", "DS 5 J DUNS SCOTO FRAY JOSE MARIA PEREZ",

                "DB 101 DIEGO BARROSO FACULTAD DE INGENIERÍAS", "DB 102 DIEGO BARROSO REGISTRO Y CONTROL", "DB 103 DIEGO BARROSO CREDITO Y CARTERA", "DB 104 DIEGO BARROSO TESORERÍA", "DB 105 DIEGO BARROSO CONTABILIDAD", "DB 106 DIEGO BARROSO GESTIÓN DOCUMENTAL", "DB 107 DIEGO BARROSO PROMOCIÓN Y ADMISIONES",
                "DB 201 DIEGO BARROSO DECANATURA DE INGENIERÍA", "DB 202 DIEGO BARROSO ORIENTADORES ACADÉMICOS", "DB 203 DIEGO BARROSO SALA DE PROFESORES", "DB 204 DIEGO BARROSO MECATRÓNICA", "DB 205 DIEGO BARROSO CENTRO DE IDIOMAS", "DB 206 DIEGO BARROSO TALENTO HUMANO", "DB 207 DIEGO BARROSO PRESUPUESTO",
                "DB 301 DIEGO BARROSO FACULTAD DE CIENCIAS HUMANAS Y SOCIALES", "DB 302 DIEGO BARROSO SALA DE PROFESORES", "DB 303 DIEGO BARROSO", "DB 304 DIEGO BARROSO", "DB 305 DIEGO BARROSO", "DB 306 DIEGO BARROSO", "DB 307 DIEGO BARROSO", "DB 308 DIEGO BARROSO CIENCIAS BASICAS",
                "DB 401 DIEGO BARROSO FACULTAD DE TEOLOGÍA", "DB 402 DIEGO BARROSO SALA DE PROFESORES", "DB 403 DIEGO BARROSO", "DB 404 DIEGO BARROSO", "DB 405 DIEGO BARROSO", "DB 406 DIEGO BARROSO", "DB 407 DIEGO BARROSO",

                "PS 102 PEDRO SIMÓN CIDEH", "PS 103 PEDRO SIMÓN PUNTO DE ATENCIÓN DE PRIMEROS AUXILIOS", "PS 104 PEDRO SIMÓN", "PS 105 PEDRO SIMÓN", "PS 106 PEDRO SIMÓN", "PS 107 PEDRO SIMÓN", "PS 108 PEDRO SIMÓN", "PS 109 PEDRO SIMÓN LABORATORIO DE NEUROCIENCIAS Y PSICOLOGÍA", "PS 110 PEDRO SIMÓN GRADUADOS",
                "PS 202 PEDRO SIMÓN FACULTAD DE FILOSOFÍA", "PS 203 PEDRO SIMÓN SALA DE PROFESORES FILOSOFÍA", "PS 204 PEDRO SIMÓN", "PS 205 PEDRO SIMÓN", "PS 206 PEDRO SIMÓN", "PS 207 PEDRO SIMÓN", "PS 208 PEDRO SIMÓN", "PS 209 PEDRO SIMÓN RECURSOS FISICOS",
                "PS 301 PEDRO SIMÓN FACULTAD DE EDUCACIÓN", "PS 302 PEDRO SIMÓN SALA DE PROFESORES EDUCACIÓN", "PS 303 PEDRO SIMÓN ", "PS 304 PEDRO SIMÓN", "PS 305 PEDRO SIMÓN", "PS 306 PEDRO SIMÓN", "PS 307 PEDRO SIMÓN", "PS 308 PEDRO SIMÓN CENTRO DE EDUCACIÓN VIRTUAL",
                "PS 401 PEDRO SIMÓN FACULTAD DE CIENCIAS JURÍDICAS, POLÍTICAS Y ECONÓMICAS", "PS 402 PEDRO SIMÓN DIRECTORES FACULTAD DE CIENCIAS JURÍDICAS, POLÍTICAS Y ECONÓMICAS", "PS 403 PEDRO SIMÓN COORDINADOR FACULTAD DE CIENCIAS JURÍDICAS, POLÍTICAS Y ECONÓMICAS", "PS 404 PEDRO SIMÓN CONSEJERÍA Y TUTORÍAS FACULTAD DE CIENCIAS JURÍDICAS, POLÍTICAS Y ECONÓMICAS", "PS 405 PEDRO SIMÓN SALA DE PROFESORES FACULTAD DE CIENCIAS JURÍDICAS, POLÍTICAS Y ECONÓMICAS", "PS 406 PEDRO SIMÓN SALA DE PROFESORES", "PS 407 PEDRO SIMÓN SALA DE JUNTAS",

                "GO 101 GUILLERMO DE OCKHAM SALA DE PROFESORES INGENIERÍA AERONÁUTICA- INGENIERÍA DE SONIDO", "GO 102 GUILLERMO DE OCKHAM SALA PRACTICA LIBRE 1", "GO 103 GUILLERMO DE OCKHAM TUTORÍAS", "GO 104 GUILLERMO DE OCKHAM ESTUDIO HIBRIDO", "GO 105 GUILLERMO DE OCKHAM ESTUDIO DIGITAL", "GO 106 GUILLERMO DE OCKHAM ESTUDIO 5.1",
                "GO 201 GUILLERMO DE OCKHAM SALA PRACTICA LIBRE 2", "GO 202 GUILLERMO DE OCKHAM INFORMÁTICA 1", "GO 203 GUILLERMO DE OCKHAM INFORMÁTICA 2", "GO 204 GUILLERMO DE OCKHAM INFORMÁTICA 3", "GO 205 GUILLERMO DE OCKHAM IDIOMAS", "GO 206 GUILLERMO DE OCKHAM LABORATORIO DE PSICOLOGÍA", "GO 207 GUILLERMO DE OCKHAM PUNTO DE ATENCIÓN LAB. DE SONIDO", "GO 208 GUILLERMO DE OCKHAM ESTUDIO DE MASTERIZACIÓN", "GO 209 GUILLERMO DE OCKHAM PREPRODUCCIÓN", "GO 210 GUILLERMO DE OCKHAM SONIDO EN VIVO", "GO 211 GUILLERMO DE OCKHAM LABORATORIO DE ACUSTICA", "GO 212 GUILLERMO DE OCKHAM POSTPRODUCCIÓN",
                "GO 301 GUILLERMO DE OCKHAM MATEMÁTICAS", "GO 302 GUILLERMO DE OCKHAM DISEÑO ASISTIDO", "GO 303 GUILLERMO DE OCKHAM PROGRAMACIÓN", "GO 304 GUILLERMO DE OCKHAM ANALISIS DE DATOS", "GO 305 GUILLERMO DE OCKHAM ALGORITMOS Y MATEMÁTICAS ESPECÍFICAS", "GO 306 GUILLERMO DE OCKHAM DISEÑO WEB", "GO 307 GUILLERMO DE OCKHAM PUNTO DE ATENCIÓN LAB. DE INFORMÁTICA", "GO 308 GUILLERMO DE OCKHAM SIMULACIÓN COMPUTACIONAL", "GO 309 GUILLERMO DE OCKHAM VIRREY SOLIS", "GO 310 GUILLERMO DE OCKHAM REDES",
                "GO 401 GUILLERMO DE OCKHAM ANALISIS EN ELECTRÓNICA", "GO 402 GUILLERMO DE OCKHAM ELECTRÓNICA 1", "GO 403 GUILLERMO DE OCKHAM ELECTRÓNICA 3", "GO 404 GUILLERMO DE OCKHAM FISICA 1", "GO 405 GUILLERMO DE OCKHAM FISICA 2", "GO 406 GUILLERMO DE OCKHAM COMUNICACIONES", "GO 407 GUILLERMO DE OCKHAM ELECTRÓNICA 2", "GO 408 GUILLERMO DE OCKHAM AUTOMATIZACIÓN", "GO 409 GUILLERMO DE OCKHAM SAN ANTONIO DE PADUA", "GO 410 GUILLERMO DE OCKHAM", "GO 411 GUILLERMO DE OCKHAM PUNTO DE ATENCIÓN LAB. DE ELECTRÓNICA",
                "GO 501 GUILLERMO DE OCKHAM SECRETARIA PADRE", "GO 502 GUILLERMO DE OCKHAM OFICINA PADRE RECTOR", "GO 503 GUILLERMO DE OCKHAM TERRAZA", "GO 504 GUILLERMO DE OCKHAM SALA DE JUNTAS", "GO 505 GUILLERMO DE OCKHAM SECRETARIA VICERRECTORÍA", "GO 506 GUILLERMO DE OCKHAM SALA DE JUNTAS RECTORÍA", "GO 507 GUILLERMO DE OCKHAM AUTOEVALUACIÓN", "GO 508 GUILLERMO DE OCKHAM VICERRECTORÍA ACADÉMICA", "GO 509 GUILLERMO DE OCKHAM VICERRECTORÍA FINANCIERA", "GO 510 GUILLERMO DE OCKHAM PROCESO JURIDICO", "GO 511 GUILLERMO DE OCKHAM DIRECCIÓN PLANEACIÓN", "GO 512 GUILLERMO DE OCKHAM SECRETARIA RECTORÍA"};

        AutoCompleteTextView origenauto = (AutoCompleteTextView) findViewById(R.id.origen);
        final AutoCompleteTextView destinoauto = (AutoCompleteTextView) findViewById(R.id.destino);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, places);
        origenauto.setAdapter(adapter);
        destinoauto.setAdapter(adapter);

        origenauto.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                destinoauto.requestFocus();
                return false;
            }
        });

        destinoauto.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                enviar();
                return false;
            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                marker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
                if (location.getLatitude() > inf & location.getLatitude() < sup & location.getLongitude() > izq & location.getLongitude() < der) {
                    marker.setTitle("Usted esta aqui");
                    marker.setSnippet("Estas en la USB-BOG");
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                } else {
                    marker.setTitle("Fuera de rango");
                    marker.setSnippet("No te encuentras en la USB-BOG");
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                }

                if (mark[19] == null & huellas[19] != null) {
                    if (huellas[19].latitude > inf & huellas[19].latitude < sup & huellas[19].longitude > izq & huellas[19].longitude < der)
                        mark[19] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in19)).position(huellas[19], bool));
                    else
                        mark[19] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out19)).position(huellas[19], bool));
                } else if (huellas[19] != null) {
                    if (huellas[19].latitude > inf & huellas[19].latitude < sup & huellas[19].longitude > izq & huellas[19].longitude < der) {
                        mark[19].setPosition(huellas[19]);
                        mark[19].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in19));
                    } else {
                        mark[19].setPosition(huellas[19]);
                        mark[19].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out19));
                    }
                }

                if (mark[18] == null & huellas[18] != null) {
                    if (huellas[18].latitude > inf & huellas[18].latitude < sup & huellas[18].longitude > izq & huellas[18].longitude < der)
                        mark[18] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in18)).position(huellas[18], bool));
                    else
                        mark[18] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out18)).position(huellas[18], bool));
                } else if (huellas[18] != null) {
                    if (huellas[18].latitude > inf & huellas[18].latitude < sup & huellas[18].longitude > izq & huellas[18].longitude < der) {
                        mark[18].setPosition(huellas[18]);
                        mark[18].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in18));
                    } else {
                        mark[18].setPosition(huellas[18]);
                        mark[18].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out18));
                    }
                }

                if (mark[17] == null & huellas[17] != null) {
                    if (huellas[17].latitude > inf & huellas[17].latitude < sup & huellas[17].longitude > izq & huellas[17].longitude < der)
                        mark[17] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in17)).position(huellas[17], bool));
                    else
                        mark[17] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out17)).position(huellas[17], bool));
                } else if (huellas[17] != null) {
                    if (huellas[17].latitude > inf & huellas[17].latitude < sup & huellas[17].longitude > izq & huellas[17].longitude < der) {
                        mark[17].setPosition(huellas[17]);
                        mark[17].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in17));
                    } else {
                        mark[17].setPosition(huellas[17]);
                        mark[17].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out17));
                    }
                }

                if (mark[16] == null & huellas[16] != null) {
                    if (huellas[16].latitude > inf & huellas[16].latitude < sup & huellas[16].longitude > izq & huellas[16].longitude < der)
                        mark[16] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in16)).position(huellas[16], bool));
                    else
                        mark[16] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out16)).position(huellas[16], bool));
                } else if (huellas[16] != null) {
                    if (huellas[16].latitude > inf & huellas[16].latitude < sup & huellas[16].longitude > izq & huellas[16].longitude < der) {
                        mark[16].setPosition(huellas[16]);
                        mark[16].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in16));
                    } else {
                        mark[16].setPosition(huellas[16]);
                        mark[16].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out16));
                    }
                }

                if (mark[15] == null & huellas[15] != null) {
                    if (huellas[15].latitude > inf & huellas[15].latitude < sup & huellas[15].longitude > izq & huellas[15].longitude < der)
                        mark[15] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in15)).position(huellas[15], bool));
                    else
                        mark[15] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out15)).position(huellas[15], bool));
                } else if (huellas[15] != null) {
                    if (huellas[15].latitude > inf & huellas[15].latitude < sup & huellas[15].longitude > izq & huellas[15].longitude < der) {
                        mark[15].setPosition(huellas[15]);
                        mark[15].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in15));
                    } else {
                        mark[15].setPosition(huellas[15]);
                        mark[15].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out15));
                    }
                }

                if (mark[14] == null & huellas[14] != null) {
                    if (huellas[14].latitude > inf & huellas[14].latitude < sup & huellas[14].longitude > izq & huellas[14].longitude < der)
                        mark[14] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in14)).position(huellas[14], bool));
                    else
                        mark[14] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out14)).position(huellas[14], bool));
                } else if (huellas[14] != null) {
                    if (huellas[14].latitude > inf & huellas[14].latitude < sup & huellas[14].longitude > izq & huellas[14].longitude < der) {
                        mark[14].setPosition(huellas[14]);
                        mark[14].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in14));
                    } else {
                        mark[14].setPosition(huellas[14]);
                        mark[14].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out14));
                    }
                }

                if (mark[13] == null & huellas[13] != null) {
                    if (huellas[13].latitude > inf & huellas[13].latitude < sup & huellas[13].longitude > izq & huellas[13].longitude < der)
                        mark[13] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in13)).position(huellas[13], bool));
                    else
                        mark[13] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out13)).position(huellas[13], bool));
                } else if (huellas[13] != null) {
                    if (huellas[13].latitude > inf & huellas[13].latitude < sup & huellas[13].longitude > izq & huellas[13].longitude < der) {
                        mark[13].setPosition(huellas[13]);
                        mark[13].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in13));
                    } else {
                        mark[13].setPosition(huellas[13]);
                        mark[13].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out13));
                    }
                }

                if (mark[12] == null & huellas[12] != null) {
                    if (huellas[12].latitude > inf & huellas[12].latitude < sup & huellas[12].longitude > izq & huellas[12].longitude < der)
                        mark[12] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in12)).position(huellas[12], bool));
                    else
                        mark[12] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out12)).position(huellas[12], bool));
                } else if (huellas[12] != null) {
                    if (huellas[12].latitude > inf & huellas[12].latitude < sup & huellas[12].longitude > izq & huellas[12].longitude < der) {
                        mark[12].setPosition(huellas[12]);
                        mark[12].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in12));
                    } else {
                        mark[12].setPosition(huellas[12]);
                        mark[12].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out12));
                    }
                }

                if (mark[11] == null & huellas[11] != null) {
                    if (huellas[11].latitude > inf & huellas[11].latitude < sup & huellas[11].longitude > izq & huellas[11].longitude < der)
                        mark[11] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in11)).position(huellas[11], bool));
                    else
                        mark[11] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out11)).position(huellas[11], bool));
                } else if (huellas[11] != null) {
                    if (huellas[11].latitude > inf & huellas[11].latitude < sup & huellas[11].longitude > izq & huellas[11].longitude < der) {
                        mark[11].setPosition(huellas[11]);
                        mark[11].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in11));
                    } else {
                        mark[11].setPosition(huellas[11]);
                        mark[11].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out11));
                    }
                }

                if (mark[10] == null & huellas[10] != null) {
                    if (huellas[10].latitude > inf & huellas[10].latitude < sup & huellas[10].longitude > izq & huellas[10].longitude < der)
                        mark[10] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in10)).position(huellas[10], bool));
                    else
                        mark[10] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out10)).position(huellas[10], bool));
                } else if (huellas[10] != null) {
                    if (huellas[10].latitude > inf & huellas[10].latitude < sup & huellas[10].longitude > izq & huellas[10].longitude < der) {
                        mark[10].setPosition(huellas[10]);
                        mark[10].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in10));
                    } else {
                        mark[10].setPosition(huellas[10]);
                        mark[10].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out10));
                    }
                }

                if (mark[9] == null & huellas[9] != null) {
                    if (huellas[9].latitude > inf & huellas[9].latitude < sup & huellas[9].longitude > izq & huellas[9].longitude < der)
                        mark[9] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in9)).position(huellas[9], bool));
                    else
                        mark[9] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out9)).position(huellas[9], bool));
                } else if (huellas[9] != null) {
                    if (huellas[9].latitude > inf & huellas[9].latitude < sup & huellas[9].longitude > izq & huellas[9].longitude < der) {
                        mark[9].setPosition(huellas[9]);
                        mark[9].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in9));
                    } else {
                        mark[9].setPosition(huellas[9]);
                        mark[9].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out9));
                    }
                }

                if (mark[8] == null & huellas[8] != null) {
                    if (huellas[8].latitude > inf & huellas[8].latitude < sup & huellas[8].longitude > izq & huellas[8].longitude < der)
                        mark[8] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in8)).position(huellas[8], bool));
                    else
                        mark[8] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out8)).position(huellas[8], bool));
                } else if (huellas[8] != null) {
                    if (huellas[8].latitude > inf & huellas[8].latitude < sup & huellas[8].longitude > izq & huellas[8].longitude < der) {
                        mark[8].setPosition(huellas[8]);
                        mark[8].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in8));
                    } else {
                        mark[8].setPosition(huellas[8]);
                        mark[8].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out8));
                    }
                }

                if (mark[7] == null & huellas[7] != null) {
                    if (huellas[7].latitude > inf & huellas[7].latitude < sup & huellas[7].longitude > izq & huellas[7].longitude < der)
                        mark[7] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in7)).position(huellas[7], bool));
                    else
                        mark[7] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out7)).position(huellas[7], bool));
                } else if (huellas[7] != null) {
                    if (huellas[7].latitude > inf & huellas[7].latitude < sup & huellas[7].longitude > izq & huellas[7].longitude < der) {
                        mark[7].setPosition(huellas[7]);
                        mark[7].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in7));
                    } else {
                        mark[7].setPosition(huellas[7]);
                        mark[7].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out7));
                    }
                }

                if (mark[6] == null & huellas[6] != null) {
                    if (huellas[6].latitude > inf & huellas[6].latitude < sup & huellas[6].longitude > izq & huellas[6].longitude < der)
                        mark[6] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in6)).position(huellas[6], bool));
                    else
                        mark[6] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out6)).position(huellas[6], bool));
                } else if (huellas[6] != null) {
                    if (huellas[6].latitude > inf & huellas[6].latitude < sup & huellas[6].longitude > izq & huellas[6].longitude < der) {
                        mark[6].setPosition(huellas[6]);
                        mark[6].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in6));
                    } else {
                        mark[6].setPosition(huellas[6]);
                        mark[6].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out6));
                    }
                }

                if (mark[5] == null & huellas[5] != null) {
                    if (huellas[5].latitude > inf & huellas[5].latitude < sup & huellas[5].longitude > izq & huellas[5].longitude < der)
                        mark[5] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in5)).position(huellas[5], bool));
                    else
                        mark[5] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out5)).position(huellas[5], bool));
                } else if (huellas[5] != null) {
                    if (huellas[5].latitude > inf & huellas[5].latitude < sup & huellas[5].longitude > izq & huellas[5].longitude < der) {
                        mark[5].setPosition(huellas[5]);
                        mark[5].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in5));
                    } else {
                        mark[5].setPosition(huellas[5]);
                        mark[5].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out5));
                    }
                }

                if (mark[4] == null & huellas[4] != null) {
                    if (huellas[4].latitude > inf & huellas[4].latitude < sup & huellas[4].longitude > izq & huellas[4].longitude < der)
                        mark[4] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in4)).position(huellas[4], bool));
                    else
                        mark[4] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out4)).position(huellas[4], bool));
                } else if (huellas[4] != null) {
                    if (huellas[4].latitude > inf & huellas[4].latitude < sup & huellas[4].longitude > izq & huellas[4].longitude < der) {
                        mark[4].setPosition(huellas[4]);
                        mark[4].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in4));
                    } else {
                        mark[4].setPosition(huellas[4]);
                        mark[4].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out4));
                    }
                }

                if (mark[3] == null & huellas[3] != null) {
                    if (huellas[3].latitude > inf & huellas[3].latitude < sup & huellas[3].longitude > izq & huellas[3].longitude < der)
                        mark[3] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in3)).position(huellas[3], bool));
                    else
                        mark[3] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out3)).position(huellas[3], bool));
                } else if (huellas[3] != null) {
                    if (huellas[3].latitude > inf & huellas[3].latitude < sup & huellas[3].longitude > izq & huellas[3].longitude < der) {
                        mark[3].setPosition(huellas[3]);
                        mark[3].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in3));
                    } else {
                        mark[3].setPosition(huellas[3]);
                        mark[3].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out3));
                    }
                }

                if (mark[2] == null & huellas[2] != null) {
                    if (huellas[2].latitude > inf & huellas[2].latitude < sup & huellas[2].longitude > izq & huellas[2].longitude < der)
                        mark[2] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in2)).position(huellas[2], bool));
                    else
                        mark[2] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out2)).position(huellas[2], bool));
                } else if (huellas[2] != null) {
                    if (huellas[2].latitude > inf & huellas[2].latitude < sup & huellas[2].longitude > izq & huellas[2].longitude < der) {
                        mark[2].setPosition(huellas[2]);
                        mark[2].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in2));
                    } else {
                        mark[2].setPosition(huellas[2]);
                        mark[2].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out2));
                    }
                }

                if (mark[1] == null & huellas[1] != null) {
                    if (huellas[1].latitude > inf & huellas[1].latitude < sup & huellas[1].longitude > izq & huellas[1].longitude < der)
                        mark[1] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in1)).position(huellas[1], bool));
                    else
                        mark[1] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out1)).position(huellas[1], bool));
                } else if (huellas[1] != null) {
                    if (huellas[1].latitude > inf & huellas[1].latitude < sup & huellas[1].longitude > izq & huellas[1].longitude < der) {
                        mark[1].setPosition(huellas[1]);
                        mark[1].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in1));
                    } else {
                        mark[1].setPosition(huellas[1]);
                        mark[1].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out1));
                    }
                }

                if (mark[0] == null & huellas[0] != null) {
                    if (huellas[0].latitude > inf & huellas[0].latitude < sup & huellas[0].longitude > izq & huellas[0].longitude < der)
                        mark[0] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.in0)).position(huellas[0], bool));
                    else
                        mark[0] = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.out0)).position(huellas[0], bool));
                } else if (huellas[0] != null) {
                    if (huellas[0].latitude > inf & huellas[0].latitude < sup & huellas[0].longitude > izq & huellas[0].longitude < der) {
                        mark[0].setPosition(huellas[0]);
                        mark[0].setImage(BitmapDescriptorFactory.fromResource(R.drawable.in0));
                    } else {
                        mark[0].setPosition(huellas[0]);
                        mark[0].setImage(BitmapDescriptorFactory.fromResource(R.drawable.out0));
                    }
                }

                marker.showInfoWindow();
                for (int i = 19; i > 0; i--) huellas[i] = huellas[i - 1];
                huellas[0] = new LatLng(location.getLatitude(), location.getLongitude());

                if (aux == 1)
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                if (!locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
                    alert.show();
                } else aux = 1;
                return false;
            }
        });

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(root, (float) 17.75));

        marker = mMap.addMarker(new MarkerOptions()
                .position(root)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .title("Ubicat dice:")
                .snippet("Cordial saludo de paz y bien"));

        marker.showInfoWindow();

        usb = mMap.addGroundOverlay(new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.satelite))
                .position(root, 170, 300));

        mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                if (i == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) aux = 0;
                if (mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().latitude<dssi.latitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().latitude>dsid.latitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().longitude>dssi.longitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().longitude<dsid.longitude&
                    mMap.getCameraPosition().zoom>=19.5)
                {tds=true;
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    if(check==1)t1.setChecked(true);else t1.setChecked(false);
                    if(check==2)t2.setChecked(true);else t2.setChecked(false);
                    if(check==3)t3.setChecked(true);else t3.setChecked(false);
                    if(check==4)t4.setChecked(true);else t4.setChecked(false);
                    if(check==5)t5.setChecked(true);else t5.setChecked(false);}
                else if (mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().latitude<gosi.latitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().latitude>goid.latitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().longitude>gosi.longitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().longitude<goid.longitude&
                    mMap.getCameraPosition().zoom>=20)
                {tgo=true;
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    if(check==1)t1.setChecked(true);else t1.setChecked(false);
                    if(check==2)t2.setChecked(true);else t2.setChecked(false);
                    if(check==3)t3.setChecked(true);else t3.setChecked(false);
                    if(check==4)t4.setChecked(true);else t4.setChecked(false);
                    if(check==5)t5.setChecked(true);else t5.setChecked(false);}
                else if (mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().latitude<dbsi.latitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().latitude>dbid.latitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().longitude>dbsi.longitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().longitude<dbid.longitude&
                    mMap.getCameraPosition().zoom>=20)
                {tdb=true;
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.INVISIBLE);
                    if(check==1)t1.setChecked(true);else t1.setChecked(false);
                    if(check==2)t2.setChecked(true);else t2.setChecked(false);
                    if(check==3)t3.setChecked(true);else t3.setChecked(false);
                    if(check==4)t4.setChecked(true);else t4.setChecked(false);
                    if(check==5)t5.setChecked(true);else t5.setChecked(false);}
                else if (mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().latitude<pssi.latitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().latitude>psid.latitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().longitude>pssi.longitude&
                    mMap.getProjection().getVisibleRegion().latLngBounds.getCenter().longitude<psid.longitude&
                    mMap.getCameraPosition().zoom>=20)
                {tps=true;
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.INVISIBLE);
                    if(check==1)t1.setChecked(true);else t1.setChecked(false);
                    if(check==2)t2.setChecked(true);else t2.setChecked(false);
                    if(check==3)t3.setChecked(true);else t3.setChecked(false);
                    if(check==4)t4.setChecked(true);else t4.setChecked(false);
                    if(check==5)t5.setChecked(true);else t5.setChecked(false);}
                else {tps=false;
                    tdb=false;
                    tds=false;
                    tgo=false;
                    t1.setVisibility(View.INVISIBLE);
                    t2.setVisibility(View.INVISIBLE);
                    t3.setVisibility(View.INVISIBLE);
                    t4.setVisibility(View.INVISIBLE);
                    t5.setVisibility(View.INVISIBLE);}
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 250, 0, locationListener);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.x:
                aux=0;
                check=0;
                origen.setText("");
                destino.setText("");
                origen.requestFocus();
                if (polyline != null) polyline.remove();
                if (imageOverlay != null) imageOverlay.remove();
                if (mar != null) mar.remove();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(root, (float) 17.75));
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(destino.getWindowToken(), 0);
                inputMethodManager.hideSoftInputFromWindow(origen.getWindowToken(), 0);
                return true;
            case R.id.dependencias:
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
                return true;
            case R.id.satelite:
                mMap.setMapType(mMap.MAP_TYPE_NORMAL);
                usb.remove();
                usb = mMap.addGroundOverlay(new GroundOverlayOptions()
                        .image(BitmapDescriptorFactory.fromResource(R.drawable.satelite))
                        .position(root, 170, 300));
                if (polyline != null) polyline.setZIndex(1);
                if (imageOverlay != null) imageOverlay.setZIndex(1);
                if (mar != null) mar.setZIndex(1);
                return true;
            case R.id.relieve:
                mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
                usb.remove();
                usb = mMap.addGroundOverlay(new GroundOverlayOptions()
                        .image(BitmapDescriptorFactory.fromResource(R.drawable.relieve))
                        .position(root, 170, 300));
                if (polyline != null) polyline.setZIndex(1);
                if (imageOverlay != null) imageOverlay.setZIndex(1);
                if (mar != null) mar.setZIndex(1);
                return true;
            case R.id.uso:
                startActivity(new Intent(MainActivity2.this, MainActivity4.class));
                return true;
            case R.id.acerca_de:
                startActivity(new Intent(MainActivity2.this, MainActivity5.class));
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void enviar(){
        if (polyline != null) polyline.remove();
        if (imageOverlay != null) imageOverlay.remove();
        if (mar!=null)mar.remove();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(destino.getWindowToken(), 0);

        if (origen.getText().length() > 3 & destino.getText().length() > 3) {
            if (!origen.getText().toString().subSequence(2, 3).equals(" ") & !destino.getText().toString().subSequence(2, 3).equals(" ")) {
                Toast.makeText(MainActivity2.this, "Error 404 - Ruta no encontrada", Toast.LENGTH_LONG).show();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(root, (float) 17.75));
                b=true;
            } else if (origen.getText().toString().subSequence(0, 2).equals("PR") & destino.getText().toString().subSequence(0, 2).equals("DB") | origen.getText().toString().subSequence(0, 2).equals("DB") & destino.getText().toString().subSequence(0, 2).equals("PR")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992,-74.030258)).add(dbp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7506754,-74.030365), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PR") & destino.getText().toString().subSequence(0, 2).equals("PS") | origen.getText().toString().subSequence(0, 2).equals("PS") & destino.getText().toString().subSequence(0, 2).equals("PR")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992,-74.030258)).add(new LatLng(4.75095946, -74.03017211)).add(psp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7507846,-74.0302475), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PR") & destino.getText().toString().subSequence(0, 2).equals("GO") | origen.getText().toString().subSequence(0, 2).equals("GO") & destino.getText().toString().subSequence(0, 2).equals("PR")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992,-74.030258)).add(new LatLng(4.75095946, -74.03017211)).add(psp).add(gup).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7507846,-74.0301375), (float) 18.625));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PR") & destino.getText().toString().subSequence(0, 2).equals("DS") | origen.getText().toString().subSequence(0, 2).equals("DS") & destino.getText().toString().subSequence(0, 2).equals("PR")) {
                if (origen.getText().toString().subSequence(0, 6).equals("PR POR")) {
                    if (destino.getText().toString().subSequence(0, 6).equals("DS 5 J") |
                            destino.getText().toString().subSequence(0, 6).equals("DS 4 C"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7512965, -74.030258)).add(ds4).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 6).equals("DS 4 W") |
                            destino.getText().toString().subSequence(0, 6).equals("DS 3 B"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7509265, -74.0302580)).add(ds1).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 4).equals("DS 1"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7511620, -74.0302580)).add(ds3).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 205 &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 209)
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7511620, -74.0302580)).add(ds3).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 200 &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 205)
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7510485, -74.030258)).add(ds2).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 300 &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 311 |
                            destino.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 400 &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 411 |
                            destino.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 500 &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 511)
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7509265, -74.0302580)).add(ds1).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 311 &

                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 320 |
                            destino.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 411 &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 420 |
                            destino.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 511 &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 520)
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7512965, -74.030258)).add(ds4).color(Color.BLUE));
                } else if (destino.getText().toString().subSequence(0, 6).equals("PR POR")){
                    if (origen.getText().toString().subSequence(0, 6).equals("DS 5 J") |
                            origen.getText().toString().subSequence(0, 6).equals("DS 4 C"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7512965, -74.030258)).add(ds4).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 6).equals("DS 4 W") |
                            origen.getText().toString().subSequence(0, 6).equals("DS 3 B"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7509265, -74.0302580)).add(ds1).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 4).equals("DS 1"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7511620, -74.0302580)).add(ds3).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 205 &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 209)
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7511620, -74.0302580)).add(ds3).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 200 &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 205)
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7510485, -74.030258)).add(ds2).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 300 &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 311 |
                            origen.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 400 &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 411 |
                            origen.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 500 &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 511)
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7509265, -74.0302580)).add(ds1).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 311 &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 320 |
                            origen.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 411 &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 420 |
                            origen.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 511 &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 520)
                        polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7512965, -74.030258)).add(ds4).color(Color.BLUE));
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7509086,-74.0304416), (float) 18.625));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PR") & destino.getText().toString().subSequence(0, 2).equals("PL") | origen.getText().toString().subSequence(0, 2).equals("PL") & destino.getText().toString().subSequence(0, 2).equals("PR")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992,-74.030258)).add(new LatLng(4.75132433, -74.030258)).add(plp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7512630,-74.0304140), (float) 18.625));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PR") & destino.getText().toString().subSequence(0, 2).equals("HN") | origen.getText().toString().subSequence(0, 2).equals("HN") & destino.getText().toString().subSequence(0, 2).equals("PR")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992,-74.030258)).add(new LatLng(4.75132433, -74.030258)).add(new LatLng(4.75191406, -74.030258)).add(new LatLng(4.75191406, -74.0304075)).add(hnp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7515126,-74.0304888), (float) 18.25));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PR") & destino.getText().toString().subSequence(0, 2).equals("CP") | origen.getText().toString().subSequence(0, 2).equals("CP") & destino.getText().toString().subSequence(0, 2).equals("PR")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(cpp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7504649,-74.0304693), 20));

            } else if (origen.getText().toString().subSequence(0, 2).equals("CP") & destino.getText().toString().subSequence(0, 2).equals("DB") | origen.getText().toString().subSequence(0, 2).equals("DB") & destino.getText().toString().subSequence(0, 2).equals("CP")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133,-74.03036858)).add(new LatLng(4.75058992,-74.030258)).add(dbp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7506196,-74.0302643), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("CP") & destino.getText().toString().subSequence(0, 2).equals("PS") | origen.getText().toString().subSequence(0, 2).equals("PS") & destino.getText().toString().subSequence(0, 2).equals("CP")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133,-74.03036858)).add(new LatLng(4.75058992,-74.030258)).add(new LatLng(4.75095946, -74.03017211)).add(psp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7507288,-74.0301468), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("CP") & destino.getText().toString().subSequence(0, 2).equals("GO") | origen.getText().toString().subSequence(0, 2).equals("GO") & destino.getText().toString().subSequence(0, 2).equals("CP")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133,-74.03036858)).add(new LatLng(4.75058992,-74.030258)).add(new LatLng(4.75095946, -74.03017211)).add(psp).add(gup).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7507288,-74.0300368), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("CP") & destino.getText().toString().subSequence(0, 2).equals("DS") | origen.getText().toString().subSequence(0, 2).equals("DS") & destino.getText().toString().subSequence(0, 2).equals("CP")) {
                if (origen.getText().toString().subSequence(0, 6).equals("CP CAP")) {
                    if (destino.getText().toString().subSequence(0, 6).equals("DS 5 J") |
                            destino.getText().toString().subSequence(0, 6).equals("DS 4 C"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7512965, -74.0302580)).add(ds4).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 6).equals("DS 4 W") |
                            destino.getText().toString().subSequence(0, 6).equals("DS 3 B"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7509265, -74.0302580)).add(ds1).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 4).equals("DS 1"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7511620, -74.0302580)).add(ds3).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 205 &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 209)
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7511620, -74.0302580)).add(ds3).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 200 &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 205)
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7510485, -74.030258)).add(ds2).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 300 &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 311 |
                            destino.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 400 &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 411 |
                            destino.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 500 &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 511)
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7509265, -74.030258)).add(ds1).color(Color.BLUE));
                    else if (destino.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 311 &
                            Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 320 |
                            destino.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 411 &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 420 |
                            destino.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 511 &
                                    Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 520)
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7512965, -74.0302580)).add(ds4).color(Color.BLUE));
                } else if (destino.getText().toString().subSequence(0, 6).equals("CP CAP")){
                    if (origen.getText().toString().subSequence(0, 6).equals("DS 5 J") |
                            origen.getText().toString().subSequence(0, 6).equals("DS 4 C"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7512965, -74.0302580)).add(ds4).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 6).equals("DS 4 W") |
                            origen.getText().toString().subSequence(0, 6).equals("DS 3 B"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7509265, -74.030258)).add(ds1).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 4).equals("DS 1"))
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7511620, -74.0302580)).add(ds3).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 205 &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 209)
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.0302580)).add(new LatLng(4.7511620, -74.0302580)).add(ds3).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 200 &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 205)
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7510485, -74.030258)).add(ds2).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 300 &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 311 |
                            origen.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 400 &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 411 |
                            origen.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 500 &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 511)
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7509265, -74.030258)).add(ds1).color(Color.BLUE));
                    else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 311 &
                            Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 320 |
                            origen.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 411 &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 420 |
                            origen.getText().toString().subSequence(0, 2).equals("DS") &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 511 &
                                    Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 520)
                        polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133, -74.03036858)).add(new LatLng(4.75058992, -74.030258)).add(new LatLng(4.7512965, -74.030258)).add(ds4).color(Color.BLUE));
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7508528,-74.0303409), (float) 18.625));
            } else if (origen.getText().toString().subSequence(0, 2).equals("CP") & destino.getText().toString().subSequence(0, 2).equals("PL") | origen.getText().toString().subSequence(0, 2).equals("PL") & destino.getText().toString().subSequence(0, 2).equals("CP")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133,-74.03036858)).add(new LatLng(4.75058992,-74.030258)).add(new LatLng(4.75132433, -74.030258)).add(plp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7512072,-74.0303133), (float) 18.25));
            } else if (origen.getText().toString().subSequence(0, 2).equals("CP") & destino.getText().toString().subSequence(0, 2).equals("HN") | origen.getText().toString().subSequence(0, 2).equals("HN") & destino.getText().toString().subSequence(0, 2).equals("CP")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133,-74.03036858)).add(new LatLng(4.75058992,-74.030258)).add(new LatLng(4.75132433, -74.030258)).add(new LatLng(4.75191406, -74.030258)).add(new LatLng(4.75191406, -74.0304075)).add(hnp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7514568,-74.0303880), (float) 18.25));

            } else if (origen.getText().toString().subSequence(0, 2).equals("HN") & destino.getText().toString().subSequence(0, 2).equals("PL") | origen.getText().toString().subSequence(0, 2).equals("PL") & destino.getText().toString().subSequence(0, 2).equals("HN")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(hnp).add(new LatLng(4.75239000, -74.0304075)).add(new LatLng(4.75239000, -74.030041)).add(new LatLng(4.75231901, -74.030041)).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7522549,-74.0303328), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("HN") & destino.getText().toString().subSequence(0, 2).equals("DS") | origen.getText().toString().subSequence(0, 2).equals("DS") & destino.getText().toString().subSequence(0, 2).equals("HN")) {
                if (origen.getText().toString().subSequence(0, 6).equals("DS 3 B") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 3 B"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(hnp).add(new LatLng(4.75191406, -74.0304075)).add(new LatLng(4.75191406, -74.030258)).add(new LatLng(4.75132433, -74.030258)).add(new LatLng(4.7509265, -74.030258)).add(ds1).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 4).equals("DS 1") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 1") |
                        origen.getText().toString().subSequence(0, 4).equals("DS 2") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 2"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(hnp).add(new LatLng(4.75191406, -74.0304075)).add(new LatLng(4.75191406, -74.030258)).add(new LatLng(4.75132433, -74.030258)).add(new LatLng(4.7511620, -74.030258)).add(ds3).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 4).equals("DS 3") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 3") |
                        origen.getText().toString().subSequence(0, 4).equals("DS 4") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 4") |
                        origen.getText().toString().subSequence(0, 4).equals("DS 5") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 5"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(hnp).add(new LatLng(4.75191406, -74.0304075)).add(new LatLng(4.75191406, -74.030258)).add(new LatLng(4.75132433, -74.030258)).add(new LatLng(4.7512965, -74.030258)).add(ds4).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7517155,-74.0303604), (float) 18.25));
            } else if (origen.getText().toString().subSequence(0, 2).equals("HN") & destino.getText().toString().subSequence(0, 2).equals("DB") | origen.getText().toString().subSequence(0, 2).equals("DB") & destino.getText().toString().subSequence(0, 2).equals("HN")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(hnp).add(new LatLng(4.75191406, -74.0304075)).add(new LatLng(4.75191406, -74.030258)).add(new LatLng(4.75132433, -74.030258)).add(dbp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7516672,-74.0302838), (float) 18.25));
            } else if (origen.getText().toString().subSequence(0, 2).equals("HN") & destino.getText().toString().subSequence(0, 2).equals("PS") | origen.getText().toString().subSequence(0, 2).equals("PS") & destino.getText().toString().subSequence(0, 2).equals("HN")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(hnp).add(new LatLng(4.75191406, -74.0304075)).add(new LatLng(4.75191406, -74.030258)).add(new LatLng(4.75132433, -74.030258)).add(psp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7517765,-74.0301663), (float) 18.25));
            } else if (origen.getText().toString().subSequence(0, 2).equals("HN") & destino.getText().toString().subSequence(0, 2).equals("GO") | origen.getText().toString().subSequence(0, 2).equals("GO") & destino.getText().toString().subSequence(0, 2).equals("HN")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(hnp).add(new LatLng(4.75191406, -74.0304075)).add(new LatLng(4.75191406, -74.030258)).add(new LatLng(4.75132433, -74.030258)).add(psp).add(gup).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7517765,-74.0300563), (float) 18.25));

            } else if (origen.getText().toString().subSequence(0, 2).equals("PL") & destino.getText().toString().subSequence(0, 2).equals("DS") | origen.getText().toString().subSequence(0, 2).equals("DS") & destino.getText().toString().subSequence(0, 2).equals("PL")) {
                if (origen.getText().toString().subSequence(0, 6).equals("DS 3 B") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 3 B"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(plp).add(new LatLng(4.75132433, -74.030258)).add(new LatLng(4.7509265, -74.030258)).add(ds1).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 4).equals("DS 1") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 1") |
                        origen.getText().toString().subSequence(0, 4).equals("DS 2") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 2"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(plp).add(new LatLng(4.75132433, -74.030258)).add(new LatLng(4.7511620, -74.030258)).add(ds3).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 4).equals("DS 3") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 3") |
                        origen.getText().toString().subSequence(0, 4).equals("DS 4") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 4") |
                        origen.getText().toString().subSequence(0, 4).equals("DS 5") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 5"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(plp).add(new LatLng(4.75132433, -74.030258)).add(new LatLng(4.7512965, -74.030258)).add(ds4).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7514659,-74.0302856), (float) 18.625));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PL") & destino.getText().toString().subSequence(0, 2).equals("DB") | origen.getText().toString().subSequence(0, 2).equals("DB") & destino.getText().toString().subSequence(0, 2).equals("PL")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(plp).add(new LatLng(4.75132433, -74.030258)).add(dbp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7514177,-74.030209), (float) 18.25));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PL") & destino.getText().toString().subSequence(0, 2).equals("PS") | origen.getText().toString().subSequence(0, 2).equals("PS") & destino.getText().toString().subSequence(0, 2).equals("PL")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(plp).add(new LatLng(4.75132433, -74.030258)).add(psp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7515269,-74.0300915), (float) 18.625));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PL") & destino.getText().toString().subSequence(0, 2).equals("GO") | origen.getText().toString().subSequence(0, 2).equals("GO") & destino.getText().toString().subSequence(0, 2).equals("PL")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(plp).add(new LatLng(4.75132433, -74.030258)).add(psp).add(gup).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7515269,-74.0299815), (float) 18.625));

            } else if (origen.getText().toString().subSequence(0, 2).equals("DS") & destino.getText().toString().subSequence(0, 2).equals("DB") | origen.getText().toString().subSequence(0, 2).equals("DB") & destino.getText().toString().subSequence(0, 2).equals("DS")) {
                if (origen.getText().toString().subSequence(0, 6).equals("DS 5 J") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 5 J") |
                        origen.getText().toString().subSequence(0, 6).equals("DS 4 C") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 4 C"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds4).add(new LatLng(4.7512965, -74.030258)).add(dbp).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 6).equals("DS 4 W") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 4 W") |
                        origen.getText().toString().subSequence(0, 6).equals("DS 3 B") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 3 B"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds1).add(new LatLng(4.7509265, -74.030258)).add(dbp).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 4).equals("DS 1") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 1"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds3).add(new LatLng(4.7511620, -74.030258)).add(dbp).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 205 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 209 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 205 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 209)
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds3).add(new LatLng(4.7511620, -74.030258)).add(dbp).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 200 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 205 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 200 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 205)
                    polyline = mMap.addPolyline(new PolylineOptions().add(dbp).add(new LatLng(4.7510485, -74.030258)).add(ds2).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().toString().subSequence(3, 6).toString()) >= 300 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 311 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 300 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 311 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 400 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 411 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 400 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 411 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 500 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 511 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 500 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 511)
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds1).add(new LatLng(4.7509265, -74.030258)).add(dbp).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 311 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 320 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 311 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 320 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 411 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 420 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 411 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 420 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 511 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 520 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 511 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 520)
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds4).add(new LatLng(4.7512965, -74.030258)).add(dbp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7510633,-74.0302366), 19));

            } else if (origen.getText().toString().subSequence(0, 2).equals("DS") & destino.getText().toString().subSequence(0, 2).equals("PS") | origen.getText().toString().subSequence(0, 2).equals("PS") & destino.getText().toString().subSequence(0, 2).equals("DS")) {
                if (origen.getText().toString().subSequence(0, 6).equals("DS 5 J") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 5 J") |
                        origen.getText().toString().subSequence(0, 6).equals("DS 4 C") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 4 C"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds4).add(new LatLng(4.7512965, -74.030258)).add(psp).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 6).equals("DS 4 W") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 4 W") |
                        origen.getText().toString().subSequence(0, 6).equals("DS 3 B") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 3 B"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds1).add(new LatLng(4.7509265, -74.030258)).add(psp).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 4).equals("DS 1") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 1"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds3).add(new LatLng(4.7511620, -74.030258)).add(psp).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 200 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 205 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 200 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 205)
                    polyline = mMap.addPolyline(new PolylineOptions().add(psp).add(new LatLng(4.7510485, -74.030258)).add(ds2).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 205 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 209 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 205 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 209)
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds3).add(new LatLng(4.7511620, -74.030258)).add(psp).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 300 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 307 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 300 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 307 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 400 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 407 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 400 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 407 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 500 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 507 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 500 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 507)
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds1).add(new LatLng(4.7509265, -74.030258)).add(psp).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 307 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 320 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 307 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 320 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 407 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 420 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 407 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 420 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 507 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 520 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 507 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 520)
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds4).add(new LatLng(4.7512965, -74.030258)).add(psp).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7511725,-74.0301191), 19));

            } else if (origen.getText().toString().subSequence(0, 2).equals("DS") & destino.getText().toString().subSequence(0, 2).equals("GO") | origen.getText().toString().subSequence(0, 2).equals("GO") & destino.getText().toString().subSequence(0, 2).equals("DS")) {
                if (origen.getText().toString().subSequence(0, 6).equals("DS 5 J") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 5 J") |
                        origen.getText().toString().subSequence(0, 6).equals("DS 4 C") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 4 C"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds4).add(new LatLng(4.7512965, -74.030258)).add(psp).add(gup).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 6).equals("DS 4 W") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 4 W") |
                        origen.getText().toString().subSequence(0, 6).equals("DS 3 B") |
                        destino.getText().toString().subSequence(0, 6).equals("DS 3 B"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds1).add(new LatLng(4.7509265, -74.030258)).add(psp).add(gup).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 4).equals("DS 1") |
                        destino.getText().toString().subSequence(0, 4).equals("DS 1"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds3).add(new LatLng(4.7511620, -74.030258)).add(psp).add(gup).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 200 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 205 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 200 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 205)
                    polyline = mMap.addPolyline(new PolylineOptions().add(gup).add(psp).add(new LatLng(4.7510485, -74.030258)).add(ds2).color(Color.BLUE));
                else if(origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 205 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 209 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 205 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 209)
                    polyline = mMap.addPolyline(new PolylineOptions().add(gup).add(psp).add(new LatLng(4.7511620, -74.030258)).add(ds3).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 300 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 307 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 300 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 307 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 400 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 407 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 400 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 407 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 500 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 507 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 500 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 507)
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds1).add(new LatLng(4.7509265, -74.030258)).add(psp).add(gup).color(Color.BLUE));
                else if (origen.getText().toString().subSequence(0, 2).equals("DS") &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 307 &
                        Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 320 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 307 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 320 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 407 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 420 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 407 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 420 |
                        origen.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) >= 507 &
                                Integer.valueOf(origen.getText().subSequence(3, 6).toString()) < 520 |
                        destino.getText().toString().subSequence(0, 2).equals("DS") &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) >= 507 &
                                Integer.valueOf(destino.getText().subSequence(3, 6).toString()) < 520)
                    polyline = mMap.addPolyline(new PolylineOptions().add(ds4).add(new LatLng(4.7512965, -74.030258)).add(psp).add(gup).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7509875,-74.0300091), 19));

            } else if (origen.getText().toString().subSequence(0, 2).equals("DB") & destino.getText().toString().subSequence(0, 2).equals("PS") | origen.getText().toString().subSequence(0, 2).equals("PS") & destino.getText().toString().subSequence(0, 2).equals("DB")) {
                if (destino.getText().toString().subSequence(3, 4).equals("1"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(new LatLng(4.75083, -74.030013)).add(new LatLng(4.75083, -74.029964)).add(new LatLng(4.7510485, -74.029964)).add(psp).color(Color.BLUE));
                else
                    polyline = mMap.addPolyline(new PolylineOptions().add(new LatLng(4.75083, -74.030013)).add(new LatLng(4.75083, -74.029938)).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7509393,-74.0300425), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("DB") & destino.getText().toString().subSequence(0, 2).equals("GO") | origen.getText().toString().subSequence(0, 2).equals("GO") & destino.getText().toString().subSequence(0, 2).equals("DB")) {
                if (destino.getText().toString().subSequence(3, 4).equals("1"))
                    polyline = mMap.addPolyline(new PolylineOptions().add(new LatLng(4.75083, -74.030013)).add(new LatLng(4.75083, -74.029964)).add(new LatLng(4.7510485, -74.029964)).add(gup).color(Color.BLUE));
                else
                    polyline = mMap.addPolyline(new PolylineOptions().add(new LatLng(4.75083, -74.030013)).add(new LatLng(4.75083, -74.0299)).add(new LatLng(4.7510485, -74.0299)).add(gup).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7509393,-74.0299325), 19));

            } else if (origen.getText().toString().subSequence(0, 2).equals("PS") & destino.getText().toString().subSequence(0, 2).equals("GO") | origen.getText().toString().subSequence(0, 2).equals("GO") & destino.getText().toString().subSequence(0, 2).equals("PS")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(new LatLng(4.7510485, -74.029795)).add(gup).color(Color.BLUE));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.7510485,-74.029815), 19));


            } else if (origen.getText().toString().subSequence(0, 2).equals("RS") & destino.getText().toString().subSequence(0, 2).equals("CP") | origen.getText().toString().subSequence(0, 2).equals("CP") & destino.getText().toString().subSequence(0, 2).equals("RS")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(cpp).add(new LatLng(4.75048133,-74.03036858)).add(new LatLng(4.75058992,-74.030258)).add(new LatLng(4.75130863,-74.03021469)).color(Color.BLUE));
                if (destino.getText().toString().subSequence(0, 2).equals("RS"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.75130863,-74.03021469), (float) 7.5, 10));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.750912325, -74.030246835), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("RS") & destino.getText().toString().subSequence(0, 2).equals("PR") | origen.getText().toString().subSequence(0, 2).equals("PR") & destino.getText().toString().subSequence(0, 2).equals("RS")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(prp).add(new LatLng(4.75058992,-74.030258)).add(new LatLng(4.75130863,-74.03021469)).color(Color.BLUE));
                if (destino.getText().toString().subSequence(0, 2).equals("RS"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.75130863,-74.03021469), (float) 7.5, 10));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.750968125, -74.030347585), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("RS") & destino.getText().toString().subSequence(0, 2).equals("DB") | origen.getText().toString().subSequence(0, 2).equals("DB") & destino.getText().toString().subSequence(0, 2).equals("RS")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(dbp).add(new LatLng(4.75130863,-74.03021469)).color(Color.BLUE));
                if (destino.getText().toString().subSequence(0, 2).equals("RS"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.75130863,-74.03021469), (float) 7.5, 10));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.751122775, -74.030142585), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("RS") & destino.getText().toString().subSequence(0, 2).equals("DS") | origen.getText().toString().subSequence(0, 2).equals("DS") & destino.getText().toString().subSequence(0, 2).equals("RS")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(ds4).add(new LatLng(4.751356025, -74.030219185)).color(Color.BLUE));
                if (destino.getText().toString().subSequence(0, 2).equals("RS"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.75130863,-74.03021469), (float) 7.5, 10));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.751277275, -74.030261835), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("RS") & destino.getText().toString().subSequence(0, 2).equals("PS") | origen.getText().toString().subSequence(0, 2).equals("PS") & destino.getText().toString().subSequence(0, 2).equals("RS")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(psp).add(new LatLng(4.7510485, -74.0299375)).add(new LatLng(4.75132232,-74.03007220)).color(Color.BLUE));
                if (destino.getText().toString().subSequence(0, 2).equals("RS"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.75132232,-74.03007220), (float) 7.5, 10));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.751232025, -74.030025085), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("RS") & destino.getText().toString().subSequence(0, 2).equals("GO") | origen.getText().toString().subSequence(0, 2).equals("GO") & destino.getText().toString().subSequence(0, 2).equals("RS")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(gup).add(new LatLng(4.7510485, -74.0297525)).add(new LatLng(4.75117130,-74.0297525)).add(new LatLng(4.75131798,-74.03007555)).color(Color.BLUE));
                if (destino.getText().toString().subSequence(0, 2).equals("RS"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.75132232,-74.03007220), (float) 7.5, 10));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.751232025, -74.029915085), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("RS") & destino.getText().toString().subSequence(0, 2).equals("PL") | origen.getText().toString().subSequence(0, 2).equals("PL") & destino.getText().toString().subSequence(0, 2).equals("RS")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(new LatLng(4.7514924, -74.02998750)).add(new LatLng(4.75191372, -74.02998750)).add(new LatLng(4.75191372, -74.0302580)).add(plp).color(Color.BLUE));
                if (destino.getText().toString().subSequence(0, 2).equals("RS"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.7514924, -74.02998750), (float) 7.5, 10));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.751710425, -74.030191585), 19));
            } else if (origen.getText().toString().subSequence(0, 2).equals("RS") & destino.getText().toString().subSequence(0, 2).equals("HN") | origen.getText().toString().subSequence(0, 2).equals("HN") & destino.getText().toString().subSequence(0, 2).equals("RS")) {
                polyline = mMap.addPolyline(new PolylineOptions().add(hnp).add(new LatLng(4.75239000, -74.0304075)).add(new LatLng(4.75239000, -74.02998750)).add(new LatLng(4.7514924, -74.02998750)).color(Color.BLUE));
                if (destino.getText().toString().subSequence(0, 2).equals("RS"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.7514924, -74.02998750), (float) 7.5, 10));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.751959975, -74.030266335), 19));


            } else if (origen.getText().toString().subSequence(0, 2).equals("PR") & destino.getText().toString().subSequence(0, 2).equals("PR") | origen.getText().toString().subSequence(0, 2).equals("PR") & destino.getText().toString().subSequence(0, 2).equals("CP")) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(prp, 20));
            } else if (origen.getText().toString().subSequence(0, 2).equals("CP") & destino.getText().toString().subSequence(0, 2).equals("CP") | origen.getText().toString().subSequence(0, 2).equals("CP") & destino.getText().toString().subSequence(0, 2).equals("CP")) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cpp, 20));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PL") & destino.getText().toString().subSequence(0, 2).equals("PL") | origen.getText().toString().subSequence(0, 2).equals("PL") & destino.getText().toString().subSequence(0, 2).equals("PL")) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(plp, 20));
            } else if (origen.getText().toString().subSequence(0, 2).equals("HN") & destino.getText().toString().subSequence(0, 2).equals("HN") | origen.getText().toString().subSequence(0, 2).equals("HN") & destino.getText().toString().subSequence(0, 2).equals("HN")) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hnp, 20));

            } else if (origen.getText().toString().subSequence(0, 2).equals("GO") & destino.getText().toString().subSequence(0, 2).equals("GO") & Integer.parseInt(origen.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(origen.getText().subSequence(3,4).toString()) < 6 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) < 6 | origen.getText().toString().subSequence(0, 2).equals("GO") & destino.getText().toString().subSequence(0, 2).equals("GO") & Integer.parseInt(origen.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(origen.getText().subSequence(3,4).toString()) < 6 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) < 6) {
                if(Integer.parseInt(origen.getText().subSequence(3,4).toString()) < Integer.parseInt(destino.getText().subSequence(3,4).toString()))Toast.makeText(MainActivity2.this, "Debes subir "+(Integer.parseInt(destino.getText().subSequence(3,4).toString())-Integer.parseInt(origen.getText().subSequence(3,4).toString()))+" piso(s)", Toast.LENGTH_SHORT).show();
                else if(Integer.parseInt(origen.getText().subSequence(3,4).toString()) > Integer.parseInt(destino.getText().subSequence(3,4).toString()))Toast.makeText(MainActivity2.this, "Debes bajar "+(Integer.parseInt(origen.getText().subSequence(3,4).toString())-Integer.parseInt(destino.getText().subSequence(3,4).toString()))+" piso(s)", Toast.LENGTH_SHORT).show();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gu, 20));
            } else if (origen.getText().toString().subSequence(0, 2).equals("PS") & destino.getText().toString().subSequence(0, 2).equals("PS") & Integer.parseInt(origen.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(origen.getText().subSequence(3,4).toString()) < 5 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) < 5 | origen.getText().toString().subSequence(0, 2).equals("PS") & destino.getText().toString().subSequence(0, 2).equals("PS") & Integer.parseInt(origen.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(origen.getText().subSequence(3,4).toString()) < 5 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) < 5) {
                if(Integer.parseInt(origen.getText().subSequence(3,4).toString()) < Integer.parseInt(destino.getText().subSequence(3,4).toString()))Toast.makeText(MainActivity2.this, "Debes subir "+(Integer.parseInt(destino.getText().subSequence(3,4).toString())-Integer.parseInt(origen.getText().subSequence(3,4).toString()))+" piso(s)", Toast.LENGTH_SHORT).show();
                else if(Integer.parseInt(origen.getText().subSequence(3,4).toString()) > Integer.parseInt(destino.getText().subSequence(3,4).toString()))Toast.makeText(MainActivity2.this, "Debes bajar "+(Integer.parseInt(origen.getText().subSequence(3,4).toString())-Integer.parseInt(destino.getText().subSequence(3,4).toString()))+" piso(s)", Toast.LENGTH_SHORT).show();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ps, 20));
            } else if (origen.getText().toString().subSequence(0, 2).equals("DB") & destino.getText().toString().subSequence(0, 2).equals("DB") & Integer.parseInt(origen.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(origen.getText().subSequence(3,4).toString()) < 5 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) < 5 | origen.getText().toString().subSequence(0, 2).equals("DB") & destino.getText().toString().subSequence(0, 2).equals("DB") & Integer.parseInt(origen.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(origen.getText().subSequence(3,4).toString()) < 5 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) < 5) {
                if(Integer.parseInt(origen.getText().subSequence(3,4).toString()) < Integer.parseInt(destino.getText().subSequence(3,4).toString()))Toast.makeText(MainActivity2.this, "Debes subir "+(Integer.parseInt(destino.getText().subSequence(3,4).toString())-Integer.parseInt(origen.getText().subSequence(3,4).toString()))+" piso(s)", Toast.LENGTH_SHORT).show();
                else if(Integer.parseInt(origen.getText().subSequence(3,4).toString()) > Integer.parseInt(destino.getText().subSequence(3,4).toString()))Toast.makeText(MainActivity2.this, "Debes bajar "+(Integer.parseInt(origen.getText().subSequence(3,4).toString())-Integer.parseInt(destino.getText().subSequence(3,4).toString()))+" piso(s)", Toast.LENGTH_SHORT).show();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(db, 20));
            } else if (origen.getText().toString().subSequence(0, 2).equals("DS") & destino.getText().toString().subSequence(0, 2).equals("DS") & Integer.parseInt(origen.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(origen.getText().subSequence(3,4).toString()) < 6 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) < 6 | origen.getText().toString().subSequence(0, 2).equals("DS") & destino.getText().toString().subSequence(0, 2).equals("DS") & Integer.parseInt(origen.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(origen.getText().subSequence(3,4).toString()) < 6 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) > 0 & Integer.parseInt(destino.getText().subSequence(3,4).toString()) < 6) {
                if(Integer.parseInt(origen.getText().subSequence(3,4).toString()) < Integer.parseInt(destino.getText().subSequence(3,4).toString()))Toast.makeText(MainActivity2.this, "Debes subir "+(Integer.parseInt(destino.getText().subSequence(3,4).toString())-Integer.parseInt(origen.getText().subSequence(3,4).toString()))+" piso(s)", Toast.LENGTH_SHORT).show();
                else if(Integer.parseInt(origen.getText().subSequence(3,4).toString()) > Integer.parseInt(destino.getText().subSequence(3,4).toString()))Toast.makeText(MainActivity2.this, "Debes bajar "+(Integer.parseInt(origen.getText().subSequence(3,4).toString())-Integer.parseInt(destino.getText().subSequence(3,4).toString()))+" piso(s)", Toast.LENGTH_SHORT).show();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ds, (float) 19.25));

            } else {
                Toast.makeText(MainActivity2.this, "Error 404 - Ruta no encontrada", Toast.LENGTH_LONG).show();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(root, (float) 17.75));
                b=true;
            }

            if(!b) {
                if (destino.getText().toString().subSequence(0, 4).equals("DS 1")) {check=1;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ds1)).position(ds, ands, alds));
                }else if (destino.getText().toString().subSequence(0, 4).equals("DS 2")) {check=2;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ds2)).position(ds, ands, alds));
                }else if (destino.getText().toString().subSequence(0, 4).equals("DS 3")) {check=3;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ds3)).position(ds, ands, alds));
                }else if (destino.getText().toString().subSequence(0, 4).equals("DS 4")) {check=4;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ds4)).position(ds, ands, alds));
                }else if (destino.getText().toString().subSequence(0, 4).equals("DS 5")) {check=5;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ds5)).position(ds, ands, alds));
                }else if (destino.getText().toString().subSequence(0, 4).equals("DB 1")) {check=1;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.db1)).position(db, andb, aldb));
                }else if (destino.getText().toString().subSequence(0, 4).equals("DB 2")) {check=2;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.db2)).position(db, andb, aldb));
                }else if (destino.getText().toString().subSequence(0, 4).equals("DB 3")) {check=3;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.db3)).position(db, andb, aldb));
                }else if (destino.getText().toString().subSequence(0, 4).equals("DB 4")) {check=4;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.db4)).position(db, andb, aldb));
                }else if (destino.getText().toString().subSequence(0, 4).equals("PS 1")) {check=1;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ps1)).position(ps, anps, alps));
                }else if (destino.getText().toString().subSequence(0, 4).equals("PS 2")) {check=2;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ps2)).position(ps, anps, alps));
                }else if (destino.getText().toString().subSequence(0, 4).equals("PS 3")) {check=3;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ps3)).position(ps, anps, alps));
                }else if (destino.getText().toString().subSequence(0, 4).equals("PS 4")) {check=4;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.ps4)).position(ps, anps, alps));
                }else if (destino.getText().toString().subSequence(0, 4).equals("GO 1")) {check=1;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.go1)).position(gu, ango, algo));
                }else if (destino.getText().toString().subSequence(0, 4).equals("GO 2")) {check=2;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.go2)).position(gu, ango, algo));
                }else if (destino.getText().toString().subSequence(0, 4).equals("GO 3")) {check=3;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.go3)).position(gu, ango, algo));
                }else if (destino.getText().toString().subSequence(0, 4).equals("GO 4")) {check=4;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.go4)).position(gu, ango, algo));
                }else if (destino.getText().toString().subSequence(0, 4).equals("GO 5")) {check=5;
                    imageOverlay = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.go5)).position(gu, ango, algo));
                }

                if (destino.getText().toString().subSequence(0, 2).equals("PL") & origen.getText().toString().subSequence(0, 2).equals("HN"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.down)).position(new LatLng(4.75231901, -74.030041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 2).equals("PL"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(plp, (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 2).equals("HN"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(hnp, (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 2).equals("CP"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(cpp, (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 2).equals("PR"))

                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(prp, (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 101")|destino.getText().toString().subSequence(0, 6).equals("DB 201")|destino.getText().toString().subSequence(0, 6).equals("DB 301")|destino.getText().toString().subSequence(0, 6).equals("DB 401"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.75081, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 102"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75078, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 202")|destino.getText().toString().subSequence(0, 6).equals("DB 302")|destino.getText().toString().subSequence(0, 6).equals("DB 402"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.750775, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 103")|destino.getText().toString().subSequence(0, 6).equals("DB 203")|destino.getText().toString().subSequence(0, 6).equals("DB 303")|destino.getText().toString().subSequence(0, 6).equals("DB 403"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75072, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 104"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75063, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 204")|destino.getText().toString().subSequence(0, 6).equals("DB 304")|destino.getText().toString().subSequence(0, 6).equals("DB 404"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7506675, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 105"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75056, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 205")|destino.getText().toString().subSequence(0, 6).equals("DB 305")|destino.getText().toString().subSequence(0, 6).equals("DB 405"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7506125, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 106"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.750505, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 206"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7505325, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 207")|destino.getText().toString().subSequence(0, 6).equals("DB 308"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75042, -74.0301), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 306")|destino.getText().toString().subSequence(0, 6).equals("DB 406"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75056, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 307")|destino.getText().toString().subSequence(0, 6).equals("DB 407"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7505075, -74.0300875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DB 107"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.75045, -74.0301), (float) 7.5, 10));

                else if (destino.getText().toString().subSequence(0, 6).equals("PS 102"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.751033, -74.0298875), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("PS 103"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.751033, -74.02985), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("PS 202")|destino.getText().toString().subSequence(0, 6).equals("PS 301")|destino.getText().toString().subSequence(0, 6).equals("PS 401"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.751033, -74.029876), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("PS 104")|destino.getText().toString().subSequence(0, 6).equals("PS 203")|destino.getText().toString().subSequence(0, 6).equals("PS 302")|destino.getText().toString().subSequence(0, 6).equals("PS 402"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7509875, -74.0298775), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("PS 105")|destino.getText().toString().subSequence(0, 6).equals("PS 204")|destino.getText().toString().subSequence(0, 6).equals("PS 303")|destino.getText().toString().subSequence(0, 6).equals("PS 403"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7509375, -74.0298775), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("PS 106")|destino.getText().toString().subSequence(0, 6).equals("PS 205")|destino.getText().toString().subSequence(0, 6).equals("PS 304")|destino.getText().toString().subSequence(0, 6).equals("PS 404"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75088, -74.0298775), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("PS 107")|destino.getText().toString().subSequence(0, 6).equals("PS 206")|destino.getText().toString().subSequence(0, 6).equals("PS 305")|destino.getText().toString().subSequence(0, 6).equals("PS 405"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7508275, -74.0298775), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("PS 108")|destino.getText().toString().subSequence(0, 6).equals("PS 207")|destino.getText().toString().subSequence(0, 6).equals("PS 306")|destino.getText().toString().subSequence(0, 6).equals("PS 406"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75078, -74.0298775), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("PS 109")|destino.getText().toString().subSequence(0, 6).equals("PS 208")|destino.getText().toString().subSequence(0, 6).equals("PS 307")|destino.getText().toString().subSequence(0, 6).equals("PS 407"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7507275, -74.0298775), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("PS 110")|destino.getText().toString().subSequence(0, 6).equals("PS 209")|destino.getText().toString().subSequence(0, 6).equals("PS 308"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.750625, -74.0299), (float) 7.5, 10));

                else if (destino.getText().toString().subSequence(0, 6).equals("GO 101"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751175, -74.029629), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 201")|destino.getText().toString().subSequence(0, 6).equals("GO 301")|destino.getText().toString().subSequence(0, 6).equals("GO 401")|destino.getText().toString().subSequence(0, 6).equals("GO 501"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751145, -74.02964), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 102"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.751175, -74.029629), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 202")|destino.getText().toString().subSequence(0, 6).equals("GO 402"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7512, -74.02964), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 302")|destino.getText().toString().subSequence(0, 6).equals("GO 502"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75124, -74.02964), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 103"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751175, -74.029629), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 203")|destino.getText().toString().subSequence(0, 6).equals("GO 403"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751255, -74.02964), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 303")|destino.getText().toString().subSequence(0, 6).equals("GO 204")|destino.getText().toString().subSequence(0, 6).equals("GO 404"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751310, -74.02964), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 503"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.75124, -74.02964), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 104"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.7509, -74.029622), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 304")|destino.getText().toString().subSequence(0, 6).equals("GO 205")|destino.getText().toString().subSequence(0, 6).equals("GO 405"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751310, -74.02963), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 105"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7509, -74.029622), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 305")|destino.getText().toString().subSequence(0, 6).equals("GO 406"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.75124, -74.02963), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 106"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.down)).position(new LatLng(4.7509, -74.029622), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 206")|destino.getText().toString().subSequence(0, 6).equals("GO 504"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.7512, -74.02964), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 306")|destino.getText().toString().subSequence(0, 6).equals("GO 407"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751145, -74.02963), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 207")|destino.getText().toString().subSequence(0, 6).equals("GO 307")|destino.getText().toString().subSequence(0, 6).equals("GO 505"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.750975, -74.02963), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 208")|destino.getText().toString().subSequence(0, 6).equals("GO 308")|destino.getText().toString().subSequence(0, 6).equals("GO 408")|destino.getText().toString().subSequence(0, 6).equals("GO 506"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.75093, -74.02963), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 209")|destino.getText().toString().subSequence(0, 6).equals("GO 507"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.75086, -74.02963), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 309")|destino.getText().toString().subSequence(0, 6).equals("GO 409"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.down)).position(new LatLng(4.75093, -74.02963), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 509"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.down)).position(new LatLng(4.75086, -74.02963), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 210"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.down)).position(new LatLng(4.75086, -74.029635), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 310")|destino.getText().toString().subSequence(0, 6).equals("GO 410")|destino.getText().toString().subSequence(0, 6).equals("GO 511"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75093, -74.02963), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 510"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.down)).position(new LatLng(4.75086, -74.02958), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 211"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75086, -74.02964), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 411"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75097, -74.02963), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 212"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.75095, -74.02964), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("GO 512"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.750975, -74.02963), (float) 7.5, 10));

                else if (destino.getText().toString().subSequence(0, 6).equals("DS 1 A"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751198, -74.03035), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 103"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.7513005, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 4 W")|destino.getText().toString().subSequence(0, 6).equals("DS 3 B"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.down)).position(new LatLng(4.7509335, -74.0304), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 200"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.down)).position(new LatLng(4.7509335, -74.03035), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 201")|destino.getText().toString().subSequence(0, 6).equals("DS 400")|destino.getText().toString().subSequence(0, 6).equals("DS 500"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7509335, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 301")|destino.getText().toString().subSequence(0, 6).equals("DS 401")|destino.getText().toString().subSequence(0, 6).equals("DS 501"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751008, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 202"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751008, -74.03035), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 302")|destino.getText().toString().subSequence(0, 6).equals("DS 402")|destino.getText().toString().subSequence(0, 6).equals("DS 502"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751008, -74.03039), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 303")|destino.getText().toString().subSequence(0, 6).equals("DS 403"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751061, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 503"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751092, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 203")|destino.getText().toString().subSequence(0, 6).equals("DS 1 D"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751061, -74.03035), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 304")|destino.getText().toString().subSequence(0, 6).equals("DS 404")|destino.getText().toString().subSequence(0, 6).equals("DS 504"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751061, -74.03039), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 305")|destino.getText().toString().subSequence(0, 6).equals("DS 405"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751115, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 204"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751115, -74.03035), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 306")|destino.getText().toString().subSequence(0, 6).equals("DS 406")|destino.getText().toString().subSequence(0, 6).equals("DS 505"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751115, -74.03039), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 307")|destino.getText().toString().subSequence(0, 6).equals("DS 407")|destino.getText().toString().subSequence(0, 6).equals("DS 506"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751170, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 205"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751170, -74.03035), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 308")|destino.getText().toString().subSequence(0, 6).equals("DS 408")|destino.getText().toString().subSequence(0, 6).equals("DS 507"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751170, -74.03039), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 309")|destino.getText().toString().subSequence(0, 6).equals("DS 409")|destino.getText().toString().subSequence(0, 6).equals("DS 508"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751226, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 206"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751226, -74.03035), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 310")|destino.getText().toString().subSequence(0, 6).equals("DS 410")|destino.getText().toString().subSequence(0, 6).equals("DS 509"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751226, -74.03039), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 207")|destino.getText().toString().subSequence(0, 6).equals("DS 411")|destino.getText().toString().subSequence(0, 6).equals("DS 511"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.7513005, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 311")|destino.getText().toString().subSequence(0, 6).equals("DS 412")|destino.getText().toString().subSequence(0, 6).equals("DS 512"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751375, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 312")|destino.getText().toString().subSequence(0, 6).equals("DS 413")|destino.getText().toString().subSequence(0, 6).equals("DS 513"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751375, -74.03039), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 313")|destino.getText().toString().subSequence(0, 6).equals("DS 414")|destino.getText().toString().subSequence(0, 6).equals("DS 514"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751416, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 314")|destino.getText().toString().subSequence(0, 6).equals("DS 415")|destino.getText().toString().subSequence(0, 6).equals("DS 515"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751426, -74.03039), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 315")|destino.getText().toString().subSequence(0, 6).equals("DS 416")|destino.getText().toString().subSequence(0, 6).equals("DS 516"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751467, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 316")|destino.getText().toString().subSequence(0, 6).equals("DS 417")|destino.getText().toString().subSequence(0, 6).equals("DS 517"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751467, -74.03039), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 317")|destino.getText().toString().subSequence(0, 6).equals("DS 418")|destino.getText().toString().subSequence(0, 6).equals("DS 518"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.rigth)).position(new LatLng(4.751522, -74.03041), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 318")|destino.getText().toString().subSequence(0, 6).equals("DS 419")|destino.getText().toString().subSequence(0, 6).equals("DS 519"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.left)).position(new LatLng(4.751522, -74.03039), (float) 7.5, 10));
                else if (destino.getText().toString().subSequence(0, 6).equals("DS 4 C")|destino.getText().toString().subSequence(0, 6).equals("DS 5 J"))
                    mar = mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.up)).position(new LatLng(4.751522, -74.0304), (float) 7.5, 10));

            }
            aux=0;
            b=false;
            if (mar != null) mar.setZIndex(1);
            if (polyline != null) polyline.setZIndex(1);
            if (imageOverlay != null) imageOverlay.setZIndex(1);
        } else Toast.makeText(MainActivity2.this, "Datos incompletos", Toast.LENGTH_SHORT).show();
    }
}