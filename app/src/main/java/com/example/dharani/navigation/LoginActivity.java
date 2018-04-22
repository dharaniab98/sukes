package com.example.dharani.navigation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    AppCompatButton loginButton;
    TextView  signupLink;
    EditText emailText;
    EditText passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         loginButton=(AppCompatButton)findViewById(R.id.btn_login);
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
                        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
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

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                        R.style.AppTheme_NoActionBar);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                // TODO: Implement your own authentication logic here.

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                onLoginSuccess();
                                // onLoginFailed();
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
                finish();
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

                if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                    passwordText.setError("between 4 and 10 alphanumeric characters");
                    valid = false;
                } else {
                    passwordText.setError(null);
                }

                return valid;
    }
}

