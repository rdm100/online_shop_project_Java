package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clothes")
public class Clothing extends Product {
    private String size;
    private String colour;
    private String range;

    public Clothing() {
    }


    public Clothing(String name, double price, int quantity, String size, String colour, String range) {
        super(name, price, quantity);
        this.size = size;
        this.colour = colour;
        this.range = range;
    }

    @Column
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Column
    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
