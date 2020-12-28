package com.example.ezpark;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ReservationExtraFragment extends Fragment {

    public ReservationExtraFragment(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView QR_code;
        Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra("username");
        String city = intent.getStringExtra("parking_city");
        String parking = intent.getStringExtra("parking_name");
        String time = intent.getStringExtra("time");
        String date = intent.getStringExtra("date");
        double latitude = intent.getDoubleExtra("parking_latitude",0.0);
        double longitude = intent.getDoubleExtra("parking_longitude",0.0);

        QR_code = (ImageView) getView().findViewById(R.id.qr_code_extra);
        String data = username + city + parking + date + time;
        QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 300);
        Bitmap bitmap = qrgEncoder.getBitmap();
        QR_code.setImageBitmap(bitmap);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reservation_extra_fragment, container, false);
        return v;
    }
}
