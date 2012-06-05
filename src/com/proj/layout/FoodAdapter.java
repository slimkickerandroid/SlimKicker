package com.proj.layout;

import java.util.ArrayList;

import com.proj.food.Food;
import com.proj.layout.DietArrayAdapter.ViewHolder;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FoodAdapter extends ArrayAdapter<Food> {

	private ArrayList<Food> foodList;
	private Typeface arialBold;
	private Typeface arial;
	
	static class ViewHolder {
		public TextView topRow;
		public TextView bottomRow;
	}
	
	public FoodAdapter(Context context, int textViewResourceId,
			ArrayList<Food> objects) {
		super(context, textViewResourceId, objects);
		foodList = objects;		
		arialBold = Typeface.createFromAsset(getContext().getAssets(), "arial_bold.ttf");
		arial = Typeface.createFromAsset(getContext().getAssets(), "arial.ttf");
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder viewHolder = null;
		
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.food_row, null);
            
	        viewHolder = new ViewHolder();
	        viewHolder.topRow = (TextView) v.findViewById(R.id.topRow);
	        viewHolder.bottomRow = (TextView) v.findViewById(R.id.bottomRow);

            v.setTag(viewHolder);
        }
        else {
        		viewHolder = (ViewHolder)v.getTag();
        }
        
        	Food selectedFood = foodList.get(position);
        	
        	if(selectedFood != null)
        	{ 
               viewHolder.topRow.setText(selectedFood.getFood_description());
               viewHolder.bottomRow.setText(selectedFood.getFood_serving_size());               
        	}
	        
		viewHolder.topRow.setTypeface(arialBold);
	    viewHolder.bottomRow.setTypeface(arial);

		return v;
        
	}

}

