package com.proj.profile.service;

public interface IProfile {
	
	public String getUserName();
	public String passWord();
	public void setEmailAddress(String _emailAddress);
	public String getEmailAddress();
	public void setImageURL(String _url);
	public String getImageURL();

}
