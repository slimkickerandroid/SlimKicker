package com.proj.service;

import java.util.List;

import com.proj.food.Food;

import android.os.Parcel;
import android.os.Parcelable;

public class AcknowledgeModel implements Parcelable
{
	
	public static final String Points_Update = "points_update";
	public static final String Tip = "message";
	public static final String Short_Message = "short_message";
	public static final String Earned_Point_Section = "earned_points_lines";
	public static final String Earned_Points = "points";;
	public static final String Reminder = "reminder";
	public static final String Daily_Stats_Section = "daily_stats";
	public static final String DIET_POINTS = "diet_points";
	public static final String POINTS_EARNED = "points_earned";
	
	private int no_servings;
	private int points_added;
	private String short_message;
	private int total_point;
	private String nutrition_tip;
	private String reminder;
	
	public int getNo_servings() {
		return no_servings;
	}
	public int getPoints_added() {
		return points_added;
	}
	public String getShort_message() {
		return short_message;
	}
	public int getTotal_point() {
		return total_point;
	}
	public String getNutrition_tip() {
		return nutrition_tip;
	}
	public String getReminder() {
		return reminder;
	}
	public void setNo_servings(int noServings) {
		no_servings = noServings;
	}
	public void setPoints_added(int pointsAdded) {
		points_added = pointsAdded;
	}
	public void setShort_message(String shortMessage) {
		short_message = shortMessage;
	}
	public void setTotal_point(int totalPoint) {
		total_point = totalPoint;
	}
	public void setNutrition_tip(String nutritionTip) {
		nutrition_tip = nutritionTip;
	}
	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(reminder);
		out.writeString(short_message);
		out.writeString(nutrition_tip);
		out.writeInt(no_servings);
		out.writeInt(points_added);
		out.writeInt(total_point);
	}
	
	public static final Parcelable.Creator<AcknowledgeModel> CREATOR = new Parcelable.Creator<AcknowledgeModel>() {
		public AcknowledgeModel createFromParcel(Parcel in) {
			
			String _reminder = in.readString();
			String _shortMessage = in.readString();
			String _nutritionTip = in.readString();
			int _noServing = in.readInt();
			int _pointsAdded = in.readInt();
			int _totalPoints = in.readInt();
			
			AcknowledgeModel model = new AcknowledgeModel();
			model.setNutrition_tip(_reminder);
			model.setShort_message(_shortMessage);
			model.setNutrition_tip(_nutritionTip);
			model.setNo_servings(_noServing);
			model.setPoints_added(_pointsAdded);
			model.setTotal_point(_totalPoints);
			return model;
		}

		@Override
		public AcknowledgeModel[] newArray(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	};

}
