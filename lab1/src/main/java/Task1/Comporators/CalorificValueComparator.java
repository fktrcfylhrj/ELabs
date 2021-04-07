package Task1.Comporators;

import Task1.Ingredient;
import java.util.Comparator;

/**
 * Class CalorificValueComparator compares two instances of class Ingredient
 * by method "getCalorieContent()"
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class CalorificValueComparator  implements Comparator<Ingredient>{
    @Override
    public int compare (Ingredient o1, Ingredient o2){
        return (int) (o1.getCalorieContent() - o2.getCalorieContent());
    }
}
