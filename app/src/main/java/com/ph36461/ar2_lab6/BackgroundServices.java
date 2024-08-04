package com.ph36461.ar2_lab6;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BackgroundServices extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service getting started", Toast.LENGTH_SHORT).show();
        try {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(BackgroundServices.this, "Here they come", Toast.LENGTH_SHORT).show();
                    Intent webIntent = new Intent(Intent.ACTION_VIEW);
                    webIntent.setData(Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                    webIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(webIntent);
                    stopSelf();
                }
            }, 5000);
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service getting stopped", Toast.LENGTH_SHORT).show();
    }
}

