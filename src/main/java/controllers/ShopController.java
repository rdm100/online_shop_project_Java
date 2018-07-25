package controllers;
import db.DBHelper;
import db.DBProduct;
import db.Seeds;
import models.Product;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.List;
import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class ShopController {
    public static void main(String[] args) {
        Seeds.seedData();
        staticFileLocation("/public");
        LoginController loginController = new LoginController();
        ClothesController clothesController = new ClothesController();
        DrinksController drinksController = new DrinksController();
        ElectronicsController electronicsController = new ElectronicsController();
        FoodController foodController = new FoodController();
        StockController stockController = new StockController();
        BasketController basketController = new BasketController();
        CustomerController customerController = new CustomerController();
        OrderController orderController = new OrderController();
        SignUpController signUpController = new SignUpController();

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();
        get("/shop", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("template", "templates/shop/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
            }, velocityTemplateEngine);

        get("shop/results", (req, res) ->{
            HashMap<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            String search = req.queryParams("search");
            List<Product> products = DBProduct.productsFromSearch(search);
            model.put("products", products);
            model.put("template", "templates/shop/search.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine  );
    }

}
