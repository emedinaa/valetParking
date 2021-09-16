package com.example.valetparking.Administrator;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.valetparking.R;
import com.example.valetparking.TabLayoutAdministratorProfile;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    ScrollView profile_admin_view, profile_place_view;
    RelativeLayout profile_location_view;
    Button button;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm__profile);

        //conexion de la parte logica con la grafica
        profile_admin_view = findViewById(R.id.profile_admin_view);
        profile_place_view = findViewById(R.id.profile_place_view);
        profile_location_view = findViewById(R.id.profile_location_view);
        button = findViewById(R.id.profile_button);

        //Visualizar primera vista
        profile_admin_view.setVisibility(View.VISIBLE);

        //Condicionales
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0){
                    profile_admin_view.setVisibility(View.INVISIBLE);
                    profile_place_view.setVisibility(View.VISIBLE);
                    profile_location_view.setVisibility(View.INVISIBLE);

                    count++;
                } else if(count == 1){
                    profile_admin_view.setVisibility(View.INVISIBLE);
                    profile_place_view.setVisibility(View.INVISIBLE);
                    profile_location_view.setVisibility(View.VISIBLE);
                    button.setText(R.string.button_update);

                    count++;
                } else if(count == 2 ){
                    customDialog().show();

                    count = 0;
                }
            }
        });
    }

    //Metodo del alertDialog (Mensaje emergente)
    private AlertDialog customDialog(){
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

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
        alert_title.setText(getResources().getString(R.string.alert_title_update));
        alert_message.setText(getResources().getString(R.string.alert_message_update));
        alert_image.setBackground(getResources().getDrawable(R.drawable.main__ic_launcher_foreground));

        //Mostrar alertDialog
        builder.setView(view);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //Cerrar alertDialog
        alert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                Intent intent = new Intent(Profile.this, TabLayoutAdministratorProfile.class);
                startActivity(intent);
            }
        });

        return alertDialog;
    }
}