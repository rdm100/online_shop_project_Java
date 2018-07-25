package controllers;

import db.DBCustomer;
import db.DBHelper;
import db.Seeds;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;


public class StockController {

    public StockController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {


        get("/stock", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            List<Product> products = DBHelper.getAll(Product.class);
            Till till = (Till)DBHelper.getAll(Till.class).get(0);
            model.put("products", products);
            model.put("till", till);
            model.put("user", loggedInUser);
            model.put("template", "templates/stock/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

}

