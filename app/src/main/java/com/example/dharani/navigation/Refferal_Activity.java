package com.example.dharani.navigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Refferal_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refferal);

        getSupportActionBar().setTitle("Invite & Earn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SaveSharedPreference preference = new SaveSharedPreference();
        final String ref_Pref = preference.getPrefRefcode(this);
        Toast.makeText(getBaseContext(), ref_Pref, Toast.LENGTH_LONG).show();

        TextView invitelink=(TextView)findViewById(R.id.inviteLink);
           invitelink.setText(ref_Pref);


        Button invite=(Button)findViewById((R.id.btn_invite));



        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String sAux = "\nclick here  and earn more\n\n";
                    sAux = sAux +"https://www.photofunny.net/efectos/grandes/gagnam-style.gif/n/n"; //"https://play.google.com/store/apps/details?id=the.package.id \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });


    }
}
