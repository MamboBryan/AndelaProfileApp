package com.mambobryan.alcone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class AboutActivity extends AppCompatActivity {

    private static final String ALC_ABOUT_PAGE_URL = "https://andela.com/alc/";
    private WebView aboutWebView;
    private ProgressBar webProgress;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        webProgress = findViewById(R.id.web_progressBar);
        /*
        Initialise the webView variable with the XML id

         */
        aboutWebView = findViewById(R.id.about_web_view);
        aboutWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webProgress.setVisibility(View.INVISIBLE);
            }
        });
        aboutWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                webProgress.setProgress(newProgress);
            }
        });
        aboutWebView.loadUrl(ALC_ABOUT_PAGE_URL);
        aboutWebView.setHorizontalScrollBarEnabled(false);

        WebSettings myWebSettings = aboutWebView.getSettings();
        myWebSettings.setJavaScriptEnabled(true);
        myWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    @Override
    public void onBackPressed() {
        if (aboutWebView.canGoBack()) {
            aboutWebView.goBack();
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        return super.onOptionsItemSelected(item);
    }
}
