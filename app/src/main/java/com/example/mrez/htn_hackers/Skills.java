package com.example.mrez.htn_hackers;

public class Skills {

    private String name;
    private int rating;

    /**
     * @Accessors
     */
    public String getName(){
        return name;
    }
    public int getRating(){
        return rating;
    }

    /**
     * @Mutators
     */
    public void setName(String name){
        this.name = name;
    }
    public void setRating(int rating){
        this.rating = rating;
    }
}
