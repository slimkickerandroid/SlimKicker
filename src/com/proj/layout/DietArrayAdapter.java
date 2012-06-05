package com.proj.layout;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;
import android.view.*;
import android.widget.*;

public class DietArrayAdapter extends ArrayAdapter<NutritionInfo> {
	private final Context context;
	private List<NutritionInfo> info;
	
	static class ViewHolder {
		public TextView label;
		public TextView totalLabel;
	}
	
	public DietArrayAdapter(Context context, List<NutritionInfo> info) {
		super(context, R.layout.diet_row, info);
		this.context = context;
		this.info = info;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		NutritionInfo nutrientInfo = info.get(position);

	 	 if (rowView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        rowView = vi.inflate(R.layout.diet_row, null);

			TextView labelView = (TextView) rowView.findViewById(R.id.label);
			Typeface font = Typeface.createFromAsset(this.context.getAssets(), "helveticaneue-bold.ttf");
			labelView.setTypeface(font);
			
			labelView = (TextView) rowView.findViewById(R.id.total_label);
			font = Typeface.createFromAsset(this.context.getAssets(), "HelveticaLight.ttf");
			labelView.setTypeface(font);
			
            ViewHolder viewHolder = new ViewHolder();
	        viewHolder = new ViewHolder();
	        viewHolder.label = (TextView) rowView.findViewById(R.id.label);
	        viewHolder.totalLabel = (TextView) rowView.findViewById(R.id.total_label);
            
            rowView.setTag(viewHolder);
        }

		ViewHolder holder = (ViewHolder) rowView.getTag();
		
		if(holder.label != null){
			holder.label.setText(nutrientInfo.getNutrient().toUpperCase());
			
			View filledBar = rowView.findViewById(R.id.nutrition_bar_filled);
			int remainder = position % 4;
			
			if(remainder == 0)
				filledBar.setBackgroundResource(R.drawable.green_rectangle);
			else if(remainder == 1)
				filledBar.setBackgroundResource(R.drawable.yellow_rectangle);
			else if(remainder == 2)
				filledBar.setBackgroundResource(R.drawable.red_rectangle);
			else
				filledBar.setBackgroundResource(R.drawable.blue_rectangle);
			
			float percentage = (float) nutrientInfo.getCurrentAmount() / (float) nutrientInfo.getMaxAmount();
			
			float dips = 290.0f * percentage;
			float scale = getContext().getResources().getDisplayMetrics().density;
			int pixels = Math.round(dips * scale);
			
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(pixels,RelativeLayout.LayoutParams.WRAP_CONTENT);
			filledBar.setLayoutParams(lp);
			
			String nutrient = nutrientInfo.getNutrient();
			
			String totalLabel = "Total " + nutrient + " eaten: " + nutrientInfo.getCurrentAmount() + " so far of " + nutrientInfo.getMaxAmount();

			if(!nutrient.equals(""))
				holder.totalLabel.setText(totalLabel);
			else
				holder.totalLabel.setText("");
		}
		
		return rowView;
	}
}

