package co.id.mii.serverside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
public class ServersideApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServersideApplication.class, args);
        System.out.println("Server Running");
    }
}
