package com.proj.challenges;

import java.util.*;

/**
 * @author www.androidcookers.co.cc
 * {@link www.androidcookers.co.cc}
 *
 */
public class ChallengeInfo {
	private Map<String, String> attributes;
	
	public ChallengeInfo(Map<String, String> attributes) {
		super();
		this.attributes = attributes;
	}
	
	public Map<String, String> getAttributes(){
		return attributes;
	}
	
	public String getValue(String attribute){
		return attributes.get(attribute);
	}
}