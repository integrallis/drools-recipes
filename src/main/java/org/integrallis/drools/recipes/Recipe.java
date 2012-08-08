package org.integrallis.drools.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recipe {
	private String name;
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	public Recipe(String name, Ingredient... ingredients) {
		this.name = name;
		this.ingredients.addAll(Arrays.asList(ingredients));
	}

	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public Integer getNumberOfIngredients() {
		return ingredients.size();
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public List<Ingredient> getRemainingIngredients(List<Ingredient> ingredients) {
        final List<Ingredient> result = new ArrayList<Ingredient>(this.ingredients);
        for (Ingredient ingredient : ingredients) {
            result.remove(ingredient);
        }
        return result;
	}
	
}
