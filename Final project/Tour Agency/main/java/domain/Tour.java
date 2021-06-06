package domain;

import java.sql.Date;

public class Tour extends Entity{
    private Date dateStart;
    private Date dateEnd;
    private boolean burning;
    private Customer customer;
    private TravelAgent travelAgent;
    private float recreationCost;
    private float excursionCost;
    private float shoppingCost;
    private float otherExpenses;

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isBurning() {
        return burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }



    public float getRecreationCost() {
        return recreationCost;
    }

    public void setRecreationCost(float recreationCost) {
        this.recreationCost = recreationCost;
    }

    public float getExcursionCost() {
        return excursionCost;
    }

    public void setExcursionCost(float excursionCost) {
        this.excursionCost = excursionCost;
    }

    public float getShoppingCost() {
        return shoppingCost;
    }

    public void setShoppingCost(float shoppingCost) {
        this.shoppingCost = shoppingCost;
    }

    public float getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(float otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TravelAgent getTravelAgent() {
        return travelAgent;
    }

    public void setTravelAgent(TravelAgent travelAgent) {
        this.travelAgent = travelAgent;
    }
}
