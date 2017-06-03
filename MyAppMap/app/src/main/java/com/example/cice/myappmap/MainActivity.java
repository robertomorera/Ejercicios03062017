package com.example.cice.myappmap;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {


    private GoogleMap googleMap;


    private void traduceDir(Location location){

        Geocoder geocoder=null;
        Address address=null;
        List<Address> addressList=null;

        if(Geocoder.isPresent()){
            Log.d("MENSAJE","Geocoder OK");
            geocoder=new Geocoder(this);

            try{
                addressList=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                if(addressList.size()>=1){
                    address=addressList.get(0);
                    Log.d("MENSAJE","DIRECCION OBTENIDA "+address);
                }

            }catch(IOException e){
                Log.e("MENSAJE","ERROR obteniendo la dirección",e);
            }
        }else{
            Log.e("MENSAJE","El servicio no está disponible");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Se invoca a este método cuando se carga el mapa.
        this.googleMap=googleMap;
        this.googleMap.setOnMapClickListener(this);
        //Para el tipo de mapa
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        //Cuando toco aqui se hace un callback a este método.
        Log.d("MENSAJE","MAPA TOCADO"+latLng);
        MarkerOptions marcador=new MarkerOptions();
        marcador.position(latLng);
        marcador.title("Aquí");
        marcador.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));

        googleMap.addMarker(marcador);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(20),2000,null);

        Location location=new Location("prueba");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);

        traduceDir(location);
    }


}
