package com.myapp

import com.facebook.react.ReactActivity
import android.webkit.WebView

class MainActivity : ReactActivity() {

    override fun getMainComponentName(): String {
        return "myapp"
    }

    override fun onCreate() {
        super.onCreate()
        // ruleid: android-webview-debugging
        WebView.setWebContentsDebuggingEnabled(true)
    }
}