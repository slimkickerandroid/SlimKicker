package com.proj.friend;

import java.util.*;

public class Friend implements Comparable<Friend> {

	private String index="1";
	private String weekly_points;
	private String URL;
	private String name;
	public String getIndex() {
		return index;
	}
	public String getWeekly_points() {
		return weekly_points;
	}
	public String getURL() {
		return URL;
	}
	public String getName() {
		return name;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public void setWeekly_points(String weeklyPoints) {
		weekly_points = weeklyPoints;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((URL == null) ? 0 : URL.hashCode());
		result = prime * result + index == null ? 0 : index.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + weekly_points == null ? 0 : weekly_points.hashCode() ;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Friend other = (Friend) obj;
		if (URL == null) {
			if (other.URL != null)
				return false;
		} else if (!URL.equals(other.URL))
			return false;
		if (index != other.index)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (weekly_points != other.weekly_points)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Friend [URL=" + URL + ", index=" + index + ", name=" + name
				+ ", weekly_points=" + weekly_points + "]";
	}
	@Override
	public int compareTo(Friend obj) {
		if(obj != null ){
			Friend friend = (Friend)obj;
			
			String friend_weekly = friend.weekly_points;
			String our_weekly = weekly_points;
			
			return our_weekly.compareTo(friend_weekly);
			
		}
		else{
			return -1;
			}
	}
	
	
}
