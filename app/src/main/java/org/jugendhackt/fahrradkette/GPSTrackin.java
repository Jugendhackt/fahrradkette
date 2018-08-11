package org.jugendhackt.fahrradkette;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class GPSTrackin {
    public class GpsBasicsAndroidExample extends Activity implements LocationListener {

        private LocationManager locationManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            ContextCompat.checkSelfPermission(this,"android.permission.ACCESS_FINE_LOCATION");

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            /********** get Gps location service LocationManager object ***********/
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

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
                    1,   //millikunden
                    0, this);

            /********* After registration onLocationChanged method  ********/
            /********* called periodically after each 3 sec ***********/
        }

        /************* Called after each 3 sec **********/
        @Override
        public void onLocationChanged(Location location) {

            String str = "Breite: "+location.getLatitude()+" Länge: "+location.getLongitude();

            Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderDisabled(String provider) {

            /******** Called when User off Gps *********/

            Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderEnabled(String provider) {

            /******** Called when User on Gps  *********/

            Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
    }
}