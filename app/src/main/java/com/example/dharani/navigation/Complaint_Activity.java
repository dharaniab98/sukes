package com.example.dharani.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class Complaint_Activity extends AppCompatActivity {


    AutoCompleteTextView serviceName;
    EditText serviceType;
    AutoCompleteTextView serviceMail;
    AutoCompleteTextView serviceMobile;
    AutoCompleteTextView serviceCity;
    AutoCompleteTextView serviceStreet;
    AutoCompleteTextView serviceDrNo;
    AutoCompleteTextView servicePinCode;
    private Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        getSupportActionBar().setTitle("Service Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String service_type = getIntent().getStringExtra("TYPE_SERVICE");
        Log.d("service is:",""+service_type);

        EditText textView = (EditText) findViewById(R.id.type);
        textView.setText(service_type);
        sub=(Button)findViewById(R.id.submit);
        serviceType=(EditText) findViewById(R.id.type);
        servicePinCode=(AutoCompleteTextView)findViewById(R.id.pin);
        serviceCity=(AutoCompleteTextView)findViewById(R.id.city);
        serviceDrNo=(AutoCompleteTextView)findViewById(R.id.d_no);
        serviceStreet=(AutoCompleteTextView)findViewById(R.id.street);
        serviceName=(AutoCompleteTextView)findViewById(R.id.name);
        serviceMail=(AutoCompleteTextView)findViewById(R.id.email);
        serviceMobile=(AutoCompleteTextView)findViewById(R.id.mob_num);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate())
                {
                    Toast.makeText(getBaseContext(), "failed", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "sucess", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    private boolean validate()
    {
        boolean valid=true;
        String name=serviceName.getText().toString();
        String mob=serviceMobile.getText().toString();
        String email=serviceMail.getText().toString();
        String street=serviceStreet.getText().toString();
        String type=serviceType.getText().toString();
        String city=serviceCity.getText().toString();
        String drNo=serviceDrNo.getText().toString();
        String pin=servicePinCode.getText().toString();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            serviceMail.setError("enter a valid email address");
            valid = false;
        } else {
            serviceMail.setError(null);
        }
        if (mob.isEmpty() || !Patterns.PHONE.matcher(mob).matches()) {
            serviceMobile.setError("enter a mobile number");
            valid = false;
        } else {
            serviceMobile.setError(null);
        }
        if (name.isEmpty()) {
            serviceName.setError("enter a name");
            valid = false;
        } else {
            serviceName.setError(null);
        }
        if (street.isEmpty() ) {
            serviceStreet.setError("enter a valid street address");
            valid = false;
        } else {
            serviceStreet.setError(null);
        }
        if (drNo.isEmpty() ) {
            serviceDrNo.setError("enter a  drno address");
            valid = false;
        } else {
            serviceDrNo.setError(null);
        }

        if (city.isEmpty() ) {
            serviceCity.setError("enter a city ");
            valid = false;
        } else {
            serviceCity.setError(null);
        }
        if (type.isEmpty() ) {
            serviceType.setError("enter a service");
            valid = false;
        } else {
            serviceType.setError(null);
        }
        if (pin.isEmpty() ) {
            servicePinCode.setError("enter a valid PinCode");
            valid = false;
        } else {
            servicePinCode.setError(null);
        }
        return valid;
    }






}
