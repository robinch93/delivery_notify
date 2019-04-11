package com.example.restaurantmanager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EditRestaurantAct extends Activity {

    private Button saveBtn;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private ImageButton imageView;
    private Bitmap photo;
    public static final String Profile_data = "profile_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);
        EditText nameTxt = (EditText)findViewById(R.id.nameTxt);
        EditText emailTxt = (EditText)findViewById(R.id.emailTxt);
        EditText descriptionTxt = (EditText)findViewById(R.id.descriptionTxt);
        EditText addressTxt = (EditText)findViewById(R.id.addressTxt);
        imageView = (ImageButton) findViewById(R.id.profImgBtn);
        nameTxt.setText(getIntent().getStringExtra("nameTv"));
        emailTxt.setText(getIntent().getStringExtra("emailTv"));
        descriptionTxt.setText(getIntent().getStringExtra("descriptionTv"));
        addressTxt.setText(getIntent().getStringExtra("addressTv"));
        loadImageFromStorage(getIntent().getStringExtra("picturePath"), imageView);
//        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("Bitmap");
//        imageView.setImageBitmap(bitmap);
        saveBtn = (Button)findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Intent mainAct= new Intent(EditRestaurantAct.this,MainActivity.class);
//                startActivity(mainAct);
                Intent resultIntent = new Intent();
                // TODO Add extras or a data URI to this intent as appropriate.
                EditText nameTxt = (EditText)findViewById(R.id.nameTxt);
                EditText emailTxt = (EditText)findViewById(R.id.emailTxt);
                EditText descriptionTxt = (EditText)findViewById(R.id.descriptionTxt);
                EditText addressTxt = (EditText)findViewById(R.id.addressTxt);
                resultIntent.putExtra("nameTxt", nameTxt.getText().toString());
                resultIntent.putExtra("emailTxt", emailTxt.getText().toString());
                resultIntent.putExtra("descriptionTxt", descriptionTxt.getText().toString());
                resultIntent.putExtra("addressTxt", addressTxt.getText().toString());
                resultIntent.putExtra("picturePath", getIntent().getStringExtra("picturePath"));
                SharedPreferences.Editor editor = getSharedPreferences(Profile_data, MODE_PRIVATE).edit();
                editor.putString("nameTxt", nameTxt.getText().toString());
                editor.putString("emailTxt", emailTxt.getText().toString());
                editor.putString("descriptionTxt", descriptionTxt.getText().toString());
                editor.putString("addressTxt", addressTxt.getText().toString());
                editor.putString("picturePath",getIntent().getStringExtra("picturePath"));
                editor.apply();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });

//        ImageButton photoButton = (ImageButton) this.findViewById(R.id.profImgBtn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            saveToInternalStorage(photo);
//            imageView.setImageBitmap(photo);
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            loadImageFromStorage(directory.toString(), imageView);
        }
    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
    private void loadImageFromStorage(String path, ImageButton imageView)
    {
        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//            ImageView img=(ImageView)findViewById(R.id.imgPicker);
            imageView.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}