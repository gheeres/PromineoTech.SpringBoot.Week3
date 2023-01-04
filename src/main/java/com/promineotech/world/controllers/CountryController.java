package com.promineotech.world.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.world.models.CountryModel;
import com.promineotech.world.service.WorldService;

//@Component
@RestController
public class CountryController {
  //@Autowired
  private WorldService service;
  
  public CountryController(WorldService service) {
    this.service = service;
  }
  
  @GetMapping(value = "/countries")
  public List<CountryModel> all() {
    // get all countries from service layer
    List<CountryModel> countries = service.getCountries();
    // return ...
    return countries;
  }
  
  //@RequestMapping(value = "/countries/{code}", method = RequestMethod.GET)
  @GetMapping(value = "/countries/{code}")
  public CountryModel get(@PathVariable String code) {
    // get country for service layer
    CountryModel country = service.getCountryByCode(code);
    // if country not found, return 404 (not found)
    if (country == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        String.format("The requested country does not exist. Country: %s", code));
    }
    // else return country
    return country;
  }
}
