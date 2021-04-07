package Task1.Comporators;

import Task1.Ingredient;
import java.util.Comparator;

/**
 * Class WeightComparator compares two instances of class Ingredient
 * by parameter "grams"
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class WeightComparator implements Comparator<Ingredient> {
    @Override
    public int compare(Ingredient o1, Ingredient o2) {
        return o1.getGrams()- o2.getGrams();
    }
}
