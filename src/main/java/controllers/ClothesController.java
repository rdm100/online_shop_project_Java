package controllers;

import db.DBCustomer;
import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ClothesController {

    public ClothesController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/clothes", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            List<Clothing> clothes = DBHelper.getAll(Clothing.class);
            model.put("clothes", clothes);
            model.put("template", "templates/clothes/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get ("/clothes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("template", "templates/clothes/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/clothes/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Clothing clothing = DBHelper.find(Clothing.class, intId);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("clothing", clothing);
            model.put("template", "templates/clothes/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/clothes/:id/buy", (req, res) -> {
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

        post("/clothes/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Clothing clothing = DBHelper.find(Clothing.class, id);
            DBHelper.delete(clothing);
            res.redirect("/clothes");
            return null;
        }, new VelocityTemplateEngine());

        post ("/clothes", (req, res) ->{
            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price"));
            int quantity = Integer.parseInt(req.queryParams("price"));
            String size = req.queryParams("size");
            String colour = req.queryParams("colour");
            String range = req.queryParams("range");
            Clothing clothing = new Clothing(name, price, quantity, size, colour, range);
            DBHelper.save(clothing);
            res.redirect("/stock");
            return null;


        },new VelocityTemplateEngine());

        post ("/clothes/:id", (req, res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Clothing clothing = DBHelper.find(Clothing.class, intId);
            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            String size = req.queryParams("size");
            String colour = req.queryParams("colour");
            String range = req.queryParams("range");
            clothing.setName(name);
            clothing.setPrice(price);
            clothing.setSize(size);
            clothing.setQuantity(quantity);
            clothing.setColour(colour);
            clothing.setRange(range);
            DBHelper.save(clothing);
            res.redirect("/stock");
            return null;

        },new VelocityTemplateEngine());


        get("/clothes/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Clothing clothing = DBHelper.find(Clothing.class, intId);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("clothing", clothing);
            model.put("template", "templates/clothes/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}