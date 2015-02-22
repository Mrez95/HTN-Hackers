package com.example.mrez.htn_hackers;

import java.util.List;

public class UserProfile {

    private String name;
    private String picture;
    private String company;
    private String email;
    private String phone;
    private float latitude;
    private float longitude;
    private List<Skills> skills;

    /**
     * @no argument constructor
     */

    public UserProfile(){
    }

    /**
     * @Accessors
     */

    public String getName()
    {
        return name;
    }
    public String getPicture()
    {
        return picture;
    }
    public String getCompany()
    {
        return company;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhone()
    {
        return phone;
    }
    public float getLatitude()
    {
        return latitude;
    }
    public float getLongitude()
    {
        return longitude;
    }
    public List<Skills> getSkills()
    {
        return skills;
    }


    /**
     * @Mutators
     */

    public void setName(String name)
    {
        this.name = name;
    }
    public void setPicture(String picture)
    {
        this.picture = picture;
    }
    public void setCompany(String company)
    {
        this.company = company;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }
    public void setLongitude(float longitude)
    {
        this.longitude = longitude;
    }
    public void setSkills(List<Skills> skills)
    {
        this.skills = skills;
    }
}
