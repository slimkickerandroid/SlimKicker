package com.proj.layout;

import java.util.ArrayList;

import com.proj.food.Food;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FoodAdapter extends ArrayAdapter<Food> {

	private ArrayList<Food> foodList;
	
	public FoodAdapter(Context context, int textViewResourceId,
			ArrayList<Food> objects) {
		super(context, textViewResourceId, objects);
		foodList = objects;		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.food_row, null);
        }
        	TextView topRow = (TextView) v.findViewById(R.id.topRow);
        	TextView bottomRow = (TextView) v.findViewById(R.id.bottomRow);
        	Food selectedFood = foodList.get(position);
        	if(selectedFood != null)
        	{ 
                topRow.setText(selectedFood.getFood_description());
                bottomRow.setText(selectedFood.getFood_serving_size());               
        	}
        
		Typeface font = Typeface.createFromAsset(getContext().getAssets(), "arial_bold.ttf");
		topRow.setTypeface(font);
        
		font = Typeface.createFromAsset(getContext().getAssets(), "arial.ttf");
		bottomRow.setTypeface(font);
		
		return v;
        
	}

}

