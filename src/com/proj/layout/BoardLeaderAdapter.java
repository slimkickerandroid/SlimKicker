package com.proj.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.proj.profile.IProfilePicture;
import com.proj.profile.ProfilePicture;
import com.proj.friend.Friend;

import java.util.*;

public class BoardLeaderAdapter extends ArrayAdapter<Friend> {

	private List<Friend> friends = new ArrayList<Friend>(); 
	IProfilePicture picture;
	
	public BoardLeaderAdapter(Context context, int textViewResourceId,
			List<Friend> objects) {
		super(context, textViewResourceId, objects);
		friends = objects;
		picture = new ProfilePicture();
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.boardleader_row, null);
        }
        	TextView index = (TextView) v.findViewById(R.id.friendindex);
        	ImageView image_view = (ImageView) v.findViewById(R.id.imageUrl);
        	TextView name = (TextView) v.findViewById(R.id.friendName);
        	TextView points = (TextView) v.findViewById(R.id.weeklypoints);
        	Friend selectedFriend = friends.get(position);
        	if(selectedFriend != null)
        	{ 
                index.setText(selectedFriend.getIndex());
                name.setText(selectedFriend.getName());
                points.setText(selectedFriend.getWeekly_points());
                picture.setImageOnView(selectedFriend.getURL(), image_view);
        	}
        return v;
        
	}
}
