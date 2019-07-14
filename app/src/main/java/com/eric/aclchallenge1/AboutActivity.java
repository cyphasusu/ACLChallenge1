package com.eric.aclchallenge1;

import android.app.ProgressDialog;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {

    private WebView mywebView;
    private ProgressDialog pdLoading;

    DialogNoInternet alertInternet = new DialogNoInternet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mywebView = findViewById(R.id.webView);

        mywebView.getSettings().setJavaScriptEnabled(true);
        mywebView.getSettings().setLoadWithOverviewMode(true);
        mywebView.getSettings().setUseWideViewPort(true);


        LoadWebView();

    }


    public void LoadWebView() {

        if (!network.isNetworkAvailable(AboutActivity.this)) {

            alertInternet.showDialog(AboutActivity.this);

        } else {

            pdLoading = ProgressDialog.show(AboutActivity.this, "Loading", "Please wait...", true);
            pdLoading.setCancelable(true);

            mywebView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    pdLoading.show();
                    view.loadUrl(url);

                    return true;
                }

                @Override
                public void onPageFinished(WebView view, final String url) {
                    pdLoading.dismiss();
                }


            });

            mywebView.loadUrl("https://andela.com/alc/");
        }
    }


    @Override
    public void onBackPressed() {

        try {

            if (mywebView.canGoBack()) {
                mywebView.goBack();
            } else {
                super.onBackPressed();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:

                LoadWebView();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
