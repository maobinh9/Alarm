package com.example.bothc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String chuoiString = intent.getStringExtra("extra");

        Intent myIntent = new Intent(context,Music.class);
        myIntent.putExtra("extra", chuoiString);
        context.startService(myIntent);
    }
}
