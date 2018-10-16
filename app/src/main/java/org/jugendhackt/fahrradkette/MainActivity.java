package org.jugendhackt.fahrradkette;

import android.app.DownloadManager;
import android.content.Intent;
import android.Manifest;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    MapView map = null;
    GPSTracking qps;

    public static ArrayList<Bikes> bikeList = new ArrayList<Bikes>();

    double latPos = 51.47931;
    double lonPos = 11.99317;
    double latBike0 = 51.48991;
    double lonBike0 = 12.015;
    double latBike1 = 51.46951;
    double lonBike1 = 11.98287;
    double latBike2 = 51.48991;
    double lonBike2 = 11.98257;
    double latBike3 = 51.46971;
    double lonBike3 = 12.01;


    MyLocationNewOverlay mLocationOverlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
        Log.d("FK","ES GEHT WEITER");

        qps = new GPSTracking(this);
        final Context contex = this;

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

        for (int i = 4;i>0;i--){
            Bikes bikes = new Bikes();
            bikeList.add(bikes);
        }

        for(int i = bikeList.size(); i >0; i--){

        }

        qps.qps_request_button(contex, 1);
        map = (MapView) findViewById(R.id.map);

        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        items.add(new OverlayItem("Bike", "Price: 1.000.000€", new GeoPoint(51.465,11.985))); // Lat/Lon decimal degrees
        apiRest(lonPos,latPos,900000000);
        int bikeId = 0;
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

        map.getOverlays().add(mOverlay);
        newBike("biky", 51.475, 11.975, 1234, "nice Bike", ctx);
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
            Intent intent = new Intent(this, List.class);
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

    public void newBike(final String name, final double lat, final double lon, final double price, final String description, Context ctx){
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        items.add(new OverlayItem(name, "" + price, new GeoPoint(lat,lon))); // Lat/Lon decimal degrees
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
                        myIntent.putExtra("price", "Preis: " + price);
                        myIntent.putExtra("description", description);
                        MainActivity.this.startActivity(myIntent);
                        return false;
                    }
                }, ctx);
        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
    }

    private static final String apiUrl =
            "http://tarf.ddns.net:8545/api/bikes/nearby";

    public static JSONObject restApi(Context context, double lat, double lon){
        try {
            URL url = new URL(String.format(apiUrl, lat,lon));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            //connection.addRequestProperty("x-api-key",context.getString(R.string.open_weather_maps_app_id));

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                Log.e("FK","FEHLER");
                return null;
            }

            return data;
        }catch(Exception e){
            Log.e("FK",e.toString());
            return null;
        }
    }

    public void apiRest(double lon,double lat, int radius){
        String url = apiUrl;
        RequestQueue queue = Volley.newRequestQueue(this);

        Map<String, String> params = new HashMap();
        params.put("lat", Double.toString(lat));
        params.put("lon", Double.toString(lon));

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //mTextView.setText("Response: " + response.toString());
                        Log.e("FKK",response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest);
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
}
