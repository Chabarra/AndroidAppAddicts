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

	Button b1, b2, b3, b4, b5;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.social);
	    
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
        
	    b1 = (Button) findViewById(R.id.stevec);
	    b1.setOnClickListener(new OnClickListener() {
	      public void onClick(View arg0) {
	        Intent i = new Intent("android.intent.action.VIEW");
	        i.setData(Uri.parse("https://plus.google.com/105781017064950794185"));
	        startActivity(i);
	      }
	    });
	    
	    b2 = (Button) findViewById(R.id.door);
	    b2.setOnClickListener(new OnClickListener() {
	      public void onClick(View arg0) {
	        Intent i = new Intent("android.intent.action.VIEW");
	        i.setData(Uri.parse("https://plus.google.com/118113599195818712808/posts"));
	        startActivity(i);
	      }
	    });
	    b3 = (Button) findViewById(R.id.sean);
	    b3.setOnClickListener(new OnClickListener() {
	      public void onClick(View arg0) {
	        Intent i = new Intent("android.intent.action.VIEW");
	        i.setData(Uri.parse("https://plus.google.com/109630441088781707762/posts"));
	        startActivity(i);
	      }
	    });
	    b4 = (Button) findViewById(R.id.eric);
	    b4.setOnClickListener(new OnClickListener() {
	      public void onClick(View arg0) {
	        Intent i = new Intent("android.intent.action.VIEW");
	        i.setData(Uri.parse("https://plus.google.com/110875897642274851773/posts"));
	        startActivity(i);
	      }
	    });

		
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

        
    }}
   
