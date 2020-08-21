package com.example.bothc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    Button btnDatGio, btnDungLai;
    TextView txtTime;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        final Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        btnDatGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
//                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    txtTime.setText("Đặt giờ báo thức : " +timePicker.getHour() +" : "+timePicker.getMinute());
                }
                intent.putExtra("extra", "on");
                pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT
                );
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            }
        });
        btnDungLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtTime.setText("Dừng lại");
                pendingIntent.cancel();
                intent.putExtra("extra", "off");
                sendBroadcast(intent);
            }
        });
    }

    private void AnhXa() {
        btnDatGio = findViewById(R.id.btnDatGio);
        btnDungLai = findViewById(R.id.btnDungLai);
        txtTime = findViewById(R.id.textview);
        timePicker = findViewById(R.id.time);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = calendar.getInstance();
    }
}