package org.jugendhackt.fahrradkette;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class UpdatePos extends AppCompatActivity {

    double lon = 0;
    double lat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Button updatePos_finish = (Button) findViewById(R.id.updatePos_finish);
        Button add_pos = (Button) findViewById(R.id.add_pos);
        Button toUpdatePos = (Button) findViewById(R.id.toUpdatePos);
        TextView updatePos_commentar = (TextView) findViewById(R.id.updatePos_commentar);
        TextView updatePos_Pin = (TextView) findViewById(R.id.updatePos_Pin);
        final TextView updatePos_Pos = (TextView) findViewById(R.id.updatePos_Pos);
        Spinner updatePos_bikes = (Spinner) findViewById(R.id.updatePos_bikes);

        String[] arraySpinner = new String[] {
                "1", "2", "3", "4", "5"
        };

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        updatePos_bikes.setAdapter(adapter);*/

        final Context context = this;
        add_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double[] position = new double[2];
                GPSTracking gpsTracking = new GPSTracking(context);
                TextView newPos_pos = (TextView) findViewById(R.id.newPos_Pos);

                position = gpsTracking.qps_request_button(context, 3);
                //newPos_Pos("" + position[0], "" + position[1]);
            }
        });

        updatePos_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lon == 0 && lat == 0){
                    Toast.makeText(context, "Bitte füren sie ihre Position noch hinzu", Toast.LENGTH_SHORT).show();
                }else if(false){
                    Toast.makeText(context, "Bitte tragen sie noch ihren gewünschten Preis und den Namen ihres Fahraden ein", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Irgendwann könnte dieser eintrag jetzt in der Blockchain landen", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        final Intent intent = new Intent(this, NewPos.class);
        toUpdatePos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        updatePos_Pin.setText(randome_code());

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

    public void newPos_Pos(double nlat, double nlon) {
        TextView updatepospos = (TextView) findViewById(R.id.updatePos_Pos);
        updatepospos.setText(nlat + ", " + nlon);
        lat = nlat;
        lon = nlon;

    }

    public String randome_code() {
        String code = "";
        for(int i = 4; i > 0; i--){
            code += (int)(Math.random() * 10) + "";
        }
        return code;
    }

}
