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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.valetparking.Database.Interfaces.Administrators;
import com.example.valetparking.Database.Interfaces.Authentication;
import com.example.valetparking.Database.Models.Administrator;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForgotPassword extends AppCompatActivity {

    private TextInputLayout sms, email;
    private String Sms, Email, id;
    private  String token;
    private  TextView new_code;
    private Button button_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log__pass_forgot_password);

        //Recuperar id
        id = getIntent().getStringExtra("id");

        //Conexion de la parte logica con la grafica
        sms = findViewById(R.id.forgot_code_sms);
        email = findViewById(R.id.forgot_code_email);

        new_code = findViewById(R.id.forgot_code_new_code);

        button_verify = findViewById(R.id.forgot_button_verify);

        //Enviar tokens
        tokenSMS();
        tokenEmail();

        //Recuperar token
        retrieveToken();

        //Validar tokens
        button_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData(v);
            }
        });

        //Reenviar codigo
        new_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenSMS();
                tokenEmail();
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
        sms.getEditText().setText("");
        email.getEditText().setText("");

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
        setSms(sms.getEditText().getText().toString());
        setEmail(email.getEditText().getText().toString());

        boolean booleanSms = validateSms(view, getSms());
        boolean booleanEmail = validateEmail(view, getEmail());

        if(booleanSms || booleanEmail){
            if(getSms().equals(String.valueOf(getToken())) || getEmail().equals(String.valueOf(getToken()))){
                setFields(view);
                Intent intent = new Intent(ForgotPassword.this, ChangePassword.class);
                intent.putExtra("id", id);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid token", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Recuperar token
    private void retrieveToken() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        Call<Administrator> call = retrofit.create(Administrators.class).getAdministrator(id);

        call.enqueue(new Callback<Administrator>() {
            @Override
            public void onResponse(Call<Administrator> call, Response<Administrator> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    Administrator administrator = response.body();

                    setToken(String.valueOf(administrator.getToken()));
                }
            }

            @Override
            public void onFailure(Call<Administrator> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Token SMS
    private void tokenSMS() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        Call<ResponseBody> call = retrofit.create(Authentication.class).tokenSMS(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Token Email
    private void tokenEmail() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        Call<ResponseBody> call = retrofit.create(Authentication.class).tokenEmail(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Metodos getter y setter
    public String getSms() {
        return Sms;
    }

    public void setSms(String sms) {
        Sms = sms;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}