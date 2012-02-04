package com.proj.layout;

import com.proj.food.Food;
import com.proj.food.FoodIngredient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
			
		}
		
	}
}
