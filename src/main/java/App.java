import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");

        //routes//
        String layout = "public/templates/layout.vtl";


        //index file//
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylist", request.session().attribute("stylist"));
            model.put("template", "public/templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //admin or owner//
        get("/admin", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/templates/admin.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //stylist//
        get("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylist", request.session().attribute("stylist"));
            model.put("template", "public/templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        //to edit//
        get("/stylist", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylist", request.session().attribute("stylist"));
            model.put("template", "public/templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylistForm", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylist", request.session().attribute("stylists"));
            model.put("template", "public/templates/stylistForm.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylistClientForm", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/templates/stylist-client-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/stylists/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylist", request.session().attribute("stylists"));
            model.put("template", "public/templates/stylistForm.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists", (request,response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            ArrayList<stylists> stylists = request.session().attribute("stylists");

            if (stylists == null) {
                stylists= new ArrayList<>();
                request.session().attribute("stylists", stylists);
            }
            String name = request.queryParams("name");
            String contact = request.queryParams("contact");
            String Email = request.queryParams("email");
            String Gender = request.queryParams("gender");
            stylists Stylist = new stylists(name,contact, Email,Gender);
            stylists.add(Stylist);

            model.put("template", "public/templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
