package org.owasp.mastestapp

import android.content.Context
import android.webkit.WebView
import java.io.*

/**
 * This class writes a sensitive file ("api-key.txt") into internal storage and then loads a vulnerable
 * page into a WebView. That page simulates an XSS injection:
 * an attacker's script (running in the context of the vulnerable page) makes an `XMLHttpRequest` to
 * read the sensitive file via the content provider and then uses `fetch` to send the content to an external server.
 *
 * By loading the page with a `file://` base URL and enabling universal access from file URLs,
 * we relax the default restriction (which would otherwise treat `content://` requests as opaque origin)
 * and allow the `XMLHttpRequest` to succeed.
 */
class MastgTestWebView(private val context: Context) {

    fun mastgTest(webView: WebView) {
        // Write a sensitive file (for example, cached credentials or private data).
        val sensitiveFile = File(context.filesDir, "api-key.txt")
        sensitiveFile.writeText("MASTG_API_KEY=072037ab-1b7b-4b3b-8b7b-1b7b4b3b8b7b")

        // Configure the WebView.
        webView.settings.apply {
            /* `javaScriptEnabled` is required for the attacker's script to even execute.
             * This is very common in WebViews, unless they only load static content.
            */
            // ruleid: mastg-android-webview-settings
            javaScriptEnabled = true

            /* `allowUniversalAccessFromFileURLs` is required in this attack since it
             * relaxes the default restrictions so that pages loaded from file:// can access
             * content from any origin (including content:// URIs).
             *
             * If this is not set, the following error will be logged in logcat:
             *
             *   [INFO:CONSOLE(0)] "Access to XMLHttpRequest at 'content://org.owasp.mastestapp.provider/sensitive.txt'
             *   from origin 'null' has been blocked by CORS policy: Cross origin requests are only supported
             *   for protocol schemes: http, data, chrome, https, chrome-untrusted.", source: file:/// (0)
             *
             * Note that the fetch to the external server will still work, but the retrieval of the file content via content:// will fail.
            */
            // ruleid: mastg-android-webview-settings
            allowUniversalAccessFromFileURLs = true


            /* `allowContentAccess` is intentionally not set to false to showcase the default behavior.
             * If we were to disable content provider access,
             * this would prevent the attacker's script from accessing the sensitive file via the content provider.
             */
            // allowContentAccess = false

        }

        val myWebView: WebView = findViewById(R.id.webview)
        // ruleid: mastg-android-webview-settings
        myWebView.settings.javaScriptEnabled = true
        
    }
}