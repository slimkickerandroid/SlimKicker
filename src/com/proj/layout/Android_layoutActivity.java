package com.proj.layout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Android_layoutActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
       // mainView.setBackgroundColor(2);
        Button login = (Button)findViewById(R.id.button1);
        Button signup = (Button)findViewById(R.id.button2);
        Button webButton = (Button)findViewById(R.id.button3);
        
        signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent("SignUp");
				startActivity(i);
			}
		});
        
        webButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent("android.intent.action.VIEW"
						,Uri.parse("http://www.slimkicker.com"));
				startActivity(i);
				
			}
		});
        
        login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 
				Intent signInIntent = new Intent("SignIn");
				startActivity(signInIntent);
				
			}
		});
        
    }
    
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        CreateMenu(menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {    
         return MenuChoice(item);    
    } 
    
    private void CreateMenu(Menu menu)
    {
    	menu.setQwertyMode(true);
        MenuItem mnu1 = menu.add(0, 0, 0, "Login");
        {
            mnu1.setAlphabeticShortcut('a');
            mnu1.setIcon(R.drawable.icon);            
        }
        MenuItem mnu2 = menu.add(0, 1, 1, "Sign Up");
        {
            mnu2.setAlphabeticShortcut('b');
            mnu2.setIcon(R.drawable.icon);            
        }
        MenuItem mnu3 = menu.add(0, 2, 2, "visit our website");
        {
            mnu3.setAlphabeticShortcut('c');
            mnu3.setIcon(R.drawable.icon);
        }
    }
 
    private boolean MenuChoice(MenuItem item)
    {        
        switch (item.getItemId()) {
        case 0:
            Toast.makeText(this, "You clicked on Item 1", 
                Toast.LENGTH_LONG).show();
            return true;
        case 1:
            Toast.makeText(this, "You clicked on Item 2", 
                Toast.LENGTH_LONG).show();
            return true;
        case 2:
            Toast.makeText(this, "You clicked on Item 3", 
                Toast.LENGTH_LONG).show();
            return true;
        case 3:
            Toast.makeText(this, "You clicked on Item 4", 
                Toast.LENGTH_LONG).show();
            return true;          
        }
        return false;
    }    
    */
}