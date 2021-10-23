package com.example.valetparking.Administrator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.valetparking.Database.Interfaces.Administrators;
import com.example.valetparking.Database.Models.Administrator;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfilePlace extends Fragment {

    private static String ID;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

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
    private SupportMapFragment mapFragment;
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        //mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.location_profile_map);
        //mapFragment.getMapAsync(this);

        //Recuperar datos
        retrieveAdministrator();

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
                }
            }

            @Override
            public void onFailure(Call<Administrator> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    //OnMapReady
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //Permisos de ubicacion en tiempo real
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
    }
    */
}