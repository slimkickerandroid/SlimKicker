package com.proj.layout;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;
import android.view.*;
import android.widget.*;
import com.proj.challenges.*;

public class ChallengeArrayAdapter extends ArrayAdapter<ChallengeInfo> {
	private final Context context;
	private List<ChallengeInfo> info;
	String LOG_TAG = "ChallengeArray";
	
	static class ViewHolder {
		public TextView challengeNameLabel;
		public TextView challengeDescLabel;
		public ImageView badge;
		public TextView challengePointsLabel;
	}
	
	public ChallengeArrayAdapter(Context context, List<ChallengeInfo> info) {
		super(context, R.layout.challenge_row, info);
		this.context = context;
		this.info = info;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ChallengeInfo challenge = info.get(position);

	 	 if (rowView == null) {
	 		
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        rowView = vi.inflate(R.layout.challenge_row, null);
	        
			TextView challengeNameView = (TextView) rowView.findViewById(R.id.challenge_name_label);
			Typeface font = Typeface.createFromAsset(this.context.getAssets(), "helveticaneue-bold.ttf");
			challengeNameView.setTypeface(font);
			
			TextView challengeDescView = (TextView) rowView.findViewById(R.id.challenge_desc_label);
			font = Typeface.createFromAsset(this.context.getAssets(), "HelveticaLight.ttf");
			challengeDescView.setTypeface(font);
			
			TextView challengePointsView = (TextView) rowView.findViewById(R.id.challenge_points_label);
			font = Typeface.createFromAsset(this.context.getAssets(), "HelveticaLight.ttf");
			challengePointsView.setTypeface(font);
	        
			ImageView badge = (ImageView) rowView.findViewById(R.id.badge);

            ViewHolder viewHolder = new ViewHolder();
	        viewHolder = new ViewHolder();
	        
	        viewHolder.challengeNameLabel = challengeNameView;
	        viewHolder.challengeDescLabel = challengeDescView;
	        viewHolder.badge = badge;
            viewHolder.challengePointsLabel = challengePointsView;
            
            rowView.setTag(viewHolder);
        }

		ViewHolder holder = (ViewHolder) rowView.getTag();
		String challengeName = challenge.getValue("challenge_name");
		String challengeDesc = challenge.getValue("challenge_desc");
		Log.i(LOG_TAG, "Found Challenge Name: " + challengeName + ", Challenge Desc: " + challengeDesc);

		int badge = Integer.parseInt(challenge.getValue("badge"));
		

		TextView challengeNameView = (TextView) rowView.findViewById(R.id.challenge_name_label);
		challengeNameView.setText(challengeName);
		
		TextView challengeDescView = (TextView) rowView.findViewById(R.id.challenge_desc_label);
		challengeDescView.setText(challengeDesc);
		
		ImageView badgeView = (ImageView) rowView.findViewById(R.id.badge);
		badgeView.setImageResource(badge);
		
		return rowView;
	}
}

