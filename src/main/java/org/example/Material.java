package org.example;

import org.example.Recipe;

public class Material  {
    private final String name;
    private Recipe recipe;
    public String getName() {
        return name;
    }
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public Material(String name, Recipe recipe) {
        this.name = name;
        this.recipe = recipe;
    }
}
