package com.example.valetparking.Administrator;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valetparking.Database.Interfaces.Operators;
import com.example.valetparking.Database.Models.Operator;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.regex.Pattern;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateAccountOperator extends Fragment {

    private static String ID;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CreateAccountOperator(String id) {
        ID = id;
    }

    public static CreateAccountOperator newInstance(String param1, String param2) {
        CreateAccountOperator fragment = new CreateAccountOperator(ID);
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

    private TextInputLayout name, phone, email, user, password, confirm_password;
    private String NameS, PhoneS, EmailS, UserS, PasswordS, Confirm_passwordS;
    private CountryCodePicker code;
    private Button create_operator_account_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.adm__operators_create_account, container, false);

        //Conexion de la parte logica con la grafica
        name = view.findViewById(R.id.create_account_operator_name);
        code = view.findViewById(R.id.create_account_operator_code);
        phone = view.findViewById(R.id.create_account_operator_telephone);
        email = view.findViewById(R.id.create_account_operator_email);
        user = view.findViewById(R.id.create_account_operator_username);
        password = view.findViewById(R.id.create_account_operator_password);
        confirm_password = view.findViewById(R.id.create_account_operator_confirm_password);

        create_operator_account_button = view.findViewById(R.id.create_account_operator_button);

        //Validar la data para pasar de ventana
        create_operator_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData(v);
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

        //Cambio de tamaño de letra
        alert_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        alert_message.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        //Personalizacion del alertDialog
        alert_title.setText(getResources().getString(R.string.alert_title_register));
        alert_message.setText(getResources().getString(R.string.alert_message_register));
        alert_image.setBackground(getResources().getDrawable(R.drawable.alert__register));

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
                setFields(v);
            }
        });

        return alertDialog;
    }

    //Metodo para setear los campos
    private void setFields(View view){
        name.getEditText().setText("");
        phone.getEditText().setText("");
        email.getEditText().setText("");
        user.getEditText().setText("");
        password.getEditText().setText("");
        confirm_password.getEditText().setText("");

        name.setHelperText(null);
        phone.setHelperText(null);
        email.setHelperText(null);
        user.setHelperText(null);
        password.setHelperText(null);
        confirm_password.setHelperText(null);
        email.setHelperText(null);

        name.clearFocus();
        phone.clearFocus();
        email.clearFocus();
        user.clearFocus();
        password.clearFocus();
        confirm_password.clearFocus();
    }

    //Validar nombre
    private boolean validateName(View view, String text_name){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        if(!pattern.matcher(text_name).matches()){
            name.setHelperText("Invalid name");
            return false;
        } else {
            name.setHelperText(null);
        }
        return true;
    }

    //Validar telefono
    private boolean validatePhone(View view, String text_phone){
        Pattern pattern = Pattern.compile("^[0-9+]+$");
        if(!pattern.matcher(text_phone).matches()){
            phone.setHelperText("Invalid phone");
            return false;
        } else {
            phone.setHelperText(null);
        }
        return true;
    }

    //validar email
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

    //Validar usuario
    private boolean validateUser(View view, String text_user){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if(!pattern.matcher(text_user).matches()){
            user.setHelperText("Invalid user");
            return false;
        } else {
            user.setHelperText(null);
        }
        return true;
    }

    //Validar clave
    private boolean validatePassword(View view, String text_password){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if(!pattern.matcher(text_password).matches()){
            password.setHelperText("Invalid password");
            return false;
        } else {
            password.setHelperText(null);
        }
        return true;
    }

    //Recuperar datos
    private Operator retrieveOperator() {
        Operator operator = new Operator();

        operator.setName(getNameS());
        operator.setPhone(getPhoneS());
        operator.setEmail(getEmailS());
        operator.setUsername(getUserS());
        operator.setPassword(getPasswordS());

        return operator;
    }

    //Guardar datos
    private void registerOperator(Operator operator) {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        Call<Operator> call = retrofit.create(Operators.class).createOperator(operator, ID);

        call.enqueue(new Callback<Operator>() {
            @Override
            public void onResponse(Call<Operator> call, Response<Operator> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    customDialog().show();
                }
            }

            @Override
            public void onFailure(Call<Operator> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Validar datos
    private void validateData(View view){
        setNameS(name.getEditText().getText().toString());
        setPhoneS(phone.getEditText().getText().toString());
        setEmailS(email.getEditText().getText().toString());
        setUserS(user.getEditText().getText().toString());
        setPasswordS(password.getEditText().getText().toString());
        setConfirm_passwordS(confirm_password.getEditText().getText().toString());

        boolean booleanName = validateName(view, getNameS());
        boolean booleanPhone = validatePhone(view, getPhoneS());
        boolean booleanEmail = validateEmail(view, getEmailS());
        boolean booleanUser = validateUser(view, getUserS());
        boolean booleanPassword = validatePassword(view, getPasswordS());

        setPhoneS(code.getSelectedCountryCodeWithPlus() + getPhoneS());

        if(booleanPassword) {
            if(getPasswordS().equals(getConfirm_passwordS())) {
                if(booleanName & booleanPhone & booleanEmail & booleanUser){
                    registerOperator(retrieveOperator());
                    confirm_password.setHelperText(null);
                }
            } else {
                confirm_password.setHelperText("Invalid password");
            }
        }
    }

    //Metodos getter y setter
    public String getNameS() {
        return NameS;
    }

    public void setNameS(String nameS) {
        NameS = nameS;
    }

    public String getPhoneS() {
        return PhoneS;
    }

    public void setPhoneS(String phoneS) {
        PhoneS = phoneS;
    }

    public String getEmailS() {
        return EmailS;
    }

    public void setEmailS(String emailS) {
        EmailS = emailS;
    }

    public String getUserS() {
        return UserS;
    }

    public void setUserS(String userS) {
        UserS = userS;
    }

    public String getPasswordS() {
        return PasswordS;
    }

    public void setPasswordS(String passwordS) {
        PasswordS = passwordS;
    }

    public String getConfirm_passwordS() {
        return Confirm_passwordS;
    }

    public void setConfirm_passwordS(String confirm_passwordS) {
        Confirm_passwordS = confirm_passwordS;
    }
}