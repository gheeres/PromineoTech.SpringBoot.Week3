package com.promineotech.world.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
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

@Tag(name = "Countries")
@OpenAPIDefinition(info = @Info(title = "Countries: Where we from..."))
@RestController
public class CountryController {
  //@Autowired
  private WorldService service;
  
  public CountryController(WorldService service) {
    this.service = service;
  }
  
  @Operation(summary = "Gets all the available countries")
  @GetMapping(value = "/countries")
  //@RequestMapping(value = "/countries", method = RequestMethod.GET)
  public List<CountryModel> all() {
     List<CountryModel> countries = service.getCountries();
     return countries;
  }
  
  @Operation(summary = "Gets a country by its unique id.")
  @GetMapping(value = "/countries/{code}")
  public CountryModel get(@PathVariable String code) {
    if ((code != null) && (! code.isEmpty())) {
      CountryModel country = service.getCountry(code);
      if (country != null) {
        return country;
      }
      
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                        String.format("Country (%s) not found.", code));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code not provided.");
  }
  
  @Operation(summary = "Creates a new country")
  //@RequestMapping(value = "/countries", method = RequestMethod.POST)
  @PostMapping(value = "/countries")
  public CountryModel create(@RequestBody CountryInputModel input) {
    if ((input != null) && (input.isValid())) {
      CountryModel country = service.createCountry(input);
      if (country != null) {
        return country;
      }
      
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unhandled exception occured. Country not created.");
    }

    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid country. Fields missing.");
  }
}
