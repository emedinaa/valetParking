package com.example.valetparking.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.valetparking.Database.Interfaces.Authentication;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VerifyUsername extends AppCompatActivity {

    TextInputLayout username;
    String Username;
    Button button_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log__pass_verify_username);

        //Conexion de la parte logica con la grafica
        username = findViewById(R.id.verify_username);
        button_verify = findViewById(R.id.verify_button);

        //Button
        button_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUsername(v, getUsername());
            }
        });
    }

    //Metodo para setear los campos
    private void setFields(View view) {
        username.getEditText().setText("");
        username.setHelperText(null);
        username.clearFocus();
    }

    //Validar username
    private boolean validateUsername(View view, String text_username){
        Pattern pattern = Pattern.compile("^[a-zA-Z0123456789]+$");
        if(!pattern.matcher(text_username).matches()){
            username.setHelperText("Invalid username");
            return false;
        } else {
            username.setHelperText(null);
        }
        return true;
    }

    //Validar datos
    private void verifyUsername(View view, final String user){
        setUsername(username.getEditText().getText().toString());

        boolean booleanUser = validateUsername(view, getUsername());

        if(booleanUser){
            Retrofit retrofit = RetrofitClient.getRetrofitClient();

            Call<ResponseBody> call = retrofit.create(Authentication.class).verifyUsername(getUsername());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    } else {

                        try {

                            String result = response.body().string();

                            JSONObject jsonObject = new JSONObject(result);

                            String id = jsonObject.getString("_id");

                            Intent intent = new Intent(VerifyUsername.this, ForgotPassword.class);
                            intent.putExtra("id", id);
                            startActivity(intent);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //Metodos getter y setter
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}