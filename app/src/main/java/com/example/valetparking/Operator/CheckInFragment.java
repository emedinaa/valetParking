package com.example.valetparking.Operator;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valetparking.CardView_Adapter;
import com.example.valetparking.CardView_Data;
import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import io.github.muddz.styleabletoast.StyleableToast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckInFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckInFragment newInstance(String param1, String param2) {
        CheckInFragment fragment = new CheckInFragment();
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

    TextInputLayout brand, year, model, color, plate, phone, email, key, vehicle;
    TextInputEditText Plate, Phone, Email, Key, Vehicle;
    AutoCompleteTextView Brand, Year, Model, Color;
    CountryCodePicker code;
    String selectorBrand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflar el Layout con el Fragment
        View view = inflater.inflate(R.layout.op__check_in, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //conexion de la parte logica con la parte grafica
            //TextInputLayout
            brand = view.findViewById(R.id.check_in_brand);
            year = view.findViewById(R.id.check_in_year);
            model = view.findViewById(R.id.check_in_model);
            color = view.findViewById(R.id.check_in_color);
            plate = view.findViewById(R.id.check_in_plate);
            phone = view.findViewById(R.id.check_in_telephone);
            email = view.findViewById(R.id.check_in_email);
            key = view.findViewById(R.id.check_in_key);
            vehicle = view.findViewById(R.id.check_in_vehicle);

            //AutoCompleteTextView
            Brand = view.findViewById(R.id.check_in_brand_edit);
            Year = view.findViewById(R.id.check_in_year_edit);
            Model = view.findViewById(R.id.check_in_model_edit);
            Color = view.findViewById(R.id.check_in_color_edit);

            //TextInputEditText
            Plate = view.findViewById(R.id.check_in_plate_edit);
            Phone = view.findViewById(R.id.check_in_telephone_edit);
            Email = view.findViewById(R.id.check_in_email_edit);
            Key = view.findViewById(R.id.check_in_key_edit);
            Vehicle = view.findViewById(R.id.check_in_vehicle_edit);

            //CountryCodePicker
            code = view.findViewById(R.id.check_in_code);

            //Button
            Button check_in_button = view.findViewById(R.id.check_in_button);

        //Validar para pasar de vista
        check_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Brand.getText().toString().equals("") ||
                    Year.getText().toString().equals("") ||
                    Model.getText().toString().equals("") ||
                    Color.getText().toString().equals("") ||
                    Plate.getText().toString().equals("") ||
                    //Code.getText().toString().equals("") ||
                    Phone.getText().toString().equals("") ||
                    Email.getText().toString().equals("") ||
                    Key.getText().toString().equals("") ||
                    Vehicle.getText().toString().equals("")
                ){
                    validateData(v);
                } else {
                    setFields(v);
                    customDialog().show();
                }
            }
        });

        //Seter marca vacia
        setSelectorBrand("");

        //EndIcons
        brand.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBrand().show();
            }
        });

        year.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectYear().show();
            }
        });

        model.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brand.getEditText().getText().toString().equals("")){
                    StyleableToast.makeText(getContext(),"Debe escoger una marca", Toast.LENGTH_LONG, R.style.toast).show();
                } else {
                    selectModel().show();
                }
            }
        });

        color.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectColor().show();
            }
        });

        return view;
    }

    //Metodo para setear los campos
    private void setFields(View view){
        Brand.setText("");
        Year.setText("");
        Model.setText("");
        Color.setText("");
        Plate.setText("");
        //Code.setText("");
        Phone.setText("");
        Email.setText("");
        Key.setText("");
        Vehicle.setText("");

        brand.setHelperText(null);
        year.setHelperText(null);
        model.setHelperText(null);
        color.setHelperText(null);
        plate.setHelperText(null);
        //code.setHelperText(null);
        phone.setHelperText(null);
        email.setHelperText(null);
        key.setHelperText(null);
        vehicle.setHelperText(null);

        plate.clearFocus();
        phone.clearFocus();
        email.clearFocus();
        key.clearFocus();
        vehicle.clearFocus();
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
        alert_title.setText(getResources().getString(R.string.alert_title_token));
        alert_message.setText(getResources().getString(R.string.alert_message_token));
        alert_image.setBackground(getResources().getDrawable(R.drawable.icon__token));

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
            }
        });

        return alertDialog;
    }

    //Metodo de selectores (Ventana emergente)
        //Brand
        private AlertDialog selectBrand(){
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

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
            card_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            String[] brands = getResources().getStringArray(R.array.array_brands);
            int[] logos = {R.drawable.check__brand_toyota, R.drawable.check__brand_honda};

            for (int i = 0; i < brands.length; i++) {
                data.add(new CardView_Data(brands[i], logos[i]));
            }

            //Asignar adapter
            adapter = new CardView_Adapter(getContext(), data);
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
                    if(model.getEditText().getText().toString().equals("")){
                        brand.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                        setSelectorBrand(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                        alertDialog.dismiss();
                    } else {
                        if(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view().equals(getSelectorBrand())){
                            brand.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            setSelectorBrand(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            alertDialog.dismiss();
                        } else {
                            brand.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            model.getEditText().setText("");
                            setSelectorBrand(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            alertDialog.dismiss();
                        }
                    }

                }
            });

            return alertDialog;
        }

        //Year
        private AlertDialog selectYear(){
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

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
            card_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            String[] years = getResources().getStringArray(R.array.array_years);

            for (int i = 0; i < years.length; i++) {
                data.add(new CardView_Data(years[i], R.drawable.icon__calendar_year_2));
            }

            //Asignar adapter
            adapter = new CardView_Adapter(getContext(), data);
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
                    year.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

        //Model
        private AlertDialog selectModel(){
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            //Obtener el layout inflater
            LayoutInflater inflater = getLayoutInflater();

            //Inflar y establecer el layout para el dialogo
            //Pasar nulo como vista principal porque va en el diseno del dialogo
            View view = inflater.inflate(R.layout.gen__card_view_recycler_view, null);
            View view2 = inflater.inflate(R.layout.op__check_in, null);

            //Conexion de la parte logica con la grafica
            card_recycler_view = view.findViewById(R.id.card_recycler_view);

            //Dimensiones del alertDialog
            view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.80));
            view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.80));

            //RecyclerView - Adapter
            card_recycler_view.setHasFixedSize(true);
            card_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            //Seleccionar con que datos se llenaria
            String[] toyota = getResources().getStringArray(R.array.array_models_toyota);
            String[] honda = getResources().getStringArray(R.array.array_models_honda);

            switch (getSelectorBrand()) {
                case "Toyota":
                    for (String model : toyota) {
                        data.add(new CardView_Data(model, R.drawable.check__brand_toyota));
                    }
                    break;
                case "Honda":
                    for (String model : honda) {
                        data.add(new CardView_Data(model, R.drawable.check__brand_honda));
                    }
                    break;
            }

            //Asignar adapter
            adapter = new CardView_Adapter(getContext(), data);
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
                    model.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

        //Color
        private AlertDialog selectColor(){
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

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
            card_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            String[] colors = getResources().getStringArray(R.array.array_colors);

            for (int i = 0; i < colors.length; i++) {
                data.add(new CardView_Data(colors[i], R.drawable.icon__color));
            }

            //Asignar adapter
            adapter = new CardView_Adapter(getContext(), data);
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
                    color.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

    //Validar marca
    private boolean validateBrand(View view, String text_brand){
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        if(!pattern.matcher(text_brand).matches()){
            brand.setHelperText("Invalid brand");
            return false;
        } else {
            brand.setHelperText(null);
        }
        return true;
    }

    //Validar año
    private boolean validateYear(View view, String text_year){
        Pattern pattern = Pattern.compile("^[0-9]{4}+$");
        if(!pattern.matcher(text_year).matches()){
            year.setHelperText("Invalid year");
            return false;
        } else {
            year.setHelperText(null);
        }
        return true;
    }

    //Validar modelo
    private boolean validateModel(View view, String text_model){
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        if(!pattern.matcher(text_model).matches()){
            model.setHelperText("Invalid model");
            return false;
        } else {
            model.setHelperText(null);
        }
        return true;
    }

    //Validar color
    private boolean validateColor(View view, String text_color){
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        if(!pattern.matcher(text_color).matches()){
            color.setHelperText("Invalid color");
            return false;
        } else {
            color.setHelperText(null);
        }
        return true;
    }

    //Validar placa
    private boolean validatePlate(View view, String text_plate){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if(!pattern.matcher(text_plate).matches()){
            plate.setHelperText("Invalid plate");
            return false;
        } else {
            plate.setHelperText(null);
        }
        return true;
    }

    //Validar telefono
    private boolean validatePhone(View view, String text_phone){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        if(!pattern.matcher(text_phone).matches()){
            phone.setHelperText("Invalid number");
            return  false;
        } else {
            phone.setHelperText(null);
        }
        return true;
    }

    //Validar correo
    private boolean validateEmail(View view, String text_email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]*@[a-zA-Z0-9]*.+$");
        if(!pattern.matcher(text_email).matches()){
            email.setHelperText("Invalid email");
            return false;
        } else {
            email.setHelperText(null);
        }
        return true;
    }

    //Validar llave
    private boolean validateKey(View view, String text_key){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if(!pattern.matcher(text_key).matches()){
            key.setHelperText("Invalid location key");
            return false;
        } else {
            key.setHelperText(null);
        }
        return true;
    }

    //Validar vehiculo
    private boolean validateVehicle(View view, String text_vehicle){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if(!pattern.matcher(text_vehicle).matches()){
            vehicle.setHelperText("Invalid location vehicle");
            return false;
        } else {
            vehicle.setHelperText(null);
        }
        return true;
    }

    //Validar datos
    private void validateData(View view){
        String Brand = brand.getEditText().getText().toString();
        String Year = year.getEditText().getText().toString();
        String Model = model.getEditText().getText().toString();
        String Color = color.getEditText().getText().toString();
        String Plate = plate.getEditText().getText().toString();
        String Phone = phone.getEditText().getText().toString();
        String Email = email.getEditText().getText().toString();
        String Key = key.getEditText().getText().toString();
        String Vehicle = vehicle.getEditText().getText().toString();

        boolean booleanBrand = validateBrand(view, Brand);
        boolean booleanYear = validateYear(view, Year);
        boolean booleanModel = validateModel(view, Model);
        boolean booleanColor = validateColor(view,Color);
        boolean booleanPlate = validatePlate(view, Plate);
        boolean booleanPhone = validatePhone(view,Phone);
        boolean booleanEmail = validateEmail(view,Email);
        boolean booleanKey = validateKey(view, Key);
        boolean booleanVehicle = validateVehicle(view, Vehicle);

        if(booleanBrand & booleanYear & booleanModel & booleanColor & booleanPlate & booleanPhone & booleanEmail & booleanKey & booleanVehicle){
            customDialog().show();
        }
    }


    //Metodos Getter y Setter
    public String getSelectorBrand() {
        return selectorBrand;
    }

    public void setSelectorBrand(String selectorBrand) {
        this.selectorBrand = selectorBrand;
    }
}