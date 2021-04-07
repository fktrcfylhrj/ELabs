package Task1.Vegetables;

import Task1.*;
import static Task1.Vitamins.*;

/**
 * Class Potatoes concretizes abstract class Vegetable and adds its own specifics
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class Potatoes extends Vegetable {
    public Potatoes( int grams) {
        super( grams);
        this.vitamins = new Vitamins[2];
        this.vitamins[0]=A;
        this.vitamins[1]=B;
    }

    @Override
    public int getCaloriePerKg() {
        return 750;
    }
}
