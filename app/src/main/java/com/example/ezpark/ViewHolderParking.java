package com.example.ezpark;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class ViewHolderParking extends RecyclerView.ViewHolder {

    public TextView parking_name;
    public TextView free_spaces;
    public TextView reserved_spaces;
    public ImageView reserved;
    public ImageView free;

    public ViewHolderParking(View itemView){
        super(itemView);
        parking_name = (TextView) itemView.findViewById(R.id.parking_name);
        free_spaces = (TextView) itemView.findViewById(R.id.freetext);
        reserved_spaces = (TextView) itemView.findViewById(R.id.reservedtext);
        reserved = (ImageView) itemView.findViewById(R.id.reservedspaces);
        free = (ImageView) itemView.findViewById(R.id.freespaces);
    }
}
