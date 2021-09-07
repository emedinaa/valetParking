package com.example.valetparking.Administrator;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SettingsPreferences {

    private String preferences_check_in = "preferences_check_in";
    private String PreferencesCheckIn;

    public void getPreferences(SharedPreferences preferences, Context context){
        PreferencesCheckIn = preferences.getString(preferences_check_in, "");

        Toast.makeText(context.getApplicationContext(), "Preferences check in: " + PreferencesCheckIn, Toast.LENGTH_SHORT).show();
    }


}
