package Task1.Comporators;

import Task1.Ingredient;
import java.util.Comparator;

/**
 * Class CaloriePerKgComparator compares two instances of class Ingredient
 * by parameter "caloriePerKg"
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class CaloriePerKgComparator implements Comparator<Ingredient> {
    @Override
    public int compare(Ingredient o1, Ingredient o2) {
        return (int) (o1.getCaloriePerKg() - o2.getCaloriePerKg());
    }
}
