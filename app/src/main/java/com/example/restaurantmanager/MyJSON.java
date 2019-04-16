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
    static String profile = "{}";
    static String meals ="[" +
            "{id:1,menuImg:'milkshake',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}," +
            "{id:2,menuImg:'blueberries',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}," +
            "{id:3,menuImg:'blueberries',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}," +
            "{id:4,menuImg:'blueberries',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}," +
            "{id:5,menuImg:'blueberries',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}," +
            "{id:6,menuImg:'blueberries',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}," +
            "{id:7,menuImg:'blueberries',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}," +
            "{id:8,menuImg:'blueberries',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}," +
            "{id:9,menuImg:'hamburger',menuName:'Chiken Biryani',menuDesc:'Chiken and Rice',menuPrice:10.0,menuQty:10}" +
            "]";
    static String orders = "[" +
            "{orderID:1,customerName:'Panther',status:0,lunchTime:'10:00',notes:'Add extra chips', meals:[" +
            "'1: Chiken Biryani - 2', '2: Pasta - 1']}," +
            "{orderID:2,customerName:'Panther',status:0,lunchTime:'10:00',notes:'Add extra chips', meals:[" +
            "'1: Chiken Biryani - 2', '2: Pasta - 1']}," +
            "{orderID:3,customerName:'Panther',status:0,lunchTime:'10:00',notes:'Add extra chips', meals:[" +
            "'1: Chiken Biryani - 2', '2: Pasta - 1']}," +
            "{orderID:4,customerName:'Panther',status:0,lunchTime:'10:00',notes:'Add extra chips', meals:[" +
            "'1: Chiken Biryani - 2', '2: Pasta - 1']}," +
            "{orderID:5,customerName:'Panther',status:0,lunchTime:'10:00',notes:'Add extra chips', meals:[" +
            "'1: Chiken Biryani - 2', '2: Pasta - 1']}," +
            "{orderID:6,customerName:'Panther',status:0,lunchTime:'10:00',notes:'Add extra chips', meals:[" +
            "'1: Chiken Biryani - 2', '2: Pasta - 1']}," +
            "{orderID:7,customerName:'Panther',status:0,lunchTime:'10:00',notes:'Add extra chips', meals:[" +
            "'1: Chiken Biryani - 2', '2: Pasta - 1']}," +
            "{orderID:8,customerName:'Panther',status:0,lunchTime:'10:00',notes:'Add extra chips', meals:[" +
            "'1: Chiken Biryani - 2', '2: Pasta - 1']}" +

            "]";


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
            if( result.isEmpty())
            {
                switch(num) {
                    case 0:
                        saveData(context, profile, num);
                        result = profile;
                        break;
                    case 1:
                        saveData(context, meals, num);
                        result = meals;
                        break;
                    case 2:
                        saveData(context, orders, num);
                        result = orders;
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