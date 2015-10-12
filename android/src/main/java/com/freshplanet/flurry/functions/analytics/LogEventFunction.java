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
import com.adobe.fre.FREArray;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.flurry.android.FlurryAgent;
import com.freshplanet.flurry.Extension;

import java.util.HashMap;

public class LogEventFunction implements FREFunction {

	private static String TAG = "[AirFlurry][LogEventFunction]";

	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		Log.d(TAG, "call");

		String eventName = null;

		try {
			eventName = arg1[0].getAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		HashMap<String, String> params = new HashMap<String, String>();

		if (arg1[1] != null && arg1[2] != null) {
			FREArray paramsKeys = (FREArray) arg1[1];
			FREArray paramsValue = (FREArray) arg1[2];

			try {
				long paramsLength = paramsKeys.getLength();
				for (long i = 0; i < paramsLength; i++) {
					FREObject key = paramsKeys.getObjectAt(i);
					FREObject value = paramsValue.getObjectAt(i);

					String keyString = key.getAsString();
					String valueString = value.getAsString();

					Log.d(TAG, "[" + keyString + "] -> " + valueString);

					params.put(keyString, valueString);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (arg1[1] != null) {
			Log.e(TAG, "parameterValues is null while parameterKeys is not");
		} else if (arg1[2] != null) {
			Log.e(TAG, "parameterKeys is null while parameterValues is not");
		}

		if (eventName != null) {
			if (params.size() > 0) {
				Log.d(TAG, "log event with params");

				FlurryAgent.logEvent(eventName, params);
			} else {
				Log.d(TAG, "log event without params");

				FlurryAgent.logEvent(eventName);
			}
		} else {
			Log.d(Extension.TAG, "null event name");
		}


		return null;
	}

}
