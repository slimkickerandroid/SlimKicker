package com.proj.food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.proj.service.FoodMeta;

import android.util.Log;

public class FoodService {

	private static String Search_Url = "http://www.slimkicker.com/search.json?keyword=";
	private static String Recent_Url = "http://www.slimkicker.com/getFoodEntries.json?username=";// AznHisoka&password=scryed00";
	private static String Created_Url = "http://www.slimkicker.com/getCreatedFoods.json?username=";
	private static String Recipes_Url = "http://www.slimkicker.com/getRecipes.json?username=";
	private static String Recent_Tag = "food_entries";
	private static final String LOG_TAG = "FoodService";

	// http://www.slimkicker.com/getFoodEntries.json?username=AznHisoka&password=scryed00

	public ArrayList<Food> getRecentList(String username, String password) {
		String url = Recent_Url + username + "&password=" + password;
		Log.i(LOG_TAG, url);
		ArrayList<Food> foodList = new ArrayList<Food>();
		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(url.toString());

		// Execute the request
		HttpResponse response;

		try {
			response = httpclient.execute(httpget);

			if (response.getStatusLine().getStatusCode() == 200) {
				// Connection was established. Get the content.
				Log.i(LOG_TAG, "connection made");
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need
				// to worry about connection release

				if (entity != null) {
					// A Simple JSON Response Read
					InputStream instream = entity.getContent();
					Log.i(LOG_TAG, "got stream from server");
					String result = convertStreamToString(instream);
					Log.i(LOG_TAG, result);
					JSONObject resultJson = new JSONObject(result);
					JSONArray foodArray = resultJson.getJSONArray(Recent_Tag);
					for (int i = 0; i < foodArray.length(); i++) {
						JSONObject food = foodArray.getJSONObject(i);
						if (food != null) {
							Food foodBuilder = getFood(food);
							if (foodBuilder != null)
								foodList.add(foodBuilder);

						}

					}

					foodList.add(null);
					// Close the stream.
					instream.close();
				}
			} else {
				// TODO
			}
		} catch (IOException ex) {

			Log.i(LOG_TAG, ex.toString());
			// TODO
		} catch (JSONException ex) {
			// TODO
			Log.i(LOG_TAG, ex.toString());
		}

		return foodList;
	}

	// http://www.slimkicker.com/getCreatedFoods.json?username=AznHisoka&password=scryed00

	public ArrayList<Food> getCreatedList(String username, String password) {
		String url = Created_Url + username + "&password=" + password;
		Log.i(LOG_TAG, url);
		ArrayList<Food> foodList = new ArrayList<Food>();
		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(url.toString());

		// Execute the request
		HttpResponse response;

		try {
			response = httpclient.execute(httpget);

			if (response.getStatusLine().getStatusCode() == 200) {
				// Connection was established. Get the content.
				Log.i(LOG_TAG, "connection made");
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need
				// to worry about connection release

				if (entity != null) {
					// A Simple JSON Response Read
					InputStream instream = entity.getContent();
					Log.i(LOG_TAG, "got stream from server");
					String result = convertStreamToString(instream);
					Log.i(LOG_TAG, result);
					JSONObject resultJson = new JSONObject(result);
					JSONArray foodArray = resultJson.getJSONArray(Recent_Tag);
					for (int i = 0; i < foodArray.length(); i++) {
						JSONObject food = foodArray.getJSONObject(i);
						if (food != null) {
							Food foodBuilder = getFood(food);
							if (foodBuilder != null)
								foodList.add(foodBuilder);

						}

					}

					foodList.add(null);
					// Close the stream.
					instream.close();
				}
			} else {
				// TODO
			}
		} catch (IOException ex) {

			Log.i(LOG_TAG, ex.toString());
			// TODO
		} catch (JSONException ex) {
			// TODO
			Log.i(LOG_TAG, ex.toString());
		}

		return foodList;
	}

	//www.slimkicker.com/getRecipes.json
	
	public ArrayList<Food> getRecipesList(String username, String password) {
		String url = Recipes_Url + username + "&password=" + password;
		Log.i(LOG_TAG, url);
		ArrayList<Food> foodList = new ArrayList<Food>();
		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(url.toString());

		// Execute the request
		HttpResponse response;

		try {
			response = httpclient.execute(httpget);

			if (response.getStatusLine().getStatusCode() == 200) {
				// Connection was established. Get the content.
				Log.i(LOG_TAG, "connection made");
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need
				// to worry about connection release

				if (entity != null) {
					// A Simple JSON Response Read
					InputStream instream = entity.getContent();
					Log.i(LOG_TAG, "got stream from server");
					String result = convertStreamToString(instream);
					Log.i(LOG_TAG, result);
					JSONObject resultJson = new JSONObject(result);
					JSONArray foodArray = resultJson.getJSONArray(Recent_Tag);
					for (int i = 0; i < foodArray.length(); i++) {
						JSONObject food = foodArray.getJSONObject(i);
						if (food != null) {
							Food foodBuilder = getFood(food);
							if (foodBuilder != null)
								foodList.add(foodBuilder);

						}

					}

					foodList.add(null);
					// Close the stream.
					instream.close();
				}
			} else {
				// TODO
			}
		} catch (IOException ex) {

			Log.i(LOG_TAG, ex.toString());
			// TODO
		} catch (JSONException ex) {
			// TODO
			Log.i(LOG_TAG, ex.toString());
		}

		return foodList;
	}

	
	// http://www.slimkicker.com/search.json?keyword=apple&beginIndex=10

