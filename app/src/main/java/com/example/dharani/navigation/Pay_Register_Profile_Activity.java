package com.example.dharani.navigation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Pay_Register_Profile_Activity extends AppCompatActivity {
    private EditText streetprofile;
    private EditText pincodeprofile;
    private EditText doornoprofile;
    private String ids[]=new String[3];
    private String statusprofile;

    public static final String DEFAULT="Not Available";
    //country details
    private HashMap<String,String> hm=new HashMap<String,String>();
   private  ArrayAdapter<String> countryAdapter;
    private Spinner spinnerCountry;
    //String countryPosition;
//State details
private HashMap<String,String> hms=new HashMap<String, String>();
    private ArrayAdapter<String> stateAdapter;
    private Spinner spinnerState;
    private int statePosition;
//city
private HashMap<String,String> hmc=new HashMap<String, String>();
    private ArrayAdapter<String> cityAdapter;
    private Spinner spinnerCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay__register__profile_);
      getCountries();

        //getState(String );
       // final EditText email = (EditText) findViewById(R.id.regemail);
       // final EditText uphno = (EditText) findViewById(R.id.reg_mob_num);
       // SharedPreferences pshared = getSharedPreferences("MyData", Context.MODE_PRIVATE);

       // String rmail = pshared.getString("spemail", DEFAULT);
       // String rphno = pshared.getString("spphno", DEFAULT);
        // On complete call either onLoginSuccess or onLoginFailed
      //  email.setText(rmail);
       // uphno.setText(rphno);

         streetprofile=(EditText)findViewById(R.id.street);
         pincodeprofile=(EditText)findViewById(R.id.pin);
         doornoprofile=(EditText)findViewById(R.id.d_no);
        SharedPreferences  pshared = getSharedPreferences("MyData", Context.MODE_PRIVATE);
//        if((pshared.getString("profile11", "null")!=null))
//        {
//            pincodeprofile.setText((pshared.getString("profile11", "").toString()));
//        }

        Button registerIntent = (Button) findViewById(R.id.movePay);
        registerIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate())
                {
                    Toast.makeText(getBaseContext(), "failed", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "sucess", Toast.LENGTH_LONG).show();
                    sendRegisteredData();
                }

            }
        });




    }


public  void getCountries(){
       //final String countryPosition;
    String url ="http://www.sukes.in/allcountries";
      int Position1;
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("countryData");
                         for(int i=0;i<jsonArray.length();i++)
                         {
                             JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                             hm.put(jsonObject.getString("country_name").toString(),jsonObject.getString("country_id").toString());

                         }




                        //    Log.d("status is:",""+email);
                    }catch(Exception e){
                        Log.e("json parse error:","exception:"+e);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error","connection error in networking "+error);

                }
            });

    // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    // final SaveSharedPreference preference = new SaveSharedPreference();
    // final String status_Pref = preference.getPrefStatus(this);


        new android.os.Handler().postDelayed(
                new Runnable() {
        public void run() {
            int i=1;
            final String []scountry=new String[hm.size()+1];
            String st=String.valueOf(hm.size());
            scountry[0]="country";
            Toast.makeText(getBaseContext(),st,Toast.LENGTH_LONG).show();
            for(HashMap.Entry<String,String>entry:hm.entrySet())
            {
                scountry[i]=entry.getKey();
                i++;
            }

            spinnerCountry=(Spinner)findViewById(R.id.spinnerCountry);
            countryAdapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,scountry);
          //  countryAdapter.add("Country");
            countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCountry.setAdapter(countryAdapter);
            spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(scountry[position].equals("country"))
                    {
                        hms.clear();
                        hmc.clear();
                        Toast.makeText(getBaseContext(),"SelectCountry",Toast.LENGTH_LONG).show();
                    }
                  //  else {
                        String countryPosition = hm.get(scountry[position]);
                        ids[0] = countryPosition;
                        hms.clear();
                        getState(countryPosition);
                        //Toast.makeText(getBaseContext(), scountry[position] + hm.get(scountry[position]), Toast.LENGTH_LONG).show();
                   // }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //String con=spinnerCountry.getSelectedItem().toString();
            //Toast.makeText(getBaseContext(),con,Toast.LENGTH_LONG).show();


        }
    }, 3000);


}


//getting  State Detailas




    public  void getState(String id){
     //String countryid=String.valueOf(countryPosition);
        String url ="http://www.sukes.in/allstates/"+id;
        int Position1;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("stateData");
                            Log.e("hello",response.toString());
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                                hms.put(jsonObject.getString("state_name").toString(),jsonObject.getString("state_id").toString());

                            }




                            //    Log.d("status is:",""+email);
                        }catch(Exception e){
                            Log.e("json parse error:","exception:"+e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error","connection error in networking "+error);

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        // final SaveSharedPreference preference = new SaveSharedPreference();
        // final String status_Pref = preference.getPrefStatus(this);


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        int i=1;
                        final String []sstate=new String[hms.size()+1];
                        sstate[0]="state";
                      //  String st=String.valueOf(hms.size());
                        //Toast.makeText(getBaseContext(),st,Toast.LENGTH_LONG).show();
                        for(HashMap.Entry<String,String>entry:hms.entrySet())
                        {
                            sstate[i]=entry.getKey();
                            i++;
                        }

                        spinnerState=(Spinner)findViewById(R.id.spinnerState);

                        stateAdapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,sstate);
                        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerState.setAdapter(stateAdapter);
                        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(sstate[position].equals("state"))
                                {
                                    hmc.clear();

                                }

                                    String StatePosition = hms.get(sstate[position]);
                                    ids[1] = StatePosition;
                                    hmc.clear();
                                    getCity(StatePosition);
                               //     Toast.makeText(getBaseContext(), sstate[position] + hms.get(sstate[position]), Toast.LENGTH_LONG).show();

                                }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });




                    }
                }, 3000);


    }

