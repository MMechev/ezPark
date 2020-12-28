package com.example.ezpark;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderCity extends RecyclerView.ViewHolder {

    public TextView cityname;
    public ImageView citypic;
    public TextView citydesc;

    public ViewHolderCity(View itemView){
        super(itemView);
        cityname = (TextView) itemView.findViewById(R.id.city_text_view);
        citypic = (ImageView) itemView.findViewById(R.id.city_image);
        citydesc = (TextView) itemView.findViewById(R.id.description_text_view);
    }

}
