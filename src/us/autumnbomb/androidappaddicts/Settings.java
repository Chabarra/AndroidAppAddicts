package us.autumnbomb.androidappaddicts;

import android.os.Bundle;
import android.preference.PreferenceActivity;



public class Settings extends PreferenceActivity {
	
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {     
	    super.onCreate(savedInstanceState);        
	    addPreferencesFromResource(R.xml.preferences);        
	}

}