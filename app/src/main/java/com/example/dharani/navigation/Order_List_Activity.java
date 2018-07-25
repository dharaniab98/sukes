package com.example.dharani.navigation;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Order_List_Activity extends AppCompatActivity {

    ArrayList<OrderData> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__list_);

        listView=(ListView)findViewById(R.id.order_list);

        dataModels= new ArrayList<OrderData>();

        dataModels.add(new OrderData("Apple Pie", "Android 1.0", "1"));
        dataModels.add(new OrderData("Banana Bread", "Android 1.1", "2"));
        dataModels.add(new OrderData("Cupcake", "Android 1.5", "3"));
        dataModels.add(new OrderData("Donut","Android 1.6","4"));
        dataModels.add(new OrderData("Eclair", "Android 2.0", "5"));
        dataModels.add(new OrderData("Froyo", "Android 2.2", "8"));
        dataModels.add(new OrderData("Gingerbread", "Android 2.3", "9"));
        dataModels.add(new OrderData("Honeycomb","Android 3.0","11"));
        dataModels.add(new OrderData("Ice Cream Sandwich", "Android 4.0", "14"));
        dataModels.add(new OrderData("Jelly Bean", "Android 4.2", "16"));
        dataModels.add(new OrderData("Kitkat", "Android 4.4", "19"));
        dataModels.add(new OrderData("Lollipop","Android 5.0","21"));
        dataModels.add(new OrderData("Marshmallow", "Android 6.0", "23"));

        adapter= new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                OrderData dataModel= dataModels.get(position);

                Snackbar.make(view, dataModel.getService_name()+"\n"+dataModel.getComplaint_date()+" API: "+dataModel.getStatus(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }


    }

