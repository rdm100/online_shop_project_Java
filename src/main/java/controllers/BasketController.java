package controllers;

import db.DBBasket;
import db.DBCustomer;
import db.DBHelper;
import db.DBProduct;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;

public class BasketController {

    public BasketController() {
        this.setupEndpoints();
    }

    LoginController loginController = new LoginController();


    private void setupEndpoints() {
        get("/basket", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            List<Product> products = DBBasket.AllProductsInABasket(customer.getBasket());
            Basket basket = customer.getBasket();
            model.put("basket", basket);
            model.put("products", products);
            model.put("template", "templates/basket/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());



        post("/basket/buy", (req, res) -> {
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            Till till = (Till)DBHelper.getAll(Till.class).get(0);
            Basket customersBasket = customer.getBasket();
            double amountPaid = customer.getBasket().giveTotal();
            Order order = new Order(customersBasket.getProducts(), customer, amountPaid);
            DBHelper.save(order);
            order.setProductToOrder();
            till.sellBasketToCustomer(customer);
//            DBProduct.saveProducts(customersBasket);
            DBHelper.save(customersBasket);
            DBHelper.save(till);
            DBHelper.save(customer);
            res.redirect("/account");
            return null;
        }, new VelocityTemplateEngine());


        post("/basket/:id/remove", (req, res) -> {
            String strId = req.params("id");
            Integer intId = Integer.parseInt(strId);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            Product product = DBHelper.find(Product.class, intId);
            customer.getBasket().removeProductFromBasket(product);
            DBHelper.save(customer.getBasket());
            DBHelper.save(product);

            res.redirect("/basket");
            return null;
        }, new VelocityTemplateEngine());


    }


}


