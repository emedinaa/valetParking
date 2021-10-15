package com.example.valetparking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valetparking.Administrator.SettingsPreferences;
import com.example.valetparking.Database.Interfaces.Authentication;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.login.CreateAccount;
import com.example.valetparking.login.ForgotPasswordActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextInputLayout username, password;
    View title_icon;
    TextView title_info, create_account, forgot_password;
    Button button_login;
    SettingsPreferences settingsPreferences;
    String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conexion de la parte logica con la grafica
        button_login = findViewById(R.id.button_login);
        create_account = findViewById(R.id.create_account);
        forgot_password = findViewById(R.id.forgot_password);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        title_icon = findViewById(R.id.title_icon);
        title_info = findViewById(R.id.title_info);


        //Button para loguearse
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData(v);
            }
        });

        //TextView para ir a crear una cuenta
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        //TextView para ir a olvidar una clave
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        /*
        //Preferences Manager
        PreferenceManager.setDefaultValues(this, R.xml.adm__root_preferences, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        settingsPreferences.getPreferences(preferences, this);
         */
    }

    //Validar usuario
    private boolean validateUsername(String user){
        Pattern pattern = Pattern.compile("^[a-zA-Z0123456789]+$");
        if(!pattern.matcher(user).matches() || user.length() > 10){
            username.setHelperText("Invalide username");
            return false;
        } else {
            username.setHelperText(null);
        }
        return true;
    }

    //Validar contraseña
    private boolean validatePassword(String pass){
        Pattern pattern = Pattern.compile("^[a-zA-Z0123456789]+$");
        if(!pattern.matcher(pass).matches() || pass.length() > 10){
            password.setHelperText("Invalide password");
            return false;
        } else {
            password.setHelperText(null);
        }
        return true;
    }

    //Crear token de autenticacion
    private String createAuthToken(String username, String password){
        byte [] data = new byte[0];

        try {
          data = (username + ":" + password).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
    }

    //Validar el login
    private void checkLogin(String authToken){
        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        //ROUTE
        Authentication authentication = retrofit.create(Authentication.class);

        //MODEL
        //Call<List<Vehicle>> call = vehicles.getVehicles();

        Call<String> call = authentication.checkLogin(authToken);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    System.out.println("---------------------------------------------\n\n\n\n\n");
                    System.out.println("Body: " + response.body());
                    System.out.println("Error body: " + response.errorBody());
                    System.out.println("Message: " + response.message());
                    System.out.println("Headers: " + response.headers());
                    System.out.println("---------------------------------------------\n\n\n\n\n");
                    return;
                } else {
                    System.out.println("TODO CORRECTO");
                    if(response.body().matches("admin")){
                        Intent intent = new Intent(MainActivity.this, TabLayoutAdministrator.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Admin", Toast.LENGTH_SHORT).show();
                    } else if(response.body().matches("operator")) {
                        Intent intent = new Intent(MainActivity.this, TabLayoutOperator.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Operator", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Login", "Error: " + t.getMessage());
            }
        });
    }

    //Validar datos
    private void validateData(View view){
        String user = username.getEditText().getText().toString();
        String pass = password.getEditText().getText().toString();

        boolean User = validateUsername(user);
        boolean Pass = validatePassword(pass);

        if(User && Pass){
            authToken = createAuthToken(user, pass);
            checkLogin(authToken);
            System.out.println("VALIDATE DATA");
        }
    }

    //option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main__option_menu, menu);
        return true;
    }

    //option menu item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.option_about:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}