//import models.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Calendar;
//
//import static org.junit.Assert.assertEquals;
//
//public class OrderTest {
//
//
//    Stock stock;
//    Product drink1;
//    Product drink2;
//    Basket basket;
//    Customer customer;
//    Order order;
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
//        order = new Order(basket.basketGivesAllProductsToCustomer(), customer, basket.giveTotal());
//
//    }
//
//    @Test
//    public void countProductsInOrder() {
//        assertEquals(2, order.countOfItemsInOrder());
//    }
//
//}
