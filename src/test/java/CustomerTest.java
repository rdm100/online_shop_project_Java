//import models.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import static junit.framework.Assert.assertEquals;
//
//public class CustomerTest {
//
//    Stock stock;
//    Product drink1;
//    Product drink2;
//    Basket basket;
//    Customer customer;
//
//    @Before
//    public void setUp() throws Exception {
//        customer = new Customer("rdm", "Roberto", 39, 10000);
//        basket = new Basket(customer);
//        stock = new Stock();
//        drink1 = new Drink("Coke", 0.50,stock,  330, 50.0, 0.05 , 40);
//        drink2 = new Drink("Pespi", 0.50,stock,  330, 50.0, 0.05 , 40);
//        basket.addProducttoBasket(drink1);
//        basket.addProducttoBasket(drink2);
//
//    }
//
//    @Test
//    public void customerHasNoProducts() {
//        assertEquals(0, customer.countCustomerHasProducts());
//    }
//
//    @Test
//    public void customerHasProducts() {
//        customer.addBasketToCustomerProducts(basket.basketGivesAllProductsToCustomer());
//        assertEquals(2, customer.countCustomerHasProducts());
//    }
//}
