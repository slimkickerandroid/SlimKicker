package com.proj.layout;

import java.util.List;
import com.proj.friend.Friend;
import com.proj.friend.FriendService;
import com.proj.profile.service.IProfilePicture;
import com.proj.profile.service.IProfileService;
import com.proj.profile.service.Profile;
import com.proj.profile.service.ProfilePicture;
import com.proj.profile.service.ProfileService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ImageView;
//import android.widget.ProgressBar;

public class ProfileLayout extends Activity {

	private static String LOG_TAG = "Profile";
	private String username;
	private String password;
	IProfilePicture picture;

	//private ProgressBar mProgress;
	
	private void SetUILayout()
	{

		String profileInfo = null;
		Bundle infoBundle =	getIntent().getExtras();
		ProfileService service = null;
		FriendService friend_service = null;
		Profile profile = null;
		picture = new ProfilePicture();
		if(infoBundle != null)
		{
			service = new ProfileService();
			profileInfo = infoBundle.getString("info");
			profile = service.parseProfileJson(profileInfo);
			friend_service = new FriendService();
			username = infoBundle.getString("username");
			password = infoBundle.getString("password");
			
		}
		
		if(service != null)
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
		}		
		
			
			List<Friend> friend_list = friend_service.parseFromJson(username, password);
			//BoardLeaderAdapter adapter = new BoardLeaderAdapter(this, R.layout.boardleader_row, friend_list);
			//v.setAdapter(adapter);
			
			
			if(friend_list.size() > 0){
			
			
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
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		SetUILayout();
	                    
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
	    	
	    	IProfileService service = new ProfileService();
			service.SetUp("AznHisoka","scryed00");
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
	 
}