package com.example.valetparking.Administrator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valetparking.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class Tickets_Adapter extends RecyclerView.Adapter<Tickets_Adapter.MyViewHolderOpenTicket> {

    private List<Tickets_Data> data;
    private Context context;

    //Constructor
    public Tickets_Adapter(Context context, ArrayList<Tickets_Data> data) {
        this.context = context;
        this.data = data;
    }

    //Actualizar los datos
    public void update(List<Tickets_Data> list) {
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
            Tickets_Data datas = data.get(position);

            holder.brand.setText(datas.getBrand());
            holder.model.setText(datas.getModel());
            holder.year.setText(datas.getYear());
            holder.color.setText(datas.getColor());
            holder.plate.setText(datas.getPlate());
            holder.telephone.setText(datas.getPhone());
            holder.email.setText(datas.getEmail());
            holder.key.setText(datas.getKey());
            holder.vehicle.setText(datas.getVehicle());

            //Seleccionar card
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Recupero los datos para mandarlos
                }
            });

        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //Mandar datos
    @Override
    public void onSend(Object o, Fragment fragment) {

    }

    public class MyViewHolderOpenTicket extends RecyclerView.ViewHolder {
        TextView brand, model, year, color, plate, telephone, email,key, vehicle;
        CardView cardView;

        public MyViewHolderOpenTicket(View itemView) {
            super(itemView);

            brand = itemView.findViewById(R.id.op__card_brand_content);
            model = itemView.findViewById(R.id.op__card_model_content);
            year = itemView.findViewById(R.id.op__card_year_content);
            color = itemView.findViewById(R.id.op__card_color_content);
            plate = itemView.findViewById(R.id.op__card_plate);
            telephone = itemView.findViewById(R.id.op__card_telephone_content);
            email = itemView.findViewById(R.id.op__card_email_content);
            key = itemView.findViewById(R.id.op__card_key_content);
            vehicle = itemView.findViewById(R.id.op__card_vehicle_content);
            cardView = itemView.findViewById(R.id.op__card_element);
        }
    }
}