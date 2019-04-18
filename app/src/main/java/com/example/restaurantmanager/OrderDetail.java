package com.example.restaurantmanager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OrderDetail extends AppCompatActivity {

    private Button saveBtn;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int PICK_PHOTO_FOR_AVATAR = 1;
    private ImageButton menuImgBtn;
    private String imageName;
    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        Intent intent = getIntent();
        Order item = (Order)intent.getSerializableExtra("item");
        final Integer id = Integer.parseInt(intent.getStringExtra("id"));
        menuImgBtn = (ImageButton) findViewById(R.id.menuImgBtn);
        TextView orderID = (TextView)findViewById(R.id.orderID);
        TextView customerName = (TextView)findViewById(R.id.customerName);
        TextView status = (TextView)findViewById(R.id.status);
//        imageView = (ImageButton) findViewById(R.id.profImgBtn);
        orderID.setText(item.getOrderID().toString());
        customerName.setText(item.getcustomerName());
        status.setText(item.getstatus().toString());

        ImageButton backButton = (ImageButton)this.findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
