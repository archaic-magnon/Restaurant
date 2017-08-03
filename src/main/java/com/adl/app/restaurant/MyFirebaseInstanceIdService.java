package com.adl.app.restaurant;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by merajahmed on 13/07/17.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private  static  final  String REG_TOKEN= "REG_TOKEN";


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        Log.e(REG_TOKEN,"nope");

        String recent_token= FirebaseInstanceId.getInstance().getToken();
        Log.e(REG_TOKEN,recent_token);


    }
}


