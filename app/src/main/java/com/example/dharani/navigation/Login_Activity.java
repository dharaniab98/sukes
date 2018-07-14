package com.example.dharani.navigation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class Login_Activity extends Activity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    Button loginButton;
    TextView  signupLink;
    EditText emailText;
    EditText passwordText;
//login page user entering data
    String email;
    String password;


  //login page requested data froom service
    String status;

    String userid;
    String refCode;
    String paystatus;
     String user_Pref;


    //Used to store profile personal Details values
       String profileFirstName;
       String profileLastName;
       String altPin;
       //0String profileFullName;
       //1String profileUserName;
       //2String profileEmail;
       //3String profilePhoneNumber1;
       //4String profilePhoneNumber2;
       //5String profileAdharCardNo;
       //6String profilePanCardNo;

    //Used to store profile Address 1 Details values
       //7String contactStreet;
       //8String contactCity;
       //9String contactState;
       //10String contactCountry;
       //11String contactPinCode;

       //Used to store Service Address
       //12String serviceStreet;
    //13String serviceCity;
    //14String serviceState;
    //15String serviceCountry;


    //Uzsed to store paymentdetails
     //16String profileBankName;
     //17String profileBankAccNo;
     //18String profileISFCCode;
     //19String profileBankAddress;
    String profile[]=new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        loginButton=findViewById(R.id.btn_login);
        signupLink=(TextView)findViewById(R.id.link_signup);
        emailText=(EditText)findViewById(R.id.input_email);
        passwordText=(EditText)findViewById(R.id.input_password);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });




        signupLink.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Signup_Activity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
         }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this,
                R.style.AppTheme_NoActionBar);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        email = emailText.getText().toString();
        Log.d("email:","is:"+email);
        password = passwordText.getText().toString();
        Log.d("password:","is:"+password);

        String url = "http://www.sukes.in/applogin/"+email+"/"+password;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("loggedInId");
                             status = obj.getString("result");
                            userid=obj.getString("userId");
                            refCode=obj.getString("refCode");
                            paystatus=obj.getString("paystatus");
                            Toast.makeText(getBaseContext(), refCode, Toast.LENGTH_LONG).show();

                            Log.d("status is:",""+status);
                            Log.d("status is:",""+refCode );
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
        final SaveSharedPreference preference = new SaveSharedPreference();
        final String status_Pref = preference.getPrefStatus(this);
              user_Pref = preference.getPrefUserid(this);


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        user_Pref=userid;
                        // On complete call either onLoginSuccess or onLoginFailed
                        if(status.equals("true")){ //|| status_Pref.equals("true")){
                            storeProfile();
                            onLoginSuccess();
                         }else
                        {
                            onLoginFailed();
                        }

                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
                System.gc();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        SaveSharedPreference obj = new SaveSharedPreference();
        obj.setPrefStatus(this,status,userid,refCode,paystatus);

      //  obj.setPrefStatus(this,userid);
        this.finish();
        System.gc();
        Intent home = new Intent(this,MainActivity.class);
        startActivity(home);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty()) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
    public void storeProfile(){
        String url = "http://www.sukes.in/appprofile/"+user_Pref;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("loggedInId");
                            profileFirstName=obj.getString("first_name");
                            profileLastName=obj.getString("last_name");
                            profile[1]=obj.getString("user_name");
                           profile[2]= obj.getString("email_id");
                           profile[3]=obj.getString("phone_no");
                            profile[4] =obj.getString("alt_phone");
                            profile[5]=obj.getString("aadhaarNo");;
                            profile[6]=obj.getString("panCard");

                            //Address Contact
                            profile[7]=obj.getString("address");
                            profile[8]=obj.getString("cityName");
                            profile[9]=obj.getString("stateName");
                            profile[10]=obj.getString("countryName");
                            profile[11]=obj.getString("pincode");

                            //Service Address
                            profile[12]=obj.getString("alt_address");
                            profile[13]=obj.getString("altcityName");
                            profile[14]=obj.getString("altstateName");
                            profile[15]=obj.getString("altcountryName");
                            altPin=obj.getString("alt_pincode");
                            //servicePinCode=obj.getString("pincode");


                            //payment Details

                            profile[16]=obj.getString("bankName");
                            profile[17]=obj.getString("bankAccNo");
                            profile[18]=obj.getString("bankIFSC");
                            profile[19]=obj.getString("bankAddress");


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
                SharedPreferences profileSharedPreferences=getSharedPreferences("MyData",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=profileSharedPreferences.edit();
                profile[0]=profileFirstName.toString()+profileLastName.toString();
                profile[15]=profile[15]+"-"+altPin;
                //dggfhf
                        profile[7]+=",";
                        profile[8]+=",";
                        profile[9]+=",";
                        profile[10]+="-";
                        profile[12]+=",";
                        profile[13]+=",";
                        profile[14]+=",";
                for(int i=0;i<profile.length;i++) {
                    editor.putString("profile"+String.valueOf(i), profile[i].toString());

                  //  editor.putString("spphno", profile[6].toString());
                }
                editor.apply();

                    }
                }, 3000);


    }
}
