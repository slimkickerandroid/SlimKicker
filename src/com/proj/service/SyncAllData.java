package com.proj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.content.Context;

public class SyncAllData {

	private CompletionService<Boolean> e;
	private ExecutorService executor;
	List<Callable<Boolean>> allCommands;
	private Context context;
	private String userName;
	private String password;
	
	
	public SyncAllData(Context _context, String _userName, String _password)
	{
		context = _context;
		executor = Executors.newFixedThreadPool(2);
    	e = new ExecutorCompletionService<Boolean>(executor);
    	allCommands = new ArrayList<Callable<Boolean>>();
    	userName = _userName;
    	password = _password;
	}
	
	public boolean Execute(String ...args)
	{
		ProfileSync profileCommand = new ProfileSync(context, userName, password);
    	FriendSync friendCommand = new FriendSync(context, userName, password);
    	for (String arg : args) 
    	{
    		if(arg.equals(ServiceMeta.PROFILE_SERVICE))
    			allCommands.add(profileCommand);
    		if(arg.equals(ServiceMeta.FRIEND_SERVICE))
    			allCommands.add(friendCommand);
		}
    	
    	for (Callable<Boolean> command : allCommands) {
    		e.submit(command);
		}
    	boolean result = true;
    	for(int i =0; i<allCommands.size(); ++i)
    	{
    		try {
			Future<Boolean> future = e.take();
			result = result && future.get();
			} 
    		catch (InterruptedException e1) {
				result = false;
			}
			catch(ExecutionException e)
			{
				result = false;
			}
    	}
    	
    	return result;	
	}
	
}
