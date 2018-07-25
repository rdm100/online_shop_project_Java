package models;

import db.DBHelper;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "baskets")
public class Basket {

    private int id;
    private List<Product> basket;
    private Customer customer;


    public Basket() {
        this.basket = new ArrayList<>();
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



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_in_basket",
            inverseJoinColumns = {@JoinColumn(name = "product_id", updatable = true)},
            joinColumns = {@JoinColumn(name = "basket_id", updatable = true)}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Product> getProducts() {
        return basket;
    }



    public void setProducts(List<Product> products) {
        this.basket = products;
    }


    @OneToOne
    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public int countProductsInBasket(){
        return this.basket.size();
    }


    public void addProducttoBasket(Product product){
        this.basket.add(product);
        product.addBaskettoProduct(this);

    }

//    public void removeProductFromBasket(Product product){
//               this.basket.remove(product);
////        public void removeProductFromBasket(Product product) {
////            product.removeBasket(this);
////            DBHelper.save(product);
////                   DBHelper.save(this);
////
////
////               basket.clear();
////                   }

    public void removeProductFromBasket(Product product) {
        List<Product> newBasket = new ArrayList<>();
        product.removeBasket(this);
        for(Product eachProduct : this.basket){
            if(eachProduct != product){}
                newBasket.add(product);
        }        basket.clear();
        newBasket.remove(product);
        product.removeBasket(this);
                basket.addAll(newBasket);
        DBHelper.save(basket);
    }


    public List<Product> basketGivesAllProductsToCustomer(){
        ArrayList<Product> customerProducts = new ArrayList<>();
        customerProducts.addAll(basket);
        basket.clear();
         return customerProducts;
    }


    public double calculateTotalCostOfAllItemsInBasket(){
        double result = 0;
        for(Product product: basket){
            result += product.getPrice();
        } return result;
    }


    public double discountAmount() {
        if (calculateTotalCostOfAllItemsInBasket() > 50.00) {
            return 10;
        }
        double twoForOneDiscount = 0;
        List<Product> cloned = this.basket.stream().distinct().collect(Collectors.toList());
        for (Product product : cloned) {
           int quantity = getQuantityOfProductInBasket(product);

            if(quantity == 2 ){
                twoForOneDiscount += product.getPrice();

            }
            if(quantity == 3 ){
                twoForOneDiscount += product.getPrice();

            }
            if(quantity == 4 ){
                twoForOneDiscount += (product.getPrice() * 2);

            }
             if(quantity == 5 ){
                twoForOneDiscount += (product.getPrice() * 2);

            }
            if(quantity > 6 ){
                twoForOneDiscount += (product.getPrice() * 3);

            }

           }return twoForOneDiscount;

    }



    public int getQuantityOfProductInBasket(Product productToSearch){
        int quantity = 0;
        for(Product product: this.basket ){
            if(product == productToSearch){
                quantity += 1;
            }
        } return quantity;
    }

    public double giveTotal(){
       double result = 0;
        result += calculateTotalCostOfAllItemsInBasket() ;
        result -= discountAmount();
        return result;
    }

    public void clearBasket(){
        for(Product product: basket){
            product.removeBasket(this);
            DBHelper.save(product);
        }
        this.basket.clear();
        DBHelper.save(this);
    }

}
