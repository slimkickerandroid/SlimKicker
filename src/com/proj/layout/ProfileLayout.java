package com.proj.layout;

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
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
		
		ProfileDBAdapter adapter = new ProfileDBAdapter(getBaseContext(), username, password);
		FriendsDBAdapter friend_adapter = new FriendsDBAdapter(getBaseContext(), username, password);
		Object profileData = adapter.getData();
		List<Friend> friend_list = (List<Friend>) friend_adapter.getData();
		Profile profile = null;
		if(profileData instanceof Profile )
		{
			profile = (Profile)profileData;
		}
		picture = new ProfilePicture();
		
		if(profile != null)
		{
			 ImageView image =  (ImageView)findViewById(R.id.imageView2);
			 picture.setImageOnView(profile.getImageURL(), image);
			 
			 TextView pointText =  (TextView)findViewById(R.id.points);
			 pointText.setText(profile.getPoints());
			 
			 TextView rewardText = (TextView)findViewById(R.id.rewards);
			 rewardText.setText(profile.getRewards());
			 
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
			 
			 SeekBar progress = (SeekBar)findViewById(R.id.progressBar);
			 int prog = getValue(profile.getDiet_points())+ getValue(profile.getChallenges_points())
			 						+ getValue(profile.getChallenges_points());
			 progress.setProgress(prog);
			 progress.setMax(100);
			 progress.setEnabled(false);
			 
			if(friend_list != null && friend_list.size() > 0){
			
			
			    Friend friend1 = friend_list.get(0);
			    
			    TextView index1 = (TextView)findViewById(R.id.friend1index);
			    index1.setText("1");
			    
			    ImageView view1 =(ImageView)findViewById(R.id.image1Url);
				picture.setImageOnView(friend1.getURL(), view1);
				
				TextView friend1Name = (TextView)findViewById(R.id.friend1Name);
				friend1Name.setText(friend1.getName());
				
				TextView points1 = (TextView)findViewById(R.id.weeklypoints1);
			    points1.setText(friend1.getWeekly_points());
			    
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

			}
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		SetUILayout();
	    prefs = getSharedPreferences(ProfileMeta.USER_INFO, MODE_PRIVATE);
	    username = prefs.getString(ProfileMeta.USER_ID, null);
		password = prefs.getString(ProfileMeta.PASSWORD, null);
	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.challenges:
	    	Log.i(LOG_TAG, "callin challenges");
	    	Intent food = new Intent("SearchFood");
			startActivity(food);
	    	return true;
	    case R.id.friends:
		//	service.getProfileData();  
	        return true;
	    case R.id.track:
	    	Intent i = new Intent("FoodTab");
	    	Log.i(LOG_TAG, "Body Menu called");
			startActivity(i);	    	
	        return true;
	    default:
	    	return true;
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.profile_menu, menu);
	    return true;
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