	public ArrayList<Food> getSearchList(String keyword, int index) {

		StringBuilder url = new StringBuilder(Search_Url);
		ArrayList<Food> foodList = new ArrayList<Food>();
		url.append(keyword).append("&beginIndex=").append(index);
		Log.i(LOG_TAG, url.toString());
		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(url.toString());

		// Execute the request
		HttpResponse response;

		try {
			response = httpclient.execute(httpget);

			if (response.getStatusLine().getStatusCode() == 200) {
				// Connection was established. Get the content.
				Log.i(LOG_TAG, "connection made");
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need
				// to worry about connection release

				if (entity != null) {
					// A Simple JSON Response Read
					InputStream instream = entity.getContent();
					Log.i(LOG_TAG, "got stream from server");
					String result = convertStreamToString(instream);
					// Load the requested page converted to a string into a
					// JSONObject.
					JSONArray foodArray = new JSONArray(result);
					Log.i(LOG_TAG, result);
					for (int i = 0; i < index + 10; i++) {
						JSONObject food = foodArray.getJSONObject(i);
						if (food != null) {
							Food foodBuilder = getFood(food);
							if (foodBuilder != null)
								foodList.add(foodBuilder);

						}

					}

					foodList.add(null);
					// Close the stream.
					instream.close();
				}
			} else {
				// TODO
			}
		} catch (IOException ex) {

			Log.i(LOG_TAG, ex.toString());
			// TODO
		} catch (JSONException ex) {
			// TODO
			Log.i(LOG_TAG, ex.toString());
		}

		return foodList;
	}

	private Food getFood(JSONObject food) throws JSONException {
		Log.i(LOG_TAG, "started parsing");
		int serving = food.getInt("serving_type");
		
		Map<String, Double> ingredients = new HashMap<String, Double>();
		ingredients.put("sugars", sanityCheck(food, "sugars"));
		ingredients.put("calcium", sanityCheck(food, "calcium"));
		ingredients.put("vitamin_a", sanityCheck(food, "vitamin_a"));
		ingredients.put("fat", sanityCheck(food, "fat"));
		ingredients.put("fiber", sanityCheck(food, "fiber"));

		Log.i(LOG_TAG, "parsed upto fiber");

		ingredients.put("polyunsaturated_fat", sanityCheck(food,
				"polyunsaturated_fat"));
		ingredients.put("vitamin_c", sanityCheck(food, "vitamin_c"));
		ingredients.put("carbs", sanityCheck(food, "carbs"));

		Log.i(LOG_TAG, "carbs");

		ingredients.put("protein", sanityCheck(food, "protein"));

		Log.i(LOG_TAG, "protein");

		ingredients.put("cholesterol", sanityCheck(food, "cholesterol"));

		Log.i(LOG_TAG, "cholestrol");

		ingredients.put("sodium", sanityCheck(food, "sodium"));

		Log.i(LOG_TAG, "sodium");

		ingredients.put("iron", sanityCheck(food, "iron"));

		Log.i(LOG_TAG, "iron");

		ingredients.put("calories_from_fat", sanityCheck(food,
				"calories_from_fat"));

		Log.i(LOG_TAG, "cal_from_fat");

		ingredients.put("monounsaturated_fat", sanityCheck(food,
				"monounsaturated_fat"));

		Log.i(LOG_TAG, "mono_fat");

		ingredients.put("potassium", sanityCheck(food, "potassium"));

		Log.i(LOG_TAG, "parsing complete");

		Map<String, Double> finalIngredients = new HashMap<String, Double>();

		JSONObject serving_type;
		JSONArray servings = food.getJSONArray("servings");
		Food newFood = null;
		Map<String, String> servingTypeMap = new HashMap<String, String>();
		List<String> serving_list = new ArrayList<String>();
		for (int i = 0; i < servings.length(); i++) {
			serving_type = servings.getJSONObject(i);
			String serving_type_name = serving_type.getString("name");
			String serving_type_no = Integer.toString(serving_type.getInt("serving_type"));
			servingTypeMap.put(serving_type_name, serving_type_no);
			serving_list.add(serving_type_name);
			
			if (serving == serving_type.getInt("serving_type")) {
				// Log.i(LOG_TAG, String.valueOf(serving));		
			    int id = serving_type.getInt(FoodMeta.FOOD_ID);
				newFood = new Food(id, food.getString("food_description"),
						serving_type.getString("name"));
				Double muiltiplier = serving_type.getDouble("multiplier");
				Iterator<String> itr = ingredients.keySet().iterator();
				while (itr.hasNext()) {
					String key = (String) itr.next();
					Double val = ingredients.get(key);
					if (val != null) {
						finalIngredients.put(key, val * muiltiplier);
						String finalVal = finalIngredients.get(key).toString();
						if (finalVal != null) {
							int len = finalVal.length();
							if (len > 6)
								newFood.SetIngredient(key, finalVal.substring(
										0, 6));
						}
					}
				}
			}
		}
		if (newFood != null)
			newFood.setServingList(serving_list);

		return newFood;

	}

	private Double sanityCheck(JSONObject food, String ingredient)
			throws JSONException {
		if (!food.isNull(ingredient)) {
			return food.getDouble(ingredient);
		}

		return null;
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

}
