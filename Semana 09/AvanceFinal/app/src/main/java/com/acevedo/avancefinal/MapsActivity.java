package com.acevedo.avancefinal;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.acevedo.avancefinal.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    double mLat, mLong;

//    int numPuntos = 0;
//    double lat1=0.0, long1=0.0, lat2=0.0, long2=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng UniversidadContinental = new LatLng(-12.047656897248576, -75.1987017818722);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);

        }
        else{
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "Dio permiso para utilizar su ubicacion", Toast.LENGTH_SHORT).show();
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }


        }
        mMap.addMarker(new MarkerOptions().position(UniversidadContinental).title("Universidad Continental"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UniversidadContinental));
        CameraPosition cameraPosition=CameraPosition.builder().target(UniversidadContinental).zoom(18).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.point_2))
                        .title("Ubicación")
                        .snippet("algo eiste por aqui")
                        .position(latLng)
                        .anchor(0.5F, 0.5f)
                );
                mLat = latLng.latitude;
                mLong = latLng.longitude;
                Toast.makeText(MapsActivity.this, mLat+"<>"+mLong, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MapsActivity.this, RegistrarPedido.class);
                i.putExtra("latitud", mLat);
                i.putExtra("longitud", mLong);
                startActivity(i);
//                if(numPuntos==1){
//                    lat2 = mLat; //nuevo punto
//                    long2 = mLong; //nuevo punto
//                    Intent i = new Intent(MapsActivity.this, RegistrarPedido.class);
//                    i.putExtra("latitud", mLat);
//                    i.putExtra("longitud", mLong);
//                    startActivity(i);
//                    numPuntos++;
//                }
//
//                if(numPuntos==0){
//                    lat1 = mLat;
//                    long1 = mLong;
//                    numPuntos++;
//                }
//
//                if(numPuntos>1){
//                    lat1 = lat2;
//                    long1 = long2;
//                    numPuntos=1;
//                }
            }
        });
    }
}