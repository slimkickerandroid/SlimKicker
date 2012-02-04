package com.proj.friend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;
import java.util.*;


public class FriendService {
	
	private static final String PIC_URL ="small_avatar_filename";
	private static final String Friend_Name = "friend_username";
	private static final String Weekly_Points = "current_week_points";
	private static final String LOG_TAG = "FriendParser";
	private String requestUrl = "http://slimkicker.com/getFriends.json?username=";
	private List<Friend> friendList = new ArrayList<Friend>();
	
	private String createRequest(String name, String password){
	
	requestUrl = requestUrl+name+"&";
	requestUrl = requestUrl+"password="+password;
	
	return requestUrl;
	}
	
	public List<Friend> parseFromJson(String userName, String password)
	{
		String request = createRequest(userName, password);
		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(request);
		
		// Execute the request
		HttpResponse response;
		try {
			Log.i(LOG_TAG, "attempt connection");
			response = httpclient.execute(httpget);
			Log.i(LOG_TAG, "connection made");
			
			if (response.getStatusLine().getStatusCode() == 200) 
			{
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need
				// to worry about connection release

				if (entity != null) {
					// A Simple JSON Response Read
					InputStream instream = entity.getContent();
					Log.i(LOG_TAG, "got stream from server");
					String friends = convertStreamToString(instream);
					JSONObject json_friend = new JSONObject(friends);
					JSONArray friend_arr = json_friend.getJSONArray("friends");
					int length = friend_arr.length();
					for(int i =0; i<length;++i)
					{
						JSONObject friend_json = friend_arr.getJSONObject(i);
						Friend friend = parseFriends(friend_json);
						if(friend != null)
						{
							friendList.add(friend);
						}
					}
					
				}
			}
		} 
		catch(Exception e)
		{
			return null;
		}
		Collections.sort(friendList);
		doIndexing(friendList);
		return friendList;
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
	
	private Friend parseFriends(JSONObject json)
	{
	
		try {
			String name = json.getString(Friend_Name);
			String points = json.getString(Weekly_Points);
			String url = json.getString(PIC_URL);
			
			Friend friend = new Friend();
			friend.setName(name);
			friend.setURL(url);
			friend.setWeekly_points(points);
			return friend;
			
		} catch (JSONException e) {
			return null;
		}
		
	}
	
	private void doIndexing(List<Friend> list)
	{
		Integer index = 1;
		for(Friend friend : list)
		{
			friend.setIndex(index.toString());
		    ++index;	
		}
	}
	
}
