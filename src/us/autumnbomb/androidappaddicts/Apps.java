/*******************************************************************************
 * Copyright 2013 Caleb Fultz and Autumn Bomb Apps
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
//Here we suppress the warning about Java being enabled. Remember, java is DANGEROUS if
//not patched. This could allow exploits into our application, but rest assured, the majority
//of the HTML/CSS/Javascript is within the application or on Google and Yahoo.
@SuppressLint("SetJavaScriptEnabled")
public class Apps extends Activity {
//Setting our WebView and ProgressDialog (the loading indicator)
//The reason behind this is to define an "easier" readable version of WebView and PD.
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
        	// load url when clicked. this kicks it outside of our application. Try an
        	// application from one of the early shows, it opens, but it stays in the view
        	// instead of opening in the PlayStore or G+ applications
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
    

        // set url for webview to load. I'll explain more there.
        mWeb.loadUrl("file:///android_asset/apps.html");
        
    }

}
