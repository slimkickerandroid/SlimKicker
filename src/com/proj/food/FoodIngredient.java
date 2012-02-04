package com.proj.food;

public abstract class FoodIngredient
{

	protected String Key;
	protected String value = "NA";
	
	public FoodIngredient(String key, String _value)
	{
		Key = key;
		value = _value;
	}
	
    public abstract FoodIngredient OfType(String key);

	@Override
	public String toString() {
		return "FoodIngredient [Key=" + Key + "]";
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void SetValue(String val)
	{
		value = val;
	}
	
	public String getKey()
	{
		return Key;
	}

	
}

class Sugar extends FoodIngredient
{

	public Sugar()
	{
		super("sugars", "NA");
	}
	
	public Sugar(String _value)
	{
		super("sugars", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		if(!key.equals(Key)) return null;
		// TODO Auto-generated method stub
		return this;
	}

	
	
}

class Calcium extends FoodIngredient
{

	public Calcium()
	{
		super("calcium", "NA");
	}
	
	public Calcium(String _value)
	{
		super("calcium", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		if(!key.equals(Key)) return null;
		
		return this;
	}
	
}

class Vitamin_a extends FoodIngredient
{

	public Vitamin_a()
	{
		super("vitamin_a", "NA");
	}
	
	public Vitamin_a(String _value)
	{
		super("vitamin_a", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		if(!key.equals(Key)) return null;
		
		return this;
	}
	
}

class Fat extends FoodIngredient
{

	public Fat()
	{
		super("fat", "NA");
	}
	
	public Fat(String _value)
	{
		super("fat", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}

class Fiber extends FoodIngredient
{

	public Fiber()
	{
		super("fiber", "NA");
	}
	
	public Fiber(String _value)
	{
		super("fiber", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}

class Polyunsaturated_fat extends FoodIngredient
{

	public Polyunsaturated_fat()
	{
		super("polyunsaturated_fat", "NA");
	}
	
	public Polyunsaturated_fat(String _value)
	{
		super("polyunsaturated_fat", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}

class Vitamin_c extends FoodIngredient
{

	public Vitamin_c()
	{
		super("vitamin_c", "NA");
	}
	
	public Vitamin_c(String _value)
	{
		super("vitamin_c", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}

class Carbs extends FoodIngredient
{

	public Carbs()
	{
		super("carbs", "NA");
	}
	
	public Carbs(String _value)
	{
		super("carbs", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}


class Protein extends FoodIngredient
{

	public Protein()
	{
		super("fat", "NA");
		Key = "protein";
	}
	
	public Protein(String _value)
	{
		super("fat", "NA");
		Key = "protein";
		value = _value;
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}


class Cholesterol extends FoodIngredient
{

	public Cholesterol()
	{
		super("cholesterol", "NA");
	}
	
	public Cholesterol(String _value)
	{
		super("cholesterol", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}


class Sodium extends FoodIngredient
{

	public Sodium()
	{
		super("sodium", "NA");
	}
	
	public Sodium(String _value)
	{
		super("sodium", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}

class Iron extends FoodIngredient
{

	public Iron()
	{
		super("iron", "NA");
	}
	
	public Iron(String _value)
	{
		super("iron", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}

class Calories_from_fat extends FoodIngredient
{

	public Calories_from_fat()
	{
		super("calories_from_fat", "NA");
	}
	
	public Calories_from_fat(String _value)
	{
		super("calories_from_fat", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return new Calories_from_fat();
	}
	
}

class Monounsaturated_fat extends FoodIngredient
{

	public Monounsaturated_fat()
	{
		super("monounsaturated_fat", "NA");
	}
	
	public Monounsaturated_fat(String _value)
	{
		super("monounsaturated_fat", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}

class Potassium extends FoodIngredient
{

	public Potassium()
	{
		super("potassium", "NA");
	}
	
	public Potassium(String _value)
	{
		super("potassium", _value);
	}
	
	@Override
	public FoodIngredient OfType(String key) {
		
		if(!key.equals(Key)) return null;
		return this;
	}
	
}


