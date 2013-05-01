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
//ActionbarSherlock imports
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;
//Imports for Admob ads
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import android.app.AlertDialog;
import android.content.DialogInterface;
//Standard imports for Android Activity 
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//Setup the name of the Class and make sure it implements the "OnClickListener"
public class Home extends SherlockActivity implements OnClickListener{
//Sets the community button on the "Home" class to allow the Google+ app to open
//or to open the default browser to the G+ community page
	Button community;
	public static final String PREFS_NAME = "AAAPrefsFile";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean dialogShown = settings.getBoolean("dialogShown", false);

        if (!dialogShown) {
          // AlertDialog code here
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setTitle("Attention Users");
        	builder.setMessage("This will be one of the final updates that will be supporting Gingerbread. " +
        			"We have conducted a poll that shows only a few users currently use Gingerbread. " +
        			"There are a lot of ways to upgrade your device to the latest OS. " +
        			"For the sake of innovation, we hope you understand. " +
        			"Any questions, comments, concerns please contact the developers. Thanks!")
        	       .setCancelable(false)
        	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	                //do things
        	           }
        	       });
        	AlertDialog alert = builder.create();
        	alert.show();

          SharedPreferences.Editor editor = settings.edit();
          editor.putBoolean("dialogShown", true);
          editor.commit();    
        }
        
        //This calls the Holo style action bar across all version of Android 
        //(NEEDS ACTIONBAR SHERLOCK LIBRARY)
        getSupportActionBar().setHomeButtonEnabled(true);
        //sets view as main.xml
        setContentView(R.layout.main);
        //Setting up action for community button
	      community = (Button) findViewById(R.id.community);
		  community.setOnClickListener(new OnClickListener() {
		      public void onClick(View arg0) {
		        Intent i = new Intent("android.intent.action.VIEW");
		        i.setData(Uri.parse("https://plus.google.com/communities/110349877541991509796"));
		        startActivity(i);
		      }
		    });
        //This is a license call. This isn't completely "working" yet, but what it does
		//is allows the application to check for the package name. Why is it here?
		//It's for someone who purchases the support application to have features or
		//in this case, remove the ads at the bottom of the application
		  
        boolean installed  =   appInstalledOrNot("us.autumnbomb.support");
        
        
        if(installed)
        {
        //Just a java log print out. You can actually see this in the logcat :)
                  System.out.println("Shooo dang! You supported me! Thanks!");
                  

        }
        else
        {
        	
        	//Request an ad if package isn't found installed on phone
        	AdView ad = (AdView) findViewById(R.id.adView);
        	ad.loadAd(new AdRequest());
        	//Another logcat print out
            System.out.println("Techincally, you're still supporting, but through ads...");
            
        }
        
        
           //This will setup the other buttons on the application 
        
        View showfeed = findViewById(R.id.showFeed);
    	showfeed.setOnClickListener(this);
    	
        View social = findViewById(R.id.social);
    	social.setOnClickListener(this);
    	
    	View apps = findViewById(R.id.apps);
    	apps.setOnClickListener(this);
    	
        
    }
         //This is a part of the earlier call. It's need to finish the check
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

        
    }
    
    
        
        //Sets up the button clicks for the additional button presses
       //Calls the intents for the other classes and views
    	
		@Override
		public void onClick(View v) {
	      switch (v.getId()) {
	      case R.id.showFeed:
	    	  Intent i = new Intent(this, Feed.class);
	    	  startActivity(i);
	    	  break;
	    	  
	      case R.id.social:
	    	  Intent s = new Intent(this, Social.class);
	    	  startActivity(s);
	    	  break;
	    	  
	      case R.id.apps:
	    	  Intent a = new Intent(this, Apps.class);
	    	  startActivity(a);
	    	  break;
	      
	      }
	      }
		
		   //Setups up menu on the action bar   
		
		@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate your menu.
	        getSupportMenuInflater().inflate(R.menu.menu, menu);

	        //This is the "share" action on the action bar         
	        // Set file with share history to the provider and set the share intent.
	        MenuItem actionItem = menu.findItem(R.id.menu_item_share_action_provider_action_bar);
	        ShareActionProvider actionProvider = (ShareActionProvider) actionItem.getActionProvider();
	        actionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
	        // Note that you can set/change the intent any time,
	        // say when the user has selected an image.
	        actionProvider.setShareIntent(createShareIntent());



	        return true;
		}
		//Selections on action bar
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
		   // Handle item selection
		   switch (item.getItemId()) {
		     
		      case R.id.about:
		    	  Intent a = new Intent(this, Settings.class);
		    	  startActivity(a);
		         return true;
		      default:
		         return super.onOptionsItemSelected(item);
		   }
		}

       //Setup what you want your share to say. This is the text version
	   //All sharing intents can be found on:
	   //http://android-developers.blogspot.com/2012/02/share-with-intents.html
	    /**
	     * Creates a sharing {@link Intent}.
	     *
	     * @return The sharing intent.
	     */
	    private Intent createShareIntent() {
	    	Intent intent = new Intent(Intent.ACTION_SEND);
	        intent.setType("text/plain");
	        intent.putExtra(Intent.EXTRA_SUBJECT, "Android App Addicts Companion");
	        intent.putExtra(Intent.EXTRA_TEXT, "Download 'Android App Addicts' from Google Play today! http://goo.gl/IZHqQ");
	        return intent;
	    }
}
