package com.example.valetparking.Administrator;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.valetparking.CardView_Adapter;
import com.example.valetparking.CardView_Data;
import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.muddz.styleabletoast.StyleableToast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tickets#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tickets extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tickets() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tickets.
     */
    // TODO: Rename and change types and number of parameters
    public static Tickets newInstance(String param1, String param2) {
        Tickets fragment = new Tickets();
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

    RecyclerView recyclerView;
    Tickets_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.adm__tickets, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Conexion de la parte logica con la grafica
        TextInputLayout tickets_search = view.findViewById(R.id.tickets_search);
        recyclerView = view.findViewById(R.id.tickets_recyclerView);

        //RecyclerView
        setRecyclerView();

        //Selectores en textinputlayout
        tickets_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog().show();
            }
        });

        //Endicons
        tickets_search.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog().show();
            }
        });

        return view;
    }

    //Asignar recyclerView
    private void setRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new Tickets_Adapter(getContext(), getList());
        recyclerView.setAdapter(adapter);
    }

    //Metodo para llenar los datos
    private List<Tickets_Data> getList(){
        List<Tickets_Data> data = new ArrayList<>();

        data.add(new Tickets_Data("Hyunday", "Getz", "2001", "Blue", "MFC99C", "04122133219", "valentinapereira2112@gmail.com", "A113", "B13"));
        data.add(new Tickets_Data("Hyunday", "Getz", "2001", "Blue", "MFC99C", "04122133219", "valentinapereira2112@gmail.com", "A113", "B13"));
        data.add(new Tickets_Data("Hyunday", "Getz", "2001", "Blue", "MFC99C", "04122133219", "valentinapereira2112@gmail.com", "A113", "B13"));
        data.add(new Tickets_Data("Hyunday", "Getz", "2001", "Blue", "MFC99C", "04122133219", "valentinapereira2112@gmail.com", "A113", "B13"));
        data.add(new Tickets_Data("Hyunday", "Getz", "2001", "Blue", "MFC99C", "04122133219", "valentinapereira2112@gmail.com", "A113", "B13"));
        data.add(new Tickets_Data("Hyunday", "Getz", "2001", "Blue", "MFC99C", "04122133219", "valentinapereira2112@gmail.com", "A113", "B13"));
        data.add(new Tickets_Data("Hyunday", "Getz", "2001", "Blue", "MFC99C", "04122133219", "valentinapereira2112@gmail.com", "A113", "B13"));
        data.add(new Tickets_Data("Hyunday", "Getz", "2001", "Blue", "MFC99C", "04122133219", "valentinapereira2112@gmail.com", "A113", "B13"));
        data.add(new Tickets_Data("Hyunday", "Getz", "2001", "Blue", "MFC99C", "04122133219", "valentinapereira2112@gmail.com", "A113", "B13"));
        data.add(new Tickets_Data("Hyunday", "Getz", "2001", "Blue", "MFC99C", "04122133219", "valentinapereira2112@gmail.com", "A113", "B13"));

        return data;
    }

    TextInputLayout brand, year, model, color, ticket, operator, date;
    AutoCompleteTextView Brand, Year, Model, ColorC, Ticket, Operator;
    TextInputEditText Date;
    String selectorBrand;

    //Metodo del alertDialog (Mensaje emergente)
    private AlertDialog filterDialog(){
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Obtener el layout inflater
        LayoutInflater inflater = getLayoutInflater();

        //Inflar y establecer el layout para el dialogo
        //Pasar nulo como vista principal porque va en el diseno del dialogo
        View view = inflater.inflate(R.layout.gen__alert_dialog_filter, null);

        //Dimensiones del alertDialog
        view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.40));
        view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.40));

        //conexion de la parte logica con la parte grafica
        //TextInputLayout
        brand = view.findViewById(R.id.alert_filter_brand);
        year = view.findViewById(R.id.alert_filter_year);
        model = view.findViewById(R.id.alert_filter_model);
        color = view.findViewById(R.id.alert_filter_color);
        ticket = view.findViewById(R.id.alert_filter_ticket);
        operator = view.findViewById(R.id.alert_filter_operator);
        date = view.findViewById(R.id.alert_filter_date);

        //AutoCompleteTextView
        Brand = view.findViewById(R.id.alert_filter_brand_edit);
        Year = view.findViewById(R.id.alert_filter_year_edit);
        Model = view.findViewById(R.id.alert_filter_model_edit);
        ColorC = view.findViewById(R.id.alert_filter_color_edit);
        Ticket = view.findViewById(R.id.alert_filter_ticket_edit);
        Operator = view.findViewById(R.id.alert_filter_operator_edit);

        //TextInputEditText
        Date = view.findViewById(R.id.alert_filter_date_edit);

        //Button
        Button alert_filter_button = view.findViewById(R.id.alert_filter_button);

        //Setear marca
        setSelectorBrand("");

        //EndIcons
        brand.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBrand().show();
            }
        });

        year.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectYear().show();
            }
        });

        model.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brand.getEditText().getText().toString().equals("")){
                    StyleableToast.makeText(getContext(),"Debe escoger una marca", Toast.LENGTH_LONG, R.style.toast).show();
                } else {
                    selectModel().show();
                }
            }
        });

        color.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectColor().show();
            }
        });

        ticket.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTicket().show();
            }
        });

        operator.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOperator().show();
            }
        });

        date.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day, month, year;

                Calendar calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DatePicker,new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int Year, int Month, int Day) {
                        Date.setText(Day + "/" + (Month+1) + "/" + Year);
                    }
                }, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawableResource(R.drawable.round__alert_dialog_dark);
                datePickerDialog.show();

            }
        });

        //Mostrar alertDialog
        builder.setView(view);
        builder.setCancelable(true);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Cerrar alertDialog
        alert_filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        return alertDialog;
    }

    //Metodo de selectores (Ventana emergente)
        //Brand
        private AlertDialog selectBrand(){
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            //Obtener el layout inflater
            LayoutInflater inflater = getLayoutInflater();

            //Inflar y establecer el layout para el dialogo
            //Pasar nulo como vista principal porque va en el diseno del dialogo
            View view = inflater.inflate(R.layout.gen__card_view_recycler_view, null);

            //Conexion de la parte logica con la grafica
            card_recycler_view = view.findViewById(R.id.card_recycler_view);

            //Dimensiones del alertDialog
            view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.80));
            view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.80));

            //RecyclerView - Adapter
            card_recycler_view.setHasFixedSize(true);
            card_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            String[] brands = getResources().getStringArray(R.array.array_brands);
            int[] logos = {R.drawable.check__brand_toyota, R.drawable.check__brand_honda};

            for (int i = 0; i < brands.length; i++) {
                data.add(new CardView_Data(brands[i], logos[i]));
            }

            //Asignar adapter
            adapter = new CardView_Adapter(getContext(), data);
            card_recycler_view.setAdapter(adapter);

            //Mostrar alertDialog
            builder.setView(view);
            builder.setCancelable(true);
            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //Asignar valor seleccionado y cerrar alertDialog
            adapter.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(model.getEditText().getText().toString().equals("")){
                        brand.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                        setSelectorBrand(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                        alertDialog.dismiss();
                    } else {
                        if(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view().equals(getSelectorBrand())){
                            brand.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            setSelectorBrand(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            alertDialog.dismiss();
                        } else {
                            brand.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            model.getEditText().setText("");
                            setSelectorBrand(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            alertDialog.dismiss();
                        }
                    }
                }
            });

            return alertDialog;
        }

        //Year
        private AlertDialog selectYear() {
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            //Obtener el layout inflater
            LayoutInflater inflater = getLayoutInflater();

            //Inflar y establecer el layout para el dialogo
            //Pasar nulo como vista principal porque va en el diseno del dialogo
            View view = inflater.inflate(R.layout.gen__card_view_recycler_view, null);

            //Conexion de la parte logica con la grafica
            card_recycler_view = view.findViewById(R.id.card_recycler_view);

            //Dimensiones del alertDialog
            view.setMinimumWidth((int) (getResources().getDisplayMetrics().widthPixels * 0.80));
            view.setMinimumHeight((int) (getResources().getDisplayMetrics().heightPixels * 0.80));

            //RecyclerView - Adapter
            card_recycler_view.setHasFixedSize(true);
            card_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            String[] years = getResources().getStringArray(R.array.array_years);

            for (int i = 0; i < years.length; i++) {
                data.add(new CardView_Data(years[i], R.drawable.icon__calendar_year_2));
            }

            //Asignar adapter
            adapter = new CardView_Adapter(getContext(), data);
            card_recycler_view.setAdapter(adapter);

            //Mostrar alertDialog
            builder.setView(view);
            builder.setCancelable(true);
            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //Asignar valor seleccionado y cerrar alertDialog
            adapter.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    year.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

        //Model
        private AlertDialog selectModel(){
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            //Obtener el layout inflater
            LayoutInflater inflater = getLayoutInflater();

            //Inflar y establecer el layout para el dialogo
            //Pasar nulo como vista principal porque va en el diseno del dialogo
            View view = inflater.inflate(R.layout.gen__card_view_recycler_view, null);
            View view2 = inflater.inflate(R.layout.op__check_in, null);

            //Conexion de la parte logica con la grafica
            card_recycler_view = view.findViewById(R.id.card_recycler_view);

            //Dimensiones del alertDialog
            view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.80));
            view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.80));

            //RecyclerView - Adapter
            card_recycler_view.setHasFixedSize(true);
            card_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            //Seleccionar con que datos se llenaria
            String[] toyota = getResources().getStringArray(R.array.array_models_toyota);
            String[] honda = getResources().getStringArray(R.array.array_models_honda);

            switch (getSelectorBrand()) {
                case "Toyota":
                    for (String model : toyota) {
                        data.add(new CardView_Data(model, R.drawable.check__brand_toyota));
                    }
                    break;
                case "Honda":
                    for (String model : honda) {
                        data.add(new CardView_Data(model, R.drawable.check__brand_honda));
                    }
                    break;
            }

            //Asignar adapter
            adapter = new CardView_Adapter(getContext(), data);
            card_recycler_view.setAdapter(adapter);

            //Mostrar alertDialog
            builder.setView(view);
            builder.setCancelable(true);
            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //Asignar valor seleccionado y cerrar alertDialog
            adapter.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

        //Color
        private AlertDialog selectColor(){
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            //Obtener el layout inflater
            LayoutInflater inflater = getLayoutInflater();

            //Inflar y establecer el layout para el dialogo
            //Pasar nulo como vista principal porque va en el diseno del dialogo
            View view = inflater.inflate(R.layout.gen__card_view_recycler_view, null);

            //Conexion de la parte logica con la grafica
            card_recycler_view = view.findViewById(R.id.card_recycler_view);

            //Dimensiones del alertDialog
            view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.80));
            view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.80));

            //RecyclerView - Adapter
            card_recycler_view.setHasFixedSize(true);
            card_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            String[] colors = getResources().getStringArray(R.array.array_colors);

            for (int i = 0; i < colors.length; i++) {
                data.add(new CardView_Data(colors[i], R.drawable.icon__color));
            }

            //Asignar adapter
            adapter = new CardView_Adapter(getContext(), data);
            card_recycler_view.setAdapter(adapter);

            //Mostrar alertDialog
            builder.setView(view);
            builder.setCancelable(true);
            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //Asignar valor seleccionado y cerrar alertDialog
            adapter.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

        //Tickets
        private AlertDialog selectTicket(){
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            //Obtener el layout inflater
            LayoutInflater inflater = getLayoutInflater();

            //Inflar y establecer el layout para el dialogo
            //Pasar nulo como vista principal porque va en el diseno del dialogo
            View view = inflater.inflate(R.layout.gen__card_view_recycler_view, null);

            //Conexion de la parte logica con la grafica
            card_recycler_view = view.findViewById(R.id.card_recycler_view);

            //Dimensiones del alertDialog
            view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.80));
            view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.80));

            //RecyclerView - Adapter
            card_recycler_view.setHasFixedSize(true);
            card_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            String[] tickets = getResources().getStringArray(R.array.array_filter_tickets);
            int[] tickets_icons = {R.drawable.icon__ticket_open, R.drawable.icon__ticket_close, R.drawable.icon__ticket_cancel};

            for (int i = 0; i < tickets.length; i++) {
                data.add(new CardView_Data(tickets[i], tickets_icons[i]));
            }

            //Asignar adapter
            adapter = new CardView_Adapter(getContext(), data);
            card_recycler_view.setAdapter(adapter);

            //Mostrar alertDialog
            builder.setView(view);
            builder.setCancelable(true);
            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //Asignar valor seleccionado y cerrar alertDialog
            adapter.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ticket.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

        //Operator
        private AlertDialog selectOperator(){
            RecyclerView card_recycler_view;
            CardView_Adapter adapter;

            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            //Obtener el layout inflater
            LayoutInflater inflater = getLayoutInflater();

            //Inflar y establecer el layout para el dialogo
            //Pasar nulo como vista principal porque va en el diseno del dialogo
            View view = inflater.inflate(R.layout.gen__card_view_recycler_view, null);

            //Conexion de la parte logica con la grafica
            card_recycler_view = view.findViewById(R.id.card_recycler_view);

            //Dimensiones del alertDialog
            view.setMinimumWidth((int)(getResources().getDisplayMetrics().widthPixels * 0.80));
            view.setMinimumHeight((int)(getResources().getDisplayMetrics().heightPixels * 0.80));

            //RecyclerView - Adapter
            card_recycler_view.setHasFixedSize(true);
            card_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

            //Llenar data
            List<CardView_Data> data = new ArrayList<>();

            String[] operators = getResources().getStringArray(R.array.array_filter_operators);

            for (int i = 0; i < operators.length; i++) {
                data.add(new CardView_Data(operators[i], R.drawable.icon__operator));
            }

            //Asignar adapter
            adapter = new CardView_Adapter(getContext(), data);
            card_recycler_view.setAdapter(adapter);

            //Mostrar alertDialog
            builder.setView(view);
            builder.setCancelable(true);
            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //Asignar valor seleccionado y cerrar alertDialog
            adapter.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

    //Metodos Getter y Setter
    public String getSelectorBrand() {
        return selectorBrand;
    }

    public void setSelectorBrand(String selectorBrand) {
        this.selectorBrand = selectorBrand;
    }
}