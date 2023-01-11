package com.promineotech.world.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.world.models.CountryInputModel;
import com.promineotech.world.models.CountryModel;
import com.promineotech.world.service.WorldService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

//@Component
@RestController
@Tag(name="Countries")
@OpenAPIDefinition(info = @Info(title = "Countries around the world..."))
public class CountryController {
  //@Autowired
  private WorldService service;
  
  public CountryController(WorldService service) {
    this.service = service;
  }
  
  @Operation(summary = "Gets all of the available countries.")
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
  
  // CRUD -> Create = POST
  //         Update = PUT
  @PostMapping(value = "/countries")
  public CountryModel add(@RequestBody CountryInputModel input) {
    if ((input != null) && (input.isValid())) {
      CountryModel result = service.addCountry(input);
      if (result != null) {
        return result;
      }
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                   String.format("An unhandled expection occured. Oops"));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                      String.format("No input or missing required fields."));
  }

  @GetMapping(value = "/countries/error")
  public String error() {
    try {
      throw new RuntimeException("Some generic error I don't know about...");
    }
    catch (Exception e) {
      //throw new ResponseStatusException(HttpStatus.OK, "This is not OK...");
      return "This is not OK...";
    }
  }
  
  @DeleteMapping(value = "/countries/{code}")
  public CountryModel delete(@PathVariable String code) {
    if (code != null) {
      // Does country exist?
      CountryModel existing = service.getCountryByCode(code);
      if (existing != null) {
        // If so, try to delete it.
        CountryModel deleted = service.deleteCountry(code);
        if (deleted != null) {
          return deleted;
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                          String.format("An unhandled expection occured. Oops"));
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Country not found."));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Country code required."));
  }
}
