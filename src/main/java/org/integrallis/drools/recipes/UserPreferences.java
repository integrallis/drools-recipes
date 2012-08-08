package org.integrallis.drools.recipes;

public class UserPreferences {
	private Integer minimumNumberOfIngredientsMissing = 1;

	public UserPreferences() {}
	
	public UserPreferences(Integer minimumNumberOfIngredientsMissing) {
		this.minimumNumberOfIngredientsMissing = minimumNumberOfIngredientsMissing;
	}

	public Integer getMinimumNumberOfIngredientsMissing() {
		return minimumNumberOfIngredientsMissing;
	}
	
}
