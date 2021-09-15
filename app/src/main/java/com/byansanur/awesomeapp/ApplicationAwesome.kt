package com.byansanur.awesomeapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
@HiltAndroidApp
class ApplicationAwesome : Application() {

//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//    }
//
//    companion object {
//        lateinit var instance: ApplicationAwesome
//        private set
//
//        fun hasNetwork() : Boolean {
//            val connectivityManager = instance.getSystemService(
//                Context.CONNECTIVITY_SERVICE
//            ) as ConnectivityManager
//
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                val activeNetwork = connectivityManager.activeNetwork ?: return false
//                val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
//                return when{
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//                    else -> false
//                }
//            }
//            else{
//                connectivityManager.activeNetworkInfo?.run {
//                    return when(type){
//                        ConnectivityManager.TYPE_WIFI -> return true
//                        ConnectivityManager.TYPE_MOBILE -> return true
//                        ConnectivityManager.TYPE_ETHERNET -> return true
//                        else -> false
//                    }
//                }
//            }
//            return false
//        }
//    }
}