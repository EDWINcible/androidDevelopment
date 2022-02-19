package com.example.edwin.mclab4;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    Button btn_locate;
    TextView tv_latitude, tv_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn_locate = (Button) findViewById(R.id.btn_locate);
        tv_latitude = (TextView) findViewById(R.id.tv_latitude);
        tv_longitude = (TextView) findViewById(R.id.tv_longhitud);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        btn_locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSLocator gpsLocator = new GPSLocator(getApplicationContext());
                Location location = gpsLocator.GetLocation();
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    tv_latitude.setText(String.valueOf(latitude));
                    tv_longitude.setText(String.valueOf(longitude));
                    Uri gmmIntentUri = Uri.parse("geo:"+tv_latitude+","+tv_longitude+"?z=21");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);

                }
            }
        });
    }
}