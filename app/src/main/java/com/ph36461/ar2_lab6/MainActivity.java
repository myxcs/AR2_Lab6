package com.ph36461.ar2_lab6;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

     Button btnSend, btnStart, btnStop, btnStartBackgroundServices;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btn_send);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        btnStartBackgroundServices = findViewById(R.id.btn_start_background_services);

        btnSend.setOnClickListener(v -> sendNotification());

        btnStart.setOnClickListener(v -> startService(new Intent(this, ForegroundService.class)));
        btnStop.setOnClickListener(v -> stopService(new Intent(this, ForegroundService.class)));

        btnStartBackgroundServices.setOnClickListener(v -> startService(new Intent(this, BackgroundServices.class)));
        Constraints constraints = new Constraints.Builder().setRequiresCharging(true).build();
        WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setConstraints(constraints).build();
        WorkManager.getInstance(MainActivity.this).enqueue(workRequest);


    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void sendNotification() {

        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.logofpt);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ConfigNotification.CHANNEL_ID)
                .setSmallIcon(R.mipmap.logofpt)
                .setContentTitle("Chao mung den FPT hehe")
                .setContentText("Android Notification")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(logo)
                        .bigLargeIcon((Bitmap) null)
                ).setLargeIcon(logo)
                .setColor(Color.BLUE)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify((int) new Date().getTime(), builder.build());
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 7979);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 7979) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    sendNotification();
                }
            }
        }
    }
}