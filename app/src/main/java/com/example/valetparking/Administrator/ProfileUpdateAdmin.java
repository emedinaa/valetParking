package com.example.valetparking.Administrator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileUpdateAdmin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileUpdateAdmin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileUpdateAdmin() {}

    public static ProfileUpdateAdmin newInstance(String param1, String param2) {
        ProfileUpdateAdmin fragment = new ProfileUpdateAdmin();
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

    TextInputLayout fullname, phone, email, user, password;
    TextInputEditText Fullname_edit, Phone_edit, Email_edit, User_edit, Password_edit;
    CountryCodePicker code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.adm__profile_update_admin, container, false);

        //Conexion de la parte logica con la grafica
            //TextInputLayouts
            fullname = view.findViewById(R.id.profile_update_admin_name);
            phone = view.findViewById(R.id.profile_update_admin_telephone);
            email = view.findViewById(R.id.profile_update_admin_email);
            user = view.findViewById(R.id.profile_update_admin_username);
            password = view.findViewById(R.id.profile_update_admin_password);

            //TextInputEditTexts
            Fullname_edit = view.findViewById(R.id.profile_update_admin_name_edit);
            Phone_edit = view.findViewById(R.id.profile_update_admin_telephone_edit);
            Email_edit = view.findViewById(R.id.profile_update_admin_email_edit);
            User_edit = view.findViewById(R.id.profile_update_admin_username_edit);
            Password_edit = view.findViewById(R.id.profile_update_admin_password_edit);

            //Country Code Picker
            code = view.findViewById(R.id.profile_update_admin_code);

            //validateFields();

        return view;
    }

    public void validateFields(){
        if(getFullname_edit().getText().toString().equals("")){
            Toast.makeText(getContext(), "Vacio", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Lleno", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodos getter y setter
    public TextInputLayout getFullname() {
        return fullname;
    }

    public void setFullname(TextInputLayout fullname) {
        this.fullname = fullname;
    }

    public TextInputLayout getPhone() {
        return phone;
    }

    public void setPhone(TextInputLayout phone) {
        this.phone = phone;
    }

    public TextInputLayout getEmail() {
        return email;
    }

    public void setEmail(TextInputLayout email) {
        this.email = email;
    }

    public TextInputLayout getUser() {
        return user;
    }

    public void setUser(TextInputLayout user) {
        this.user = user;
    }

    public TextInputLayout getPassword() {
        return password;
    }

    public void setPassword(TextInputLayout password) {
        this.password = password;
    }

    public TextInputEditText getFullname_edit() {
        return Fullname_edit;
    }

    public void setFullname_edit(TextInputEditText fullname_edit) {
        Fullname_edit = fullname_edit;
    }

    public TextInputEditText getPhone_edit() {
        return Phone_edit;
    }

    public void setPhone_edit(TextInputEditText phone_edit) {
        Phone_edit = phone_edit;
    }

    public TextInputEditText getEmail_edit() {
        return Email_edit;
    }

    public void setEmail_edit(TextInputEditText email_edit) {
        Email_edit = email_edit;
    }

    public TextInputEditText getUser_edit() {
        return User_edit;
    }

    public void setUser_edit(TextInputEditText user_edit) {
        User_edit = user_edit;
    }

    public TextInputEditText getPassword_edit() {
        return Password_edit;
    }

    public void setPassword_edit(TextInputEditText password_edit) {
        Password_edit = password_edit;
    }

    public CountryCodePicker getCode() {
        return code;
    }

    public void setCode(CountryCodePicker code) {
        this.code = code;
    }
}