package com.example.dharani.navigation;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Signup_Activity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    EditText nameText;
    EditText passwordText;
    EditText emailText;
    EditText refcode;
    TextView loginLink;
    AppCompatButton signupButton,buttonConfirm;
    EditText phoneText,editTextConfirmOtp;

    String status;
    String username;
    String password;
    String referral;
    String phone;
    String email;
    String otp;
    String user_id;
    String otp_status;
    int otp_regcheck;
    String otp_userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        loginLink=(TextView)findViewById(R.id.link_login);
        signupButton=(AppCompatButton)findViewById(R.id.btn_signup);
        nameText=(EditText)findViewById(R.id.input_name);
        emailText=(EditText)findViewById(R.id.input_email);
        passwordText=(EditText)findViewById(R.id.input_password);
        phoneText=(EditText)findViewById(R.id.input_phone);
        refcode = (EditText)findViewById(R.id.input_referral);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup_Activity.this,
                R.style.AppTheme_NoActionBar);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        username = nameText.getText().toString();
        email = emailText.getText().toString();
        password = passwordText.getText().toString();
        phone = phoneText.getText().toString();
        referral = refcode.getText().toString();
        if(referral.equals("")){
            referral = "SUK0000";
        }

        String url = "http://www.sukes.in/appregister/"+username+"/"+email+"/"+phone+"/"+password+"/"+referral;
        //signup networking code
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("loggedInId");
                            status = obj.getString("result");
                            Log.d("status is:",""+status);
                            user_id = obj.getString("userId");
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
                        if(status.equals("true")){
                            confirmOtp();
                        }
                       else {
                             onSignupFailed();
                        }
                        progressDialog.dismiss();
                    }
                }, 3000);
    } 


    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        //confirmOtp();
        SaveSharedPreference obj = new SaveSharedPreference();
        obj.setPrefStatus(this,status,"","","");
        this.finish();
        System.gc();
        Intent home = new Intent(this,MainActivity.class);
        startActivity(home);
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String phone = phoneText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 10) {
            phoneText.setError("Enter Valid Number");
            valid = false;
        } else {
            phoneText.setError(null);
        }

        return valid;
    }

    // confirm otp displays confirmation screen to enter otp and send's it for verification
    private void confirmOtp(){
        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_confirm_otp, null);

        //Initizliaing confirm button fo dialog box and edittext of dialog box
        buttonConfirm = (AppCompatButton) confirmDialog.findViewById(R.id.buttonConfirm);
        editTextConfirmOtp = (EditText) confirmDialog.findViewById(R.id.editTextOtp);

        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        //On the click of the confirm button from alert dialog
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();
                Log.d("testing:","inside alert");

                //Displaying a progressbar
                final ProgressDialog loading = ProgressDialog.show(Signup_Activity.this, "Authenticating", "Please wait while we check the entered code", false, false);

                //Getting the user entered otp from edittext
                otp = editTextConfirmOtp.getText().toString().trim();


                Log.d("otp is :",""+otp);


        //Network request
                String url = "http://sukes.in/appregconfirm/"+user_id+"/"+otp;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("loggedInId");
                            otp_status = obj.getString("result");
                            otp_regcheck = obj.getInt("regcheck");
                            Log.d("status_otp is :",""+otp_status);
                            Log.d("otp reg check is:",""+otp_regcheck);

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

                MySingleton.getInstance(getBaseContext()).addToRequestQueue(jsonObjectRequest);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        if(otp_regcheck == 1){ //|| status_Pref.equals("true"))

                            onSignupSuccess();
                        }else
                        {
                            Toast.makeText(getBaseContext(),"Re-enter OTP",Toast.LENGTH_SHORT).show();
                            confirmOtp();
                        }

                        loading.dismiss();
                    }
                }, 3000);
            }});
    }



//                //Creating an string request
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CONFIRM_URL,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                //if the server response is success
//                                if(response.equalsIgnoreCase("success")){
//                                    //dismissing the progressbar
//                                    loading.dismiss();
//
//                                    //Starting a new activity
//                                    startActivity(new Intent(MainActivity.this, Success.class));
//                                }else{
//                                    //Displaying a toast if the otp entered is wrong
//                                    Toast.makeText(MainActivity.this,"Wrong OTP Please Try Again",Toast.LENGTH_LONG).show();
//                                    try {
//                                        //Asking user to enter otp again
//                                        confirmOtp();
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                alertDialog.dismiss();
//                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
//                            }
//                        }){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String,String> params = new HashMap<String, String>();
//                        //Adding the parameters otp and username
//                        params.put(Config.KEY_OTP, otp);
//                        params.put(Config.KEY_USERNAME, username);
//                        return params;
//                    }
//                };
//
//                //Adding the request to the queue
//                requestQueue.add(stringRequest);
//            }
//        });
    }

