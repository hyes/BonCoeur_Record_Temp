package com.example.hyes.myrecord_bc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by hyes on 2015. 4. 10..
 */
public class NetworkState {

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public NetworkState() {
    }

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {

        int conn = NetworkState.getConnectivityStatus(context);

        String status = null;
        if (conn == NetworkState.TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == NetworkState.TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == NetworkState.TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }
//        try{
//            ConnectivityManager conMan = (ConnectivityManager)get.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState(); // wifi
//            if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
//                return true;
//            }
//
//            NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState(); // mobile ConnectivityManager.TYPE_MOBILE
//            if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
//                return true;
//            }
//
//        } catch (NullPointerException e) {
//            return false;
//        }
//
//        return false;

}
