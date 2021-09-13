package com.example.valetparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardView_RecyclerView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardView_RecyclerView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CardView_RecyclerView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardView_RecyclerView.
     */
    // TODO: Rename and change types and number of parameters
    public static CardView_RecyclerView newInstance(String param1, String param2) {
        CardView_RecyclerView fragment = new CardView_RecyclerView();
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

    private ArrayList<CardView_Data> data;
    private RecyclerView card_recycler_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gen__card_view_recycler_view, container, false);

        /*
        //Conexion de la parte logica con la grafica
        card_recycler_view = view.findViewById(R.id.card_recycler_view);

        //Llenar datos
        data = new ArrayList<>();
        data.add(new CardView_Data("Facebook", R.drawable.facebook));
        data.add(new CardView_Data("Facebook", R.drawable.facebook));
        data.add(new CardView_Data("Facebook", R.drawable.facebook));
        data.add(new CardView_Data("Facebook", R.drawable.facebook));

        CardView_Adapter cardView_adapter = new CardView_Adapter(getContext(), data);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        card_recycler_view.setLayoutManager(linearLayoutManager);
        card_recycler_view.setAdapter(cardView_adapter);
         */


        return view;
    }
}