package com.example.ritwik.musicapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;


/**
 * Created by Luther on 8/3/17.
 */

public class ShowUrl extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        getSupportActionBar().hide();

        //Showing data in webview
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(this.getIntent().getDataString());
    }
}
