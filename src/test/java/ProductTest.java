//import models.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Calendar;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class ProductTest {
//
//    Food food;
//    Stock stock;
//
//    @Before
//    public void setUp() throws Exception {
//        Calendar bestBefore = Calendar.getInstance();
//        bestBefore.set(Calendar.YEAR, 2018);
//        bestBefore.set(Calendar.MONTH, 10);
//        bestBefore.set(Calendar.DAY_OF_MONTH, 16);
//        stock = new Stock();
//        food = new Food("Bread", 100,stock,  bestBefore, "Scotland", 100);
//
//    }
//
//    @Test
//    public void name() {
//        assertEquals("16-11-2018", food.bestBeforeToString());
//    }
//
//    @Test
//    public void getProductTypeString() {
//        assertEquals("food", food.productType());
//    }
//
//
//
//}
