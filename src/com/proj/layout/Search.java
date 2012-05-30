package com.proj.layout;

import java.util.ArrayList;

import com.joelapenna.foursquared.MainActivity;
import com.joelapenna.foursquared.R;
import com.joelapenna.foursquared.widget.SegmentedButton;
import com.joelapenna.foursquared.widget.SegmentedButton.OnClickListenerSegmentedButton;
import com.markupartist.android.widget.ActionBar;
import com.proj.food.Food;
import com.proj.food.FoodService;
import com.proj.service.ProfileMeta;

import android.util.Log;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
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
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.search);
		
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setHomeLogo(R.drawable.home_logo);
		
		RadioGroup group1 = (RadioGroup) this.findViewById(R.id.buttongroup1);
        
		group1.check(R.id.option1);
		
		Integer[] options = {R.id.option1, R.id.option2, R.id.option3, R.id.option4};
		
		for(int i=0; i < options.length; i++){
			RadioButton b = (RadioButton)this.findViewById(options[i]);
			
			
		}
		
		
		
		
        TextView dividerText = (TextView)findViewById(R.id.diet_divider_label);
        Typeface font = Typeface.createFromAsset(getAssets(), "arial_bold.ttf");
		dividerText.setTypeface(font);
		
		EditText editText = (EditText) this.findViewById(R.id.editText1);
        font = Typeface.createFromAsset(getAssets(), "arial.ttf");

		editText.setTypeface(font);
		
		//ProgressBar bar = (ProgressBar) this.findViewById(R.id.searching_indicator);
		//bar.setVisibility(View.VISIBLE);
		  // Create an animation


		  // and apply it to your imageview
		  ImageView loadingView = (ImageView) findViewById(R.id.loadingView);
		  loadingView.setVisibility(View.GONE);
		 
		  
		service = new FoodService();
		Log.i(LOG_TAG, "onCreate");
		onFocusReceived();
		// default to recent
		//RecentFoodCommand(null);
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
/*
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
	*/
	void createFoodList(String food_name, int index) {
		
		/*
		 RotateAnimation rotation = new RotateAnimation(
			      0f,
			      360f,
			      Animation.RELATIVE_TO_SELF,
			      0.5f,
			      Animation.RELATIVE_TO_SELF,
			      0.5f);
			  rotation.setDuration(1200);
			  rotation.setInterpolator(new LinearInterpolator());
			  rotation.setRepeatMode(Animation.RESTART);
			  rotation.setRepeatCount(Animation.INFINITE);
			  
			  ImageView loadingView = (ImageView) findViewById(R.id.loadingView);
			  
			  loadingView.setVisibility(View.VISIBLE);
			  
			  findViewById(R.id.loadingView).startAnimation(rotation);
		*/
		hideKeyBoard();
		new SearchTask(food_name, index, this).execute();

		//findViewById(R.id.loadingView).clearAnimation();	
		//ImageView loadingView = (ImageView) findViewById(R.id.loadingView);

		//loadingView.setVisibility(View.GONE);
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
	
	private class SearchTask extends AsyncTask < Void, Void, Void >  {
		private String foodName;
		private int searchIndex;
		private Context context;
		private ArrayList<Food> results;
		
		public SearchTask(String foodName, int searchIndex, Context context) {
			this.foodName = foodName;
			this.searchIndex = searchIndex;			
			this.context = context;
		}
		
		protected void onPreExecute() {     
		
		}
	
		protected Void doInBackground(Void... params) {
			results = service.getSearchList(foodName, searchIndex);
			Log.i("Search", "in search");

			return null;
		}
	
		protected void onPostExecute(Void unused) {
			foodAdapter = new FoodAdapter(context, R.layout.food_row, results);
			ListView food = (ListView) findViewById(R.id.food);
			food.setAdapter(foodAdapter);
			setListeners(food, results);
		}
	
	}
        	
}


