package com.example.dharani.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class Profile_Activity extends AppCompatActivity {

     public static final String DEFAULT="Not Available";
    //String email;
   // String phno;
   // Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        final TextView uname = (TextView) findViewById(R.id.name);
        final TextView uphno = (TextView) findViewById(R.id.phone);
        SharedPreferences pshared = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String rmail = pshared.getString("spemail", DEFAULT);
        String rphno = pshared.getString("spphno", DEFAULT);
        // On complete call either onLoginSuccess or onLoginFailed
        uname.setText(rmail);
        uphno.setText(rphno);


       /* Toolbar tb=(Toolbar)findViewById(R.id.profiletoolbar);
        setActionBar(tb);*/
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

  /*  final  SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
       Intent refresh = new Intent(this, Profile_Activity.class);
        startActivity(refresh);//Start the same Activity
        finish();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        })*/

        Button bu=(Button)findViewById(R.id.butt);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });



    }

      public void refresh() {
          final TextView uname = (TextView) findViewById(R.id.name);
        final TextView uphno = (TextView) findViewById(R.id.phone);
        SharedPreferences pshared = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String rmail = pshared.getString("spemail", DEFAULT);
        String rphno = pshared.getString("spphno", DEFAULT);
        // On complete call either onLoginSuccess or onLoginFailed
        uname.setText(rmail);
        uphno.setText(rphno);
      }
}
