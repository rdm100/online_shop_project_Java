package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "till")
public class Till {

    private int id;
    private double cash;

    public Till(double cash) {
        this.cash = cash;
    }

    public Till() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column
    public double getCash() {
        return cash;
    }


    public void setCash(double cash) {
        this.cash = cash;
    }

    public void addMoneyToTill(double money){
        this.cash += money;
    }


    public void sellBasketToCustomer(Customer customer){
        Basket basket = customer.getBasket();
        if(customer.customerCanAffordShopping(basket)){
        double total = basket.giveTotal();
        this.addMoneyToTill(total);
        for(Product product: basket.getProducts()){
            int newTotal = product.getQuantity() - 1;
            product.setQuantity(newTotal);
            product.removeBasket(basket);

        }
        customer.customerPaysForBasket(basket);
        customer.getBasket().clearBasket();

        }
    }

}
