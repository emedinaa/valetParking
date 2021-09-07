package com.example.valetparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardView_Adapter extends RecyclerView.Adapter<CardView_Adapter.MyViewHolderCardView> implements View.OnClickListener{

    List<CardView_Data> data;
    Context context;
    private View.OnClickListener listener;

    //Constructor
    public CardView_Adapter(Context context, List<CardView_Data> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolderCardView onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.gen__card_view_element, parent, false);

        view.setOnClickListener(this);

        return new MyViewHolderCardView(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolderCardView holder, int position) {
        if(data != null && data.size() >0){
            CardView_Data datas = data.get(position);

            holder.card_text_view.setText(datas.getCard_text_view());
            holder.card_image_view.setImageResource(datas.getCard_image_view());
        } else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickLister(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    //Clase View Holder
    public class MyViewHolderCardView extends  RecyclerView.ViewHolder{

        ImageView card_image_view;
        TextView card_text_view;

        public MyViewHolderCardView(View itemView){
            super(itemView);

            card_image_view = itemView.findViewById(R.id.card_image_view);
            card_text_view = itemView.findViewById(R.id.card_text_view);
        }
    }
}
