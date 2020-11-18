package com.example.h_house;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.*;


public class ReceiveSMS extends BroadcastReceiver {
    private static MessageListener mListener;
    String msg,phoneNo;
      @Override

    public void onReceive(Context context, Intent intent) {
        try {
            Bundle data = intent.getExtras();
            Object[] pdus = (Object[]) data.get("pdus");
            for (int i = 0; i < pdus.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
                mListener.messageReceived(smsMessage.getDisplayOriginatingAddress(), smsMessage.getMessageBody());
                msg = smsMessage.getMessageBody();
                phoneNo = smsMessage.getDisplayOriginatingAddress();
                //Toast.makeText(context ,"message="+msg+"\nNumber"+phoneNo,Toast.LENGTH_LONG).show();

            }
        }
        catch (Exception e) { }
    }

    public static void bindListener(MessageListener listener){
        mListener = listener;
    }
    public static void bindListener2(MessageListener listener){
        mListener = listener;
    }





}




