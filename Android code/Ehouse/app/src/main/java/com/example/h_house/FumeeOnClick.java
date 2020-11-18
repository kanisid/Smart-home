package com.example.h_house;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class FumeeOnClick extends AppCompatActivity {

    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fumee_on_click);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
        }

        if((MainActivity.type.equals("alerte gaz"))&&(MainActivity.etat.equals("1")))
        {
            findViewById(R.id.ONfumee).setBackgroundColor(Color.GREEN);
            findViewById(R.id.OFFfumee).setBackgroundColor(Color.WHITE);
            TextView textViewgaz = (TextView) findViewById(R.id.textViewgaz);
            textViewgaz.setText("Presence de gaz detectée");
        }
        else
        {
            findViewById(R.id.ONfumee).setBackgroundColor(Color.WHITE);
            findViewById(R.id.OFFfumee).setBackgroundColor(Color.RED);
            TextView textViewgaz = (TextView) findViewById(R.id.textViewgaz);
            textViewgaz.setText("Absence de gaz ");

        }


        Button retourMENUE = findViewById(R.id.retourMENUE);
            retourMENUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FumeeOnClick.this, MenuOnClick.class));
                finish();
            }
        });

        res = findViewById(R.id.textView3);
        Button ONfumee = findViewById(R.id.ONfumee);
            ONfumee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{ONfumee}",null,null);
                res.setText("capteur allumé");
                findViewById(R.id.ONfumee).setBackgroundColor(Color.GREEN);
                findViewById(R.id.OFFfumee).setBackgroundColor(Color.WHITE);




            }
        });

        Button OFFfumee = findViewById(R.id.OFFfumee);
            OFFfumee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{OFFfumee}",null,null);
                res.setText("capteur eteint");
                findViewById(R.id.ONfumee).setBackgroundColor(Color.WHITE);
                findViewById(R.id.OFFfumee).setBackgroundColor(Color.RED);
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