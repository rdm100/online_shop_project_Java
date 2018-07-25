package controllers;

import db.DBCustomer;
import db.DBHelper;
import models.Customer;
import models.Drink;
import models.Food;
import models.Product;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import sun.security.pkcs11.Secmod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class CustomerController {


    public CustomerController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/account", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            model.put("customer", customer);
            model.put("template","templates/customer/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/account/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Customer customer = DBHelper.find(Customer.class, intId);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("customer", customer);
            model.put("template","templates/customer/edit.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/account/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            String username = req.queryParams("username");
            req.session().attribute("username", username);
            Customer customer = DBHelper.find(Customer.class, intId);
            String name = req.queryParams("name");
            int age = Integer.parseInt(req.queryParams("age"));
            double wallet = Double.parseDouble(req.queryParams("wallet"));
            customer.setName(name);
            customer.setWallet(wallet);
            customer.setAge(age);
            DBHelper.save(customer);
            res.redirect("/account");
            return null;
        }, new VelocityTemplateEngine());
    }
}

