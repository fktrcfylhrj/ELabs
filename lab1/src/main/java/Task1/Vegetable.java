package Task1;

/**
 * Class Vegetable is destined to inherit concrete vegetable
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public abstract class Vegetable extends Ingredient{
    protected Vitamins[] vitamins;

    public Vegetable( int grams){
        this.grams=grams;
    }

    @Override
    public float getCalorieContent() {
        return (float) (this.getCaloriePerKg()*this.grams/1000.0);
    }

    @Override
    public int getGrams() {
        return this.grams;
    }

    public Vitamins[] getVitamins() {
        return this.vitamins;
    }

    @Override
    void setGrams(int grams) {
        this.grams=grams;
    }

    @Override
    public void showInformation(){
        System.out.println("Weight:");
        System.out.println(this.grams);
        System.out.println("Calories per kg:");
        System.out.println(this.getCaloriePerKg());
    }
}
