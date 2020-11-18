package com.example.h_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LedOnClick extends AppCompatActivity {

    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_on_click);

        Button retourMENUE = findViewById(R.id.retourMENUE);
        retourMENUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LedOnClick.this, MenuOnClick.class));
                finish();
            }
        });


        res = findViewById(R.id.textView3);
        Button ONcuisine = findViewById(R.id.ONcuisine);
        ONcuisine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{led-on-cuisine}",null,null);
                res.setText("led-on-cuisine");
                findViewById(R.id.ONcuisine).setBackgroundColor(Color.GREEN);
                findViewById(R.id.OFFcuisine).setBackgroundColor(Color.WHITE);
            }
        });

        Button OFFcuisine = findViewById(R.id.OFFcuisine);
        OFFcuisine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{led-off-cuisine}",null,null);
                res.setText("led-off-cuisine");
                findViewById(R.id.ONcuisine).setBackgroundColor(Color.WHITE);
                findViewById(R.id.OFFcuisine).setBackgroundColor(Color.RED);
            }
        });

        Button ONsallon = findViewById(R.id.ONsallon);
        ONsallon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{led-on-salon}",null,null);
                res.setText("led-on-salon");
                findViewById(R.id.ONsallon).setBackgroundColor(Color.GREEN);
                findViewById(R.id.OFFsallon).setBackgroundColor(Color.WHITE);
            }
        });

        Button OFFsallon = findViewById(R.id.OFFsallon);
        OFFsallon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{led-off-salon}",null,null);
                res.setText("led-off-salon");

                findViewById(R.id.ONsallon).setBackgroundColor(Color.WHITE);
                findViewById(R.id.OFFsallon).setBackgroundColor(Color.RED);
            }
        });

        Button ONgarage = findViewById(R.id.ONgarage);
        ONgarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{led-on-garage}",null,null);
                res.setText("led-on-garage");

                findViewById(R.id.ONgarage).setBackgroundColor(Color.GREEN);
                findViewById(R.id.OFFgarage).setBackgroundColor(Color.WHITE);
            }
        });

        Button OFFgarage = findViewById(R.id.OFFgarage);
        OFFgarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{led-off-garage}",null,null);
                res.setText("led-off-garage");
                findViewById(R.id.ONgarage).setBackgroundColor(Color.WHITE);
                findViewById(R.id.OFFgarage).setBackgroundColor(Color.RED);
            }
        });



    }
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),MenuOnClick.class);
        startActivity(intent);
        //Toast.makeText(this,"Appuyer sur RETOUR AU MENU PRCIPAL",Toast.LENGTH_LONG).show();
    }


}
