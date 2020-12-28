package com.example.ezpark;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ReservationConfirmFragment extends Fragment {

    String username, city, parking, time, date;
    double longitude, latitude;
    DatabaseHelper db;
    TextView username1;
    TextView city1;
    TextView parking1;
    TextView time1;
    TextView date1;
    Button navigate;
    ImageView qr;

    public ReservationConfirmFragment(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        username = intent.getStringExtra("username");
        city = intent.getStringExtra("parking_city");
        parking = intent.getStringExtra("parking_name");
        time = intent.getStringExtra("time");
        date = intent.getStringExtra("date");
        longitude = intent.getDoubleExtra("parking_longitude",0.0);
        latitude = intent.getDoubleExtra("parking_latitude",0.0);

        qr = (ImageView) getView().findViewById(R.id.QR_code);

        String data = username + city + parking + date + time;
        QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 50);
        Bitmap bitmap = qrgEncoder.getBitmap();
        qr.setImageBitmap(bitmap);

        username1 = (TextView) getView().findViewById(R.id.real_username);
        username1.setText(username + "");
        city1 = (TextView) getView().findViewById(R.id.real_city);
        city1.setText(city + "");
        parking1 = (TextView) getView().findViewById(R.id.real_parking);
        parking1.setText(parking + "");
        time1 = (TextView) getView().findViewById(R.id.real_time);
        time1.setText(time + "");
        date1 = (TextView) getView().findViewById(R.id.real_date);
        date1.setText(date + "");
        navigate = (Button) getView().findViewById(R.id.navigation_button);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri navUri = Uri.parse("google.navigation:q="+latitude+","+longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW,navUri);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reservation_confirm_fragment, container, false);
        return v;
    }


}
