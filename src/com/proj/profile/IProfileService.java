package com.proj.profile;

import org.json.JSONObject;

public interface IProfileService {

	public String getProfileJson();
	
	public void SetUp(String _userName, String _passWord);
	
	public Profile parseJson(String json); 
	
}
