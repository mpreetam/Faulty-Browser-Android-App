package com.psjorwal.faultybrowser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by P S Jorwal on 21-03-2017.
 */
public class myClient extends WebViewClient {

    // Deprecated means that android studio has something else to offer we can use it if we want
    // But we wont use suggested super method as it's supported in Android 7.0 devices only

    @SuppressWarnings("deprecation") //it supresses deprecation warning
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
