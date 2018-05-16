package com.example.dharani.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Complaint_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        getSupportActionBar().setTitle("Service Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String service_type = getIntent().getStringExtra("TYPE_SERVICE");
        Log.d("service is:",""+service_type);

        TextView textView = (TextView) findViewById(R.id.type);
        textView.setText(service_type);
    }
}
