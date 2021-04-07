package Task1;

/**
 * Class Sauce stores information about sauce
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class Sauce extends Ingredient{
    private String name;

    private int caloriePerKg;

    public Sauce(int caloriesPerKg, int grams, String name){
        this.name = name;
        this.caloriePerKg=caloriesPerKg;
        this.grams=grams;
    }

    public Sauce(int caloriesPerKg, int grams){
        this.caloriePerKg=caloriesPerKg;
        this.grams=grams;
        this.name=null;
    }

    @Override
    public float getCalorieContent() {
        return (float) (this.caloriePerKg*this.grams/1000.0);
    }

    @Override
    public int getGrams() {
        return this.grams;
    }

    @Override
    public int getCaloriePerKg() {
        return this.caloriePerKg;
    }

    public String getName() {
        return this.name;
    }

    @Override
    void setGrams(int grams) {
        this.grams=grams;
    }

    public void setName(String name){
        this.name=name;
    }

    @Override
    public void showInformation() {
        System.out.println("Weight:");
        System.out.println(this.grams);
        System.out.println("Calories per kg:");
        System.out.println(this.caloriePerKg);
    }
}
