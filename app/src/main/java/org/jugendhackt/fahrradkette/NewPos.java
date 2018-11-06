package org.jugendhackt.fahrradkette;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NewPos extends AppCompatActivity{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    double lon = 0;
    double lat = 0;
    Bitmap bitmap = null;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView) findViewById(R.id.mImageView);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final Button newPos_finish = (Button) findViewById(R.id.newPos_finish);
        Button add_pos = (Button) findViewById(R.id.add_pos);
        Button add_image = (Button) findViewById(R.id.add_image);
        final TextView newPos_name = (TextView) findViewById(R.id.newPos_name);
        final TextView newPos_money = (TextView) findViewById(R.id.newPos_money);
        TextView newPos_Pin = (TextView) findViewById(R.id.newPos_Pin);
        final TextView newPos_specialities = (TextView) findViewById(R.id.newPos_specialities);

        newPos_name.setText("");
        newPos_money.setText("");
        newPos_specialities.setText("");

        final Context context = this;
        add_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double[] position = new double[2];
                GPSTracking gpsTracking = new GPSTracking(context);
                TextView newPos_pos = (TextView) findViewById(R.id.newPos_Pos);

                position = gpsTracking.qps_request_button(context, 2);

                //dispatchTakePictureIntent();
                //newPos_Pos("" + position[0], "" + position[1]);
            }
        });

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        newPos_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lon == 0 && lat == 0 && bitmap == null){
                    Toast.makeText(context, "Bitte füren sie noch ihre Position und ein Bild hinzu", Toast.LENGTH_SHORT).show();
                }else if(newPos_name.getText().equals("") && newPos_money.getText().equals("")){
                    Toast.makeText(context, "Bitte tragen sie noch ihren gewünschten Preis und den Namen ihres Fahraden ein", Toast.LENGTH_SHORT).show();
                    }else {
                    Toast.makeText(context, "Irgendwann könnte dieser eintrag jetzt in der Blockchain landen", Toast.LENGTH_SHORT).show();

                    //String newBike = "{name:"+bikes.name+",lat:"+bikes.lat+",lon:"+bikes.lon+",specialities:"+bikes.description+"}";
                    //MainActivity mainActivity = new MainActivity();
                    //mainActivity.newBikeAufrufen(newPos_name.getText().toString(), lon, lat, newPos_specialities.getText().toString(),"MyUserName", 900);

                    /*URL url = null;
                    try {
                        Toast.makeText(context, "Senden zum server", Toast.LENGTH_SHORT).show();
                        url = new URL("http://tarf.ddns.net:8545");
                        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                        httpCon.setDoOutput(true);
                        httpCon.setRequestMethod("GET");
                        OutputStreamWriter out = new OutputStreamWriter(
                                httpCon.getOutputStream());
                        out.write(newBike);
                        out.close();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        Toast.makeText(context, "Falsche serveradresse", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Toast.makeText(context, "Verbindung Fehlgeschlagen", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }*/

                    finish();
                }
            }
        });

        TextView newPospos = (TextView) findViewById(R.id.newPos_Pin);
        newPospos.setText(randome_code());
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
        TextView newPospos = (TextView) findViewById(R.id.newPos_Pos);
        newPospos.setText(nlat + ", " + nlon);
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
