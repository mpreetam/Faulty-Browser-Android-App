package com.psjorwal.faultybrowser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    // Defining WebView, ProgressBar, EditText and Button variables
    ProgressBar bar;
    EditText editurl;
    WebView browser;
    Button go, fwd, back, refresh, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initializing WebView, ProgressBar EditText and Button variables
        bar = (ProgressBar) findViewById(R.id.progressBar);
        editurl = (EditText) findViewById(R.id.et_url);
        browser = (WebView) findViewById(R.id.wv_brow);
        go = (Button) findViewById(R.id.btn_go);
        fwd = (Button) findViewById(R.id.btn_fwd);
        back = (Button) findViewById(R.id.btn_back);
        refresh = (Button) findViewById(R.id.btn_refresh);
        clear = (Button) findViewById(R.id.btn_clear);

        /* We have to set our own WebViewClient in case our typed links open
        in other(default)browser */
        browser.setWebViewClient(new myClient());

        // Setting progress bar
        browser.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                bar.setProgress(newProgress);
                if(newProgress==100)
                    bar.setVisibility(View.GONE);
                else
                    bar.setVisibility(View.VISIBLE);
            }
        });

        //Enabling JavaScript
        WebSettings webset = browser.getSettings();
        webset.setJavaScriptEnabled(true);

        browser.loadUrl("http://www.google.com");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting user typed text from EditText field then converting it to string
                // Then assigning it to String variable "url"
                String url = editurl.getText().toString();

                // If typed url doesn't start with "http://" then add it in front
                if (!url.startsWith("http://")) {
                    url = "http://" + url;
                }
                browser.loadUrl(url);

                // hide keyboard after using EditText(after GO btn is clicked)
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(browser.getWindowToken(), 0);
            }
        });

        // when FORWARD button is pressed go forward if it can(if there is a site to go to)
        fwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (browser.canGoForward())
                    browser.goForward();
            }
        });

        // when BACK button is pressed go back if it can(if there is a site to go to)
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (browser.canGoBack())
                    browser.goBack();
            }
        });

        // when REFRESH button is pressed reload current site
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browser.reload();
            }
        });

        // when CLEAR button is pressed clear history
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browser.clearHistory();
            }
        });
    }
}
