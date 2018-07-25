package controllers;
import db.DBCustomer;
import db.DBHelper;
import models.Customer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.get;
import static spark.Spark.post;
public class LoginController {
    public LoginController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {
        get("/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/login.vtl");
        }, new VelocityTemplateEngine());

            post("/login", (req, res) -> {
        String inputtedUsername = req.queryParams("username");
        List<Customer> signedUpUsers = DBHelper.getAll(Customer.class);
        for (Customer customer: signedUpUsers){

            if(inputtedUsername.equals(customer.getUsername())){
                res.redirect("/account");
            }
        }
        req.session().attribute("username", inputtedUsername);
        res.redirect("/signup");
        return null;
        }, new VelocityTemplateEngine());

        get("/logout", (req, res) -> {
            req.session().removeAttribute("username");
            res.redirect("/shop");
            return null;
        }, new VelocityTemplateEngine());
    }

    public static String getLoggedInUserName(Request req, Response res) {
        String username = req.session().attribute("username");
        if (username == null || username.isEmpty()) {
            res.redirect("/login");
        }
        return username;
    }

}