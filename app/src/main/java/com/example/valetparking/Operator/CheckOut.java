package com.example.valetparking.Operator;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.valetparking.Database.Interfaces.Vehicles;
import com.example.valetparking.Database.Models.Vehicle;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckOut#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckOut extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckOut() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckOut.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckOut newInstance(String param1, String param2) {
        CheckOut fragment = new CheckOut();
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

    private TextInputLayout brand, model, year, color, plate, phone, email, key, vehiclee;
    private int token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflar el Layout con el Fragment
        View view = inflater.inflate(R.layout.op__check_out, container, false);

        //conexion de la parte logica con  la grafica
            //TextinputLayout
            brand = view.findViewById(R.id.check_in_brand);
            model = view.findViewById(R.id.check_in_model);
            year = view.findViewById(R.id.check_in_year);
            color = view.findViewById(R.id.check_in_color);
            plate = view.findViewById(R.id.check_in_plate);
            phone = view.findViewById(R.id.check_in_telephone);
            email = view.findViewById(R.id.check_in_email);
            key = view.findViewById(R.id.check_in_key);
            vehiclee = view.findViewById(R.id.check_in_vehicle);

            //Button
            Button check_out_button = view.findViewById(R.id.check_out_button);

            //Floating action button
            FloatingActionButton check_out_token = view.findViewById(R.id.check_out_token);

        //Ventana emergente del id
        check_out_token.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idDialog().show();
            }
        });

        //Mensaje del checkout
        check_out_button.setOnClickListener(new View.OnClickListener() {
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
        View view =  inflater.inflate(R.layout.gen__alert_dialog, null);

        //Dimensiones del alertDialog
        view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.40));
        view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.40));

        //Conexion de la parte logica con grafica
        TextView alert_title = view.findViewById(R.id.alert_title);
        TextView alert_message = view.findViewById(R.id.alert_message);
        ImageView alert_image = view.findViewById(R.id.alert_image);
        Button alert_button = view.findViewById(R.id.alert_button);

        //Personalizacion del alertDialog
        alert_title.setText(getResources().getString(R.string.alert_title_exit));
        alert_message.setText(getResources().getString(R.string.alert_message_exit));
        alert_image.setBackground(getResources().getDrawable(R.drawable.alert__exit));

        //Mostrar alertDialog
        builder.setView(view);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Cerrar alertDialog
        alert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        return alertDialog;
    }

    //Metodo del alertDialog (Mensaje emergente)
    private AlertDialog idDialog(){
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Obtener el layout inflater
        LayoutInflater inflater = getLayoutInflater();

        //Inflar y establecer el layout para el dialogo
        //Pasar nulo como vista principal porque va en el diseno del dialogo
        View view = inflater.inflate(R.layout.gen__alert_dialog_id, null);

        //Dimensiones del alertDialog
        view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.30));
        view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.30));

        //Conexion de la parte logica con grafica
        TextInputLayout id = view.findViewById(R.id.alert_id);
        Button alert_id_button = view.findViewById(R.id.alert_id_button);

        //RequestFocus
        id.requestFocus();

        //Mostrar alertDialog
        builder.setView(view);
        builder.setCancelable(true);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //Cerrar alertDialog
        alert_id_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getEditText().getText().toString().length() != 5){
                    Toast.makeText(getContext(), "Debe ingresar el token id", Toast.LENGTH_SHORT).show();
                } else {
                    token = Integer.parseInt(id.getEditText().getText().toString());

                    Retrofit retrofit = RetrofitClient.getRetrofitClient();

                    Call<Vehicle> call = retrofit.create(Vehicles.class).getCloseVehicle(token);

                    call.enqueue(new Callback<Vehicle>() {
                        @Override
                        public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            } else {
                                Vehicle vehicle = response.body();

                                brand.getEditText().setText(vehicle.getBrand());
                                model.getEditText().setText(vehicle.getModel());
                                year.getEditText().setText(vehicle.getYear());
                                color.getEditText().setText(vehicle.getColor());
                                plate.getEditText().setText(vehicle.getPlate());
                                phone.getEditText().setText(vehicle.getPhone());
                                email.getEditText().setText(vehicle.getEmail());
                                key.getEditText().setText(vehicle.getKey());
                                vehiclee.getEditText().setText(vehicle.getVehicle());

                                alertDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<Vehicle> call, Throwable t) {
                            Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return alertDialog;
    }
}