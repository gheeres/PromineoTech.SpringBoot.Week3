package com.promineotech.world.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.world.models.CountryModel;
import com.promineotech.world.service.WorldService;

@RestController
public class CountryController {
  //@Autowired
  private WorldService service;
  
  public CountryController(WorldService service) {
    this.service = service;
  }
  
  @GetMapping(value = "/countries")
  //@RequestMapping(value = "/countries", method = RequestMethod.GET)
  public List<CountryModel> all() {
     List<CountryModel> countries = service.getCountries();
     return countries;
  }
}
