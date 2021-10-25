package com.example.valetparking.Administrator;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.valetparking.R;

import androidx.fragment.app.Fragment;

public class CancelVehicle extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String brand,  year,  model,  color,  plate, phone, email,  key,  vehicle;
    private String mParam1;
    private String mParam2;

    public CancelVehicle() {
        // Required empty public constructor
    }

    public static CancelVehicle newInstance(String param1, String param2) {
        CancelVehicle fragment = new CancelVehicle();
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

    Button cancel_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.adm__cancel_vehicle, container, false);

        //Conexion de la parte logica con la parte grafica
        cancel_button = view.findViewById(R.id.cancel_button);

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog().show();
            }
        });

        return view;
    }

    //Metodo del alertDialog (Mensaje emergente)
    private AlertDialog customDialog(){
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Obtener el layout inflater
        LayoutInflater inflater = getLayoutInflater();

        //Inflar y establecer el layout para el dialogo
        //Pasar nulo como vista principal porque va en el diseno del dialogo
        View view =  inflater.inflate(R.layout.adm__cancel_vehicle_alert_dialog, null);

        //Dimensiones del alertDialog
        view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.40));
        view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.40));

        //Conexion de la parte logica con grafica
        Button cancel_alert_button = view.findViewById(R.id.cancel_alert_button);

        //Mostrar alertDialog
        builder.setView(view);
        builder.setCancelable(true);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Cerrar alertDialog
        cancel_alert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        return alertDialog;
    }

    //Obtener la data del vehiculo
    public void getData(String brand, String year, String model, String color, String plate, String phone, String email, String key, String vehicle){
        setBrand(brand);
        setYear(year);
        setModel(model);
        setColor(color);
        setPlate(plate);
        setPhone(phone);
        setEmail(email);
        setKey(key);
        setVehicle(vehicle);

        Toast.makeText(getContext(), "Plate: " + getPlate(), Toast.LENGTH_SHORT).show();
    }

    //Metodos getter y setter
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}