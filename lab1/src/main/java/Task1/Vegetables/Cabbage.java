package Task1.Vegetables;

import Task1.*;
import static Task1.Vitamins.*;

/**
 * Class Cabbage concretizes abstract class Vegetable and adds its own specifics
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class Cabbage extends Vegetable{
    public Cabbage( int grams) {
        super(grams);
        this.vitamins = new Vitamins[4];
        this.vitamins[0]=C;
        this.vitamins[1]=E;
        this.vitamins[2]=K;
        this.vitamins[3]=N;
    }

    @Override
    public int getCaloriePerKg() {
        return 270;
    }
}
