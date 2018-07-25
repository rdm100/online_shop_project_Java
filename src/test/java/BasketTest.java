import models.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BasketTest {



    Product drink1;
    Product drink2;
    Basket basket;
    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("rdm", "Roberto", 39, 10000);
        basket = customer.getBasket();
        drink1 = new Drink("Coke", 0.50,4,  330, 50.0, 0.05 , 40);
        drink2 = new Drink("Pespi", 0.50,4,  330, 50.0, 0.05 , 40);
        basket.addProducttoBasket(drink1);
        basket.addProducttoBasket(drink2);

    }

    @Test
    public void checkBasketRunningTotal() {
        assertEquals(1.00, basket.calculateTotalCostOfAllItemsInBasket() );
    }

    @Test
    public void addItemsToBasket() {
        assertEquals(2, basket.countProductsInBasket());
    }

    @Test
    public void canRemoveFromBasket() {
        basket.removeProductFromBasket(drink1);
        assertEquals(1, basket.countProductsInBasket());
    }

    @Test
    public void check2ForOneDiscount() {
        basket.addProducttoBasket(drink1);
        assertEquals(1.00, basket.giveTotal() );
    }

    @Test
    public void check2ForOneDiscountTwoProdcuts() {
        basket.addProducttoBasket(drink1);
        basket.addProducttoBasket(drink2);
        assertEquals(1.00, basket.giveTotal() );
    }


    @Test
    public void emptyBasket() {
        basket.basketGivesAllProductsToCustomer();
        assertEquals(0,basket.countProductsInBasket() );
    }
}


