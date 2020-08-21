package com.example.bothc;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class Music extends Service {
    MediaPlayer mediaPlayer;
    int isChecked;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String nhanKey = intent.getStringExtra("extra");
        Log.d("AAA", nhanKey + " Check " +isChecked);
        if(nhanKey.equals("on")){
            isChecked = 1;
        }else if(nhanKey.equals("off")){
            isChecked = 0;
        }
        if(isChecked == 1){
            mediaPlayer = MediaPlayer.create(this,R.raw.bao_thuc);
            mediaPlayer.start();
            isChecked = 0;
        }else if(isChecked == 0) {
            
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        return START_NOT_STICKY;
    }
}
