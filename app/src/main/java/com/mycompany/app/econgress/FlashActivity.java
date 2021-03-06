package com.mycompany.app.econgress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class FlashActivity extends Activity {
	
    private int mProgressStatus = 0;
    private String url = "https://www.googleapis.com";
    private String command = "/civicinfo/v2/representatives?includeOffices=true&levels=country&address=";
    private String key = "&key=AIzaSyDZnyOg_OE5FNBCZiYGPjLlEdL7-iUzFZs";
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.flash_activity);
        
		SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(getString(R.string.url), url);
		editor.putString(getString(R.string.command), command);
		editor.putString(getString(R.string.key), key);
		editor.apply();
		
        new Thread(new Runnable() {
            public void run() {
      
                while (mProgressStatus < 1) { 
                	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						Log.i("FlashActivity", "InterruptedException " + e.getMessage());
					}
                	mProgressStatus = mProgressStatus + 1;
                }
                           
                Intent intent = new Intent(FlashActivity.this, MainActivity.class);
                startActivity(intent);  
                finish();

            }
        }).start();
    }
	
}