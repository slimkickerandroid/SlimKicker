package com.proj.profile.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;


public class ProfileService implements IProfileService {

	private static final String LOG_TAG = "ProfileService";
	public static final String ConnectionFailure="No connection";
	public static final String InfoUnAvail="UserName/Password not available";
	private String URL1 = "http://www.slimkicker.com/getInfo.json?username=";
	private String URL2 = "&password="; 
	private String request = "" ;
	private static final String User_Info = "user_info"; 
	private static final String Daily_Stats = "daily_stats";
	private static final String Num_Rewards="num_rewards";
	private static final String Num_Ach = "num_achievements";
	private static final String Week_Points = "current_week_points";
	private static final String Level = "level";
	private static final String Avatar_URL = "avatar_filename";
	
	public static final String USER_ID = "user";
	public static final String PASSWORD = "password";
	public static final String USER_INFO = "userinfo";
	
	private static final String Exercise_Points = "exercise_points";
	private static final String Diet_Points = "diet_points";
	private static final String Challenges_Points = "challenges_points";
	
	
	public ProfileService()
	{
		
	}

	@Override
	public void SetUp(String _userName, String _password) {
		String addUsername = URL1+_userName;
		String addPassword = URL2+_password;
		request = addUsername+addPassword;
	}

	@Override
	public String getProfileJson() {
		return JsonParsing();
	}
	
	private String JsonParsing()
	{
		
		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(request);
		
		// Execute the request
		HttpResponse response;
		try {
			Log.i(LOG_TAG, "attempt connection");
			response = httpclient.execute(httpget);
			Log.i(LOG_TAG, "connection made");
			
			if (response.getStatusLine().getStatusCode() == 200) {
				// Connection was established. Get the content.
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need
				// to worry about connection release

				if (entity != null) {
					// A Simple JSON Response Read
					InputStream instream = entity.getContent();
					Log.i(LOG_TAG, "got stream from server");
					String profile = convertStreamToString(instream);
					if(profile.length() < 20) return InfoUnAvail;
					// Load the requested page converted to a string into a
					// JSONObject.
					//JSONObject profile = new JSONObject(result);
					//JSONObject profile = profileArray.getJSONObject(name);
					Log.i(LOG_TAG, profile);
					// Close the stream.
					instream.close();
					return profile;
				}
			} else {
				return ConnectionFailure;
			}
		} catch (IOException ex) {
			
			Log.i(LOG_TAG, ex.toString());
			return ConnectionFailure;
		} 
		return InfoUnAvail;
	}
	
	private String convertStreamToString(InputStream is) throws IOException {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			throw new IOException(); 
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new IOException();
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Profile parseProfileJson(String json) {
		
		try {
			JSONObject profileInfo = new JSONObject(json);
		    JSONObject info = profileInfo.getJSONObject(User_Info);
		    
		    JSONObject stats = profileInfo.getJSONObject(Daily_Stats);
		    Iterator<String> keys =  stats.keys();
		    
		    JSONObject stat = null;
		    while(keys.hasNext())
		    {
		    	String key = keys.next();
		    	stat = stats.optJSONObject(key);
		    }
		    
		    Profile profile = new Profile();
		    
		    Integer level = info.getInt(Level);
		    profile.setLevel(level.toString());
		    
		    Integer ach = info.getInt(Num_Ach);
		    profile.setAchievement(ach.toString());
		    
		    Integer rewards = info.getInt(Num_Rewards);
		    profile.setReward(rewards.toString());
		    
		    String url = info.getString(Avatar_URL);
		    profile.setImageURL(url);
		    
		    Integer points = info.getInt(Week_Points);
		    profile.setPoints(points.toString());
		    
		    if(stat != null)
		    {
		    
		    	Integer week_exercise = stat.getInt(Exercise_Points);
		    	profile.setExcecise_points(week_exercise.toString());
		    
		    
		    	Integer week_diet = stat.getInt(Diet_Points);
		    	profile.setDiet_points(week_diet.toString());
		    
		    
		    	Integer week_challenges = stat.getInt(Challenges_Points);
		    	profile.setChallenges_points(week_challenges.toString());
		    
		    	Integer total_points = week_exercise + week_diet + week_challenges;
		    	profile.setTotal_points(total_points.toString());
		    
		    }
		    
		    return profile;
		} catch (JSONException e) {
			return null;
		}
		
	}
	
	
	
}
