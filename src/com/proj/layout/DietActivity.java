package com.proj.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * @author www.androidcookers.co.cc
 * {@link www.androidcookers.co.cc}
 *
 */
public class DietActivity extends Activity {
	private ListView listView;
	
	private List<NutritionInfo> getNutritionInfo (){

		List<NutritionInfo> info = new ArrayList<NutritionInfo>();
		
		String[] nutrients = new String[] {"Calories", "Carbs", "Fat", "Protein", 
				"Sugars", "Fiber", "Cholesterol", "Sodium", ""};
		Random diceRoller = new Random();

		for(int i =0; i < nutrients.length; i++){
			int currentAmount = diceRoller.nextInt(1000) + 500;
			if(i == nutrients.length -1){
				currentAmount = 0;
			}
			
			NutritionInfo newInfo = new NutritionInfo(currentAmount, 2000, nutrients[i]);
			info.add(newInfo);
		}
		
		return info;
	}
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

		setContentView(R.layout.diet);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

		//set background o
		
        listView = (ListView) findViewById(R.id.diet_list);
        
        List<NutritionInfo> info = getNutritionInfo();
        ArrayAdapter<NutritionInfo> adapter = new DietArrayAdapter(this,
        		info);

        	listView.setAdapter(adapter);

        	 Button addFood = (Button) findViewById(R.id.add_food_button);
         addFood.setOnClickListener(new OnClickListener() {
                 public void onClick(View v) {
                	    Intent food = new Intent("SearchFood");
         			
         			startActivity(food);
         	    	
                 }
         });
        	
    }
}