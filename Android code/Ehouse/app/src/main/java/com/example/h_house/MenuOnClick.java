package com.example.h_house;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import static android.os.SystemClock.sleep;

public class MenuOnClick extends AppCompatActivity  {
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_on_click);


        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
        }

        Button LEDs = (Button) findViewById(R.id.LEDs);
        LEDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{led}",null,null);
                startActivity(new Intent(MenuOnClick.this, LedOnClick.class));
            }
        });

        final Button temperature = (Button) findViewById(R.id.Temperature);
        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{temperature}",null,null);



                progressDialog = new ProgressDialog(MenuOnClick.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent


                );




                // startActivity(new Intent(MenuOnClick.this, temperature.class));
            }

            public void onBackPressed(){
                progressDialog.dismiss();
                finish();


            }



            });

        Button Bruit = (Button) findViewById(R.id.Bruit);
        Bruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuOnClick.this, BruitOnClick.class));
/*
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{bruit}",null,null);

                progressDialog = new ProgressDialog(MenuOnClick.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent


                );


 */



                // startActivity(new Intent(MenuOnClick.this, temperature.class));
            }

            public void onBackPressed() {
                progressDialog.dismiss();
                finish();
            }
        });

        Button Garage = (Button) findViewById(R.id.Garage);
        Garage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuOnClick.this, PorteOnClick.class));
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{garage}",null,null);

            }
        });

        Button fumee = (Button) findViewById(R.id.fumée);
        fumee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuOnClick.this, FumeeOnClick.class));
/*                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{fumee}",null,null);


                progressDialog = new ProgressDialog(MenuOnClick.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent


                );


 */



                // startActivity(new Intent(MenuOnClick.this, temperature.class));
            }

            public void onBackPressed() {
                progressDialog.dismiss();
                finish();
            }
        });

        Button ventilation = (Button) findViewById(R.id.Ventilation);
        ventilation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuOnClick.this, VentilationOnClick.class));
                SmsManager.getDefault().sendTextMessage("+33766189022",null,"\n{ventillation}",null,null);

            }
        });

        Button exit1 = (Button) findViewById(R.id.exit);
        exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

            }
        });


    }
    public void onBackPressed()
    {
        finish();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
        //Toast.makeText(this,"Appuyer sur QUITTER",Toast.LENGTH_LONG).show();
    }


}
