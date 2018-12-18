package org.jugendhackt.fahrradkette;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.Manifest;

import android.content.Context;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    MapView map = null;
    GPSTracking qps;

    public static ArrayList<BikeDb> bikeList = new ArrayList<BikeDb>();

    double latPos = 51.47931;
    double lonPos = 11.99317;


    MyLocationNewOverlay mLocationOverlay;
    public static Context ctxx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctxx = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Speicher berechtigung Abfrage (Speicher wird für Map benötigt)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "True",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "False",
                    Toast.LENGTH_SHORT).show();

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        //Log.d("FK","ES GEHT WEITER");

        qps = new GPSTracking(this);
        final Context contex = this;

        /*Log.i("db","Thread start");
        new Thread(new Runnable() {
            public void run() {
                Log.i("db","Die Datenbank Info ist: --");
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").build();
                BikeDb bikeDb = new BikeDb();
                bikeDb.setUid(200);
                bikeDb.setName("alex");
                bikeDb.setLat(latPos);
                bikeDb.setLon(lonPos);
                bikeDb.setDescription("Ein sehr geiles Bike");
                bikeDb.setIsMy(true);
                db.bikeDao().insert(bikeDb);
                Log.i("db","Die Datenbank Info ist:");
                Log.i("db",String.valueOf(db.bikeDao().getAll().size()));
                db.close();
            }});*/

        BikeVar bikeVar = new BikeVar();
        Bikes bikes = new Bikes(2000,"alex",latPos,lonPos,"ist geil",true);

        bikeVar.setBikes(bikes);

        //Log.i("dbi", String.valueOf(bikeVar.getAllBikes().size()));
        //for (int i = 0;i<bikeVar.getAllBikes().size();i++)
        //    Log.i("db",bikeVar.getAllBikes().get(i).toString());


        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent intent = new Intent(this, UpdatePos.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        FloatingActionButton mapCenter = (FloatingActionButton) findViewById(R.id.mapCenter);
        mapCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qps.qps_request_button(contex, 1);
            }
        });

        qps.qps_request_button(contex, 1);
        map = (MapView) findViewById(R.id.map);

        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        //items.add(new OverlayItem("Bike", "Price: 1.000.000€", new GeoPoint(51.465,11.985))); // Lat/Lon decimal degrees
        newBike("Weißes Fahrrad",51.465,11.985, "Ein Sehr schönes Fahrad","Herr Müller",400, this);
        newBike("Dunkles Fahrrad",51.460,11.980, "Ein normales Fahrad2","Herr Müller",400, this);
        newBike("Rotes Fahrrad",51.470,11.990, "Ein Sehr schlechtes Fahrad3","Herr Müller",400, this);

        final int bikeId = 0;
        //the overlay
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {

                        return true;
                    }
                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        Intent myIntent = new Intent(MainActivity.this, bikeInfo.class);
                        myIntent.putExtra("bikeId", 0); //Optional parameters
                        MainActivity.this.startActivity(myIntent);
                        return false;
                    }
                }, ctx);
        mOverlay.setFocusItemsOnTap(true);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").build();
                BikeDb bikeDb = new BikeDb();
                bikeDb.setLat(0.3);
                bikeDb.setLon(1);
                bikeDb.setDescription("Geiles bike, oder?");
                bikeDb.setOwner("Alex");
                bikeDb.setName("Gelbes Fahrad");
                db.bikeDao().insert(bikeDb);
                Log.i("db",db.bikeDao().getAll().get(0).getName().toString());
            }
        }).start();*/

        map.getOverlays().add(mOverlay);
        newBike("biky", 51.475, 11.975,  "nice Bike","ICH",123 ,ctx);
    }

    private void test(String s){
        Log.i("dbin",s);
    }

    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }
    public void onPause(){
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Info.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.alexList) {
            Intent intent = new Intent(this, BikeList.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.myBikes) {
            Intent intent = new Intent(this, MyBikes.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newBike(final String name, final double lat, final double lon, final String description, final String owner, final int distance, Context ctx){

        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        items.add(new OverlayItem(name, description, new GeoPoint(lat,lon))); // Lat/Lon decimal degrees
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {

                        return true;
                    }
                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        Intent myIntent = new Intent(MainActivity.this, bikeInfo.class);
                        myIntent.putExtra("name", "Name: " + name); //Optional parameters
                        myIntent.putExtra("lat", "Latitude: " + lat);
                        myIntent.putExtra("lon", "Longitude:" + lon);
                        myIntent.putExtra("distance", "Enfernung: " + distance);
                        myIntent.putExtra("description", "Beschreibung: " + description);
                        myIntent.putExtra("owner","Eigentümer: " + owner);
                        MainActivity.this.startActivity(myIntent);
                        return false;
                    }
                }, ctx);
        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
    }

    public void mapCenter(double lat, double lon) {
        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(16);
        this.mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this),map);
        this.mLocationOverlay.enableMyLocation();
        map.getOverlays().add(this.mLocationOverlay);
        GeoPoint startPoint = new GeoPoint(lat, lon);
        mapController.setCenter(startPoint);
    }

    public String[] myBikes (boolean ImOwner){
        String myBikes [] = {"Altes Bike", "Neues Bike", "RostEsel"};
        return myBikes;
    }
}