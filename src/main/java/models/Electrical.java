package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "electricals")
public class Electrical extends Product {

    private String model;
    private String colour;

    public Electrical() {
    }

    public Electrical(String name, double price, int quantity, String model, String colour) {
        super(name, price, quantity);
        this.model = model;
        this.colour = colour;
    }

    @Column
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @Column
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
