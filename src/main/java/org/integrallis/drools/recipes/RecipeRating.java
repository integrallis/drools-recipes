package org.integrallis.drools.recipes;

public class RecipeRating {
	private Recipe recipe;
	private Integer rating;
	private Person person;
	
	public RecipeRating(Recipe recipe, Integer rating, Person person) {
		this.recipe = recipe;
		this.rating = rating;
		this.person = person;
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

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}
}
