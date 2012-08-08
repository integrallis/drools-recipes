package org.integrallis.drools.recipes;

public class AverageRecipeRating implements Comparable<AverageRecipeRating> {
	private Recipe recipe;
	private Double rating;

	public AverageRecipeRating(Recipe recipe, Double rating) {
		this.recipe = recipe;
		this.rating = rating;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	@Override
	public int compareTo(AverageRecipeRating arr) {
		return rating.compareTo(arr.getRating());
	}

	@Override
	public String toString() {
		return recipe + " ==> " + rating;
	}
}
