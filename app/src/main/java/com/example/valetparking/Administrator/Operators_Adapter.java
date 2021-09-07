package com.example.valetparking.Administrator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valetparking.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Operators_Adapter extends RecyclerView.Adapter<Operators_Adapter.MyViewHolderOperators> {

    List<Operators_Data> data;
    Context context;

    //Constructor
    public Operators_Adapter(Context context, List<Operators_Data> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolderOperators onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adm__operators_card_view_element, parent, false);
        return new MyViewHolderOperators(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderOperators holder, int position) {
        if(data != null && data.size() > 0){
            Operators_Data datas = data.get(position);

            holder.name.setText(datas.getName());
            holder.hour_entry.setText(String.valueOf(datas.getHourIn()));
            holder.hour_exit.setText(String.valueOf(datas.getHourOut()));
            holder.vehicle_entry.setText(String.valueOf(datas.getVehiclesIn()));
            holder.vehicle_exit.setText(String.valueOf(datas.getVehiclesOut()));
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //Clase View Holder
    public class MyViewHolderOperators extends RecyclerView.ViewHolder {
        TextView name, hour_entry, hour_exit, vehicle_entry, vehicle_exit;

        public MyViewHolderOperators(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.adm__card_name);
            hour_entry = itemView.findViewById(R.id.adm__card_hour_entry_content);
            hour_exit = itemView.findViewById(R.id.adm__card_hour_exit_content);
            vehicle_entry = itemView.findViewById(R.id.adm__card_vehicles_entry_content);
            vehicle_exit = itemView.findViewById(R.id.adm__card_vehicles_exit_content);
        }
    }
}
