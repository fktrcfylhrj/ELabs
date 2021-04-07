package Task1.Vegetables;

import Task1.*;
import static Task1.Vitamins.*;

/**
 * Class Tomato concretizes abstract class Vegetable and adds its own specifics
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class Tomato extends Vegetable {
    public Tomato( int grams) {
        super(grams);
        this.vitamins = new Vitamins[2];
        this.vitamins[0]=B;
        this.vitamins[1]=C;
    }

    @Override
    public int getCaloriePerKg() {
        return 180;
    }
}
