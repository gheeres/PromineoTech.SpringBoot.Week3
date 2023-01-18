package com.promineotech.world.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import com.promineotech.world.models.CountryInputModel;
import com.promineotech.world.models.CountryModel;
import com.promineotech.world.repository.CountryRepository;

//@Component
@Service
public class DefaultWorldService implements WorldService {
  //@Autowired
  private CountryRepository countryRepository;
  
  public DefaultWorldService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @Override
  public List<CountryModel> getCountries() {
    Stream<CountryModel> countries = countryRepository.getAllCountries();
    return countries.toList();
  }

  @Override
  public CountryModel getCountry(String code) {
    if ((code != null) && (! code.isEmpty())) {
      Optional<CountryModel> country = countryRepository.getCountry(code);
      if (country.isPresent()) {
        return country.get();
      }
    }
    return null;
  }

  @Override
  public CountryModel createCountry(CountryInputModel input) {
    if ((input != null) && (input.isValid())) {
      Optional<CountryModel> country = countryRepository.createCountry(input);
      if (country.isPresent()) {
        return country.get();
      }
    }
    return null;
  }
}
