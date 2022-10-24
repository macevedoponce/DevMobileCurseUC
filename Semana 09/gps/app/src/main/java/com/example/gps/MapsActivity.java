package com.example.gps;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Button btnSAtelite, btnhibrido, btnterreno, btnInterior, btnMarcas;
    double mLat, mLong;
    int numPuntos = 0;
    double lat1=0.0, long1=0.0, lat2=0.0, long2=0.0;
    Boolean crearMarcas = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnSAtelite = findViewById(R.id.btnSatelite);
        btnhibrido = findViewById(R.id.btnHibrido);
        btnterreno = findViewById(R.id.btnTerreno);
        btnMarcas = findViewById(R.id.btnMarcas);
        btnInterior = findViewById(R.id.btnInterior);
        btnSAtelite.setOnClickListener(this);
        btnhibrido.setOnClickListener(this);
        btnterreno.setOnClickListener(this);
        btnInterior.setOnClickListener(this);
        btnMarcas.setOnClickListener(this);


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
        LatLng unLugar = new LatLng(-12.048018124646825, -75.19897478678546);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //  mMap.setMyLocationEnabled(true);
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

        mMap.addMarker(new MarkerOptions().position(unLugar).title("Universidad Continental"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unLugar));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            double distancia=0.0;
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                        .title("Punto")
                        .snippet("Yo estuve aquí")
                        .position(latLng).anchor(0.5F,0.5f));
                mLat =latLng.latitude;
                mLong = latLng.longitude;
                Toast.makeText(MapsActivity.this, mLat+ "<>"+ mLong, Toast.LENGTH_SHORT).show();
                if(crearMarcas){

                    if(numPuntos==1){
                        lat2 = mLat;
                        long2 = mLong;
                        distancia = Utils.getDistance(lat1, long1, lat2, long2);
                        numPuntos++;
                        Toast.makeText(MapsActivity.this, "Distancia = "+ distancia, Toast.LENGTH_SHORT).show();
                    }

                    if(numPuntos==0){
                        lat1 = mLat;
                        long1 = mLong;
                        numPuntos++;
                    }

                    if(numPuntos>1){
                        lat1 = lat2;
                        long1 = long2;
                        numPuntos=1;
                    }


                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSatelite:{
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                LatLng laguna= new LatLng(-12.048982129630486, -75.19773337812639);
                mMap.addMarker(new MarkerOptions().position(laguna).title("Parque de la Identidad Huanca"));
                CameraPosition cameraPosition=CameraPosition.builder().target(laguna).zoom(18).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                break;
            }
            case R.id.btnHibrido:{
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                LatLng laguna = new LatLng(-12.040165091412915, -75.19230510320276);
                mMap.addMarker(new MarkerOptions().position(laguna).title("Laguna de no se que"));
                CameraPosition cameraPosition = CameraPosition.builder().target(laguna).zoom(18).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                break;
            }
            case R.id.btnTerreno:{
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                LatLng laguna = new LatLng(-12.05459316031716, -75.1953061763025);
                mMap.addMarker(new MarkerOptions().position(laguna).title("Laguna de no se que"));
                CameraPosition cameraPosition = CameraPosition.builder().target(laguna).zoom(18).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                break;
            }case R.id.btnInterior:{
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                LatLng laguna = new LatLng(40.750572982797635, -73.99334678293765);
                mMap.addMarker(new MarkerOptions().position(laguna).title("Madison Squaere Garden"));
                CameraPosition cameraPosition = CameraPosition.builder().target(laguna).zoom(18).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                break;
            }case R.id.btnMarcas:{
                crearMarcas = !crearMarcas;
                if(crearMarcas){

                }
                break;
            }

        }
    }
}

// TAREAAAAAAAAAAAAAAAAAAAAAAAAAAAA
/*
* direccion de destino = te muestra el mapa
* marca automaicamente en la direccion
* captura latitud, longitud y muestra un activity o notificación que pida los datos adicionales
* Tipo / nombre, descripción del paquete
* guardar todo en firebase
-----------------------------------------------------------
* visualizar la lista de pedidos
* seleccionar un pedido
* mapa con la ubicación del pedido
* snipper con el tipo, nombre y descreipcion
*
* */