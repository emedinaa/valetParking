package com.example.valetparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.valetparking.Administrator.SettingsPreferences;
import com.example.valetparking.login.CreateAccountActivity;
import com.example.valetparking.login.ForgotPasswordActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextInputLayout username, password;
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

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
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
                validateData(v);
            }
        });

        //TextView para ir a crear una cuenta
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
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

    //Validar datos
    private void validateData(View view){
        String user = username.getEditText().getText().toString();
        String pass = password.getEditText().getText().toString();

        boolean User = validateUsername(user);
        boolean Pass = validatePassword(pass);

        if(User && Pass){
            Intent intent = new Intent(MainActivity.this, TabLayoutOperator.class);
            startActivity(intent);
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














































