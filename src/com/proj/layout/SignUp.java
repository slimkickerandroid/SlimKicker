package com.proj.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.util.regex.*;
import android.widget.RadioButton;

public class SignUp extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Button confirm = (Button)findViewById(R.id.button1);
        confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(
						isAllEntryCorrect()
						)
						{
							//call for profile

						}
						
						else 
						{
							//display in red
						}
			}
		});
        
	}
	
	boolean isAllEntryCorrect()
	{

	       EditText userName = (EditText)findViewById(R.id.editText1);
	       if(!isContentCorrect(userName.getText().toString(), Pattern.compile("[a-zA-Z]{5,}")))
	       {
	    	   
	       }
	       if(!isContentCorrect(userName.getText().toString(), Pattern.compile("[a-zA-Z]{2,}")))
	       {
	    	   //TODO call error
	       }
	       EditText passWord = (EditText)findViewById(R.id.editText3);
	       if(!isContentCorrect(passWord.getText().toString(), Pattern.compile("[a-zA-Z@]{6,}")))
	       {
	    	   //TODO error
	       }
	       RadioButton female = (RadioButton)findViewById(R.id.radio0);
	       RadioButton male = (RadioButton)findViewById(R.id.radio1);
				if(female.isChecked() || male.isChecked())
				{
					//TODO fine
				}
				else 
				{
					//TODO error
				}
		return true;
	}
	
	boolean isContentCorrect(String content, Pattern p)
	{
	 return	p.matcher(content).matches();
	}
	
}
