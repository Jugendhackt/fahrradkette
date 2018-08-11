package org.jugendhackt.fahrradkette;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class GPSTracking implements LocationListener {

    private Context context;
    private LocationManager locationManager;

    public GPSTracking(Context context){
        this.context = context;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Berechtigung gewährt
            trackingAllowed();
        }else{
            // Berechtigung NICHT gewährt

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


        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                1000,   // 3 sec
                1, this);

        /********* After registration onLocationChanged method  ********/
        /********* called periodically after each 3 sec ***********/


    }


    @Override
    public void onLocationChanged(Location location) {

        String str = "Latitude: "+location.getLatitude()+"Longitude: "+location.getLongitude();

        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
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
}
