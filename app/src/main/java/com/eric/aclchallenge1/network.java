package com.eric.aclchallenge1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class network {
    private static boolean isNetwokAvailable;
    private static boolean isInternetAvailable;
    private static boolean isOnline;

    public static boolean isNetworkAvailable(Context ctx) {

        if (ctx != null) {
            ConnectivityManager connectionManager = (ConnectivityManager) ctx
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = connectionManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileInfo = connectionManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())) {
                isNetwokAvailable = true;
            } else {
                isNetwokAvailable = false;
            }
        } else {
            return false;
        }

        return isNetwokAvailable;
    }

}
