package com.proj.service;

import java.util.concurrent.Callable;

import android.content.Context;

import com.proj.dbadapters.FriendsDBAdapter;

public class FriendSync implements Callable<Boolean> {

	private FriendsDBAdapter adapter;
	public FriendSync(Context context, String id, String password)
	{
		adapter = new FriendsDBAdapter(context, id, password);	
	}
	
	@Override
	public Boolean call() throws Exception {
		return adapter.syncData();
	}
	

}
