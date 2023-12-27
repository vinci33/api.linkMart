package com.linkmart;

import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Linkmart API",
                version = "1.0",
                description = """
                        Explore the world of shopping with our groundbreaking social e-commerce platform, a gateway to global access that redefines international shopping. We connect you with personal shoppers worldwide, making it effortless to acquire exclusive items from any corner of the globe. Whether seeking something unique from afar or rare commodities not available locally, our network of shoppers is ready to fulfill your every request.
                        
                        Embrace a shopping experience that transcends borders, uniting people and markets across continents.
                        
                        Our platform isn't just about transactions; it's about building a global community where distances fade and accessibility reigns. Get ready for an unparalleled shopping adventure that brings the world’s treasures directly to your doorstep!
                        
                        Our Frontend Members:
                        - Elain Chiu  \n ytelainechiu@gmail.com
                        
                        - Fredy Cheung  \n e.kuquer@gmail.com
                        
                        Our Backend Members:
                        - Nicolas Lo  \n nicolaloht@gmail.com
                        
                        - Kenneth D Lai  \n kennethdouglasslai@gmail.com
                        
                        Please check out our site: 
                        
                        \n https://linkmart.yt20chill.me
                        
                        Documentation Linkmart API v1.0
                        """
        )

)
public class Main {
    public static void main(String[] args) {
            SpringApplication.run(Main.class,args);
        System.out.println(org.hibernate.Version.getVersionString());
    }
}