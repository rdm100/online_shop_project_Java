package models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "foods")
public class Food extends Product {

    private Calendar bestBefore;
    private String origin;
    private int calories;

    public Food() {
    }


    public Food(String name, double price, int quantity, Calendar bestBefore, String origin, int calories) {
        super(name, price, quantity);
        this.bestBefore = bestBefore;
        this.origin = origin;
        this.calories = calories;
    }

    @Temporal(TemporalType.DATE)
    @Column(name ="best_before")
    public Calendar getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(Calendar bestBefore) {
        this.bestBefore = bestBefore;
    }

    @Column
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Column
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }


    public String bestBeforeToString(){
        Date date = bestBefore.getTime();
        SimpleDateFormat formattedTime = new SimpleDateFormat("dd-MM-yyyy");
         String dateAsString = formattedTime.format(date);
            return dateAsString;
    }
}


