package controllers;

import db.*;
import models.Customer;
import models.Order;
import models.Product;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class OrderController {

    public OrderController() {
        this.setupEndpoints();
    }

    LoginController loginController = new LoginController();


    private void setupEndpoints() {
        get("/orders", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            List<Order> orders = DBCustomer.AllOrdersBelongingToACustomer(customer);
            model.put("orders", orders);
            model.put("template", "templates/orders/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


    get("/orders/:id", (req, res) -> {
        String strId = req.params(":id");
        Integer intId = Integer.parseInt(strId);
        Map<String, Object> model = new HashMap<>();
        String loggedInUser = LoginController.getLoggedInUserName(req, res);
        model.put("user", loggedInUser);
        Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
        Order order = DBHelper.find(Order.class, intId);
        List<Product> products = DBOrder.findProductsInOrder(order);
        model.put("products", products);
        model.put("template", "templates/orders/order.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());
}
}
