package com.promineotech.world.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import com.promineotech.world.models.CountryInputModel;
import com.promineotech.world.models.CountryModel;
import com.promineotech.world.repository.CountryRepository;

//@Component
@Service
public class DefaultWorldService implements WorldService {
  //@Autowired
  private CountryRepository repository;
  
  public DefaultWorldService(CountryRepository repository) {
    this.repository = repository;
  }
  
  @Override
  public List<CountryModel> getCountries() {
    Stream<CountryModel> countries = repository.all();
    // C# LINQ == Java Streams
    return countries.collect(Collectors.toList());
  }

  @Override
  public CountryModel getCountryByCode(String code) {
    Optional<CountryModel> model = repository.get(code);
    if (model.isPresent()) {
      return model.get();
    }
    return null;
  }

  @Override
  public CountryModel addCountry(CountryInputModel input) {
    if ((input != null) && (input.isValid())) {
      Optional<CountryModel> optional = repository.add(input);
      if (optional.isPresent()) {
        return optional.get();
      }
    }
    return null;
  }

  @Override
  public CountryModel deleteCountry(String code) {
    if (code != null) {
      Optional<CountryModel> optional = repository.delete(code);
      if (optional.isPresent()) {
        return optional.get();
      }
    }
    return null;
  }
}
