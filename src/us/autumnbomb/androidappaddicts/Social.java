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

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Social extends Activity{
//Here is where we setup the buttons. I have five here, but you can add more or remove 
//a few. I will say that it may be better with a scroll view, which may be being added
//shortly to the layouts folder under the /res folder.
	Button b1, b2, b3, b4, b5;
//Sets up the view
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.social);
	   
//Again, we setup the ability to remove the ads IF the end user has purchased a "support
//application. This could also be used if your application requires an additional app to
//to be installed, for example QR codes. This will make sure that app is installed before it
//will run the code. This is a very basic example.
        boolean installed  =   appInstalledOrNot("us.autumnbomb.support");
        
        
        if(installed)
        {

                  System.out.println("Shooo dang! You supported me! Thanks!");

        }
        else
        {
        	
        	//Request an ad
        	AdView ad = (AdView) findViewById(R.id.adView);
        	ad.loadAd(new AdRequest());
            System.out.println("Techincally, you're still supporting, but through ads...");
        }
//Button One, Steve C.'s Google+ Page      
        
//We're calling b1 from above to allow us to use a button to click and have an
//intent or "action" to happen. Think IF/THEN
	    b1 = (Button) findViewById(R.id.stevec);
	    b1.setOnClickListener(new OnClickListener() {
//This is our IF section
	    	public void onClick(View arg0) {
	        Intent i = new Intent("android.intent.action.VIEW");
	        i.setData(Uri.parse("https://plus.google.com/105781017064950794185"));
//This is our THEN section
	        startActivity(i);
	      }
	    });
//Button Two, Steve M.'s aka DoortoDoorGeek's Google+ Page	    
	    b2 = (Button) findViewById(R.id.door);
	    b2.setOnClickListener(new OnClickListener() {
	      public void onClick(View arg0) {
	        Intent i = new Intent("android.intent.action.VIEW");
	        i.setData(Uri.parse("https://plus.google.com/118113599195818712808/posts"));
	        startActivity(i);
	      }
	    });
//Button Three, Sean's Google+ Page
	    b3 = (Button) findViewById(R.id.sean);
	    b3.setOnClickListener(new OnClickListener() {
	      public void onClick(View arg0) {
	        Intent i = new Intent("android.intent.action.VIEW");
	        i.setData(Uri.parse("https://plus.google.com/109630441088781707762/posts"));
	        startActivity(i);
	      }
	    });
//Button Four, Eric's Google+ Page
	    b4 = (Button) findViewById(R.id.eric);
	    b4.setOnClickListener(new OnClickListener() {
	      public void onClick(View arg0) {
	        Intent i = new Intent("android.intent.action.VIEW");
	        i.setData(Uri.parse("https://plus.google.com/110875897642274851773/posts"));
	        startActivity(i);
	      }
	    });

		
	}
//Once again, this is for our "installation check" above. This just makes sure that
//the application is installed.
	
    private boolean appInstalledOrNot(String uri)
    {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try
        {
               pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
               app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
               app_installed = false;
        }
        return app_installed ;

        
    }}
   
