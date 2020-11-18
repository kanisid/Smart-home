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

public class VentilationOnClick extends AppCompatActivity {

    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventilation_on_click);


    Button retourMENUE = findViewById(R.id.retourMENUE);
        retourMENUE.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(VentilationOnClick.this, MenuOnClick.class));

            finish();
        }
    });

    res = findViewById(R.id.textView3);
    Button ONventillation = findViewById(R.id.ONventillation);
        ONventillation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{ONventillation}",null,null);
                res.setText("ventilation activée");
                findViewById(R.id.ONventillation).setBackgroundColor(Color.GREEN);
                findViewById(R.id.OFFventillation).setBackgroundColor(Color.WHITE);
            }
        });

        Button OFFventillation = findViewById(R.id.OFFventillation);
        OFFventillation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{OFFventillation}",null,null);
                res.setText("ventilation desactivée");

                findViewById(R.id.ONventillation).setBackgroundColor(Color.WHITE);
                findViewById(R.id.OFFventillation).setBackgroundColor(Color.RED);
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