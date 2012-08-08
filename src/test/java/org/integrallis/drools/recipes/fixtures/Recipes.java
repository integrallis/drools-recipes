package org.integrallis.drools.recipes.fixtures;

import static org.integrallis.drools.recipes.fixtures.Ingredients.ADOBO;
import static org.integrallis.drools.recipes.fixtures.Ingredients.CAYENNE;
import static org.integrallis.drools.recipes.fixtures.Ingredients.CHICKEN;
import static org.integrallis.drools.recipes.fixtures.Ingredients.CILANTRO;
import static org.integrallis.drools.recipes.fixtures.Ingredients.GREEN_PEPPERS;
import static org.integrallis.drools.recipes.fixtures.Ingredients.GROUND_BEEF;
import static org.integrallis.drools.recipes.fixtures.Ingredients.GROUND_PORK;
import static org.integrallis.drools.recipes.fixtures.Ingredients.ONIONS;
import static org.integrallis.drools.recipes.fixtures.Ingredients.PEAS;
import static org.integrallis.drools.recipes.fixtures.Ingredients.RED_PEPPERS;
import static org.integrallis.drools.recipes.fixtures.Ingredients.RICE;
import static org.integrallis.drools.recipes.fixtures.Ingredients.SHRIMP;
import static org.integrallis.drools.recipes.fixtures.Ingredients.SPAGUETTI;

import java.util.ArrayList;
import java.util.List;

import org.integrallis.drools.recipes.Recipe;

public final class Recipes {
	public static Recipe SEAFOOD_GUMBO = new Recipe("Seafood Gumbo", SHRIMP, RICE, ONIONS, CAYENNE);
	public static Recipe ARROZ_CON_POLLO = new Recipe("Arroz con Pollo", RICE, CHICKEN, PEAS, CILANTRO, ADOBO);
	public static Recipe PAELLA = new Recipe("Paella", RICE, SHRIMP, RED_PEPPERS, GREEN_PEPPERS);
	public static Recipe SPAGHETTI_AND_MEATBALLS = new Recipe("Spaghetti and Meatballs", SPAGUETTI, GROUND_BEEF, GROUND_PORK);
	
	public static List<Recipe> all = new ArrayList<Recipe>();
	static {
		all.add(SEAFOOD_GUMBO);
		all.add(ARROZ_CON_POLLO);
		all.add(PAELLA);
		all.add(SPAGHETTI_AND_MEATBALLS);
	}
	
	private Recipes() {};
}
