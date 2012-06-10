package com.proj.layout;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import com.markupartist.*;
import com.proj.actions.*;

public class MainTabBarActivity extends TabActivity {
	/** Called when the activity is first created. */	
	private String LOG_TAG = "MainTabBarActivity";
	
	public void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "Creating all challenges");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.all_challenge);
		Log.i(LOG_TAG, "Setting tabs");
		
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		//actionBar.setHomeAction(new ToastAction());
		actionBar.setHomeLogo(R.drawable.home_logo);
		//actionBar.addAction(new IntentAction(this, HomeActivity.createIntent(this), R.drawable.ic_title_home_default));
		actionBar.addAction(new DietAction(this));
		actionBar.addAction(new ChallengeAction());
		
		
		setTabs() ;
	}
	
	private void setTabs()
	{
		getTabHost().getTabWidget().setDividerDrawable(R.drawable.tab_divider);
		addTab("1", R.drawable.tab_home, ProfileLayout.class);
		addTab("2", R.drawable.diet_icon, DietActivity.class);
		addTab("3", R.drawable.challenges_icon, ChallengeActivity.class);
		addTab("4", R.drawable.body_icon, ChallengeActivity.class);

		//To add more tabs just use addTab() method here like previous line.
		
		getTabHost().setOnTabChangedListener(new OnTabChangeListener(){
			@Override
			public void onTabChanged(String tabId) {
			    	Log.i(LOG_TAG, "Tab changed, sir");
			    	
			    	int numTabs = 4;
			    	
			    	for(int i =0; i < 4; i++){
			    		View tab = getTabWidget().getChildTabViewAt(i);
			    		View line = (View) tab.findViewById(R.id.red_line);
			    		line.setBackgroundResource(R.color.dark_black);
			    	}
			    	
			    	ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
			    Log.i(LOG_TAG, "Tab ID selected: " + tabId);
			    
			    	if(tabId.equals("3"))
			    		actionBar.setVisibility(View.GONE);
			    else
			    		actionBar.setVisibility(View.VISIBLE);
			    
			    	View currentTab =	getTabHost().getCurrentTabView();
			    	
			    	View line = (View) currentTab.findViewById(R.id.red_line);
			    	line.setBackgroundResource(R.color.orange);
			}});
		
	}
	
	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec(labelId);	
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		View line = (View) tabIndicator.findViewById(R.id.red_line);
		View icon = (View) tabIndicator.findViewById(R.id.tab_icon);
		icon.setBackgroundResource(R.drawable.home_icon);
		/*
		if(labelId.equals("2"))
			icon.setBackgroundResource(R.drawable.home_icon);
		else if(labelId.equals("3"))
			icon.setBackgroundResource(R.drawable.challenges_icon);
		else if(labelId.equals("4"))
			icon.setBackgroundResource(R.drawable.body_icon);
		*/
		
		if(!labelId.equals("1"))
			line.setBackgroundResource(R.color.dark_black);
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
		
	}
}