package com.ph36461.ar2_lab6;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Date;

public class ConfigNotification extends Application {
    public static final String CHANNEL_ID = "POLYTECHNIC";

    @Override
    public void onCreate() {
        super.onCreate();
        config();
    }

    private void config() {
       if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
           //ten cua notification channel can dang ki
           CharSequence name = getString(R.string.channel_name);
           String description = getString(R.string.channel_description);
           int importance = NotificationManager.IMPORTANCE_DEFAULT;
           Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

           AudioAttributes attributes = new AudioAttributes.Builder()
                   .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                   .build();

           NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
           channel.setDescription(description);
           channel.setSound(uri, attributes);
           NotificationManager notificationManager = getSystemService(NotificationManager.class);
           notificationManager.createNotificationChannel(channel);

       }
    }

}
