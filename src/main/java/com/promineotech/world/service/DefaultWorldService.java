package com.promineotech.world.service;

import java.util.List;
import org.springframework.stereotype.Service;
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
    return countryRepository.getAllCountries();
  }
}
