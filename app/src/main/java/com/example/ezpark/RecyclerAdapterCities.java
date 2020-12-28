package com.example.ezpark;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class RecyclerAdapterCities extends RecyclerView.Adapter<ViewHolderCity> {

    ArrayList<City> list;
    Context context;
    String username;

    public RecyclerAdapterCities(ArrayList<City> cities, Application application, String username){
        this.list = cities;
        this.context = application;
        this.username = username;
    }

    @NonNull
    @Override
    public ViewHolderCity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city,parent,false);
        ViewHolderCity holder = new ViewHolderCity(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCity holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ReservationFormActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("city_name", list.get(position).getCityName());
                intent.putExtra("city_img", list.get(position).getImageResourceId());
                context.startActivity(intent);
            }
        });
        holder.cityname.setText(list.get(position).getCityName());
        holder.citypic.setImageResource(list.get(position).getImageResourceId());
        holder.citydesc.setText(list.get(position).getDescription());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


}
