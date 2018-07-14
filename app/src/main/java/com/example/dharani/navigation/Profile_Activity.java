package com.example.dharani.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.w3c.dom.Text;

import static android.content.Context.MODE_PRIVATE;

public class Profile_Activity extends AppCompatActivity {

     public static final String DEFAULT="Not Available";
    //String email;
   // String phno;
   // Context context;
    TextView t[]=new TextView[20];
    String txt[]=new String[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        t[0] = (TextView) findViewById(R.id.person0);
        t[1] = (TextView) findViewById(R.id.person1);
        t[2] = (TextView) findViewById(R.id.person2);
        t[3] = (TextView) findViewById(R.id.person3);
        t[4] = (TextView) findViewById(R.id.person4);
        t[5] = (TextView) findViewById(R.id.person5);
        t[6] = (TextView) findViewById(R.id.person6);
        t[7] = (TextView) findViewById(R.id.person7);
        t[8] = (TextView) findViewById(R.id.person8);
        t[9] = (TextView) findViewById(R.id.person9);
        t[10] = (TextView) findViewById(R.id.person10);
        t[11] = (TextView) findViewById(R.id.person11);
        t[12] = (TextView) findViewById(R.id.person12);
        t[13] = (TextView) findViewById(R.id.person13);
        t[14] = (TextView) findViewById(R.id.person14);
        t[15] = (TextView) findViewById(R.id.person15);
        t[16] = (TextView) findViewById(R.id.person16);
        t[17] = (TextView) findViewById(R.id.person17);
        t[18] = (TextView) findViewById(R.id.person18);
        t[19] = (TextView) findViewById(R.id.person19);

        SharedPreferences pshared = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        for(int i=0;i<txt.length;i++) {
            String str="profile"+String.valueOf(i);
            txt[i] = pshared.getString(str, DEFAULT);
            t[i].setText(txt[i]);
        }
        // On complete call either onLoginSuccess or onLoginFailed


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

      /*  Button bu=(Button)findViewById(R.id.butt);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        })*/



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.profileRefresh:
                refresh();
                return true;
            // Respond to a click on the "Delete all entries" menu option
          //  case R.id.action_delete_all_entries:
                // Do nothing for now
             //   return true;
        }
        return super.onOptionsItemSelected(item);
    }



   public void refresh() {


        SharedPreferences pshared = getSharedPreferences("MyData", Context.MODE_PRIVATE);
       for(int i=0;i<txt.length;i++) {
           String str="profile"+String.valueOf(i);
           txt[i] = pshared.getString(str, DEFAULT);
           t[i].setText(txt[i]);
       }
      }
}