public void getCity(String id)
{
    String url ="http://www.sukes.in/allcities/"+id;
    int Position1;
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("cityData");
                        Log.e("hello",response.toString());
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                            hmc.put(jsonObject.getString("city_name").toString(),jsonObject.getString("city_id").toString());

                        }




                        //    Log.d("status is:",""+email);
                    }catch(Exception e){
                        Log.e("json parse error:","exception:"+e);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error","connection error in networking "+error);

                }
            });

    // Access the RequestQueue through your singleton class.
    MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    // final SaveSharedPreference preference = new SaveSharedPreference();
    // final String status_Pref = preference.getPrefStatus(this);


    new android.os.Handler().postDelayed(
            new Runnable() {
                public void run() {

                    int i=1;
                    final String []scity=new String[hmc.size()+1];
                    //String st=String.valueOf(hmc.size());
                    //Toast.makeText(getBaseContext(),st,Toast.LENGTH_LONG).show();
                    scity[0]="city";
                    for(HashMap.Entry<String,String>entry:hmc.entrySet())
                    {
                        scity[i]=entry.getKey();
                        i++;
                    }

                    spinnerCity=(Spinner)findViewById(R.id.spinnerCity);

                    cityAdapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,scity);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(cityAdapter);
                    spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(scity[position].equals("city"))
                            {
                                Toast.makeText(getBaseContext(),"selectstate",Toast.LENGTH_SHORT).show();
                            }


                                String cityPosition = hmc.get(scity[position]);
                                ids[2] = cityPosition;
                                //getState(countryPosition);

                             //   Toast.makeText(getBaseContext(), scity[position] + hms.get(scity[position]), Toast.LENGTH_LONG).show();

                            }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });




                }
            }, 3000);



}


public void sendRegisteredData()
{


    final SaveSharedPreference preference = new SaveSharedPreference();
   // final String status_Pref = preference.getPrefStatus(this);
   String userid = preference.getPrefUserid(this);


    final ProgressDialog progressDialog = new ProgressDialog(Pay_Register_Profile_Activity.this,
            R.style.AppTheme_NoActionBar);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Registering Account...");
    progressDialog.show();

    String streettext = streetprofile.getText().toString();
    String pincode = pincodeprofile.getText().toString();
    String doorno=doornoprofile.getText().toString();
     Toast.makeText(getBaseContext(),streettext+ids[0]+ids[1]+userid,Toast.LENGTH_LONG).show();
    Log.e("val",streettext+ids[0]+ids[1]+userid);
    String url = "http://www.sukes.in/app-locaton/"+userid+"/"+doorno+"/"+streettext+"/"+ids[0]+"/"+ids[1]+"/"+ids[2]+"/"+pincode;
    //signup networking code
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject obj = response.getJSONObject("locationupdate");
                        statusprofile = obj.getString("result");
                        Log.d("status is:",""+statusprofile);

                    }catch(Exception e){
                        Log.e("json parse error:","exception:"+e);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error","connection error in networking "+error);

                }
            });

    // Access the RequestQueue through your singleton class.
    MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    new android.os.Handler().postDelayed(
            new Runnable() {
                public void run() {
                    // On complete call either onSignupSuccess or onSignupFailed
                    // depending on success
                    if(statusprofile.equals("true")){
                       Toast.makeText(getBaseContext(),"hello",Toast.LENGTH_SHORT).show();
                    }
                    else {

                    }
                    progressDialog.dismiss();
                }
            }, 3000);
}



    private boolean validate()
    {
        boolean valid=true;
      //  String name=serviceName.getText().toString();
      //  String mob=serviceMobile.getText().toString();
       // String email=serviceMail.getText().toString();
        String street=streetprofile.getText().toString();
       // String type=serviceType.getText().toString();
        //String city=serviceCity.getText().toString();
        String drNo=doornoprofile.getText().toString();
        String pin=pincodeprofile.getText().toString();
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            serviceMail.setError("enter a valid email address");
//            valid = false;
//        } else {
//            serviceMail.setError(null);
//        }
//        if (mob.isEmpty() || !Patterns.PHONE.matcher(mob).matches()) {
//            serviceMobile.setError("enter a mobile number");
//            valid = false;
//        } else {
//            serviceMobile.setError(null);
//        }
//        if (name.isEmpty()) {
//            serviceName.setError("enter a name");
//            valid = false;
//        } else {
//            serviceName.setError(null);
//        }
        if (street.isEmpty() ) {
            streetprofile.setError("enter a valid street address");
            valid = false;
        } else {
            streetprofile.setError(null);
        }
        if (drNo.isEmpty() ) {
            doornoprofile.setError("enter a  drno address");
            valid = false;
        } else {
            doornoprofile.setError(null);
        }

//        if (city.isEmpty() ) {
//            serviceCity.setError("enter a city ");
//            valid = false;
//        } else {
//            serviceCity.setError(null);
//        }

        if (pin.isEmpty() ) {
            pincodeprofile.setError("enter a valid PinCode");
            valid = false;
        } else {
            pincodeprofile.setError(null);
        }
        return valid;
    }



}




