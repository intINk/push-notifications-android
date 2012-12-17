package com.si.pushnotifications;


import com.google.android.gcm.GCMRegistrar;
import com.si.pushnotifications.CommonUtilities;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void clickBtn(View view)
	{

		setContentView(R.layout.activity_main);
		// Get GCM registration id
		final String regId = GCMRegistrar.getRegistrationId(this);
		if (regId.equals("")) 
		{
			// Registration is not present, register now with GCM			
			GCMRegistrar.register(this, CommonUtilities.SENDER_ID);
		} 
		TextView TV = (TextView)findViewById(R.id.regIdTxtField);
				TV.setText(regId);
				
		final String TAG = "myActivity";
		Log.i(TAG, regId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
