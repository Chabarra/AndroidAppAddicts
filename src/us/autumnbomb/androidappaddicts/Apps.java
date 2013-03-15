package us.autumnbomb.androidappaddicts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
 
@SuppressLint("SetJavaScriptEnabled")
public class Apps extends Activity {
    
	WebView mWeb;
	ProgressDialog mProgress;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        // set webview as main content only
        mWeb = new WebView(this);
        setContentView(mWeb);
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        // the init state of progress dialog
        mProgress = ProgressDialog.show(this, "Loading Apps List", "There are A LOT of apps... They are sorted from newest to oldest. If it doesn't load, retry. For now.... Please wait.");

        // add a WebViewClient for WebView, which actually handles loading data from web
        mWeb.setWebViewClient(new WebViewClient() {
        	// load url
        	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        	        if (url != null && url.startsWith("https://")) {
        	            view.getContext().startActivity(
        	                new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        	            return true;
        	        } else {
        	        	
        	            return false;
        	        }
        	    }


        	// when finish loading page
        	public void onPageFinished(WebView view, String url) {
        		if(mProgress.isShowing())
        		{
        			mProgress.dismiss();
        		}
        	}
        });
    

        // set url for webview to load
        mWeb.loadUrl("file:///android_asset/apps.html");
        
    }

}