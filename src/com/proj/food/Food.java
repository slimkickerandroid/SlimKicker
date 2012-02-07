package com.proj.food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {

	private String food_description = null;
	private String food_serving_size = null;
	private List<String> serving_size_list = null;

	public Food(String description, String serving_size) {
		food_description = description;
		food_serving_size = serving_size;
		serving_size_list = new ArrayList<String>();
	}
	
	public void setServingList(List<String> _list)
	{
		serving_size_list = _list;
	}
	
	public List<String> getServingList()
	{
		return serving_size_list;
	}

	public String getFood_description() {
		return food_description;
	}

	public String getFood_serving_size() {
		return food_serving_size;
	}

	private FoodIngredient[] ingredients = new FoodIngredient[] { 
			
	new Sugar(),

	new Calcium(),

	new Vitamin_a(),

	new Fat(),

	new Fiber(),

	new Polyunsaturated_fat(),

	new Vitamin_c(),

	new Carbs(),

	new Protein(),

	new Cholesterol(),

	new Sodium(),

	new Iron(),

	new Calories_from_fat(),

	new Monounsaturated_fat(),

	new Potassium() };

	public FoodIngredient[] GetIngredient() {
		return ingredients;
	}

	public void SetIngredient(String key, String value) {
		for (FoodIngredient element : ingredients) {
			FoodIngredient ingredient = element.OfType(key);
			if (ingredient != null) {
				if (value != null)
					ingredient.SetValue(value);
			}
		}
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int arg1) {

		out.writeString(food_description);
		out.writeString(food_serving_size);
		
		String[] keyArr = new String[ingredients.length];
		String[] valArr = new String[ingredients.length];
		
		for (int i = 0; i < ingredients.length ; ++i) 
		{
		
			keyArr[i] = ingredients[i].getKey(); 
			valArr[i] = ingredients[i].getValue();
		}
		
		
		out.writeStringList(Arrays.asList(keyArr));
		out.writeStringList(Arrays.asList(valArr));
		out.writeStringList(serving_size_list);

	}

	public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
		public Food createFromParcel(Parcel in) {
			
			String description = in.readString();
			String serving_size = in.readString();
			List<String> keyArr = in.createStringArrayList();
			List<String> valArr = in.createStringArrayList();
			List<String> serving_list = in.createStringArrayList();
			Food writeFood = null;
			if(description != null && serving_size != null)
			{
				writeFood = new Food(description, serving_size);
				writeFood.setServingList(serving_list);
				for(int i=0; i<keyArr.size(); ++i )
				{
					writeFood.SetIngredient(keyArr.get(i), valArr.get(i));
				}
			}
			return writeFood;
		}

		@Override
		public Food[] newArray(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

	};

}
