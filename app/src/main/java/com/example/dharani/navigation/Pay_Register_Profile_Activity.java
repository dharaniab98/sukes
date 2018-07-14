package com.example.dharani.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Pay_Register_Profile_Activity extends AppCompatActivity {
    public static final String DEFAULT="Not Available";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay__register__profile_);

        final EditText email = (EditText) findViewById(R.id.regemail);
        final EditText uphno = (EditText) findViewById(R.id.reg_mob_num);
        SharedPreferences pshared = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        String rmail = pshared.getString("spemail", DEFAULT);
        String rphno = pshared.getString("spphno", DEFAULT);
        // On complete call either onLoginSuccess or onLoginFailed
        email.setText(rmail);
        uphno.setText(rphno);


        Button registerIntent = (Button) findViewById(R.id.movePay);
        registerIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regIntent=new Intent(v.getContext(),PayRegister_Activity.class);
                startActivity(regIntent);
            }
        });




    }
}
