package com.example.valetparking.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputLayout sms, email;
    TextInputEditText Sms, Email;
    TextView new_code;
    Button button_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log__pass_forgot_password);

        //Conexion de la parte logica con la grafica
        sms = findViewById(R.id.forgot_code_sms);
        email = findViewById(R.id.forgot_code_email);

        Sms = findViewById(R.id.forgot_code_sms_edit);
        Email = findViewById(R.id.forgot_code_email_edit);

        new_code = findViewById(R.id.forgot_code_new_code);

        button_verify = findViewById(R.id.forgot_button_verify);

        //Validar para pasar de ventana
        button_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData(v);
            }
        });

        new_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog().show();
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
        alert_title.setText(getResources().getString(R.string.alert_title_code));
        alert_message.setText(getResources().getString(R.string.alert_message_code));
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
            }
        });

        return alertDialog;
    }

    //Metodo para setear los campos
    private void setFields(View view) {
        Sms.setText("");
        Email.setText("");

        sms.setHelperText(null);
        email.setHelperText(null);

        sms.clearFocus();
        email.clearFocus();
    }

    //Validar sms
    private boolean validateSms(View view, String text_sms){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        if(!pattern.matcher(text_sms).matches()){
            sms.setHelperText("Invalid code");
            return false;
        } else {
            sms.setHelperText(null);
        }
        return true;
    }

    //Validar email
    private boolean validateEmail(View view, String text_email){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        if(!pattern.matcher(text_email).matches()){
            email.setHelperText("Invalid code");
            return false;
        } else {
            email.setHelperText(null);
        }
        return true;
    }

    //Validar datos
    private void validateData(View view){
        String Sms = sms.getEditText().getText().toString();
        String Email = email.getEditText().getText().toString();

        boolean booleanSms = validateSms(view, Sms);
        boolean booleanEmail = validateEmail(view, Email);

        if(booleanSms || booleanEmail){
            setFields(view);
            Intent intent = new Intent(ForgotPasswordActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        }
    }
}