package com.proj.layout;

import java.util.ArrayList;
import java.util.List;

import com.proj.dbadapters.FriendsDBAdapter;
import com.proj.dbadapters.ProfileDBAdapter;
import com.proj.friend.Friend;
import com.proj.profile.IProfilePicture;
import com.proj.profile.Profile;
import com.proj.profile.ProfilePicture;
import com.proj.service.ProfileMeta;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.util.Log;
import android.widget.ImageView;
//import android.widget.ProgressBar;

public class ProfileLayout extends Activity {
	private static String LOG_TAG = "ProfileLayout";
	private String username;
	private String password;
	private  SharedPreferences prefs;
	IProfilePicture picture;

	//private ProgressBar mProgress;
	
	private void SetUILayout()
	{
		Log.i(LOG_TAG, "Setting profile");
		Profile profile = null;
		List<Friend> friend_list = new ArrayList<Friend>();
		String debug = getResources().getString(R.string.debug);
		if(debug.equals("false")){
			Log.i(LOG_TAG, "Debug is false! !!");

			ProfileDBAdapter adapter = new ProfileDBAdapter(getBaseContext(), username, password);
			FriendsDBAdapter friend_adapter = new FriendsDBAdapter(getBaseContext(), username, password);
			Object profileData = adapter.getData();
			friend_list = (List<Friend>) friend_adapter.getData();
	
			if(profileData instanceof Profile )
			{
				profile = (Profile)profileData;
			}
		} else {
			profile = new Profile();
			profile.setAchievement("1");
			profile.setChallenges_points("100");
			profile.setDiet_points("50");
			profile.setEmailAddress("test@slimkicker.com");
			profile.setExcecise_points("10");
			profile.setImageURL("http://s3.amazonaws.com/slimkicker_avatars/slimkickeradmin.png");
			profile.setLevel("4");
			profile.setLogin("tester", "scryed");
			profile.setPoints("160");
			profile.setReward("1");
			profile.setTotal_points("5000");
			
			Friend friend = new Friend();
			friend.setIndex("1");
			friend.setName("SlimKickerAdmin");
			friend.setURL("http://s3.amazonaws.com/slimkicker_defaultavatars/girl-6-normal.png");
			friend.setWeekly_points("100");
			
			friend_list.add(friend);
		}
		picture = new ProfilePicture();
		
		if(profile != null)
		{
			 ImageView image =  (ImageView)findViewById(R.id.imageView2);
			 picture.setImageOnView(profile.getImageURL(), image);
			 
			 TextView pointText =  (TextView)findViewById(R.id.points);
			 pointText.setText(profile.getPoints());
			 
			 //TextView rewardText = (TextView)findViewById(R.id.rewards);
			 //rewardText.setText(profile.getRewards());
			 
			 TextView achText = (TextView)findViewById(R.id.achievement);
			 achText.setText(profile.getAchievement());
			 
			 TextView levelText = (TextView)findViewById(R.id.level);
			 levelText.setText(profile.getLevel());
			 
			 TextView exercise_text = (TextView)findViewById(R.id.exercise);
			 exercise_text.setText(profile.getExcecise_points());
			 
			 TextView challenges_text = (TextView)findViewById(R.id.challenges);
			 challenges_text.setText(profile.getChallenges_points());
			 
			 TextView total_points = (TextView)findViewById(R.id.total_points);
			 total_points.setText(profile.getTotal_points());
			 
			 
			 TextView diet = (TextView)findViewById(R.id.diet);
			 diet.setText(profile.getDiet_points());	
			 
			 //SeekBar progress = (SeekBar)findViewById(R.id.progressBar);
			 //int prog = getValue(profile.getDiet_points())+ getValue(profile.getChallenges_points())
			 						//+ getValue(profile.getChallenges_points());
			 //progress.setProgress(prog);
			 //progress.setMax(100);
			 //progress.setEnabled(false);
			 
			if(friend_list != null && friend_list.size() > 0){
			
			
			    Friend friend1 = friend_list.get(0);
			    
			    TextView index1 = (TextView)findViewById(R.id.friend1index);
			    index1.setText("#1");
			    
			    ImageView view1 =(ImageView)findViewById(R.id.image1Url);
				picture.setImageOnView(friend1.getURL(), view1);
				
				TextView friend1Name = (TextView)findViewById(R.id.friend1Name);
				friend1Name.setText(friend1.getName());
				
				TextView points1 = (TextView)findViewById(R.id.weeklypoints1);
			    points1.setText(friend1.getWeekly_points());
			    /*
			    Friend friend2 = friend_list.get(1);
			    
			    TextView index2 = (TextView)findViewById(R.id.friend2index);
			    index2.setText("2");
				
			    ImageView view2 =(ImageView)findViewById(R.id.image2Url);
				picture.setImageOnView(friend2.getURL(), view2);
				
				TextView friend2Name = (TextView)findViewById(R.id.friend2Name);
				friend2Name.setText(friend2.getName());
				
				TextView points2 = (TextView)findViewById(R.id.weeklypoints2);
			    points2.setText(friend2.getWeekly_points());
			
			    Friend friend3 = friend_list.get(2);
			    
			    TextView index3 = (TextView)findViewById(R.id.friend3index);
			    index3.setText("3");
				
			    ImageView view3 =(ImageView)findViewById(R.id.image3Url);
				picture.setImageOnView(friend3.getURL(), view3);
				
				TextView friend3Name = (TextView)findViewById(R.id.friend3Name);
				friend3Name.setText(friend3.getName());
				
				TextView points3 = (TextView)findViewById(R.id.weeklypoints3);
			    points3.setText(friend3.getWeekly_points());  
				*/
			}
			
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

		setContentView(R.layout.profile);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
		
		Integer[] ids = {R.id.points_label, R.id.badges_label, R.id.level_label, 
				
				R.id.points, R.id.achievement, R.id.level,
				R.id.total_points, R.id.diet, R.id.exercise, R.id.challenges};
		
		for(int i=0; i< ids.length; i++){
			int id = ids[i];
			TextView txt = (TextView) findViewById(id);
			Typeface font = Typeface.createFromAsset(getAssets(), "museo700regular.ttf");
			txt.setTypeface(font);
		}
		
		Integer dailySummaryIds[] = {R.id.progress_next_level,
				R.id.daily_summary_total_points, R.id.daily_summary_diet, R.id.daily_summary_exercise, R.id.daily_summary_challenges};
		
		for(int i =0; i < dailySummaryIds.length; i++){
			int id = dailySummaryIds[i];
			TextView txt = (TextView) findViewById(id);
			Typeface font = Typeface.createFromAsset(getAssets(), "arial.ttf");
			txt.setTypeface(font);	
		}
		
		TextView txt = (TextView) findViewById(R.id.daily_summary_label);
		Typeface font = Typeface.createFromAsset(getAssets(), "arial_bold.ttf");
		txt.setTypeface(font);
		
		txt = (TextView) findViewById(R.id.leaderboard_label);
		txt.setTypeface(font);
		
		txt = (TextView) findViewById(R.id.this_week_label);
		txt.setTypeface(font);
		
		SetUILayout();
	    prefs = getSharedPreferences(ProfileMeta.USER_INFO, MODE_PRIVATE);
	    username = prefs.getString(ProfileMeta.USER_ID, null);
		password = prefs.getString(ProfileMeta.PASSWORD, null);
	}
	
	private int getValue(String str)
	{
		try{
	 int val = Integer.parseInt(str);
	 return val;
	 }catch(NumberFormatException e)
	 {
		 return 0;
	 }
	}
	
}