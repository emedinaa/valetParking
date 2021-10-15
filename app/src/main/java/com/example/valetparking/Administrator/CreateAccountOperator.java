package com.example.valetparking.Administrator;

import android.app.AlertDialog;
import android.content.Intent;
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

import androidx.fragment.app.Fragment;

import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateAccountOperator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAccountOperator extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateAccountOperator() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateAccountOperator.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateAccountOperator newInstance(String param1, String param2) {
        CreateAccountOperator fragment = new CreateAccountOperator();
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

    TextInputLayout name, phone, email, user, password, confirm_password;
    TextInputEditText Name, Phone, Email, User, Password, Confirm_password;
    CountryCodePicker code;
    Button create_operator_account_button;

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

        Name = view.findViewById(R.id.create_account_operator_name_edit);
        Phone = view.findViewById(R.id.create_account_operator_telephone_edit);
        Email = view.findViewById(R.id.create_account_operator_email_edit);
        User = view.findViewById(R.id.create_account_operator_username_edit);
        Password = view.findViewById(R.id.create_account_operator_password_edit);
        Confirm_password = view.findViewById(R.id.create_account_operator_confirm_password_edit);

        create_operator_account_button = view.findViewById(R.id.create_account_operator_button);

        //Validar la data para pasar de ventana
        create_operator_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Name.getText().toString().equals("") ||
                        Phone.getText().toString().equals("") ||
                        Email.getText().toString().equals("") ||
                        User.getText().toString().equals("") ||
                        Password.getText().toString().equals("") ||
                        Confirm_password.getText().toString().equals("")
                ){
                    validateData(v);
                } else {
                    validateData(v);
                    setFields(v);
                }
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
                Intent intent = new Intent(CreateAccountOperator.super.getContext(), TabLayoutAdministrator.class);
                startActivity(intent);
            }
        });

        return alertDialog;
    }

    //Metodo para setear los campos
    private void setFields(View view){
        Name.setText("");
        Phone.setText("");
        Email.setText("");
        User.setText("");
        Password.setText("");
        Confirm_password.setText("");

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
        Pattern pattern = Pattern.compile("^[0-9]+$");
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

    //Validar confirmacion de clave
    private boolean validateConfirmPassword(View view, String text_confirm_password){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if(!pattern.matcher(text_confirm_password).matches()){
            confirm_password.setHelperText("Invalid password");
            return false;
        } else {
            confirm_password.setHelperText(null);
        }
        return true;
    }

    //Validar datos
    private void validateData(View view){
        String Name = name.getEditText().getText().toString();
        String Phone = phone.getEditText().getText().toString();
        String Email = email.getEditText().getText().toString();
        String User = user.getEditText().getText().toString();
        String Password = password.getEditText().getText().toString();
        String Confirm_password = confirm_password.getEditText().getText().toString();

        boolean booleanName = validateName(view, Name);
        boolean booleanPhone = validatePhone(view,Phone);
        boolean booleanEmail = validateEmail(view,Email);
        boolean booleanUser = validateUser(view, User);
        boolean booleanPassword = validatePassword(view, Password);
        boolean booleanConfirmPassword = validateConfirmPassword(view, Confirm_password);

        if(booleanName & booleanPhone & booleanEmail & booleanUser & booleanPassword & booleanConfirmPassword){
            customDialog().show();
        }
    }
}