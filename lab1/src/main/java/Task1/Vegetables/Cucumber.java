package Task1.Vegetables;

import Task1.*;
import static Task1.Vitamins.*;

/**
 * Class Cucumber concretizes abstract class Vegetable and adds its own specifics
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class Cucumber extends Vegetable{
    public Cucumber(int grams) {
        super(grams);
        this.vitamins = new Vitamins[3];
        this.vitamins[0]=B;
        this.vitamins[1]=C;
        this.vitamins[2]=P;
    }

    @Override
    public int getCaloriePerKg() {
        return 150;
    }
}
