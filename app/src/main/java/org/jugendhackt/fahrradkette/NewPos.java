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

        Button add_pos = (Button) findViewById(R.id.add_pos);
        final Context context = this;
        add_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double[] position = new double[2];
                GPSTracking gpsTracking = new GPSTracking(context);
                TextView newPos_pos = (TextView) findViewById(R.id.newPos_Pos);

                position = gpsTracking.qps_request_button(context);
                //newPos_Pos("" + position[0], "" + position[1]);
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

    public void newPos_Pos(double lat, double lon) {
        TextView newPospos = (TextView) findViewById(R.id.newPos_Pos);
        newPospos.setText(lat + ", " + lon);

    }
}
