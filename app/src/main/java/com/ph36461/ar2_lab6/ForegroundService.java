package com.ph36461.ar2_lab6;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service đang chạy", Toast.LENGTH_SHORT).show();

        //return super.onStartCommand(intent, flags, startId);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ConfigNotification.CHANNEL_ID)
                .setSmallIcon(R.mipmap.logofpt)
                .setContentTitle("Service đang chạy")
                .setContentText("Ban ko thể tắt bằng cách lướt")
                .setColor(Color.BLUE);

        Notification notification = (Notification) builder.build();
        startForeground(1, notification);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service đã dừng", Toast.LENGTH_SHORT).show();
    }
}
