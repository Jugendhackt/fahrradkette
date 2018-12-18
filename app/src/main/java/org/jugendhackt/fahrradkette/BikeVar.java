package org.jugendhackt.fahrradkette;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import java.util.List;

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

    public Bikes getBikes (int index){
        int [] id = {index};

        Bikes bikes = new Bikes(
                db.bikeDao().loadAllByIds(id).get(0).getUid(),
                db.bikeDao().loadAllByIds(id).get(0).getName(),
                db.bikeDao().loadAllByIds(id).get(0).getLon(),
                db.bikeDao().loadAllByIds(id).get(0).getLat(),
                db.bikeDao().loadAllByIds(id).get(0).getDescription(),
                db.bikeDao().loadAllByIds(id).get(0).getIsMy()
        );

        return bikes;
    }

    public void setBikes (Bikes bikes){

        BikeDb bikeDb = new BikeDb();
        bikeDb.setUid(bikes.id);
        bikeDb.setLat(bikes.lat);
        bikeDb.setLon(bikes.lon);
        bikeDb.setDescription(bikes.description);
        bikeDb.setName(bikes.name);
        bikeDb.setIsMy(bikes.isMy);
        db.bikeDao().insert(bikeDb);
    }

    public List getAllBikes(){

        Log.i("db", String.valueOf(db.bikeDao().getAll().size()));
        List<BikeDb> bikeDao;
        bikeDao = db.bikeDao().getAll();

        return bikeDao;
    }



    private static final String apiUrl = "https://tarf.ddns.net:8545/api/bikes/1";

    public void apiRest(double lon,double lat, int radius){
        /*String url = apiUrl;
        RequestQueue queue = Volley.newRequestQueue(this);

        Log.d ("api","ApiRestStart");
        Map<String, String> params = new HashMap();
        params.put("lat", Double.toString(lat));
        params.put("lon", Double.toString(lon));

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
        queue.add(jsonObjectRequest);*/
    }
}
