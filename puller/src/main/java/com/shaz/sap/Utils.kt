package com.shaz.sap

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Parcelable

/**
 * Created by ${Shahbaz} on 04-08-2017.
 */
class Utils {

    companion object {
        fun isConnectedToInternet(_context: Context): Boolean {
            if(_context == null)
                return false
            val connectivity = _context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivity.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val info = it.activeNetworkInfo
                    return info?.isConnected ?: false
                } else {
                    val info = it.allNetworkInfo
                    (info != null)
                    info.indices
                            .filter { info[it].state == NetworkInfo.State.CONNECTED }
                            .forEach { return true }
                }
            }
            return false
        }
    }


}