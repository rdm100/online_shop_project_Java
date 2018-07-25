package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    private int id;
    private String username;
    private String name;
    private int age;
    private double wallet;
    private Basket basket;
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String username, String name, int age, double wallet) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.basket = new Basket();
        basket.setCustomer(this);
        this.wallet = wallet;
        this.orders = new ArrayList<>();
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


    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column
    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public List<Order> getOrders() {
        return orders;
    }


    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public boolean customerCanAffordShopping(Basket basket){
    if(getWallet() > basket.giveTotal())
    {return true;}
    return false;
    }

    public double customerPaysForBasket(Basket basket){
        double total = basket.calculateTotalCostOfAllItemsInBasket();
        if(customerCanAffordShopping(basket)){
            setWallet(getWallet() - basket.giveTotal());
            this.basket.clearBasket();
            return  total;
        } return 0;
    }
    @OneToOne(mappedBy = "customer", cascade=CascadeType.ALL)
    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;}
}



