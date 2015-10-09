//////////////////////////////////////////////////////////////////////////////////////
//
//  Copyright 2012 Freshplanet (http://freshplanet.com | opensource@freshplanet.com)
//  
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//  
//    http://www.apache.org/licenses/LICENSE-2.0
//  
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//  
//////////////////////////////////////////////////////////////////////////////////////

package com.freshplanet.flurry.functions.analytics;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.flurry.android.FlurryAgent;

public class StartSessionFunction implements FREFunction {
	private static String TAG = "Flurry - StartSessionFunction";

	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		String apiKey = null;
		try {
			apiKey = arg1[0].getAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (apiKey != null) {
			// Set Flurry logs
			FlurryAgent.setLogEnabled(false);
			FlurryAgent.setLogLevel(Log.DEBUG);

			// Start Flurry session and initialize ads
			FlurryAgent.onStartSession(arg0.getActivity(), apiKey);
			Log.d(TAG, "Started session and initalized ads");
		} else {
			Log.e(TAG, "API Key is null");
		}

		return null;
	}

}
