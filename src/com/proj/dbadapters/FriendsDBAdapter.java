package com.proj.dbadapters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.proj.friend.Friend;
import com.proj.service.ServiceMeta;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FriendsDBAdapter extends SQLiteOpenHelper {

	private static String LOG_TAG = "FriendsDBAdapter";
	private static final int DATABASE_VERSION = 1;
	private String id;
	private String password;
	private FriendService service;
	private static final String TAG = "FriendsAdapter";

	public FriendsDBAdapter(Context context, String _id, String _password) {
		super(context, FriendService.DB_NAME, null, DATABASE_VERSION);
		id = _id;
		password = _password;
		service = new FriendService();
		service.setUp(id, password);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE " + FriendService.DB_TABLE + " ("
				+ FriendService.DB_USERNAME + " TEXT," + FriendService.DB_JSON
				+ " TEXT" + ");");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS notes");
		onCreate(db);
	}

	private boolean setData(String user, String info) {

		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(FriendService.DB_USERNAME, user);
		values.put(FriendService.DB_JSON, info);
		if(info.equals(ServiceMeta.CONNECTION_FAILURE) || info.equals(ServiceMeta.NO_INFO_FOUND))
		{
			return false;
		}
		try {
			db.update(FriendService.DB_TABLE, values, FriendService.DB_USERNAME+"="+user, null);
			db.close();
			return true;
		} catch (SQLiteException e) {
			Log.e(LOG_TAG, e.toString());
			db.insert(FriendService.DB_TABLE, null, values);
			db.close();
			return true;
		}
	}

	public boolean syncData() {
		String json = service.parseJson();
		return setData(id, json);
	}

	public Object getData() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(FriendService.DB_TABLE, null, null, null, null,
				null, null);
		if (c.moveToFirst()) {
			db.close();
			return service.parseJson(c.getString(1));
		}

		return null;
	}

	private static class FriendService {

		
		private static final String PIC_URL = "small_avatar_filename";
		private static final String Friend_Name = "friend_username";
		private static final String Weekly_Points = "current_week_points";
		private static final String LOG_TAG = "FriendParser";
		private String requestUrl = "http://slimkicker.com/getFriends.json?username=";
		public static final String DB_NAME = "friend.db";
		public static final String DB_TABLE = "friendInfo";
		public static final String DB_USERNAME = "username";
		public static final String DB_JSON = "json";
		
		
		public String setUp(String name, String password) {

			requestUrl = requestUrl + name + "&";
			requestUrl = requestUrl + "password=" + password;

			return requestUrl;
		}

		public String parseJson() {
			HttpClient httpclient = new DefaultHttpClient();

			HttpGet post = new HttpGet(requestUrl);
			
			// Prepare a request object
			/*
			HttpPost post = new HttpPost(test);
			 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		        nameValuePairs.add(new BasicNameValuePair("username", "AznHisoka"));
		        nameValuePairs.add(new BasicNameValuePair("password", "scryed"));
		        nameValuePairs.add(new BasicNameValuePair("active_date", "20120331"));
		        nameValuePairs.add(new BasicNameValuePair("id", "2110754"));
		        nameValuePairs.add(new BasicNameValuePair("meal_type", "0"));
		        nameValuePairs.add(new BasicNameValuePair("num_servings", "1"));
		        nameValuePairs.add(new BasicNameValuePair("serving_type", "0"));
		        
		        try {
					post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				} catch (UnsupportedEncodingException e1) {
					return ServiceMeta.CONNECTION_FAILURE;
				}
				*/
			// Execute the request
			HttpResponse response;
			try {
				Log.i(LOG_TAG, "attempt connection");
				response = httpclient.execute(post);
				Log.i(LOG_TAG, "connection made");

				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					// If the response does not enclose an entity, there is no
					// need
					// to worry about connection release

					if (entity != null) {
						// A Simple JSON Response Read
						InputStream instream = entity.getContent();
						Log.i(LOG_TAG, "got stream from server");
						String friends = convertStreamToString(instream);
						return friends;
					}
				} else {
					return ServiceMeta.CONNECTION_FAILURE;
				}
			} catch (Exception e) {
				return ServiceMeta.NO_INFO_FOUND;
			}

			return ServiceMeta.NO_INFO_FOUND;
		}

		private String convertStreamToString(InputStream is) throws IOException {
			/*
			 * To convert the InputStream to String we use the
			 * BufferedReader.readLine() method. We iterate until the
			 * BufferedReader return null which means there's no more data to
			 * read. Each line will appended to a StringBuilder and returned as
			 * String.
			 */
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
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

		public List<Friend> parseJson(String json) {
			List<Friend> friendList = new ArrayList<Friend>();
			JSONObject json_friend;
			try {
				json_friend = new JSONObject(json);

				JSONArray friend_arr = json_friend.getJSONArray("friends");
				int length = friend_arr.length();
				for (int i = 0; i < length; ++i) {
					JSONObject friend_json = friend_arr.getJSONObject(i);
					Friend friend = parseFriends(friend_json);
					if (friend != null) {
						friendList.add(friend);
					}
				}
			} catch (Exception e) {
				return friendList;
			}
			Collections.sort(friendList);
			doIndexing(friendList);
			return friendList;
		}

		private Friend parseFriends(JSONObject json) {

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

		private void doIndexing(List<Friend> list) {
			Integer index = 1;
			for (Friend friend : list) {
				friend.setIndex(index.toString());
				++index;
			}
		}

	}

}
