package com.proj.layout;

import com.proj.profile.service.ProfileService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.Toast;

import android.widget.EditText;

public class SignIn extends Activity {

	private  SharedPreferences prefs;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
		prefs = getSharedPreferences(ProfileService.USER_INFO, MODE_PRIVATE);
		
		String id = prefs.getString(ProfileService.USER_ID, null);
		String password = prefs.getString(ProfileService.PASSWORD, null);
		
		if(id == null|| id == "" || password == null || password == "")
		{
			
		}
		else{ 		
		CheckLogInAndRedirect(id, password);
		}
	}
	
	public void signInCommand(View view)
	{
	
		EditText loginText = (EditText)findViewById(R.id.userName);
        EditText paswdText = (EditText)findViewById(R.id.passWord);
        String login = loginText.getText().toString();
        String pswd = paswdText.getText().toString();
        
        CheckLogInAndRedirect(login, pswd);
        
	}
	
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
        ProfileService service = new ProfileService();
        service.SetUp(id, password);
        String parsable = service.getProfileJson();
			if(parsable.equals(ProfileService.ConnectionFailure) 
					|| parsable.equals(ProfileService.InfoUnAvail))
			{
				Toast toast = Toast.makeText(context, parsable, duration);
				toast.show();				
			}else
			{
			    
				SharedPreferences.Editor editor =	prefs.edit();
			    editor.putString(ProfileService.USER_ID, id);
			    editor.putString(ProfileService.PASSWORD, password);
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
