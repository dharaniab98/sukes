package com.example.dharani.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView ElectricalsIntent;
    ImageView KitchenIntent;
    ImageView SanitationIntent;
    ImageView plumberIntent;
    TextView paidIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(SaveSharedPreference.getPrefStatus(MainActivity.this).length() == 0)
        {
            Intent home = new Intent(this, Login_Activity.class);
            startActivity(home);
        }
        setContentView(R.layout.activity_main);


        ElectricalsIntent = (ImageView) findViewById(R.id.electricals);
        ElectricalsIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent electric=new Intent(v.getContext(),Complaint_Activity.class);
               startActivity(electric);
            }
        });
        KitchenIntent = (ImageView) findViewById(R.id.kitchens);
        KitchenIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent electric=new Intent(v.getContext(),Complaint_Activity.class);
                startActivity(electric);
            }
        });
        SanitationIntent = (ImageView) findViewById(R.id.sanitations);
        SanitationIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent electric=new Intent(v.getContext(),Complaint_Activity.class);
                startActivity(electric);
            }
        });

        plumberIntent = (ImageView) findViewById(R.id.plumbers);
        plumberIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent electric=new Intent(v.getContext(),Complaint_Activity.class);
                startActivity(electric);
            }
        });


        paidIntent = (TextView) findViewById(R.id.paids);
        paidIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent electric=new Intent(v.getContext(),Paid_Services_Activity.class);
                startActivity(electric);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.orders) {

            Intent intent = new Intent(this,Paid_Services_Activity.class);
            startActivity(intent);

        } else if (id == R.id.profile) {
            Intent intent = new Intent(this,Profile_Activity.class);
            startActivity(intent);

        } else if (id == R.id.references) {

        } else if (id == R.id.about) {

        } else if (id == R.id.logout) {
            SaveSharedPreference obj = new SaveSharedPreference();
            obj.setPrefStatus(this,"");
            this.finish();
            Intent i = new Intent(this,Login_Activity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

        /* clicking icons to go to complaint page */




    }
}
