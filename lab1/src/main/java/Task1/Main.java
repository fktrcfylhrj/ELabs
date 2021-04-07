package Task1;

import Task1.Comporators.CaloriePerKgComparator;
import Task1.Filters.CaloriesFilter;
import Task1.Vegetables.*;
import java.util.ArrayList;

/**
 * Class Main has method main
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class Main {

    /**Makes example salad, in order to show, how program works*/
    public static Salad makeExampleSalad(){
        Vegetable[] vegetables = new Vegetable[5];
        vegetables[0] = new Carrot(100);
        vegetables[1] = new Cucumber( 200);
        vegetables[2] = new Potatoes( 300);
        vegetables[3] = new Tomato( 200);
        vegetables[4] = new Cabbage(150);
        Sauce sauce = new Sauce(6800, 400);
        Salad salad = new Salad("BelarusianOlivier", vegetables, sauce);
        return salad;
    }

    public static void main(String[] args){
        int from = 40;
        int to = 1000;
        Salad salad = makeExampleSalad();
        ArrayList<Ingredient> listForCheck = new ArrayList<Ingredient>();
        CaloriesFilter caloriesFilter = new CaloriesFilter(from, to);

        salad.sort(new CaloriePerKgComparator());
        for (Ingredient ingredient : salad.getIngredients()){
            ingredient.showInformation();
            if(caloriesFilter.check(ingredient)){
                listForCheck.add(ingredient);
            }
        }
        System.out.println("Ingredients\' calalorific value in range [" + from + "; " + to + "]:");
        for (Ingredient ingredient : listForCheck){
            ingredient.showInformation();
        }
        System.out.println("Calories of salad: " + salad.getCalorieContent());
    }
}
