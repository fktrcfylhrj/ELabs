package Task1;

/**
 * Abstract class Ingredient describes ingredient
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public abstract class Ingredient {
    protected int grams;

    abstract public int getGrams();

    abstract public int getCaloriePerKg();

    abstract public float getCalorieContent();

    abstract void setGrams (int grams);

    abstract public void showInformation();
}
