package org.integrallis.drools.recipes.fixtures;

import java.util.ArrayList;
import java.util.List;

import org.integrallis.drools.recipes.Ingredient;

public final class Ingredients {
	public static Ingredient RICE = new Ingredient("Rice");
	public static Ingredient POTATOES = new Ingredient("Potatoes");
	public static Ingredient SPAGUETTI = new Ingredient("Spaghetti");
	public static Ingredient FETTUCCINE = new Ingredient("Fettuccine");
	public static Ingredient YUCCA_ROOT = new Ingredient("Yucca Root");
	public static Ingredient CHICKEN = new Ingredient("Chicken");
	public static Ingredient MEAT_FOR_STEW = new Ingredient("Meat for Stew");
	public static Ingredient PORK_CHOPS = new Ingredient("Pork Chops");
	public static Ingredient GROUND_PORK = new Ingredient("Ground Pork");
	public static Ingredient GROUND_BEEF = new Ingredient("Ground Beef");
	public static Ingredient GROUND_LAMB = new Ingredient("Ground Lamb");
	public static Ingredient LAMB_SHANKS = new Ingredient("Lamb Shanks");
	public static Ingredient SALT = new Ingredient("Salt");
	public static Ingredient ONIONS = new Ingredient("Onions");
	public static Ingredient CELERY = new Ingredient("Celery");
	public static Ingredient PARSLEY = new Ingredient("Parsley");
	public static Ingredient THYME = new Ingredient("Thyme");
	public static Ingredient EGGS = new Ingredient("Eggs");
	public static Ingredient BACON = new Ingredient("Bacon");
	public static Ingredient CARROTS = new Ingredient("Carrots");
	public static Ingredient BAY_LEAVES = new Ingredient("Bay Leaves");
	public static Ingredient PEAS = new Ingredient("Peas");
	public static Ingredient CILANTRO = new Ingredient("Cilantro");
	public static Ingredient SHRIMP = new Ingredient("Shrimp");
	public static Ingredient GREEN_PEPPERS = new Ingredient("Green Peppers");
	public static Ingredient RED_PEPPERS = new Ingredient("Red Peppers");
	public static Ingredient SAFFRON = new Ingredient("Saffron");
	public static Ingredient ADOBO = new Ingredient("Adobo");
	public static Ingredient CAYENNE = new Ingredient("Cayenne Pepper");
	
	public static List<Ingredient> all = new ArrayList<Ingredient>();
	static {
		all.add(RICE);
		all.add(POTATOES);
		all.add(SPAGUETTI);
		all.add(FETTUCCINE);
		all.add(YUCCA_ROOT);
		all.add(CHICKEN);
		all.add(MEAT_FOR_STEW);
		all.add(PORK_CHOPS);
		all.add(GROUND_PORK);
		all.add(GROUND_BEEF);
		all.add(GROUND_LAMB);
		all.add(LAMB_SHANKS);
		all.add(SALT);
		all.add(ONIONS);
		all.add(CELERY);
		all.add(PARSLEY);
		all.add(THYME);
		all.add(EGGS);
		all.add(BACON);
		all.add(CARROTS);
		all.add(BAY_LEAVES);
		all.add(PEAS);
		all.add(CILANTRO);
		all.add(SHRIMP);
		all.add(GREEN_PEPPERS);
		all.add(RED_PEPPERS);
		all.add(SAFFRON);
		all.add(ADOBO);
		all.add(CAYENNE);
	}
	
	private Ingredients() {};
}
