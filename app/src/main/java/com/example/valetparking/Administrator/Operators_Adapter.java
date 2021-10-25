package com.example.valetparking.Administrator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valetparking.Database.Interfaces.Operators;
import com.example.valetparking.Database.RetrofitClient;
import com.example.valetparking.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Operators_Adapter extends RecyclerView.Adapter<Operators_Adapter.MyViewHolderOperators> {

    List<Operators_Data> data;
    Context context;

    //Constructor
    public Operators_Adapter(Context context, List<Operators_Data> data) {
        this.context = context;
        this.data = data;
    }

    //Actualizar los datos
    public void update(List<Operators_Data> list) {
        data = list;
        notifyDataSetChanged();
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

            holder.username.setText(datas.getUsername());
            holder.hour_entry.setText(String.valueOf(datas.getHourIn()));
            holder.hour_exit.setText(String.valueOf(datas.getHourOut()));
            holder.vehicle_entry.setText(String.valueOf(datas.getVehiclesIn()));
            holder.vehicle_exit.setText(String.valueOf(datas.getVehiclesOut()));

            //Eliminar registro
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Retrofit retrofit = RetrofitClient.getRetrofitClient();

                    Call<ResponseBody> call = retrofit.create(Operators.class).deleteOperatorForUsername(datas.getUsername());

                    System.out.println("=====================" + datas.getUsername());

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(v.getContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(v.getContext(), "Operator: " + datas.getUsername() + " deleted", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(v.getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
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

    //Clase View Holder
    public class MyViewHolderOperators extends RecyclerView.ViewHolder {
        TextView username, hour_entry, hour_exit, vehicle_entry, vehicle_exit;
        ImageView delete;

        public MyViewHolderOperators(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.adm__card_username);
            hour_entry = itemView.findViewById(R.id.adm__card_hour_entry_content);
            hour_exit = itemView.findViewById(R.id.adm__card_hour_exit_content);
            vehicle_entry = itemView.findViewById(R.id.adm__card_vehicles_entry_content);
            vehicle_exit = itemView.findViewById(R.id.adm__card_vehicles_exit_content);
            delete = itemView.findViewById(R.id.adm__card_delete);
        }
    }
}
