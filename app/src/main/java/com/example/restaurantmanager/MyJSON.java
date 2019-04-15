package com.example.restaurantmanager;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Pratik.
 */
public class MyJSON {

    static String fileName = "myFile.json";
    static String[] files = {"profile","meals","orders"};
    static String profile = "profile: {}";
    static String meals ="[" +
            "{id:0,menuImg:'coffee.jpg',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}," +
            "{id:1,menuImg:'donut.jpg',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}," +
            "{id:2,menuImg:'broiled.jpg',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}" +
            "]";
    static String orders = "orders:{}";


    //  0= profile, 1= meal, 2= orders
    public static void saveData(Context context, String mJsonResponse, Integer num) {
        try {
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + files[num]);
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }

    public static String getData(Context context, Integer num) {
        try {
            File f = new File(context.getFilesDir().getPath() + "/" + files[num]);
            f.createNewFile();
            //check whether file exists
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String result = new String(buffer);
            if( result.isEmpty()){
                switch(num) {
                    case 0:
                        saveData(context, profile, num);
                        result = profile;
                        break;
                    case 1:
                        saveData(context, meals, num);
                        result = profile;
                        break;
                    case 2:
                        saveData(context, orders, num);
                        result = profile;
                        break;
                }
            }
            return result;
        } catch (IOException e) {
            Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            return null;
        }
    }
}