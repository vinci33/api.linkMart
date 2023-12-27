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
                        - **Elaine Chiu**  \n ***ytelainechiu@gmail.com***
                        
                        - **Fredy Cheung**  \n ***e.kuquer@gmail.com***
                        
                        Our Backend Members:
                        - **Nicolas Lo**  \n ***nicolaloht@gmail.com***
                        
                        - **Kenneth D Lai**  \n ***kennethdouglasslai@gmail.com***
                        
                        Please check out our site:
                        
                        \n ***https://linkmart.yt20chill.me***
                        
                        Documentation Linkmart API v1.0
                        
                        For Testing:
                        
                        JWT Token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInVzZXJJZCI6IjAxSEhaWjAxNVNWWlZESDRYVFpDWUNKTVcwIiwiaWF0IjoxNzAyOTUzODQ5LCJleHAiOjE3MDQyNjk5ODR9.HyEZdN1_402M8UlRmZjXnVI7tIMdNcHp9EibLihk-Qo
                        
                        User Id: 01HHZZ015SVZVDH4XTZCYCJMW0
                        
                        Request Id: 01HJ3HK0SBBW80KTCAV2JKPPG0
                        
                        Offer Id: 01HJ5JXT718C04ZFCTPN8HKW0M
                        """
        )

)
public class Main {
    public static void main(String[] args) {
            SpringApplication.run(Main.class,args);
        System.out.println(org.hibernate.Version.getVersionString());
    }
}