package com.proj.service;

import java.util.concurrent.Callable;

import com.proj.dbadapters.ProfileDBAdapter;

import android.content.Context;

public class ProfileSync implements Callable<Boolean> {

	private ProfileDBAdapter adapter;
	
	public ProfileSync(Context context, String _userName, String _password)
	{		
		adapter = new ProfileDBAdapter(context, _userName, _password);		
	}
	
	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub
		return adapter.syncData();
	}

}
