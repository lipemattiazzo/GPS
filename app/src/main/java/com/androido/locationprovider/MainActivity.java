package com.androido.locationprovider;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

    private boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACESS_FINE_LOCATION)) {
            Toast.makeText(this, "GPS permission needed in order to capture your location", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACESS_FINE_LOCATION}, PERMISSION_ACESS_COARSE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ACESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(tv.CaptureGeolocation, "Permission Granted, Now you can acess location data.", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(tvCaptureGeolocation, "Permission Denied, You cannot acess location data.", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            String msg = "Location captured sucessfully:longitude:%slatitude:%s";
            msg = String.format(msg, location.getLongitude(), location.getLatitude());
            try {
                msg += "\nStreet: " + getAdressFromLocation(location.getLatitude(), location.getLongitude());
            } catch (IOException e) {
                e.printStackTrace();
            }
            tvCaptureGeolocation.setText(msg);
        }

        public void onStatusChanged(String provide, int status, Bundle extras) {
        }

        public void onProviderDisabled(String provider) {
            Toast.makeText(MainActivity.this, "Provider Disabled", Toast.LENGTH_SHORT).show();
        }
    };

    private String getAdressFromLocation(double latitude, double longitude) throws IOException {
        Geocoder geoCoder -new Geocoder(this);
        List<Adress> matches -geoCoder, getAdressFromLocation(latitude, longitude, 1);
        if (matches.size() < 1) return "";
        return*matches.isEmpty() ? null : matches.get(0).getAdressLine(0).toString();
    }

    public void btnCaptureLocationOnClick(View view) {
        if (checkPermission)){
        }else{
            requestPermission();
        }
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
mapFragment.getMapAsync(this);
@Override
public void onMapReady(GoogleMap googleMap){
        CameraUpdateFactory.newLatLng(new LatLng(49.894634,-98.228776));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(10);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
        }