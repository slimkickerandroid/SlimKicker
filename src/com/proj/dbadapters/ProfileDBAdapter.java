package com.proj.dbadapters;

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

import com.proj.profile.IProfileService;
import com.proj.profile.Profile;
import com.proj.service.ProfileMeta;
import com.proj.service.ServiceMeta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProfileDBAdapter extends SQLiteOpenHelper {

	private static final String TAG = "ProfileDBAdapter";
	private static final int DATABASE_VERSION = 1;
	private ProfileService service;
	private String id;
	private String password;

	public ProfileDBAdapter(Context context, String _id, String _password) {
		super(context, ProfileService.DB_NAME, null, DATABASE_VERSION);
		service = new ProfileService();
		id = _id;
		password = _password;
		service.SetUp(id, password);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE " + ProfileService.DB_TABLE + " ("
				+ ProfileService.DB_USERNAME + " TEXT,"
				+ ProfileService.DB_JSON + " TEXT" + ");");

	}

	private boolean setData(String user, String info) {

		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ProfileService.DB_USERNAME, user);
		values.put(ProfileService.DB_JSON, info);
		if (info.equals(ServiceMeta.NO_INFO_FOUND)) {
			return false;
		}
		if (!info.equals(ServiceMeta.CONNECTION_FAILURE)) {
			try {
				db.delete(ProfileService.DB_TABLE, null, null);
				db.insert(ProfileService.DB_TABLE, null, values);
				db.close();
				return true;
			} catch (SQLiteException e) {
				db.insert(ProfileService.DB_TABLE, null, values);
				db.close();
				return true;
			}
		}
		return false;
	}

	public boolean syncData() {
		String json = service.getProfileJson();
		return setData(id, json);
	}

	public Object getData() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(ProfileService.DB_TABLE, null, null, null, null,
				null, null);
		if (c.moveToFirst()) {
			db.close();
			return service.parseJson(c.getString(1));
		}

		return null;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS notes");
		onCreate(db);
	}

	private static class ProfileService implements IProfileService {

		// DB related

		public static final String DB_NAME = "profile.db";
		public static final String DB_TABLE = "user";
		public static final String DB_USERNAME = "username";
		public static final String DB_JSON = "json";

		// -----------------------------------------

		private static final String LOG_TAG = "ProfileService";
		private String URL1 = "http://www.slimkicker.com/getInfo.json?username=";
		private String URL2 = "&password=";
		private String request = "";

		@Override
		public void SetUp(String _userName, String _password) {
			String addUsername = URL1 + _userName;
			String addPassword = URL2 + _password;
			request = addUsername + addPassword;
		}

		@Override
		public String getProfileJson() {
			return JsonParsing();
		}

		public String JsonParsing() {

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
					HttpEntity entity = response.getEntity();

					if (entity != null) {
						// A Simple JSON Response Read
						InputStream instream = entity.getContent();
						Log.i(LOG_TAG, "got stream from server");
						String profile = convertStreamToString(instream);
						if (profile.length() < 20)
							return ServiceMeta.NO_INFO_FOUND;
						Log.i(LOG_TAG, profile);
						instream.close();
						return profile;
					}
				} else {
					return ServiceMeta.CONNECTION_FAILURE;
				}
			} catch (IOException ex) {

				Log.d(LOG_TAG, ex.toString());
				return ServiceMeta.CONNECTION_FAILURE;
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

		@SuppressWarnings("unchecked")
		@Override
		public Profile parseJson(String json) {

			try {
				JSONObject profileInfo = new JSONObject(json);
				JSONObject info = profileInfo.getJSONObject(ProfileMeta.User_Info);

				JSONObject stats = profileInfo.getJSONObject(ProfileMeta.Daily_Stats);
				Iterator<String> keys = stats.keys();

				JSONObject stat = null;
				while (keys.hasNext()) {
					String key = keys.next();
					stat = stats.optJSONObject(key);
				}

				Profile profile = new Profile();

				Integer level = info.getInt(ProfileMeta.Level);
				profile.setLevel(level.toString());

				Integer ach = info.getInt(ProfileMeta.Num_Ach);
				profile.setAchievement(ach.toString());

				Integer rewards = info.getInt(ProfileMeta.Num_Rewards);
				profile.setReward(rewards.toString());

				String url = info.getString(ProfileMeta.Avatar_URL);
				profile.setImageURL(url);

				Integer points = info.getInt(ProfileMeta.Points);
				profile.setPoints(points.toString());

				if (stat != null) {

					Integer week_exercise = stat.getInt(ProfileMeta.Exercise_Points);
					profile.setExcecise_points(week_exercise.toString());

					Integer week_diet = stat.getInt(ProfileMeta.Diet_Points);
					profile.setDiet_points(week_diet.toString());

					Integer week_challenges = stat.getInt(ProfileMeta.Challenges_Points);
					profile.setChallenges_points(week_challenges.toString());

					Integer total_points = week_exercise + week_diet
							+ week_challenges;
					profile.setTotal_points(total_points.toString());

				}

				return profile;
			} catch (JSONException e) {
				return null;
			}

		}

	}

}
