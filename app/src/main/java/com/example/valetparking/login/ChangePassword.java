package com.example.valetparking.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.valetparking.MainActivity;
import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class ChangePassword extends AppCompatActivity {

    TextInputLayout password, confirm_password;
    TextInputEditText Password, Confirm_password;
    Button change_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log__pass_change_password);

        //Conexion de la parte logica con la grafica
        password = findViewById(R.id.change_password);
        confirm_password = findViewById(R.id.change_confirm_password);

        Password = findViewById(R.id.change_password_edit);
        Confirm_password = findViewById(R.id.change_confirm_password_edit);

        change_button = findViewById(R.id.change_button);

        //Validar para pasar de vista
        change_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData(v);
            }
        });
    }

    //Metodo para setear los campos
    private void setFields(View view) {
        Password.setText("");
        Confirm_password.setText("");

        password.setHelperText(null);
        confirm_password.setHelperText(null);

        password.clearFocus();
        confirm_password.clearFocus();
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
        String Password = password.getEditText().getText().toString();
        String Confirm_password = confirm_password.getEditText().getText().toString();

        boolean booleanPassword = validatePassword(view, Password);
        boolean booleanConfirmPassword = validateConfirmPassword(view, Confirm_password);

        if(booleanPassword & booleanConfirmPassword){
            setFields(view);
            customDialog().show();
        }
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

        //Cambio de tamaño de letra
        alert_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        alert_message.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        //Personalizacion del alertDialog
        alert_title.setText(getResources().getString(R.string.alert_title_password));
        alert_message.setText(getResources().getString(R.string.alert_message_password));
        alert_image.setBackground(getResources().getDrawable(R.drawable.alert__lock));

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
                Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                startActivity(intent);
            }
        });

        return alertDialog;
    }
}