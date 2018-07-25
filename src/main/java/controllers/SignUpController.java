package controllers;

import db.DBHelper;
import models.Customer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class SignUpController {
    public SignUpController() {
        this.setupEndpoints();
    }
    private void setupEndpoints() {

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();
        get("/signup", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("template", "templates/signup.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);


        post("/signup", (req, res) -> {
        String username = req.queryParams("username");
        req.session().attribute("username", username);
        String name = req.queryParams("name");
        int age = Integer.parseInt(req.queryParams("age"));
        double wallet = Double.parseDouble(req.queryParams("wallet"));
        Customer customer = new Customer(username, name, age, wallet);
        DBHelper.save(customer);
        res.redirect("/shop");
        return null;
    }, new VelocityTemplateEngine());
}
}
