package com.proj.layout;

public class NutritionInfo {
    private int currentAmount;
    private int maxAmount;
    private String nutrient;
    
	public NutritionInfo(int currentAmount, int maxAmount, String nutrient) {
		super();
		this.currentAmount = currentAmount;
		this.maxAmount = maxAmount;
		this.nutrient = nutrient;
	}
	
	public int getCurrentAmount() {
		return currentAmount;
	}
	
	public int getMaxAmount() {
		return maxAmount;	
	}
	
	public String getNutrient() {
		return nutrient;
	}
	
}