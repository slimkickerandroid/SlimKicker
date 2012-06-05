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
import android.view.KeyEvent;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
	private ArrayList<Food> results;
	private ProgressDialog spinnerDialog;

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
		  
		service = new FoodService();
		Log.i(LOG_TAG, "onCreate");
		onFocusReceived();
		// default to recent
		//RecentFoodCommand(null);
		IsFocused = false;
		
		ListView food = (ListView) findViewById(R.id.food);
		
		food.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Intent foodDetail = new Intent("FoodDetail");
				Food food = results.get(position);
				foodDetail.putExtra("food", food);
				startActivity(foodDetail);
			}
		});
		
	}

	private void resizeListView(boolean small){
		final Context currentContext = this;
		ListView food = (ListView) findViewById(R.id.food);

		float dips = 60.0f;
		float scale = currentContext.getResources().getDisplayMetrics().density;
		int height = Math.round(dips * scale);
		
		if(small == false) //resize it to original size
			height = LinearLayout.LayoutParams.WRAP_CONTENT;

		LinearLayout.LayoutParams mParam = 
				new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, height);
		
        food.setLayoutParams(mParam);
	}
	
	public void onFocusReceived() {
		EditText searchBox = (EditText) findViewById(R.id.editText1);
		final Context currentContext = this;
		
		searchBox.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        		Log.i(LOG_TAG, "Click on text edit");
	        		//ListView food = (ListView) findViewById(R.id.food);
	        		//food.setAdapter(null);
	        		
	        		//resizeListView(true);			        
	        }
	    });
		
		searchBox.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				Log.i(LOG_TAG, "On text view focus");
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

	void createFoodList(String foodName, int searchIndex) {

		new SearchTask(foodName, searchIndex).execute();
	}
	
	public void hideKeyBoard()
	{
		//get rid of all items in list view

		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		//EditText searchBox = (EditText) findViewById(R.id.editText1);
		//imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
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
		
		public SearchTask(String foodName, int searchIndex) {
			this.foodName = foodName;
			this.searchIndex = searchIndex;			
		}
		
		public void hideKeyBoard()
		{
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			EditText searchBox = (EditText) findViewById(R.id.editText1);
			imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
		}
		
		protected void onPreExecute() {   
			hideKeyBoard();

			spinnerDialog = ProgressDialog.show(  
					Search.this, "",  "Searching foods...", true);  
		}
	
		protected Void doInBackground(Void... params) {
			results = service.getSearchList(foodName, searchIndex);
			Log.i("Search", "in search");
			return null;
		}
	
		protected void onPostExecute(Void unused) {
			spinnerDialog.dismiss();
		    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.buttongroup1);
            radioGroup.clearCheck();

			foodAdapter = new FoodAdapter(Search.this, R.layout.food_row, results);
			ListView food = (ListView) findViewById(R.id.food);

			food.setAdapter(foodAdapter);
			
		}
	}
        	
}


