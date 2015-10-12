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

import android.content.Context;
import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.flurry.android.FlurryAgent;

public class StartSessionFunction implements FREFunction {
	private static String TAG = "[AirFlurry][StartSessionFunction]";

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Log.d(TAG, "call");

		Context androidContext = context.getActivity().getBaseContext();

		String apiKey = null;

		try {
			apiKey = args[0].getAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (apiKey != null) {
			Log.d(TAG, "API Key = " + apiKey);

			try {
				// Set Flurry logs
				FlurryAgent.setLogEnabled(false);
				FlurryAgent.setLogLevel(Log.VERBOSE);

				Log.d(TAG, "log set");

				// Start Flurry session and initialize ads
				FlurryAgent.init(androidContext, apiKey);
				FlurryAgent.onStartSession(androidContext);

				Log.d(TAG, "Started session and initialized ads");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Log.e(TAG, "API Key is null");
		}

		return null;
	}

}
