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
import com.flurry.android.Constants;
import com.flurry.android.FlurryAgent;

public class SetUserInfoFunction implements FREFunction {

	private static String TAG = "[AirFlurry][SetUserInfoFunction]";

	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		Log.d(TAG, "call");

		int age = 0;

		try {
			age = arg1[0].getAsInt();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (age > 0) {
			Log.d(TAG, "age: " + Integer.toString(age));

			FlurryAgent.setAge(age);
		} else {
			Log.d(TAG, "age is null");
		}

		String genderString;

		byte gender = 0;
		Boolean hasGender = false;

		try {
			genderString = arg1[1].getAsString();

			Log.d(TAG, "gender: " + genderString);

			if (genderString.compareTo("m") == 0) {
				Log.d(TAG, "gender Male");

				hasGender = true;
				gender = Constants.MALE;
			} else if (genderString.compareTo("f") == 0) {
				Log.d(TAG, "gender Female");

				hasGender = true;
				gender = Constants.FEMALE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		if (hasGender) {
			FlurryAgent.setGender(gender);
		} else {
			Log.d(TAG, "gender is null");
		}

		return null;
	}

}
