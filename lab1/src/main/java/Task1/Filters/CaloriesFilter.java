package Task1.Filters;

import Task1.Filter;
import Task1.Ingredient;

/**
 * Class CaloriesFilter checks the vegetable for compliance with the specified calorie range
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class CaloriesFilter implements Filter {
    private int from;

    private int to;

    public CaloriesFilter(int from, int to){
        if(from>to){
            int buf=from;
            from=to;
            to=buf;
        }
        this.from=from;
        this.to=to;
    }

    @Override
    public boolean check(Ingredient ingredient) {
        return from<=ingredient.getCalorieContent() && ingredient.getCalorieContent()<=to;
    }
}
