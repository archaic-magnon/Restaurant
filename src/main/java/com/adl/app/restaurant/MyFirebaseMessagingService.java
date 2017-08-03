package com.adl.app.restaurant;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import in.mamga.carousalnotification.Carousal;
import in.mamga.carousalnotification.CarousalItem;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by merajahmed on 13/07/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e("reciv","ok");
        Intent intent = new Intent(this,BrowseMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);





        NotificationCompat.Builder notificationBuilder= new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("FCM Notification");
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSmallIcon(R.drawable.logo);
        notificationBuilder.setContentIntent(pendingIntent);


/*
        //
        int notificationId = new Random().nextInt(); // just use a counter in some util class...
        PendingIntent dismissIntent = this.getDismissIntent(notificationId, context);

        //

        //bigNotification
        notificationBuilder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText("message"));
        notificationBuilder .addAction (R.drawable.logo,
                "Dismiss", piDismiss);
*/

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        notificationManager.notify(0, notificationBuilder.build());








    }
}
