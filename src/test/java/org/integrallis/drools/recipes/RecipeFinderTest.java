package org.integrallis.drools.recipes;

import static org.integrallis.drools.recipes.fixtures.Ingredients.ADOBO;
import static org.integrallis.drools.recipes.fixtures.Ingredients.CHICKEN;
import static org.integrallis.drools.recipes.fixtures.Ingredients.CILANTRO;
import static org.integrallis.drools.recipes.fixtures.Ingredients.GREEN_PEPPERS;
import static org.integrallis.drools.recipes.fixtures.Ingredients.PEAS;
import static org.integrallis.drools.recipes.fixtures.Ingredients.RED_PEPPERS;
import static org.integrallis.drools.recipes.fixtures.Ingredients.RICE;
import static org.integrallis.drools.recipes.fixtures.Ingredients.SHRIMP;
import static org.integrallis.drools.recipes.fixtures.People.ANNE;
import static org.integrallis.drools.recipes.fixtures.People.BRIAN;
import static org.integrallis.drools.recipes.fixtures.People.MICHAEL;
import static org.integrallis.drools.recipes.fixtures.People.STEPHEN;
import static org.integrallis.drools.recipes.fixtures.Recipes.ARROZ_CON_POLLO;
import static org.integrallis.drools.recipes.fixtures.Recipes.PAELLA;
import static org.integrallis.drools.recipes.fixtures.Recipes.SEAFOOD_GUMBO;
import static org.integrallis.drools.recipes.fixtures.Recipes.SPAGHETTI_AND_MEATBALLS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;
import org.integrallis.drools.junit.BaseDroolsTestCase;
import org.integrallis.drools.recipes.fixtures.Recipes;
import org.junit.Before;
import org.junit.Test;


public class RecipeFinderTest extends BaseDroolsTestCase {

	public RecipeFinderTest() {
		super("recipes.drl");
	}

	@Before
	public void loadRecipes() {
		for (Recipe recipe : Recipes.all) {
			knowledgeSession.insert(recipe);
		}
	}

	@Test
	public void testCanMakeArrozConPollo() {
		knowledgeSession.insert(RICE);
		knowledgeSession.insert(CHICKEN);
		knowledgeSession.insert(PEAS);
		knowledgeSession.insert(CILANTRO); 
		knowledgeSession.insert(ADOBO);
		knowledgeSession.fireAllRules();
		
		assertTrue(recipesWeCanMake().contains(ARROZ_CON_POLLO));
	}
	
	@Test
	public void testCantMakeArrozConPollo() {
		knowledgeSession.insert(RICE);
		knowledgeSession.insert(CHICKEN);
		knowledgeSession.fireAllRules();
		
		assertFalse(recipesWeCanMake().contains(ARROZ_CON_POLLO));
	}
	
	@Test
	public void testDetermineAverageRecipeRatingForMatchedRecipes() {
		knowledgeSession.insert(new Match(ARROZ_CON_POLLO));
		knowledgeSession.insert(new RecipeRating(ARROZ_CON_POLLO, 1, BRIAN));
		knowledgeSession.insert(new RecipeRating(ARROZ_CON_POLLO, 2, ANNE));
		knowledgeSession.fireAllRules();
		double actual = getAverageRecipeRatingFor(ARROZ_CON_POLLO).getRating();
		double expected = 1.5;
		assertEquals(expected, actual, 0.1);
	}
	
	@Test
	public void testSelectTopThreeRecipesThatCanBeMade() {
		knowledgeSession.insert(new Match(ARROZ_CON_POLLO));
		knowledgeSession.insert(new RecipeRating(ARROZ_CON_POLLO, 1, MICHAEL));
		knowledgeSession.insert(new RecipeRating(ARROZ_CON_POLLO, 2, STEPHEN));
		
		knowledgeSession.insert(new Match(PAELLA));
		knowledgeSession.insert(new RecipeRating(PAELLA, 3, ANNE));
		knowledgeSession.insert(new RecipeRating(PAELLA, 2, MICHAEL));
		
		knowledgeSession.insert(new Match(SEAFOOD_GUMBO));
		knowledgeSession.insert(new RecipeRating(SEAFOOD_GUMBO, 1, BRIAN));
		knowledgeSession.insert(new RecipeRating(SEAFOOD_GUMBO, 1, STEPHEN));
		
		knowledgeSession.insert(new Match(SPAGHETTI_AND_MEATBALLS));
		knowledgeSession.insert(new RecipeRating(SPAGHETTI_AND_MEATBALLS, 4, BRIAN));
		knowledgeSession.insert(new RecipeRating(SPAGHETTI_AND_MEATBALLS, 1, ANNE));		
		
		knowledgeSession.fireAllRules();
		
		List<AverageRecipeRating> allRatings = getAllAverageRecipeRatings();
		List<AverageRecipeRating> top3Ratings = allRatings.subList(allRatings.size() - 3, allRatings.size());
		List<Recipe> top3Recipes = new ArrayList<Recipe>();

		for (AverageRecipeRating arr : top3Ratings) {
			top3Recipes.add(arr.getRecipe());
		}
		
		assertTrue(top3Recipes.contains(ARROZ_CON_POLLO));
		assertTrue(top3Recipes.contains(PAELLA));
		assertTrue(top3Recipes.contains(SPAGHETTI_AND_MEATBALLS));
	}
	
