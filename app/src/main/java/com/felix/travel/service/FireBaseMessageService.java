package com.felix.travel.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by felixlin on 2017/3/12.
 */

public class FirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("From: " , remoteMessage.getFrom());
        Log.d("Message Body: " , remoteMessage.getNotification().getBody());
    }

}
