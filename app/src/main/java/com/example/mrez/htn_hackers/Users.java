
package com.example.mrez.htn_hackers;

import java.util.List;

public class Users{
   	private String company;
   	private String email;
   	private float latitude;
   	private float longitude;
   	private String name;
   	private String phone;
   	private String picture;
   	private List<Skills> skills;

    /**
     * Accessors
     */

    public String getCompany(){
        return this.company;
    }
    public String getEmail(){
        return this.email;
    }
    public float getLatitude(){
        return this.latitude;
    }
    public float getLongitude(){
        return this.longitude;
    }
    public String getName(){
        return this.name;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getPicture(){
        return this.picture;
    }
    public List<Skills> getSkills(){
        return this.skills;
    }

    /**
     * Mutators
     */

	public void setCompany(String company){
		this.company = company;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setLatitude(float latitude){
        this.latitude = latitude;
    }

	public void setLongitude(float longitude){
		this.longitude = longitude;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public void setPicture(String picture){
		this.picture = picture;
	}

	public void setSkills(List<Skills> skills){
		this.skills = skills;
	}
}
