package com.proj.service;

import android.os.Parcel;
import android.os.Parcelable;

/*
public class FoodDetail implements Parcelable {

	private String food_description = null;
	private String food_calorie = null;
	private String food_sugar = null;
	private String food_fat = null;
	private String food_fiber = null;
	private String food_vitamin_a = null;
	private String food_trans_fat = null;
	private String food_monounsaturated_fat = null;
	private String food_potassium = null; 
	private String food_serving_size = null;
	private String food_vitamin_c = null;
	private String food_carbs = null;
	private String food_protien = null;
	private String food_cholesterol = null;
	private String food_calcium = null;
	private String food_sodium = null;
	private String food_iron = null;
	private String food_calorie_from_fat = null;
	
	public FoodDetail(String _food_description, String _food_serving_size)
	{
		food_description = _food_description;
		food_serving_size = _food_serving_size;
	}
	
	/*
	public void addCalorie(Double calorie){this.food_calorie = doubleConverter(calorie);}
	public void addSugar(Double sugar){ this.food_sugar = doubleConverter(sugar); }
	public void addFat(Double fat){ this.food_fat = doubleConverter(fat); }
	public void addFiber(Double fiber){this.food_fiber = doubleConverter(fiber); }
	public void addVitamin_A(Double vitA){ this.food_vitamin_a = doubleConverter(vitA); }
	public void addTrans_Fat(Double trans_fat){this.food_trans_fat = doubleConverter(trans_fat); }
	public void addCholestrol(Double cholestrol){this.food_cholesterol = doubleConverter(cholestrol); }
	public void addMonoFat(Double monoFat){this.food_monounsaturated_fat = doubleConverter(monoFat); }
	public void addPotassium(Double potaasium){this.food_potassium = doubleConverter(potaasium); }
	public void addCarbs(Double carbs){this.food_carbs = doubleConverter(carbs); }
	public void addVitamin_C(Double vitC){this.food_vitamin_c = doubleConverter(vitC); }
	public void addProtein(Double protien){this.food_protien = doubleConverter(protien); }
	public void addCalcuim(Double cal){this.food_calcium = doubleConverter(cal); }
	public void addSodium(Double sodium){this.food_sodium = doubleConverter(sodium); }
	public void addIron(Double iron){this.food_iron = doubleConverter(iron); }
	public void addCaloriefromFat(Double cff){this.food_calorie_from_fat= doubleConverter(cff); }
	
	
	private String doubleConverter(Double val)
	{
		if(val == null){ return "NA";}
		return val.toString();
	}
	
	  */

