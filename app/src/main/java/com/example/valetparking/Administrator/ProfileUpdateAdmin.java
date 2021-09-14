package com.example.valetparking.Administrator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

    TextInputLayout fullname, code, phone, email, user, password;
    TextInputEditText Fullname, Phone, Email, User, Password;
    AutoCompleteTextView Code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.adm__profile_update_admin, container, false);

        //Conexion de la parte logica con la grafica
            //TextInputLayouts
            fullname = view.findViewById(R.id.profile_admin_name);
            code = view.findViewById(R.id.profile_admin_code);
            phone = view.findViewById(R.id.profile_admin_telephone);
            email = view.findViewById(R.id.profile_admin_email);
            user = view.findViewById(R.id.profile_admin_username);
            password = view.findViewById(R.id.profile_admin_password);

            //TextInputEditTexts
            Fullname = view.findViewById(R.id.profile_admin_name_edit);
            Phone = view.findViewById(R.id.profile_admin_telephone_edit);
            Email = view.findViewById(R.id.profile_admin_email_edit);
            User = view.findViewById(R.id.profile_admin_username_edit);
            Password = view.findViewById(R.id.profile_admin_password_edit);

            //AutoCompleteTextView
            Code = view.findViewById(R.id.profile_admin_code_edit);
        return view;
    }
}