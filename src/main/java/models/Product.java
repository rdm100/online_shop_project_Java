package models;

import db.DBHelper;
import javassist.expr.Instanceof;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    private int id;
    private String name;
    private double price;
    private List<Basket> baskets;
    private int quantity;
    private List<Order> orders;

    public Product() {
    }

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.baskets = new ArrayList<>();
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

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @ManyToMany(mappedBy = "products")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<Basket> baskets) {
        this.baskets = baskets;
    }


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_in_order",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "order_id", nullable = false)}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrdertoProduct(Order order){
        this.orders.add(order);

    }

    public void addBaskettoProduct(Basket basket){
        this.baskets.add(basket);

    }
    public void removeBasket(Basket basket) {
        this.baskets.remove(basket);
        DBHelper.save(this);
    }

    public String productType(){
        if(this instanceof Food){
            return "foods"; }
        if(this instanceof Electrical){
            return "electronics";
        } if(this instanceof Clothing){
            return "clothes";
        } if(this instanceof Drink){
            return "drinks";
        }return null;
    }




}
