package com.proj.layout;

import com.proj.food.Food;
import com.proj.food.FoodIngredient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class FoodDetailAcitvity extends Activity {

	private static String LOG_TAG = "FoodDetailAcitivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_desc_row);
		Log.i(LOG_TAG, "creating food detail activity");
		displayDescription();
	}

	private void displayDescription() {
		Log.i(LOG_TAG, "in food detail activity");
		Bundle bundle = getIntent().getExtras();
		Food fb = null;
		if (bundle != null) {
			Log.i(LOG_TAG, "in bundle");
			try {
				fb = (Food) bundle.get("food");
				if (fb != null) {
					createView(fb);
				}
			} catch (Exception e) {
				Log.i(LOG_TAG, e.getMessage());
			}
		}
		TextView food_name = (TextView) findViewById(R.id.textView3);
		food_name.setText(fb.getFood_description());
		Log.i(LOG_TAG, "adapter setted for foodListView");

	}

	private void createView(Food food) {
		TextView name = (TextView) findViewById(R.id.textView3);
		name.setText(food.getFood_description());

		FoodIngredient[] ingredients = food.GetIngredient();
		
		TextView sugar_name = (TextView)findViewById(R.id.textView6);
		TextView sugar_value = (TextView)findViewById(R.id.textView7);
		
		TextView fat_name = (TextView)findViewById(R.id.textView8);
		TextView fat_value = (TextView)findViewById(R.id.textView9);
		
		TextView vitaminA_name = (TextView)findViewById(R.id.textView10);
		TextView vitaminA_value = (TextView)findViewById(R.id.textView11);
		
		TextView calcium_name = (TextView)findViewById(R.id.textView12);
		TextView calcium_value = (TextView)findViewById(R.id.textView13);
		
		TextView fiber_name = (TextView)findViewById(R.id.textView14);
		TextView fiber_value = (TextView)findViewById(R.id.textView15);
		
		TextView polyunsaturated_fat_name = (TextView)findViewById(R.id.textView16);
		TextView polyunsaturated_fat_value = (TextView)findViewById(R.id.textView17);
		
		TextView vitamin_c_name = (TextView)findViewById(R.id.textView18);
		TextView vitamin_c_value = (TextView)findViewById(R.id.textView19);
		
		TextView carbs_name = (TextView)findViewById(R.id.textView20);
		TextView carbs_value = (TextView)findViewById(R.id.textView21);
		
		TextView protein_name = (TextView)findViewById(R.id.textView22);
		TextView protein_value = (TextView)findViewById(R.id.textView23);
		
		TextView cholesterol_name = (TextView)findViewById(R.id.textView24);
		TextView cholesterol_value = (TextView)findViewById(R.id.textView25);
		
		TextView sodium_name = (TextView)findViewById(R.id.textView26);
		TextView sodium_value = (TextView)findViewById(R.id.textView27);
		
		TextView iron_name = (TextView)findViewById(R.id.textView28);
		TextView iron_value = (TextView)findViewById(R.id.textView29);
		
		TextView calories_from_fat_name = (TextView)findViewById(R.id.textView30);
		TextView calories_from_fat_value = (TextView)findViewById(R.id.textView31);
		
		TextView monounsaturated_fat_name = (TextView)findViewById(R.id.textView32);
		TextView monounsaturated_fat_value = (TextView)findViewById(R.id.textView33);
		
		TextView potassium_name = (TextView)findViewById(R.id.textView34);
		TextView potassium_value = (TextView)findViewById(R.id.textView35);
		
		
		for(int i =0; i<ingredients.length; ++i)
		{
			FoodIngredient val = ingredients[i];
			if(val.OfType("sugars") != null)
			{
				sugar_name.setText(val.getKey());
				sugar_value.setText(val.getValue());
			}
			
			if(val.OfType("fat") != null)
			{
				fat_name.setText(val.getKey());
				fat_value.setText(val.getValue());
			}
			
			if(val.OfType("vitamin_a") != null)
			{
				vitaminA_name.setText(val.getKey());
				vitaminA_value.setText(val.getValue());
			}
			
			if(val.OfType("calcium") != null)
			{
				calcium_name.setText(val.getKey());
				calcium_value.setText(val.getValue());
			}
			
			if(val.OfType("fiber") != null)
			{
				fiber_name.setText(val.getKey());
				fiber_value.setText(val.getValue());
			}
			
			if(val.OfType("polyunsaturated_fat") != null)
			{
				polyunsaturated_fat_name.setText(val.getKey());
				polyunsaturated_fat_value.setText(val.getValue());
			}
			
			if(val.OfType("vitamin_c") != null)
			{
				vitamin_c_name.setText(val.getKey());
				vitamin_c_value.setText(val.getValue());
			}
			
			if(val.OfType("carbs") != null)
			{
				carbs_name.setText(val.getKey());
				carbs_value.setText(val.getValue());
			}
			
			if(val.OfType("protein") != null)
			{
				protein_name.setText(val.getKey());
				protein_value.setText(val.getValue());
			}
			
			if(val.OfType("cholesterol") != null)
			{
				cholesterol_name.setText(val.getKey());
				cholesterol_value.setText(val.getValue());
			}
			
			if(val.OfType("sodium") != null)
			{
				sodium_name.setText(val.getKey());
				sodium_value.setText(val.getValue());
			}
			
			if(val.OfType("iron") != null)
			{
				iron_name.setText(val.getKey());
				iron_value.setText(val.getValue());
			}
			
			if(val.OfType("calories_from_fat") != null)
			{
				calories_from_fat_name.setText(val.getKey());
				calories_from_fat_value.setText(val.getValue());
			}
			
			if(val.OfType("monounsaturated_fat") != null)
			{
				monounsaturated_fat_name.setText(val.getKey());
				monounsaturated_fat_value.setText(val.getValue());
			}
			
			if(val.OfType("potassium") != null)
			{
				potassium_name.setText(val.getKey());
				potassium_value.setText(val.getValue());
			}
		}
		
		Spinner servingSpinner = (Spinner) findViewById(R.id.servingNumber);
		Integer[] numbers = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}; 
		ArrayAdapter<Integer> servingAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,numbers);
		servingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		servingSpinner.setAdapter(servingAdapter);
		
		
		Spinner spinner = (Spinner) findViewById(R.id.serveSize);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,food.getServingList());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}
}
