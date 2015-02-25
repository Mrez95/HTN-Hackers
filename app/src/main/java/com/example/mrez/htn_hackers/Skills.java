
package com.example.mrez.htn_hackers;

public class Skills{
   	private String name;
   	private float rating;

    /**
     * Accessors
     */

 	public String getName(){
		return this.name;
	}

    public float getRating(){
        return this.rating;
    }

    /**
     * Mutators
     */

	public void setName(String name){
		this.name = name;
	}

	public void setRating(float rating){
		this.rating = rating;
	}
}
