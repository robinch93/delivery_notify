package com.example.restaurantmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Hema Bhandari on 4/8/2019.
 */

public class MenuActivity extends Activity {
    int[] IMAGES ={android.R.drawable.appetizers,android.R.drawable.blueberries,android.R.drawable.broiled,
                   android.R.drawable.coffee,android.R.drawable.donut,android.R.drawable.hamburger,
            android.R.drawable.milkshake,android.R.drawable.oranges,android.R.drawable.pizza,android.R.drawable.turkey};
    String[] NAMES = {"appetizers", "blueberries", "broiled","coffee","donut","hamburger","milkshake","oranges","pizza","turkey"};
    String[] DESCRIPTION = {"abc", "abc", "abc", "abc","abc", "abc", "abc", "abc","abc", "abc"};
    String[] PRICE = {"€ 6", "€ 6","€ 6", "€ 6","€ 6", "€ 6","€ 6", "€ 6","€ 6", "€ 6"};
    String[] QTY = {"200gm", "500gm","200gm", "500gm","200gm", "500gm","200gm", "500gm","200gm", "500gm"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        ListView menuListView = (ListView)findViewById(R.id.menuListView);

        menuLvAdapter menuLvAdapter = new menuLvAdapter();
        menuListView.setAdapter(menuLvAdapter);

    }
    class menuLvAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(android.R.layout.menuitems_layout, null);
            ImageView menuImg = (ImageView)view.findViewById(android.R.id.menuImg);
            TextView menuNameTv = (TextView)view.findViewById(android.R.id.menuNameTv);
            TextView menuDescTv = (TextView)view.findViewById(android.R.id.menuDescTv);
            TextView menuPriceTv = (TextView)view.findViewById(android.R.id.menuPriceTv);
            TextView menuQtyTv = (TextView)view.findViewById(android.R.id.menuQtyTv);

            menuImg.setImageResource(IMAGES[i]);
            menuNameTv.setText(NAMES[i]);
            menuDescTv.setText(DESCRIPTION[i]);
            menuPriceTv.setText(PRICE[i]);
            menuQtyTv.setText(QTY[i]);

            return view;
        }
    }
}
