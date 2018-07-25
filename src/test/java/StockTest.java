//import models.Drink;
//import models.Product;
//import org.junit.Before;
//import org.junit.Test;
//
//import static junit.framework.Assert.assertEquals;
//
//public class StockTest {
//    Stock stock;
//    Product drink;
//    Product drink2;
//
//
//    @Before
//    public void setUp() throws Exception {
//        stock = new Stock();
//        drink = new Drink("Coke", 0.50,stock,  330, 50.0, 0.05 , 40);
//        drink2 = new Drink("Pepsi Max", 0.65,stock,  330, 50.0, 0.05 , 40);
//
//    }
//
//    @Test
//    public void canAddProductToStock() {
//        stock.addProductToStock(drink);
//        assertEquals(1, stock.stockListCount());
//    }
//
//    @Test
//    public void checkProductQuantity() {
//        stock.addProductToStock(drink);
//        stock.addProductToStock(drink);
//        stock.addProductToStock(drink2);
//        assertEquals(2, stock.getQuantityOfProduct(drink));
//    }
//
//    @Test
//    public void checkProductQuantityForDrinks2() {
//        stock.addProductToStock(drink);
//        stock.addProductToStock(drink);
//        stock.addProductToStock(drink2);
//        assertEquals(1, stock.getQuantityOfProduct(drink2));
//    }
//}
