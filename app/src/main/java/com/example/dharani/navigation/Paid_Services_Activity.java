package com.example.dharani.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Paid_Services_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid__services_);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("service1");
        arrayList.add("service2");
        arrayList.add("service3");
        arrayList.add("service4");
        arrayList.add("service5");
        arrayList.add("service6");
        arrayList.add("service7");
        arrayList.add("service8");
        arrayList.add("service9");
        arrayList.add("service10");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_paid_services,arrayList);
        ListView paid_services = findViewById(R.id.paid_services_list);
        paid_services.setAdapter(adapter);
    }
}
