package com.example.valetparking.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.valetparking.CardView_Adapter;
import com.example.valetparking.CardView_Data;
import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AboutThePlaceActivity extends AppCompatActivity {

    TextInputLayout name, type, description, phone, facebook, instagram, twitter;
    TextInputEditText Name, Description, Phone, Facebook, Instagram, Twitter;
    AutoCompleteTextView Type;
    CountryCodePicker code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log__about_the_place);

        //Conexion de la parte logica con la grafica
        name = findViewById(R.id.about_name);
        type = findViewById(R.id.about_type);
        description = findViewById(R.id.about_description);
        code = findViewById(R.id.about_code);
        phone = findViewById(R.id.about_telephone);
        facebook = findViewById(R.id.about_facebook);
        instagram = findViewById(R.id.about_instagram);
        twitter = findViewById(R.id.about_twitter);

        Name = findViewById(R.id.about_name_edit);
        Description = findViewById(R.id.about_description_edit);
        Phone = findViewById(R.id.about_telephone_edit);
        Facebook = findViewById(R.id.about_facebook_edit);
        Instagram = findViewById(R.id.about_instagram_edit);
        Twitter = findViewById(R.id.about_twitter_edit);

        Type = findViewById(R.id.about_type_edit);

        Button about_button = findViewById(R.id.about_button);

        //Endicon
        type.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPlace().show();
            }
        });

        //Validar para pasar de ventana
        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Name.getText().toString().equals("") ||
                        Type.getText().toString().equals("") ||
                        Description.getText().toString().equals("") ||
                        Phone.getText().toString().equals("")
                ){
                    validateData(v);
                } else {
                    validateData(v);
                    setFields(v);
                }
            }
        });
    }

    //Metodo para setear los campos
    private void setFields(View view) {
        Name.setText("");
        Type.setText("");
        Description.setText("");
        Phone.setText("");

        name.setHelperText(null);
        type.setHelperText(null);
        description.setHelperText(null);
        phone.setHelperText(null);

        name.clearFocus();
        description.clearFocus();
        phone.clearFocus();
        facebook.clearFocus();
        instagram.clearFocus();
        twitter.clearFocus();
    }

    //Metodo de selector de lugar
    private AlertDialog selectPlace(){
        RecyclerView card_recycler_view;
        CardView_Adapter adapter;

        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Obtener el layout inflater
        LayoutInflater inflater = getLayoutInflater();

        //Inflar y establecer el layout para el dialogo
        //Pasar nulo como vista principal porque va en el diseno del dialogo
        View view = inflater.inflate(R.layout.gen__card_view_recycler_view, null);

        //Conexion de la parte logica con la grafica
        card_recycler_view = view.findViewById(R.id.card_recycler_view);

        //Dimensiones del alertDialog
        view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.80));
        view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.80));

        //RecyclerView - Adapter
        card_recycler_view.setHasFixedSize(true);
        card_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        //Llenar data
        List<CardView_Data> data = new ArrayList<>();

        String[] places = getResources().getStringArray(R.array.array_places);
        int[] Places = {R.drawable.icon__place_hospital, R.drawable.icon__place_mall, R.drawable.icon__place_restaurant};

        for (int i = 0; i < places.length; i++) {
            data.add(new CardView_Data(places[i], Places[i]));
        }

        //Asignar adapter
        adapter = new CardView_Adapter(this, data);
        card_recycler_view.setAdapter(adapter);

        //Mostrar alertDialog
        builder.setView(view);
        builder.setCancelable(true);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //Asignar valor seleccionado y cerrar alertDialog
        adapter.setOnClickLister(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                alertDialog.dismiss();
            }
        });

        return alertDialog;
    }

    //Validar nombre
    private boolean validateName(View view, String text_name){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
        if(!pattern.matcher(text_name).matches()){
            name.setHelperText("Invalid name");
            return false;
        } else {
            name.setHelperText(null);
        }
        return true;
    }

    //Validar tipo
    private boolean validateType(View view, String text_type){
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        if(!pattern.matcher(text_type).matches()){
            type.setHelperText("Invalid type");
            return false;
        } else {
            type.setHelperText(null);
        }
        return true;
    }

    //Validar descripcion
    private boolean validateDescription(View view, String text_description){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
        if(!pattern.matcher(text_description).matches()){
            description.setHelperText("Invalid description");
            return false;
        } else {
            description.setHelperText(null);
        }
        return true;
    }

    //Validar telefono
    private boolean validatePhone(View view, String text_phone){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        if(!pattern.matcher(text_phone).matches()){
            phone.setHelperText("Invalid phone");
            return false;
        } else {
            phone.setHelperText(null);
        }
        return true;
    }

    //Validar datos
    private void validateData(View view){
        String Name = name.getEditText().getText().toString();
        String Type = type.getEditText().getText().toString();
        String Description = description.getEditText().getText().toString();
        String Phone = phone.getEditText().getText().toString();

        boolean booleanName = validateName(view, Name);
        boolean booleanType = validateType(view, Type);
        boolean booleanDescription = validateDescription(view, Description);
        boolean booleanPhone = validatePhone(view,Phone);

        if(booleanName & booleanType & booleanDescription & booleanPhone){
            Intent intent = new Intent(AboutThePlaceActivity.this, LocationActivity.class);
            startActivity(intent);
        }
    }
}