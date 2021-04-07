package Task1;

/**
 * Abstract class Dish describes abstract dish
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public abstract class Dish {
    protected String name;

    protected String recipe;

    protected Ingredient[] ingredients;

    abstract public String getName();

    abstract public String getRecipe();

    abstract public Ingredient[] getIngredients();

    abstract void setName(String name);

    abstract void setRecipe(String recipe);

    abstract void setIngredients(Ingredient[] ingredients);
}
