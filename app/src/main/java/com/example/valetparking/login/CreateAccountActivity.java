package com.example.valetparking.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    TextInputLayout name, phone, email, user, password, confirm_password;
    TextInputEditText Name, Phone, Email, User, Password, Confirm_password;
    CountryCodePicker code;
    Button create_account_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log__create_account);

        //Conexion de la parte logica con la grafica
        create_account_button = findViewById(R.id.create_account_button);
        name = findViewById(R.id.create_account_name);
        code = findViewById(R.id.create_account_code);
        phone = findViewById(R.id.create_account_telephone);
        email = findViewById(R.id.create_account_email);
        user = findViewById(R.id.create_account_username);
        password = findViewById(R.id.create_account_password);
        confirm_password = findViewById(R.id.create_account_confirm_password);

        Name = findViewById(R.id.create_account_name_edit);
        Phone = findViewById(R.id.create_account_telephone_edit);
        Email = findViewById(R.id.create_account_email_edit);
        User = findViewById(R.id.create_account_username_edit);
        Password = findViewById(R.id.create_account_password_edit);
        Confirm_password = findViewById(R.id.create_account_confirm_password_edit);

        //Validar la data para pasar de ventana
        create_account_button.setOnClickListener(new View.OnClickListener() {
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
            Intent intent = new Intent(CreateAccountActivity.this, AboutThePlaceActivity.class);
            startActivity(intent);
        }
    }
}