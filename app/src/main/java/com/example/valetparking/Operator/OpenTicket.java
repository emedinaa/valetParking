package com.example.valetparking.Operator;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.valetparking.CardView_Adapter;
import com.example.valetparking.CardView_Data;
import com.example.valetparking.Database.Interfaces.Vehicles;
import com.example.valetparking.Database.Models.Operator;
import com.example.valetparking.Database.Models.Vehicle;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OpenTicket extends Fragment {

    private static String ID;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public OpenTicket(String id) {
        ID = id;
    }

    public static OpenTicket newInstance(String param1, String param2) {
        OpenTicket fragment = new OpenTicket(ID);
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

    private TextInputLayout open_ticket_search;
    private RecyclerView recyclerView;
    private OpenTicket_Adapter adapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.op__open_ticket, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Conexion de la parte logica con la grafica
        recyclerView = view.findViewById(R.id.open_ticket_recyclerView);
        open_ticket_search = view.findViewById(R.id.open_ticket_search);
        refreshLayout = view.findViewById(R.id.open_ticket_refresh);

        //RecyclerView
        setRecyclerView();

        //Endicons
        open_ticket_search.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog().show();
            }
        });

        //Recuperar datos y mostrarlos
        retrieveVehicles();
        retrieveOperator();

        //Recargar datos
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveVehicles();

                //Evitar el bucle
                refreshLayout.setRefreshing(false);
            }
        });
    }

    //Recuperar datos
    private void  retrieveVehicles() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        Toast.makeText(getContext(), "ID: " + ID, Toast.LENGTH_SHORT).show();

        Call<List<Vehicle>> call = retrofit.create(Vehicles.class).openTicket(ID);

        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    List<Vehicle> vehicleList = response.body();
                    populateVehicles(vehicleList);
                }
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Poblar los datos
    private void  populateVehicles(List<Vehicle> vehicleList) {
        List<OpenTicket_Data> data = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            data.add(new OpenTicket_Data(
                    vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(),
                    vehicle.getColor(), vehicle.getPlate(), (vehicle.getCode() + vehicle.getPhone()),
                    vehicle.getEmail(),vehicle.getKey(), vehicle.getVehicle()));
        }
        adapter.update(data);
    }

    //Asignar recyclerView
    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new OpenTicket_Adapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    private TextInputLayout brand, year, model, color, operator, date;
    private String BrandS, YearS, ModelS, ColorS, OperatorS, DateS;
    private String selectorBrand;
    private List<Operator> operatorList;

    //Recuperar operators
    private void retrieveOperator() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();

        Call<List<Operator>> call = retrofit.create(com.example.valetparking.Database.Interfaces.Operators.class).getOperatorsForOperator(ID);

        call.enqueue(new Callback<List<Operator>>() {
            @Override
            public void onResponse(Call<List<Operator>> call, Response<List<Operator>> response) {
                if(response.isSuccessful()){
                    setOperatorList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Operator>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Metodo del alertDialog (Mensaje emergente)
    private AlertDialog filterDialog() {
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Obtener el layout inflater
        LayoutInflater inflater = getLayoutInflater();

        //Inflar y establecer el layout para el dialogo
        //Pasar nulo como vista principal porque va en el diseno del dialogo
        View view = inflater.inflate(R.layout.op__alert_dialog_filter_open_ticket, null);

        //Dimensiones del alertDialog
        view.setMinimumWidth((int) (getResources().getDisplayMetrics().widthPixels * 0.40));
        view.setMinimumHeight((int) (getResources().getDisplayMetrics().heightPixels * 0.40));

        //conexion de la parte logica con la parte grafica
        //TextInputLayout
        brand = view.findViewById(R.id.alert_filter_brand);
        year = view.findViewById(R.id.alert_filter_year);
        model = view.findViewById(R.id.alert_filter_model);
        color = view.findViewById(R.id.alert_filter_color);
        operator = view.findViewById(R.id.alert_filter_operator);
        date = view.findViewById(R.id.alert_filter_date);

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
                if (brand.getEditText().getText().toString().equals("")) {
                    StyleableToast
                            .makeText(getContext(), "Debe escoger una marca", Toast.LENGTH_LONG, R.style.toast)
                            .show();
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DatePicker,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int Year, int Month, int Day) {
                                date.getEditText().setText(Day + "/" + (Month + 1) + "/" + Year);
                                setDateS(Day + "/" + (Month + 1) + "/" + Year);
                            }
                        }, year, month, day);
                datePickerDialog.getWindow()
                        .setBackgroundDrawableResource(R.drawable.round__alert_dialog_dark);
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
                //Recuperar datos filtrados
                Retrofit retrofit = RetrofitClient.getRetrofitClient();

                Toast.makeText(getContext(), "ID: " + ID, Toast.LENGTH_SHORT).show();

                Call<List<Vehicle>> call = retrofit.create(Vehicles.class).openTicketFilter(ID, getBrandS(), getYearS(), getModelS(), getColorS(), getDateS(), getOperatorS());

                call.enqueue(new Callback<List<Vehicle>>() {
                    @Override
                    public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        } else {
                            List<Vehicle> vehicleList = response.body();
                            populateVehicles(vehicleList);
                            setBrandS("");
                            setYearS("");
                            setModelS("");
                            setColorS("");
                            setDateS("");
                            setOperatorS("");
                            alertDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                        Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return alertDialog;
    }

        //Metodo de selectores (Ventana emergente)
        //Brand
        private AlertDialog selectBrand() {
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
            alertDialog.getWindow()
                    .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //Asignar valor seleccionado y cerrar alertDialog
            adapter.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (model.getEditText().getText().toString().equals("")) {
                        brand.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                        setSelectorBrand(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                        setBrandS(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                        alertDialog.dismiss();
                    } else {
                        if (data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view().equals(getSelectorBrand())) {
                            brand.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            setSelectorBrand(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            setBrandS(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            alertDialog.dismiss();
                        } else {
                            brand.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            model.getEditText().setText("");
                            setSelectorBrand(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                            setBrandS(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
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
                    setYearS(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

        //Model
        private AlertDialog selectModel() {
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
            view.setMinimumWidth((int) (getResources().getDisplayMetrics().widthPixels * 0.80));
            view.setMinimumHeight((int) (getResources().getDisplayMetrics().heightPixels * 0.80));

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
                    setModelS(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

        //Color
        private AlertDialog selectColor() {
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
            alertDialog.getWindow()
                    .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //Asignar valor seleccionado y cerrar alertDialog
            adapter.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    color.getEditText().setText(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    setColorS(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
                    alertDialog.dismiss();
                }
            });

            return alertDialog;
        }

        //Operator
        private AlertDialog selectOperator() {
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

            //Poblar datos
            List<CardView_Data> data = new ArrayList<>();

            for (Operator operator : getOperatorList()) {
                data.add(new CardView_Data(operator.getUsername(), R.drawable.icon__operator));
            }

            //Llenar data
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
                    setOperatorS(data.get(card_recycler_view.getChildAdapterPosition(v)).getCard_text_view());
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

    public String getBrandS() {
        return BrandS;
    }

    public void setBrandS(String brandS) {
        BrandS = brandS;
    }

    public String getYearS() {
        return YearS;
    }

    public void setYearS(String yearS) {
        YearS = yearS;
    }

    public String getModelS() {
        return ModelS;
    }

    public void setModelS(String modelS) {
        ModelS = modelS;
    }

    public String getColorS() {
        return ColorS;
    }

    public void setColorS(String colorS) {
        ColorS = colorS;
    }

    public String getOperatorS() {
        return OperatorS;
    }

    public void setOperatorS(String operatorS) {
        OperatorS = operatorS;
    }

    public String getDateS() {
        return DateS;
    }

    public void setDateS(String dateS) {
        DateS = dateS;
    }

    public List<Operator> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(List<Operator> operatorList) {
        this.operatorList = operatorList;
    }
}

