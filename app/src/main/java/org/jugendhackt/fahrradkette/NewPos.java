package org.jugendhackt.fahrradkette;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewPos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Button add_bike = (Button) findViewById(R.id.add_bike);
        add_bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity maingr = new MainActivity();

                TextView newPos_name = (TextView) findViewById(R.id.newPos_name);
                TextView newPos_lat = (TextView) findViewById(R.id.newPos_lat);
                TextView newPos_lon = (TextView) findViewById(R.id.newPos_lon);
                TextView newPos_money = (TextView) findViewById(R.id.newPos_money);
                TextView newPos_pin = (TextView) findViewById(R.id.newPos_pin);
                //main.newBike(newPos_name.getText().toString(), Double.parseDouble(newPos_lat.getText().toString()), Double.parseDouble(newPos_lon.getText().toString()), Double.parseDouble(newPos_money.getText().toString()), Double.parseDouble(newPos_pin.getText().toString()), "", getApplicationContext());
                maingr.newBike("name", 51.5, 12, 221, 6315, "");
                maingr.newBike("biky", 51.475, 11.975, 1234, 3769, "nice Bike");
            }
        });

        Button add_pos = (Button) findViewById(R.id.add_pos);
        final Context context = this;
        add_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double[] position = new double[2];
                GPSTracking gpsTracking = new GPSTracking(context);
                //TextView newPos_pos = (TextView) findViewById(R.id.newPos_Pos);

                gpsTracking.qps_request_button(context);
                //newPos_Pos(position);
            }
        });
        GPSTracking qps = new GPSTracking(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void newPos_Pos(double[] position) {
        TextView newPos_lat = (TextView) findViewById(R.id.newPos_lat);
        TextView newPos_lon = (TextView) findViewById(R.id.newPos_lon);
        newPos_lat.setText("" + position[0]);
        newPos_lon.setText("" + position[1]);
    }
}
