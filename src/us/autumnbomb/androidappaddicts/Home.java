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
//Standard imports for Android Activity 
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//Setup the name of the Class and make sure it implements the "OnClickListener"
public class Home extends SherlockActivity implements OnClickListener{

	Button community;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        setContentView(R.layout.main);
        
	      community = (Button) findViewById(R.id.community);
		  community.setOnClickListener(new OnClickListener() {
		      public void onClick(View arg0) {
		        Intent i = new Intent("android.intent.action.VIEW");
		        i.setData(Uri.parse("https://plus.google.com/communities/110349877541991509796"));
		        startActivity(i);
		      }
		    });
        
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
        
        
    
        
        View showfeed = findViewById(R.id.showFeed);
    	showfeed.setOnClickListener(this);
    	
        View social = findViewById(R.id.social);
    	social.setOnClickListener(this);
    	
    	View apps = findViewById(R.id.apps);
    	apps.setOnClickListener(this);
    	
        
    }
    
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
		@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate your menu.
	        getSupportMenuInflater().inflate(R.menu.menu, menu);

	        
	        // Set file with share history to the provider and set the share intent.
	        MenuItem actionItem = menu.findItem(R.id.menu_item_share_action_provider_action_bar);
	        ShareActionProvider actionProvider = (ShareActionProvider) actionItem.getActionProvider();
	        actionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
	        // Note that you can set/change the intent any time,
	        // say when the user has selected an image.
	        actionProvider.setShareIntent(createShareIntent());



	        return true;
		}
		
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