/*
	
	public String toString()
	{
		StringBuilder name = new StringBuilder();
		name.append(this.food_description).append(" ").append(this.food_serving_size).append(" ").append(this.food_calorie);
		return name.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof FoodDetail)) return false;
		
		return this.toString().equals(o.toString());
		
	}
	

	public String getFood_sodium(){
		return food_sodium;
	}

	
	public String getFood_description() {
		return food_description;
	}
	
	
	public String getFood_calorie() {
		return food_calorie;
	}


	public String getFood_sugar() {
		return food_sugar;
	}

	public String getFood_fat() {
		return food_fat;
	}


	public String getFood_fiber() {
		return food_fiber;
	}


	public String getFood_vitamin_a() {
		return food_vitamin_a;
	}

	public String getFood_trans_fat() {
		return food_trans_fat;
	}

	public String getFood_monounsaturated_fat() {
		return food_monounsaturated_fat;
	}


	public String getFood_potassium() {
		return food_potassium;
	}


	public String getFood_serving_size() {
		return food_serving_size;
	}

	
	public String getFood_vitamin_c() {
		return food_vitamin_c;
	}


	public String getFood_carbs() {
		return food_carbs;
	}
	

	public String getFood_protien() {
		return food_protien;
	}


	public String getFood_cholesterol() {
		return food_cholesterol;
	}

	public String getFood_calcuim() {
		return food_calcium;
	}
	
	public String getIron(){
		return food_iron;
	}

	public String getCalorieFromFat()
	{
		return food_calorie_from_fat;
	}


	public void setFood_description(String _description){
		food_description = _description;
	}
	
	public void setFood_serving_size(String _serving_size){
		food_serving_size = _serving_size;
	}
	
	public void setFood_vitamin_a(String foodVitaminA) {
		food_vitamin_a = foodVitaminA;
	}


	public void setFood_monounsaturated_fat(String foodMonounsaturatedFat) {
		food_monounsaturated_fat = foodMonounsaturatedFat;
	}


	public void setFood_vitamin_c(String foodVitaminC) {
		food_vitamin_c = foodVitaminC;
	}


	public void setFood_carbs(String foodCarbs) {
		food_carbs = foodCarbs;
	}


	public void setFood_protien(String foodProtien) {
		food_protien = foodProtien;
	}


	public void setFood_cholesterol(String foodCholesterol) {
		food_cholesterol = foodCholesterol;
	}


	public void setFood_calcium(String foodCalcium) {
		food_calcium = foodCalcium;
	}


	public void setFood_iron(String foodIron) {
		food_iron = foodIron;
	}


	public void setFood_calorie_from_fat(String foodCalorieFromFat) {
		food_calorie_from_fat = foodCalorieFromFat;
	}

	public void setFood_calorie(String _calorie){
		food_calorie = _calorie;
	}
	
	public void setFood_sodium(String _sodium){
		food_sodium = _sodium;
	}
		
	public void setFood_potassium(String _potassium){
		food_potassium = _potassium;
	}
	
	public void setFood_trans_fat(String _transFat){
		food_trans_fat = _transFat;
	}
	
	public void setFood_fat(String _fat){
		food_fat = _fat;
	}
	
	public void setFood_fiber(String _fiber){
		food_fiber = _fiber;
	}
	
	public void setFood_sugar(String _sugar){
		food_sugar = _sugar;
	}


	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static final Parcelable.Creator<FoodDetail> CREATOR
    = new Parcelable.Creator<FoodDetail>() {
			public FoodDetail createFromParcel(Parcel in) {
		
				FoodDetail fb = new FoodDetail(in.readString(), in.readString());
				fb.setFood_calorie(in.readString());
				fb.setFood_calorie_from_fat(in.readString());
				fb.setFood_cholesterol(in.readString());
				fb.setFood_carbs(in.readString());
				fb.setFood_calcium(in.readString());
				fb.setFood_fat(in.readString());
				fb.setFood_fiber(in.readString());
				fb.setFood_iron(in.readString());
				fb.setFood_monounsaturated_fat(in.readString());
				fb.setFood_potassium(in.readString());
				fb.setFood_protien(in.readString());
				fb.setFood_sodium(in.readString());
				fb.setFood_sugar(in.readString());
				fb.setFood_trans_fat(in.readString());
				fb.setFood_vitamin_a(in.readString());
				fb.setFood_vitamin_c(in.readString());
				
				return fb;
				
			}

			public FoodDetail[] newArray(int size) {
				return new FoodDetail[size];
			}
		   };
	
	

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(food_description);
		dest.writeString(food_serving_size);
		dest.writeString(food_calorie);
		dest.writeString(food_calorie_from_fat);
		dest.writeString(food_cholesterol);
		dest.writeString(food_carbs);
		dest.writeString(food_calcium);
		dest.writeString(food_fat);
		dest.writeString(food_fiber);
		dest.writeString(food_iron);
		dest.writeString(food_monounsaturated_fat);
		dest.writeString(food_potassium);
		dest.writeString(food_protien);
		dest.writeString(food_sodium);
		dest.writeString(food_sugar);
		dest.writeString(food_trans_fat);
		dest.writeString(food_vitamin_a);
		dest.writeString(food_vitamin_c);
	}
	

	
}*/
