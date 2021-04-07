package Task1;

import java.util.*;

/**
 * Class Salad stores information about salad
 *
 * @version 1.0 21 Mar 2021
 * @author  Kachanau Aliaksandr
 * */
public class Salad extends Dish{
    private Sauce sauce;

    public Salad(String name, Ingredient[] ingredients, Sauce sauce, String recipe){
        this.ingredients=ingredients;
        this.sauce=sauce;
        this.name=name;
        this.recipe=recipe;
    }

    public Salad(String name, Ingredient[] ingredients, Sauce sauce){
        this.ingredients=ingredients;
        this.sauce=sauce;
        this.name=name;
    }

    public Salad(String name, Ingredient[] ingredients){
        this.ingredients=ingredients;
        this.name=name;
        this.sauce=null;
    }

    public Ingredient[] getIngredients(){ return ingredients; }

    public String getName() {
        return name;
    }
    @Override

    public String getRecipe() {
        return this.recipe;
    }

    public int getCalorieContent(){
        int sum=0;
        for(Ingredient ingredient:this.ingredients){
            sum+=ingredient.getCalorieContent();
        }
        if(this.sauce!=null){
        sum+=sauce.getCalorieContent();
        }
        return sum;
    }

    public void setRecipe(String recipe){
        this.recipe=recipe;
    }

    public void setIngredients(Ingredient[] ingredients){
        this.ingredients=ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**Adds another ingredient*/
    public void addIngredient (Ingredient ingredient){
        List<Ingredient> arrList = new ArrayList<Ingredient>(Arrays.asList(this.ingredients));
        arrList.add(ingredient);
        this.ingredients=arrList.toArray(this.ingredients);
    }

    /**Sorts according to comparator*/
    public void sort(Comparator<Ingredient> comparator){
        Arrays.sort(getIngredients(),comparator);
    }
}
