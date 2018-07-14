package com.example.dharani.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.braintreepayments.cardform.view.CardForm;

public class PayRegister_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_register_);


        CardForm cardForm = (CardForm) findViewById(R.id.card_form);
        cardForm.cardRequired(true);
        cardForm.expirationRequired(true);
        cardForm.cvvRequired(true);
        cardForm.postalCodeRequired(true);
        cardForm.mobileNumberRequired(true);
        cardForm.mobileNumberExplanation("SMS is required on this number");
        cardForm.actionLabel("Purchase");
        cardForm.setup(PayRegister_Activity.this);
       // https://www.codingdemos.com/android-credit-card-form-tutorial/

        Button buy = (Button)findViewById(R.id.btnBuy);
    }
}
