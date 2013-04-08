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
//See Apps.java comments for more information about what's going on here.
@SuppressLint("SetJavaScriptEnabled")
public class Feed extends Activity {
    
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
        mProgress = ProgressDialog.show(this, "Loading Newest Shows", "Please wait...");

        // add a WebViewClient for WebView, which actually handles loading data from web
        mWeb.setWebViewClient(new WebViewClient() {
        	// load url
        	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        	        if (url != null && url.startsWith("http://")) {
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
        mWeb.loadUrl("file:///android_asset/index.html");
    }

}
