package com.example.dharani.navigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register_Benefits_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__benefits_);
        Button registerIntent = (Button) findViewById(R.id.movePayProfile);
        registerIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regIntent=new Intent(v.getContext(),Pay_Register_Profile_Activity.class);
                startActivity(regIntent);
            }
        });

    }
}
