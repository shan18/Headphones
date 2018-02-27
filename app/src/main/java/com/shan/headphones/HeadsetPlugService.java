package com.shan.headphones;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

public class HeadsetPlugService extends IntentService {

    HeadsetReceiver receiver;

    public HeadsetPlugService() {
        super("HeadsetPlugService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            receiver = new HeadsetReceiver();

            IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
            registerReceiver(receiver, filter);
        }
    }

    public class HeadsetReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                        Toast.makeText(HeadsetPlugService.this, "Headset unplugged", Toast.LENGTH_SHORT).show();
                        NotificationManager dismissNotificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                        dismissNotificationManager.cancel(1403);
                        break;
                    case 1:
                        Toast.makeText(HeadsetPlugService.this, "Headset plugged", Toast.LENGTH_SHORT).show();
                        NotificationCompat.Builder builder = (NotificationCompat.Builder) new
                                NotificationCompat.Builder(HeadsetPlugService.this)
                                .setSmallIcon(R.drawable.ic_headset_black_48dp)
                                .setContentTitle("Headphones")
                                .setContentText("Headset plugged")
                                .setOngoing(true);
                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                        notificationManager.notify(1403, builder.build());
                        break;
                }
            }
        }
    }
}
