package org.jugendhackt.fahrradkette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class bikeInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_info);
        Intent intent = getIntent();
        TextView name = (TextView) findViewById(R.id.name);
        TextView lat = (TextView) findViewById(R.id.lat);
        TextView lon = (TextView) findViewById(R.id.lon);
        TextView price = (TextView) findViewById(R.id.price);
        Button back = (Button) findViewById(R.id.back);
        Button rent = (Button) findViewById(R.id.rent);

        name.setText(intent.getStringExtra("name")); //if it's a string you stored.
        lat.setText(intent.getStringExtra("lat"));
        lon.setText(intent.getStringExtra("lon"));
        price.setText(intent.getStringExtra("price"));

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
