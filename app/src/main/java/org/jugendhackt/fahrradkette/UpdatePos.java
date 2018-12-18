package org.jugendhackt.fahrradkette;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static org.jugendhackt.fahrradkette.NewPos.REQUEST_IMAGE_CAPTURE;


public class UpdatePos extends AppCompatActivity {

    double lon = 0;
    double lat = 0;

    ImageView mImageView;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView) findViewById(R.id.UpdatePos_ImageView);

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
        Button add_image = (Button) findViewById(R.id.UpdatePos_add_image);
        TextView updatePos_commentar = (TextView) findViewById(R.id.updatePos_commentar);
        TextView updatePos_Pin = (TextView) findViewById(R.id.updatePos_Pin);
        final TextView updatePos_Pos = (TextView) findViewById(R.id.updatePos_Pos);
        Spinner updatePos_MyBikes = (Spinner) findViewById(R.id.updatePos_myBikes);

        final MainActivity mainActivity = new MainActivity();

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        updatePos_bikes.setAdapter(adapter);*/
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, mainActivity.myBikes(false));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        updatePos_MyBikes.setAdapter(spinnerArrayAdapter);
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

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(bitmap);
        }
    }

}
