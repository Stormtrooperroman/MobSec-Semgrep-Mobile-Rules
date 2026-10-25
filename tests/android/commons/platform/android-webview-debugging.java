package com.myapp;

import com.facebook.react.ReactActivity;
import android.webkit.WebView;

public class MainActivity extends ReactActivity {

    @Override
    protected String getMainComponentName() {
        return "myapp";
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        // ruleid: android-webview-debugging
        WebView.setWebContentsDebuggingEnabled(true);
    }
}