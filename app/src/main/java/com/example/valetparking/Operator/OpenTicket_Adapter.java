package com.example.valetparking.Operator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valetparking.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OpenTicket_Adapter extends RecyclerView.Adapter<OpenTicket_Adapter.MyViewHolderOpenTicket> {

    List<OpenTicket_Data> data;
    Context context;

    //Constructor
    public OpenTicket_Adapter(Context context, ArrayList<OpenTicket_Data> data) {
        this.context = context;
        this.data = data;
    }

    //Actualizar los datos
    public void update(List<OpenTicket_Data> list) {
        data = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolderOpenTicket onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.op__open_ticket_card_view_element, parent, false);
        return new MyViewHolderOpenTicket(v);
    }

    //Asignar los datos
    @Override
    public void onBindViewHolder(@NonNull MyViewHolderOpenTicket holder, int position) {
        if(data != null && data.size() > 0){
            OpenTicket_Data datas = data.get(position);

            holder.brand.setText(datas.getBrand());
            holder.year.setText(datas.getYear());
            holder.model.setText(datas.getModel());
            holder.color.setText(datas.getColor());
            holder.plate.setText(datas.getPlate());
            holder.phone.setText(datas.getPhone());
            holder.email.setText(datas.getEmail());
            holder.key.setText(datas.getKey());
            holder.vehicle.setText(datas.getVehicle());

        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolderOpenTicket extends RecyclerView.ViewHolder {
        TextView brand, model, year, color, plate, phone, email,key, vehicle;

        public MyViewHolderOpenTicket(View itemView) {
            super(itemView);

            brand = itemView.findViewById(R.id.op__card_brand_content);
            model = itemView.findViewById(R.id.op__card_model_content);
            year = itemView.findViewById(R.id.op__card_year_content);
            color = itemView.findViewById(R.id.op__card_color_content);
            plate = itemView.findViewById(R.id.op__card_plate);
            phone = itemView.findViewById(R.id.op__card_telephone_content);
            email = itemView.findViewById(R.id.op__card_email_content);
            key = itemView.findViewById(R.id.op__card_key_content);
            vehicle = itemView.findViewById(R.id.op__card_vehicle_content);
        }
    }
}