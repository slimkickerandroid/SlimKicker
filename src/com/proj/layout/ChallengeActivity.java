package com.proj.layout;

import com.joelapenna.foursquared.R;
import com.joelapenna.foursquared.widget.SegmentedButton;
import com.proj.challenges.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.*;
/**
 * @author www.androidcookers.co.cc
 * {@link www.androidcookers.co.cc}
 *
 */
public class ChallengeActivity extends Activity {
	private ListView listView;
	private String LOG_TAG = "ChallengeActivity";
	
	private List<ChallengeInfo> getInfo (){
		ArrayList<ChallengeInfo> info = new ArrayList<ChallengeInfo>();
		
		Map<String, String> challengeMap = new HashMap<String, String>();
		challengeMap.put("id", "1");
		challengeMap.put("name", "No Snacks After Dinner");
		ChallengeInfo challenge = new ChallengeInfo(challengeMap);
		info.add(challenge);
		
		Map<String, String> challengeMap2 = new HashMap<String, String>();
		challengeMap2.put("id", "1");
		challengeMap2.put("name", "No Snacks After Dinner");
		ChallengeInfo challenge2 = new ChallengeInfo(challengeMap2);
		info.add(challenge2);
		
		return info;
	}
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.challenges);
		
        SegmentedButton buttons = (SegmentedButton)findViewById(R.id.segmented);
        buttons.clearButtons();
        buttons.addButtons("Recommended",
                "My Challenges", "Browse");

        // First button is selected
        buttons.setPushedButtonIndex(0);
		
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
        listView = (ListView) findViewById(R.id.challenge_list);
               
        String[] challengeNames = {"No Snacks After Dinner", "Replace Soda Challenge", "Replace Soda Challenge", "Replace Soda Challenge", "Replace Soda Challenge"};
        String[] challengeDescs = {"Do not eat anything after dinner for next 7 days. This include desserts, candy, and midnight snacks. You can drink tea.",
        		"Replace all soda with tea, water, or coffee (unsweetened) for the next 7 days.",
        		"Just put on your sneakers for 10 mins everyday this week.",
        		"Pick an unhealthy snack or food you indulge in, and eat a healthier alternative for the next 7 days.",
        		"Get 3 hours of slow movement/exercise in this week."};
        		
    		Integer[] badges = new Integer[] {R.drawable.wolf_blue, R.drawable.soda_lime, R.drawable.slow_down, R.drawable.new_veggie, R.drawable.mix};
    		
         List<ChallengeInfo> info = new ArrayList<ChallengeInfo>();

    		for(int i =0; i < challengeNames.length; i++){
    			Map<String, String> map = new HashMap<String, String>();
    			map.put("challenge_name", challengeNames[i]);
    			map.put("challenge_desc", challengeDescs[i]);
    			map.put("badge", badges[i].toString());
    			
    			Log.i(LOG_TAG, "Challenge Name: " + challengeNames[i] + ", Challenge Desc: " + challengeDescs[i] + ", Badge: " + badges[i].toString());
    			
    			ChallengeInfo challenge = new ChallengeInfo(map);
    			info.add(challenge);
    		}
    		
        ArrayAdapter<ChallengeInfo> adapter = new ChallengeArrayAdapter(this,
        		info);

        	listView.setAdapter(adapter);
    }
}