package com.example.mrez.htn_hackers;

public class Skills {

    private String name;
    private Number rating;

    /**
     * @Accessors
     */
    public String getName(){
        return name;
    }
    public Number getRating(){
        return rating;
    }

    /**
     * @Mutators
     */
    public void setName(String name){
        this.name = name;
    }
    public void setRating(Number rating){
        this.rating = rating;
    }
}
