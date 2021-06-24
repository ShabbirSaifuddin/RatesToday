package com.staffshaw.ratestoday.Connection;

import android.content.Context;
import android.net.ConnectivityManager;

public class Connection {

    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
