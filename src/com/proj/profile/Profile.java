package com.proj.profile;

public class Profile implements IProfile {

	private String userName;
	private String passWord;
	private String emailAddress;
	private String Url;
	private String points;
	private String achievement;
	private String rewards;
	private String level;
	private String diet_points;
	private String challenges_points;
	private String excecise_points;
	private String total_points;	
	
	public String getDiet_points() {
		return diet_points;
	}

	public String getChallenges_points() {
		return challenges_points;
	}

	public String getExcecise_points() {
		return excecise_points;
	}

	public String getTotal_points() {
		return total_points;
	}

	public void setDiet_points(String dietPoints) {
		diet_points = dietPoints;
	}

	public void setChallenges_points(String challengesPoints) {
		challenges_points = challengesPoints;
	}

	public void setExcecise_points(String excecisePoints) {
		excecise_points = excecisePoints;
	}

	public void setTotal_points(String totalPoints) {
		total_points = totalPoints;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Profile()
	{
		
	}
	
	public void setLogin(String _userName, String _passWord)
	{
		userName = _userName;
		passWord = _passWord;		
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String passWord()
	{
		return passWord;
	}
	
	public void setEmailAddress(String _emailAddress)
	{
		emailAddress = _emailAddress;
	}
	
	public String getEmailAddress()
	{
		return emailAddress;
	}
	
	public void setImageURL(String _url)
	{
		Url = _url;
	}
	
	public String getImageURL()
	{
		return Url;
	}
	
	public void setReward(String _reward)
	{
		rewards = _reward;
	}
	
	public String getRewards()
	{
		return rewards;
	}
	
	public void setAchievement(String _achievement)
	{
		achievement = _achievement;
	}
	
	public String getAchievement()
	{
		return achievement;
	}
	
	public void setPoints(String _point)
	{
		points = _point;
	}
	
	public String getPoints()
	{
		return points;
	}
	
}
