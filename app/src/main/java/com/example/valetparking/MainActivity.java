package com.example.valetparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valetparking.Administrator.SettingsPreferences;
import com.example.valetparking.Administrator.TabLayoutAdministrator;
import com.example.valetparking.Database.Interfaces.Authentication;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.Operator.TabLayoutOperator;
import com.example.valetparking.login.CreateAccount;
import com.example.valetparking.login.ForgotPassword;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    String user, pass;
    TextInputLayout ti_username, ti_password;
    View title_icon;
    TextView title_info, create_account, forgot_password, pruebaAdmin;
    Button button_login;
    SettingsPreferences settingsPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conexion de la parte logica con la grafica
        button_login = findViewById(R.id.button_login);
        create_account = findViewById(R.id.create_account);
        forgot_password = findViewById(R.id.forgot_password);
        pruebaAdmin = findViewById(R.id.title_info);

        ti_username = findViewById(R.id.login_username);
        ti_password = findViewById(R.id.login_password);
        title_icon = findViewById(R.id.title_icon);
        title_info = findViewById(R.id.title_info);

        //TextView de prueba para ir a las vistas del admin
        pruebaAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabLayoutAdministrator.class);
                startActivity(intent);
            }
        });

        //Button para loguearse
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginCheck(user, pass);
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
                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
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
            ti_username.setHelperText("Invalide username");
            return false;
        } else {
            ti_username.setHelperText(null);
        }
        return true;
    }

    //Validar contraseña
    private boolean validatePassword(String pass){
        Pattern pattern = Pattern.compile("^[a-zA-Z0123456789]+$");
        if(!pattern.matcher(pass).matches() || pass.length() > 25){
            ti_password.setHelperText("Invalide password");
            return false;
        } else {
            ti_password.setHelperText(null);
        }
        return true;
    }

    //Validar Login
    private void LoginCheck(final String username, final String password) {
        setUser(ti_username.getEditText().getText().toString());
        setPass(ti_password.getEditText().getText().toString());

        boolean User = validateUsername(getUser());
        boolean Pass = validatePassword(getPass());

        if(User && Pass){
            Retrofit retrofit = RetrofitClient.getRetrofitClient();

            Call<ResponseBody> call = retrofit.create(Authentication.class).checkLogin(getUser(), getPass());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    } else {

                        try {

                            String result = response.body().string();

                            JSONObject jsonObject = new JSONObject(result);

                            String rol = jsonObject.getString("rol");
                            String id = jsonObject.getString("_id");

                            if(rol.equals("admin")) {
                                Intent intent = new Intent(MainActivity.this, TabLayoutAdministrator.class);
                                startActivity(intent);
                            } else if(rol.equals("operator")){
                                Intent intent = new Intent(MainActivity.this, TabLayoutOperator.class);
                                startActivity(intent);
                            }

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

    //Metodos getter y setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}














































