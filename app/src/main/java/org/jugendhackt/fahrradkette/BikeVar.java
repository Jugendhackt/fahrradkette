package org.jugendhackt.fahrradkette;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Bikes{
    public int id;
    public String name;
    public String description;
    public double lon;
    public double lat;
    public boolean isMy;

    public Bikes(int id, String name, double lon, double lat, String description, boolean isMy){
        this.id = id;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.description = description;
        this.isMy = isMy;
    }
}

public class BikeVar {

    Context ctx = MainActivity.ctxx;

    AppDatabase db = Room.databaseBuilder(ctx.getApplicationContext(),
            AppDatabase.class, "database-name").build();

    int [] id;
    public Bikes getBikes (int index){
        id[0] = index;

        new Thread(new Runnable() {
            public void run() {
                pBikes = new Bikes(
                        db.bikeDao().loadAllByIds(id).get(0).getUid(),
                        db.bikeDao().loadAllByIds(id).get(0).getName(),
                        db.bikeDao().loadAllByIds(id).get(0).getLon(),
                        db.bikeDao().loadAllByIds(id).get(0).getLat(),
                        db.bikeDao().loadAllByIds(id).get(0).getDescription(),
                        db.bikeDao().loadAllByIds(id).get(0).getIsMy()
                );
            }}).start();

        return pBikes;
    }

    private Bikes pBikes;

    public void setBikes (final Bikes bikes){
        pBikes = bikes;
        new Thread(new Runnable() {
            public void run() {
                Bikes bikes = pBikes;
                BikeDb bikeDb = new BikeDb();
                bikeDb.setLat(bikes.lat);
                bikeDb.setLon(bikes.lon);
                bikeDb.setDescription(bikes.description);
                bikeDb.setName(bikes.name);
                bikeDb.setIsMy(bikes.isMy);
                db.bikeDao().insert(bikeDb);
            }}).start();
    }

    private List<BikeDb> bikeDb;
    public List getAllBikes(){

        Log.i("db", String.valueOf(db.bikeDao().getAll().size()));

        new Thread(new Runnable() {
            public void run() {
                bikeDb = db.bikeDao().getAll();
            }}).start();

        return bikeDb;
    }


    private static final String apiUrl = "https://devtarf.ddns.net/api/bikes/nearby";

    public void apiRest(double lon,double lat, int radius){
        String url = apiUrl;
        RequestQueue queue = Volley.newRequestQueue(ctx);

        Log.d ("api","ApiRestStart");
        Map<String, String> params = new HashMap();
        params.put("lat", Double.toString(lat));
        params.put("lon", Double.toString(lon));
        params.put("radius", Double.toString(radius));

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //mTextView.setText("Response: " + response.toString());
                        Log.i("api","JSON:");
                        Log.i("api",response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e ("api",error.toString());
                    }
                });

// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest);
    }
}