package com.example.valetparking.Administrator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.valetparking.Database.Interfaces.Administrators;
import com.example.valetparking.Database.Models.Administrator;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfilePlace extends Fragment implements OnMapReadyCallback {

    private static String ID;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private FragmentManager supportFragmentManager;

    public ProfilePlace(String id) {
        ID = id;
    }

    public static ProfilePlace newInstance(String param1, String param2) {
        ProfilePlace fragment = new ProfilePlace(ID);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextInputLayout name, type, description, facebook, instagram, twitter, phone;
    private Double lat, lng;
    private SupportMapFragment mapFragment;
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.adm__profile_place, container, false);

        //Conexion de la parte logica con la grafica
        name = view.findViewById(R.id.place_profile_name);
        type = view.findViewById(R.id.place_profile_type);
        description = view.findViewById(R.id.place_profile_description);
        facebook = view.findViewById(R.id.place_profile_facebook);
        instagram = view.findViewById(R.id.place_profile_instagram);
        twitter = view.findViewById(R.id.place_profile_twitter);
        phone = view.findViewById(R.id.place_profile_telephone);

        //Recuperar datos
        retrieveAdministrator();

        mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.location_profile_map);

        return view;
    }

    //Recuperar datos y poblarlos
    private void retrieveAdministrator() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        Call<Administrator> call = retrofit.create(Administrators.class).getAdministrator(ID);

        call.enqueue(new Callback<Administrator>() {
            @Override
            public void onResponse(Call<Administrator> call, Response<Administrator> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    Administrator administrator = response.body();

                    name.getEditText().setText(administrator.getPlaceName());
                    type.getEditText().setText(administrator.getPlaceType());
                    description.getEditText().setText(administrator.getPlaceDescription());
                    facebook.getEditText().setText(administrator.getPlaceFacebook());
                    instagram.getEditText().setText(administrator.getPlaceInstagram());
                    twitter.getEditText().setText(administrator.getPlaceTwitter());
                    phone.getEditText().setText(administrator.getPlacePhone());

                    setLat(Double.parseDouble(administrator.getLatitude()));
                    setLng(Double.parseDouble(administrator.getLongitude()));

                    System.out.println(getLat());
                    System.out.println(getLng());
                }
            }

            @Override
            public void onFailure(Call<Administrator> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void locationMap() {
        LatLng latLng = new LatLng(getLat(), getLng());

        map.addMarker(new MarkerOptions().position(latLng));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(14).bearing(90).tilt(45).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        locationMap();
    }


    //Metodos getter y setter
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}