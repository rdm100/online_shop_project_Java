package models;

import db.DBHelper;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    private int id;
    private List<Product> boughtProducts;
    private Customer customer;
    private double amountPaid;

    public Order() {
    }

    public Order(List<Product> boughtProducts, Customer customer, double amountPaid) {
        this.boughtProducts = boughtProducts;
        this.customer = customer;
        this.amountPaid = amountPaid;

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

    @ManyToMany(mappedBy = "orders")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Product> getProducts() {
        return boughtProducts;
    }


    public void setProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }


    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name ="customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int countOfItemsInOrder(){
       return this.boughtProducts.size();
    }

    public void addBasketProductsToOrder(List<Product> customersBoughtProducts){
        this.boughtProducts.addAll(customersBoughtProducts);
    }

    @Column(name = "amount_paid")
    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setProductToOrder(){
        for(Product product: this.boughtProducts){
            product.addOrdertoProduct(this);
        }
    }
}

