import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");

        //port manager//
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
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

//        post("/stylists", (request,response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            String name = request.queryParams("name");
//            stylists stylist = new stylists(name);new stylists(name);
//            stylist.save();
//
//            model.put("template", "public/templates/stylist.vtl");
//            return new ModelAndView(model, layout);
//        }, new VelocityTemplateEngine());
        post("/stylists", (request,response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            ArrayList<stylists> stylists= request.session().attribute("stylists");
            String name = request.queryParams("name");
            stylists stylist = new stylists(name);
            stylist.save();
//            stylists.add(stylist);


            model.put("template", "public/templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}
