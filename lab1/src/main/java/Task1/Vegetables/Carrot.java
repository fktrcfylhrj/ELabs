package Task1.Vegetables;

import Task1.*;
import static Task1.Vitamins.*;

/**
 * Class Carrot concretizes abstract class Vegetable and adds its own specifics
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class Carrot extends Vegetable{
    public Carrot ( int grams) {
        super(grams);
        this.vitamins = new Vitamins[1];
        this.vitamins[0]=K;
    }

    @Override
    public int getCaloriePerKg() {
        return 350;
    }
}
