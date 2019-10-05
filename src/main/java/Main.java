import classes.Usuario;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;

import static spark.Spark.*;

public class Main {

     private static Usuario log = null;


    public static void main(String[] args) {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        configuration.setClassForTemplateLoading(Main.class, "/");



        Usuario usuario = new Usuario("valido","123");
        staticFiles.location("/public");

        before("/",(request, response) -> {
            System.out.println(log);
            if(log == null){
                response.redirect("/login");
            }
        });



        get("/login", (request, response) -> {
            Template template = configuration.getTemplate("public/test.ftl");
            StringWriter writer = new StringWriter();
            template.process(null,writer);

            return  writer;
        });


        get("/", (request, response) -> {
            Template template = configuration.getTemplate("public/index.ftl");
            StringWriter writer = new StringWriter();
            template.process(null,writer);

            return  writer;
        });

        post("/login",(request, response) -> {

            String u = request.queryParams("user");
            String pass = request.queryParams("password");



            System.out.println(u);
            System.out.println(pass);


            if(u.equals(usuario.getUsuario()) && pass.equals(usuario.getContrasena())){
                log = new Usuario(u,pass);
                response.redirect("/");

            }
            else
                response.redirect("/login");


            return null;
        });



    }
}