	@Test
	public void testCanMakeArrozConPolloWithOneMoreIngredient() {
		knowledgeSession.insert(new UserPreferences());
		knowledgeSession.insert(RICE);
		knowledgeSession.insert(CHICKEN);
		knowledgeSession.insert(PEAS);
		knowledgeSession.insert(CILANTRO); 
		knowledgeSession.insert(SHRIMP); 
		knowledgeSession.insert(RED_PEPPERS); 
		
		knowledgeSession.fireAllRules();
		
		List<Recipe> partialRecipes = new ArrayList<Recipe>();

		for (Match match : getPartialMatches()) {
			if (match.getRecipe() == PAELLA) {
				assertTrue(match.getMissingIngredients().contains(GREEN_PEPPERS));
			} else if (match.getRecipe() == ARROZ_CON_POLLO) {
				assertTrue(match.getMissingIngredients().contains(ADOBO));
			}
			partialRecipes.add(match.getRecipe());
		}
		
		assertTrue(partialRecipes.contains(ARROZ_CON_POLLO));
		assertTrue(partialRecipes.contains(PAELLA));
	}
	
	@Test
	public void testCanMakeRecipesWithAGivenNumberOfMissingIngredients() {
		knowledgeSession.insert(new UserPreferences(2));
		knowledgeSession.insert(RICE);
		knowledgeSession.insert(CHICKEN);
		knowledgeSession.insert(PEAS);
		knowledgeSession.insert(CILANTRO); 
		knowledgeSession.insert(RED_PEPPERS); 
		
		knowledgeSession.fireAllRules();
		
		List<Recipe> partialRecipes = new ArrayList<Recipe>();

		for (Match match : getPartialMatches()) {
			if (match.getRecipe() == PAELLA) {
				assertTrue(match.getMissingIngredients().containsAll(Arrays.asList(GREEN_PEPPERS, SHRIMP)));
			} else if (match.getRecipe() == ARROZ_CON_POLLO) {
				assertTrue(match.getMissingIngredients().containsAll(Arrays.asList(ADOBO)));
			}
			partialRecipes.add(match.getRecipe());
		}
		
		assertTrue(partialRecipes.contains(ARROZ_CON_POLLO));
		assertTrue(partialRecipes.contains(PAELLA));
	}
	
	
	
	//
	// private methods
	//
	
    private List<Recipe> recipesWeCanMake() {
    	QueryResults results = knowledgeSession.getQueryResults( "getFullMatches" );
		List<Recipe> recipesWeCanMake = new ArrayList<Recipe>();
		for ( QueryResultsRow row : results ) {
			Match match = (Match) row.get( "match" );
			recipesWeCanMake.add(match.getRecipe());
		}
		return recipesWeCanMake;
    }
    
    private List<Match> getPartialMatches() {
    	QueryResults results = knowledgeSession.getQueryResults( "getPartialMatches" );
		List<Match> partials = new ArrayList<Match>();
		for ( QueryResultsRow row : results ) {
			Match match = (Match) row.get( "match" );
			partials.add(match);
		}
		return partials;
    }
    
    private AverageRecipeRating getAverageRecipeRatingFor(Recipe recipe) {
    	QueryResults results = knowledgeSession.getQueryResults( "getAverageRecipeRatingFor", new Object[] { recipe } );
    	QueryResultsRow row = results.iterator().next();
		return (AverageRecipeRating) row.get( "rating" );
    }
    
    private List<AverageRecipeRating> getAllAverageRecipeRatings() {
    	QueryResults results = knowledgeSession.getQueryResults( "getAllAverageRecipeRatings" );
		List<AverageRecipeRating> allRatings = new ArrayList<AverageRecipeRating>();
		for ( QueryResultsRow row : results ) {
			AverageRecipeRating rating = (AverageRecipeRating) row.get( "rating" );
			allRatings.add(rating);
		}
		Collections.sort(allRatings);
		return allRatings;
    }

}
