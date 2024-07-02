package com.august.ua.blackout.ui.common.extensions

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.august.ua.blackout.App

fun networkConnection(init: NetworkCallbackBuilder.() -> Unit) {
    val builder = NetworkCallbackBuilder()
    builder.init()
    App.application.registerNetworkCallback(builder)
}

class NetworkCallbackBuilder {
    internal var available: (() -> Unit)? = null
    internal var lost: (() -> Unit)? = null

    fun available(success: (() -> Unit)? = null) {
        this.available = success
    }

    fun lost(error: (() -> Unit)? = null) {
        this.lost = error
    }
}

fun Application.registerNetworkCallback(callbackBuilder: NetworkCallbackBuilder) {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val builder = NetworkRequest.Builder()
    val networkRequest = builder.build()
    connectivityManager.registerNetworkCallback(networkRequest,
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                callbackBuilder.available?.invoke()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                callbackBuilder.lost?.invoke()
            }
        })
}