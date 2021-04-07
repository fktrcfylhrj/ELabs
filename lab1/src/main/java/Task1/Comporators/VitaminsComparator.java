package Task1.Comporators;

import Task1.Vegetable;
import java.util.Comparator;

/**
 * Class VitaminsComparator compares two instances of class Vegetable
 * by number of vitamins
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class VitaminsComparator implements Comparator<Vegetable> {
    @Override
    public int compare(Vegetable o1, Vegetable o2) {
        return (int) (o1.getVitamins().length - o2.getVitamins().length);
    }
}
