package com.proj.layout;

import java.util.ArrayList;
import com.proj.food.Food;
import com.proj.food.FoodService;
import com.proj.service.ProfileMeta;

import android.util.Log;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.AdapterView.OnItemClickListener;

public class Search extends Activity {

	private final String LOG_TAG = "Search";
	private FoodAdapter foodAdapter = null;
	private FoodService service = null;
	private boolean IsFocused;
	static final int PROGRESS_DIALOG = 0;
    ProgressDialog progressDialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		service = new FoodService();
		Log.i(LOG_TAG, "onCreate");
		onFocusReceived();
		// default to recent
		RecentFoodCommand(null);
		IsFocused = false;
		
	}

	public void onFocusReceived() {
		EditText searchBox = (EditText) findViewById(R.id.editText1);
		searchBox.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus) {
					if (foodAdapter != null) {
						IsFocused = true;
						foodAdapter.clear();
					}
				}

			}
		});
	}

	public void inSearchCommand(View view) 
	{
		EditText searchBox = (EditText) findViewById(R.id.editText1);
		final String food_name = searchBox.getText().toString();
		Log.i(LOG_TAG, "onClick");
		createFoodList(food_name, 0);
	}

	public void RecentFoodCommand(View view) {
		RadioButton recentButton = (RadioButton) findViewById(R.id.radio0);
		if (recentButton.isChecked() && !IsFocused) 
		{
			SharedPreferences prefs = getSharedPreferences(ProfileMeta.USER_INFO, MODE_PRIVATE);
			String userName = prefs.getString(ProfileMeta.USER_ID, null);
			String passWord = prefs.getString(ProfileMeta.PASSWORD, null);
			
			final ArrayList<Food> names = service.getRecentList(userName, passWord);
			ListView food = (ListView) findViewById(R.id.food);
			foodAdapter = new FoodAdapter(this, R.layout.food_row, names);
			food.setAdapter(foodAdapter);
			setListeners(food, names);
		}
	}
	
	public void CreatedFoodCommand(View view) {
		RadioButton createdButton = (RadioButton) findViewById(R.id.radio1);
		if (createdButton.isChecked() && !IsFocused) 
		{
			SharedPreferences prefs = getSharedPreferences(ProfileMeta.USER_INFO, MODE_PRIVATE);
			String userName = prefs.getString(ProfileMeta.USER_ID, null);
			String passWord = prefs.getString(ProfileMeta.PASSWORD, null);
			
			final ArrayList<Food> names = service.getRecentList(userName, passWord);
			ListView food = (ListView) findViewById(R.id.food);
			foodAdapter = new FoodAdapter(this, R.layout.food_row, names);
			food.setAdapter(foodAdapter);
			setListeners(food, names);
		}
	}

	public void RecipeFoodCommand(View view) {
		RadioButton recipeButton = (RadioButton) findViewById(R.id.radio2);
		if (recipeButton.isChecked() && !IsFocused) 
		{
			SharedPreferences prefs = getSharedPreferences(ProfileMeta.USER_INFO, MODE_PRIVATE);
			String userName = prefs.getString(ProfileMeta.USER_ID, null);
			String passWord = prefs.getString(ProfileMeta.PASSWORD, null);
			
			final ArrayList<Food> names = service.getRecentList(userName, passWord);
			ListView food = (ListView) findViewById(R.id.food);
			foodAdapter = new FoodAdapter(this, R.layout.food_row, names);
			food.setAdapter(foodAdapter);
			setListeners(food, names);
		}
	}
	
	void createFoodList(String food_name, int index) {
		ListView food = (ListView) findViewById(R.id.food);

		final ArrayList<Food> names = service.getSearchList(food_name, index);
		Log.i("Search", "in search");
		foodAdapter = new FoodAdapter(this, R.layout.food_row, names);
		food.setOnScrollListener(new EndlessScrollListener(10, food_name,
				index, foodAdapter));
		food.setAdapter(foodAdapter);
		setListeners(food, names);
		hideKeyBoard();
	}
	
	private void hideKeyBoard()
	{
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		EditText searchBox = (EditText) findViewById(R.id.editText1);
		imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
	}
	
	private void setListeners(ListView food, final ArrayList<Food> names)
	{
		food.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Intent foodDetail = new Intent("FoodDetail");
				Food food = names.get(position);
				foodDetail.putExtra("food", food);
				startActivity(foodDetail);

			}
		});
	}
        	
}


