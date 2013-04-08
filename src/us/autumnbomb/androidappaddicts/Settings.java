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

import android.os.Bundle;
import android.preference.PreferenceActivity;

//Literally, this was copied and pasted from one of my old apps. This is DEPRCACTED for now.
//As soon as more users start moving away from Gingerbread, we will go to fragments.

public class Settings extends PreferenceActivity {
	
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {     
	    super.onCreate(savedInstanceState);        
	    addPreferencesFromResource(R.xml.preferences);        
	}

}
