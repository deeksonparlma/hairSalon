import org.apache.log4j.BasicConfigurator;
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

        BasicConfigurator.configure();

        //index file//
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", request.session().attribute("stylists"));
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
            model.put("stylists", stylists.all());
            model.put("template", "public/templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        //to edit//


        get("/stylistForm", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/templates/stylistForm.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylistClientForm", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/stylists/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/templates/stylistForm.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylist", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", stylists.all());
            model.put("template", "public/templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/stylist/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            stylists stylist = stylists.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("clients", stylist.getClients());
            System.out.println("heyyy" +
                    "yyyy" +
                    "yyyy" +
                    "y" + stylist.getClients());
            model.put("template", "public/templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//        post("/stylists", (request,response) -> {
////            Map<String, Object> model = new HashMap<String, Object>();
////            String name = request.queryParams("name");
////            String email = request.queryParams("email");
////            String gender = request.queryParams("gender");
////            stylists stylist = new stylists(name,email,gender);
////            stylist.save();
////
////            model.put("template", "public/templates/stylist.vtl");
////            return new ModelAndView(model, layout);
////        }, new VelocityTemplateEngine());

        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String gender = request.queryParams("gender");
            stylists newStylist = new stylists(name,email,gender);
            newStylist.save();
            model.put("template", "public/templates/process.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            int stylistid = Integer.parseInt(request.queryParams("stylistid"));
            clients newClient = new clients(name,email,stylistid);
            newClient.save();
            model.put("template", "public/templates/process.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//        post("/clients", (request,response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       String clientName = request.queryParams("client-name");
//       stylists stylist = stylists.find(Integer.parseInt(request.queryParams("id")));
//       Client newClient = new Client(clientName, stylist.getId());
//       newClient.save();
//       model.put("template", "public/templates/process.vtl");
//            return new ModelAndView(model, layout);
//        }, new VelocityTemplateEngine() );

//        post("/stylists", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//
//            ArrayList<stylists> stylists = request.session().attribute("stylists");
//
//            if (stylists == null) {
//                stylists = new ArrayList<>();
//                request.session().attribute("stylists", stylists);
//            }
//
//            String name = request.queryParams("name");
//            String email = request.queryParams("email");
//            String gender = request.queryParams("gender");
//            stylists newStylist = new stylists(name,email,gender);
//            stylists.add(newStylist);
//
//            model.put("template", "public/templates/process.vtl");
//            return new ModelAndView(model, layout);
//        }, new VelocityTemplateEngine());
    }
}
