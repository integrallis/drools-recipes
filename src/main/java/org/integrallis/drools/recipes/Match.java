package org.integrallis.drools.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Match {
	private Recipe recipe;
	private Integer rating;
	private List<Ingredient> missing = new ArrayList<Ingredient>();
	
	public Match(Recipe recipe) {
		this.recipe = recipe;
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setMissingIngredients(List<Ingredient> missing) {
		this.missing = missing;
	}

	public List<Ingredient> getMissingIngredients() {
		return missing;
	}
	
	public void addMissingIngredients(Ingredient... ingredients) {
		missing.addAll(Arrays.asList(ingredients));
	}
	
	public boolean fullMatch() {
		return missing.isEmpty();
	}
	
	@Override
	public String toString() {
		return "You can make " + recipe + (fullMatch() ? "" : " if you get " + missing);
	}
}
