package com.tapan.try1masterdetailflow;

import java.io.Serializable;

/**
 * Created by DELL on 6/16/2017.
 */

public class Recipe implements Serializable {

    String recipeName,servings;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }
}
