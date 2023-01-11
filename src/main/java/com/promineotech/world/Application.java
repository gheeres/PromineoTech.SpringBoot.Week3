package com.promineotech.world;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.promineotech.world.models.CountryModel;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
//    CountryModel country = new CountryModel("", "");
//    country.setCode("USA");
//    country.setName("United States");
//        
//    country = new CountryModel("", "")
//                  .setCode("USA")
//                  .setName("United States");
  }
}
