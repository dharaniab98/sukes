package com.example.dharani.navigation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Contact_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView phone=(TextView)findViewById(R.id.phone_contact);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phone_intent=new Intent(Intent.ACTION_DIAL);
                phone_intent.setData(Uri.parse("tel:9000999192"));
                startActivity(phone_intent);

            }
        });
        TextView mailtext=(TextView)findViewById(R.id.email_contact);
        mailtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mail_intent=new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:support@sukes.in"));
                mail_intent.putExtra(Intent.EXTRA_SUBJECT,"Your Subject");
                mail_intent.putExtra(Intent.EXTRA_SUBJECT,"Meassage");
                try{
                    startActivity(mail_intent);
                }catch(android.content.ActivityNotFoundException ex){
                   // Toast.makeText(this,"Mail is not configured", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
