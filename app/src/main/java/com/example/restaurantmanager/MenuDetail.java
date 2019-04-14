package com.example.restaurantmanager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MenuDetail extends AppCompatActivity {

    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_details);
        Intent intent = getIntent();
        Meal item = (Meal)intent.getSerializableExtra("item");
        final Integer id = Integer.parseInt(intent.getStringExtra("id"));
//        ImageButton menuImgBtn = (ImageButton) findViewById(R.id.menuImgBtn);
        EditText menuNameTxt = (EditText)findViewById(R.id.menuNameTxt);
        EditText menuDescText = (EditText)findViewById(R.id.menuDescText);
        EditText menuPriceTxt = (EditText)findViewById(R.id.menuPriceTxt);
        EditText menuQtyTxt = (EditText)findViewById(R.id.menuQtyTxt);
//        imageView = (ImageButton) findViewById(R.id.profImgBtn);
        menuNameTxt.setText(item.getmenuName());
        menuDescText.setText(item.getmenuDesc());
        menuPriceTxt.setText(item.getmenuPrice().toString());
        menuQtyTxt.setText(item.getmenuQty().toString());
//        loadImageFromStorage(getIntent().getStringExtra("picturePath"), imageView);

        saveBtn = (Button)findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Intent mainAct= new Intent(EditRestaurantAct.this,MainActivity.class);
//                startActivity(mainAct);
                Intent resultIntent = new Intent();
                //        ImageButton menuImgBtn = (ImageButton) findViewById(R.id.menuImgBtn);
                EditText menuNameTxt = (EditText)findViewById(R.id.menuNameTxt);
                EditText menuDescText = (EditText)findViewById(R.id.menuDescText);
                EditText menuPriceTxt = (EditText)findViewById(R.id.menuPriceTxt);
                EditText menuQtyTxt = (EditText)findViewById(R.id.menuQtyTxt);
                Meal newItem = new Meal(id, "img",menuNameTxt.getText().toString(), menuDescText.getText().toString(), Double.parseDouble(menuPriceTxt.getText().toString()), Integer.parseInt(menuQtyTxt.getText().toString()));
                resultIntent.putExtra("id",id.toString());
                resultIntent.putExtra("item", newItem);
//                resultIntent.putExtra("picturePath", getIntent().getStringExtra("picturePath"));
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });

    }
}
