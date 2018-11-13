package org.jugendhackt.fahrradkette;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BikeList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView exampleListView;
    private String[] bikes = new String[] {"Bike - 40m", "Bike1 - 230m", "Alex Altes Bike - 500m", "Mias Fahrrad - 2km"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        exampleListView = (ListView) findViewById(R.id.mobile_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, bikes);
        exampleListView.setAdapter(adapter);
        exampleListView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> lV, View view, int pos, long id) {
        Toast.makeText(this, "Stadt " + bikes[pos] + " ausgewählt!",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
