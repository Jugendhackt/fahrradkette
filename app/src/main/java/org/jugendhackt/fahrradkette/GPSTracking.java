package org.jugendhackt.fahrradkette;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.constraint.Constraints.TAG;

public class GPSTracking implements LocationListener {
    public double[] position = new double[2];
    private NewPos newPos;
    private MainActivity mainActivity;
    private Context context;
    private LocationManager locationManager;

    public GPSTracking(Context context){
        this.context = context;
        Activity activity = (Activity)context;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Berechtigung gewährt
            trackingAllowed();
        }else{
            // Berechtigung NICHT gewährt
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // Berechtigung gewährt
                trackingAllowed();
            }else{
                // Berechtigung NICHT gewährt
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    private void trackingAllowed() {
        ContextCompat.checkSelfPermission(context,"android.permission.ACCESS_FINE_LOCATION");
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        /* CAL METHOD requestLocationUpdates */

        // Parameters :
        //   First(provider)    :  the name of the provider with which to register
        //   Second(minTime)    :  the minimum time interval for notifications,
        //                         in milliseconds. This field is only used as a hint
        //                         to conserve power, and actual time between location
        //                         updates may be greater or lesser than this value.
        //   Third(minDistance) :  the minimum distance interval for notifications, in meters
        //   Fourth(listener)   :  a {#link LocationListener} whose onLocationChanged(Location)
        //                         method will be called for each location update

    //    locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
    //            1,   // 3 sec
    //            0, this);

        /********* After registration onLocationChanged method  ********/
        /********* called periodically after each 3 sec ***********/


    }


    @Override
    public void onLocationChanged(Location location) {

        String str = "Latitude: "+location.getLatitude()+"Longitude: "+location.getLongitude();
        position[0] = location.getLatitude();
        position[1] = location.getLongitude();
        String posstr = position[0] + " | " + position[1];
        newPos.newPos_Pos(position[0],position[1]);
        mainActivity.mapCenter(position[0],position[1]);
        //Toast.makeText(context, str, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String provider) {

        /******** Called when User off Gps *********/

        Toast.makeText(context, "Gps turned off ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

        /******** Called when User on Gps  *********/

        Toast.makeText(context, "Gps turned on ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    public double[] qps_request_button(final Context newposcontext, final int site) {
        String srue = "Singel Update Location";
        Intent updateIntent = new Intent(srue);
        final PendingIntent singleUpatePI = PendingIntent.getBroadcast(context, 0, updateIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG,"onReceive");
                //unregister the receiver so that the application does not keep listening the broadcast even after the broadcast is received.
                context.unregisterReceiver(this);

                // get the location from the intent send in broadcast using the key - this step is very very important
                String key = LocationManager.KEY_LOCATION_CHANGED;
                Location location = (Location)intent.getExtras().get(key);

                // Call the function required
                if (location != null && site == 1) {
                    mainActivity = (MainActivity) newposcontext;
                    //onLocationChanged(location);
                    String str = "Latitude: "+location.getLatitude()+"Longitude: "+location.getLongitude();
                    position[0] = location.getLatitude();
                    position[1] = location.getLongitude();
                    mainActivity.mapCenter(position[0],position[1]);
                    //newPos.newPos_Pos(position[0] + " | " + position[1]);

                }

                if (location != null && site == 2) {
                    newPos = (NewPos) newposcontext;
                    //onLocationChanged(location);
                    String str = "Latitude: "+location.getLatitude()+"Longitude: "+location.getLongitude();
                    position[0] = location.getLatitude();
                    position[1] = location.getLongitude();
                    newPos.newPos_Pos(position[0],position[1]);
                    //newPos.newPos_Pos(position[0] + " | " + position[1]);

                }

                // finally remove the updates for the pending intent
                locationManager.removeUpdates(singleUpatePI);
            }
        };
        context.registerReceiver(br, new IntentFilter(srue));
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_LOW);
        ContextCompat.checkSelfPermission(context,"android.permission.ACCESS_FINE_LOCATION");
        locationManager.requestSingleUpdate(criteria, singleUpatePI);
        return(position);
    }
}
