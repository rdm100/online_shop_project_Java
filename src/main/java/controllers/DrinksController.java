package controllers;

import db.DBCustomer;
import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;

public class DrinksController {

    public DrinksController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/drinks/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);

            Drink drink = DBHelper.find(Product.class, intId);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("drink", drink);
            model.put("template", "templates/drinks/edit.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



        get("/drinks", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            List<Drink> drinks = DBHelper.getAll(Drink.class);
            model.put("drinks", drinks);
            model.put("template","templates/drinks/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get ("/drinks/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("template", "templates/drinks/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


    get("/drinks/:id", (req, res) -> {
        String strId = req.params(":id");
        Integer intId = Integer.parseInt(strId);
        Drink drink = DBHelper.find(Product.class, intId);
        Map<String, Object> model = new HashMap<>();
        String loggedInUser = LoginController.getLoggedInUserName(req, res);
        model.put("user", loggedInUser);
        model.put("drink", drink);
        model.put("template","templates/drinks/show.vtl");

        return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    post ("/drinks/:id", (req, res) ->{
        String strId = req.params(":id");
        Integer intId = Integer.parseInt(strId);
        Drink drink = DBHelper.find(Drink.class, intId);
        String name = req.queryParams("name");
        double price = Double.parseDouble(req.queryParams("price"));
        int volume = Integer.parseInt(req.queryParams("volume"));
        int quantity = Integer.parseInt(req.queryParams("quantity"));
        double sugarContent = Double.parseDouble(req.queryParams("sugarContent"));
        double alcholContent = Double.parseDouble(req.queryParams("alcoholContent"));
        int caffeineContent = Integer.parseInt(req.queryParams("caffeineContent"));
        drink.setName(name);
        drink.setPrice(price);
        drink.setVolume(volume);
        drink.setQuantity(quantity);
        drink.setAlcholContent(alcholContent);
        drink.setSugarContent(sugarContent);
        drink.setCaffineContent(caffeineContent);
        DBHelper.save(drink);
        res.redirect("/stock");
        return null;


        },new VelocityTemplateEngine());

        post ("/drinks/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Drink drink = DBHelper.find(Drink.class, id);
            DBHelper.delete(drink);
            res.redirect("/drinks");
            return null;
        }, new VelocityTemplateEngine());


        post("/drinks/:id/buy", (req, res) -> {
            String strId = req.params("id");
            Integer intId = Integer.parseInt(strId);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            Product product = DBHelper.find(Product.class, intId);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            customer.getBasket().addProducttoBasket(product);
            DBHelper.save(product);
            DBHelper.save(customer.getBasket());

            res.redirect("/basket");
            return null;
        }, new VelocityTemplateEngine());

        post ("/drinks", (req, res) ->{
            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price"));
            int volume = Integer.parseInt(req.queryParams("volume"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            double sugarContent = Double.parseDouble(req.queryParams("sugarContent"));
            double alcholContent = Double.parseDouble(req.queryParams("alcoholContent"));
            int caffeineContent = Integer.parseInt(req.queryParams("caffeineContent"));
            Drink drink = new Drink(name, price,quantity,  volume, sugarContent, alcholContent, caffeineContent);
            DBHelper.save(drink);
            res.redirect("/stock");
            return null;


        },new VelocityTemplateEngine());

    }
}



