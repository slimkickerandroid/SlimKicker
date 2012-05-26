package com.proj.service;

import android.app.ProgressDialog;
import android.content.Context;

public class DefaultProgressController extends ProgressDialog {

	public DefaultProgressController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void show()
	{
		super.setMessage("");
		super.setTitle("");
		super.setCancelable(false);
		super.setIndeterminate(true);
		super.show();		
	}
	
	public void show(String title, String message)
	{
		super.setMessage(message);
		super.setTitle(title);
		super.setCancelable(false);
		super.setIndeterminate(true);
		super.show();
	}

	public void close()
	{
		super.dismiss();
	}
	
}
