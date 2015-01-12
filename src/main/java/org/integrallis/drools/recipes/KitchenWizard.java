package org.integrallis.drools.recipes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.integrallis.drools.recipes.fixtures.Ingredients;
import org.integrallis.drools.recipes.fixtures.Recipes;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;


public class KitchenWizard {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KieSession knowledgeSession = null;
		try {
	        KieServices ks = KieServices.Factory.get();
    	        KieContainer kContainer = ks.getKieClasspathContainer();
    	        knowledgeSession = kContainer.newKieSession("ksession-rules");
			
			String input = ""; // Line read from standard in

			System.out.println("What do you have in the kitchen?: (-1 when done)");
			for (Ingredient ingredient : Ingredients.all) {
				System.out.println(Ingredients.all.indexOf(ingredient) + " ===> " + ingredient);
			}
			
			InputStreamReader converter = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(converter);
			
			//
			// Add available ingredients
			//
			Boolean doneAddingIngredients = false;

			while (!doneAddingIngredients) {
                if (!doneAddingIngredients) {
                	input = in.readLine();
                	doneAddingIngredients = input.equals("-1");
                }
				if (!doneAddingIngredients) {
					Integer index = new Integer(input);
					Ingredient ingredientToAdd = Ingredients.all.get(index);
					System.out.println("You chose: " + ingredientToAdd);
			
			        knowledgeSession.insert(ingredientToAdd);
				}
			}		
			
			//
			// Show recipes with missing ingredients?
			//
			System.out.println("Maximum number of missing ingredients in order to consider a recipe?: ");
			input = in.readLine();
			
			System.out.println("You chose: " + input);
			Integer missingIngredients = new Integer(input);
			
			knowledgeSession.insert(new UserPreferences(missingIngredients));
			
			// Load all recipes
			for (Recipe recipe : Recipes.all) {
				knowledgeSession.insert(recipe);
			}
			
			// Fire the rules
			knowledgeSession.fireAllRules();
			
			// Query the recipes we can make (full matches)
			System.out.println("With what you have in your kitchen you can make...\n");
			
			List<Recipe> weCanMake = recipesWeCanMake(knowledgeSession);
			
			for (Recipe recipe : weCanMake) {
				System.out.println("===> " + recipe);
			}
			if (weCanMake.isEmpty()) { System.out.println("Nothing!"); }
			
			// Query partial matches
			System.out.println("Buying " + missingIngredients + " missing ingredient(s) will allow you to also make...\n");
			
			List<Match> partials = getPartialMatches(knowledgeSession);
			for (Match partial : partials) {
				System.out.println("===> " + partial);
			}		
			if (partials.isEmpty()) { System.out.println("Sorry, no partial matches!"); }
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}	
	
    private static List<Recipe> recipesWeCanMake(KieSession knowledgeSession) {
    		QueryResults results = knowledgeSession.getQueryResults( "getFullMatches" );
		List<Recipe> recipesWeCanMake = new ArrayList<Recipe>();
		for ( QueryResultsRow row : results ) {
			Match match = (Match) row.get( "match" );
			recipesWeCanMake.add(match.getRecipe());
		}
		return recipesWeCanMake;
    }
    
    private static List<Match> getPartialMatches(KieSession knowledgeSession) {
    		QueryResults results = knowledgeSession.getQueryResults( "getPartialMatches" );
		List<Match> partials = new ArrayList<Match>();
		for ( QueryResultsRow row : results ) {
			Match match = (Match) row.get( "match" );
			partials.add(match);
		}
		return partials;
    }
}
