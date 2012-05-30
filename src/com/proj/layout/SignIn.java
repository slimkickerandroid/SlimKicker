package com.proj.layout;

import com.proj.service.DefaultProgressController;
import com.proj.service.ProfileMeta;
import com.proj.service.ServiceMeta;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.Toast;

import android.widget.EditText;

public class SignIn extends Activity {

	private  SharedPreferences prefs;
	private DefaultProgressController dialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
		prefs = getSharedPreferences(ProfileMeta.USER_INFO, MODE_PRIVATE);
		
		String id = prefs.getString(ProfileMeta.USER_ID, null);
		String password = prefs.getString(ProfileMeta.PASSWORD, null);
		
		dialog = new DefaultProgressController(this);
		dialog.show();
	
		if(id == null|| id == "" || password == null || password == "")
		{
			
		}
		else{ 		
		CheckLogInAndRedirect(id, password);
		}  
	}
	
	private Handler uiCallback = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			dialog.close();
		}
	};
	
	private void CheckLogInAndRedirect(String id, String password)
	{

		Context context = getApplicationContext();
		CharSequence text = "Incorrect parameters!";
		int duration = Toast.LENGTH_SHORT;
		
		if(id == null|| id == "" || password == null || password == "")
		{
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();			
		}
		else
		{
        String parsable = "";
        uiCallback.sendEmptyMessage(0);
			if(parsable.equals(ServiceMeta.CONNECTION_FAILURE) 
					|| parsable.equals(ServiceMeta.NO_INFO_FOUND))
			{
				Toast toast = Toast.makeText(context, parsable, duration);
				toast.show();				
			}else
			{
			    
				SharedPreferences.Editor editor =	prefs.edit();
			    editor.putString(ProfileMeta.USER_ID, id);
			    editor.putString(ProfileMeta.PASSWORD, password);
			    editor.commit();
			    
				Intent i = new Intent("Profile");
				Bundle profileInfo = new Bundle();
				profileInfo.putString("info", parsable);
				profileInfo.putString("username", id);
				profileInfo.putString("password", password);
				i.putExtras(profileInfo);
				startActivity(i);
			}
		
		}
	}
	
}