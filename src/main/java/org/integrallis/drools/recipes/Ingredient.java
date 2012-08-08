package org.integrallis.drools.recipes;

public class Ingredient {
	private String name;

	public Ingredient(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean inRecipe(Recipe recipe) {
		return recipe.getIngredients().contains(this);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
