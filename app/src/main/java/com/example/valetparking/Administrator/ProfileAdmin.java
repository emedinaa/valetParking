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
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileAdmin extends Fragment {

    private static  String ID;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileAdmin(String id) {
        ID = id;
    }

    public static ProfileAdmin newInstance(String param1, String param2) {
        ProfileAdmin fragment = new ProfileAdmin(ID);
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

    private TextInputLayout name, phone, email, user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.adm__profile_admin, container, false);

        //Conexion de la parte logica con la grafica
        name = view.findViewById(R.id.admin_profile_name);
        phone = view.findViewById(R.id.admin_profile_telephone);
        email = view.findViewById(R.id.admin_profile_email);
        user = view.findViewById(R.id.admin_profile_username);

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

                    name.getEditText().setText(administrator.getAdminName());
                    phone.getEditText().setText(administrator.getAdminPhone());
                    email.getEditText().setText(administrator.getAdminEmail());
                    user.getEditText().setText(administrator.getAdminUsername());

                }
            }

            @Override
            public void onFailure(Call<Administrator> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}