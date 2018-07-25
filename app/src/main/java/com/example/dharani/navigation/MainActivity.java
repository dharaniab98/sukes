package com.example.dharani.navigation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView ElectricalsIntent;
    ImageView KitchenIntent;
    ImageView SanitationIntent;
    ImageView plumberIntent;
    TextView paidIntent;
    TextView registerIntent;
    TextView blink;
    public  ViewFlipper animation_flip;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(SaveSharedPreference.getPrefStatus(MainActivity.this).length() == 0)
        {   this.finish();
            System.gc();
            Intent home = new Intent(this, Login_Activity.class);
            startActivity(home);
        }
        setContentView(R.layout.activity_main);

       /*  blink=(TextView)findViewById(R.id.register_blink);
        ObjectAnimator blinkAnim=(ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.blinking_animation);
        blinkAnim.setTarget(blink);
        blinkAnim.start();*/



       /*To stop the view flipper animations   when we touch the images*/


               animation_flip = (ViewFlipper)findViewById(R.id.view_anim);
             /*  animation_flip.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                   }
               });*/
       /*animation_flip.setOnTouchListener(new View.OnTouchListener() {

                                                @Override
                                                public boolean onTouch(View v, MotionEvent event) {
                                                 switch(event.getAction()){
                                                     case MotionEvent.ACTION_DOWN:
                                                         animation_flip.stopFlipping();
                                                         break;
                                                     case MotionEvent.ACTION_UP:
                                                         animation_flip.startFlipping();
                                                         break;
                                                 }
                                                    return true;
                                                }



                                            });*/


                  ElectricalsIntent = (ImageView) findViewById(R.id.electricals);
        ElectricalsIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent electric=new Intent(v.getContext(),Complaint_Activity.class);
               electric.putExtra("TYPE_SERVICE","Electrical");
               startActivity(electric);
            }
        });
        KitchenIntent = (ImageView) findViewById(R.id.kitchens);
        KitchenIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kitchen=new Intent(v.getContext(),Complaint_Activity.class);
                kitchen.putExtra("TYPE_SERVICE","Kitchen");
                startActivity(kitchen);
            }
        });
        SanitationIntent = (ImageView) findViewById(R.id.sanitations);
        SanitationIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sanitation=new Intent(v.getContext(),Complaint_Activity.class);
                sanitation.putExtra("TYPE_SERVICE","Sanitation");
                startActivity(sanitation);
            }
        });

        plumberIntent = (ImageView) findViewById(R.id.plumbers);
        plumberIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent plumbing=new Intent(v.getContext(),Complaint_Activity.class);
                plumbing.putExtra("TYPE_SERVICE","Plumbing");
                startActivity(plumbing);
            }
        });


        paidIntent = (TextView) findViewById(R.id.paids);
        paidIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent paidIntent=new Intent(v.getContext(),Paid_Services_Activity.class);
                startActivity(paidIntent);
            }
        });
        registerIntent = (TextView) findViewById(R.id.register_blink);
        registerIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regIntent=new Intent(v.getContext(),Register_Benefits_Activity.class);
                startActivity(regIntent);
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
            //this.finish();
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

            Intent intent = new Intent(this,Order_List_Activity.class);
            startActivity(intent);

        } else if (id == R.id.profile) {
            Intent intent = new Intent(this,Profile_Activity.class);
            startActivity(intent);

        } else if (id == R.id.references) {
            Intent intent = new Intent(this,Refferal_Activity.class);
            startActivity(intent);
        } else if (id == R.id.about) {
            Intent intent = new Intent(this,Contact_us.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            SaveSharedPreference obj = new SaveSharedPreference();
            obj.setPrefStatus(this,"","","refCode","paystats");
            //obj.setPrefStatus(this,"");
            SharedPreferences profileSharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=profileSharedPreferences.edit();
           // editor.putString("spemail","");
          //  editor.putString("spphno","");
            for(int i=0;i<20;i++) {
                String str="profile"+String.valueOf(i);
                editor.putString(str,"");
            }
            editor.apply();
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
