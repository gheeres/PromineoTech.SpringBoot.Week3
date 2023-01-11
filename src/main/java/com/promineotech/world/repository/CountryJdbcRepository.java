package com.promineotech.world.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.promineotech.world.models.CountryModel;

/**
 * An implementation of the CountryRepository using MySQL/JDBC
 */
//@Component
@Repository
public class CountryJdbcRepository implements CountryRepository {
  //@Autowired
  private NamedParameterJdbcTemplate provider;
  
  public CountryJdbcRepository(NamedParameterJdbcTemplate provider) {
    this.provider = provider;
  }

  @Override
  public List<CountryModel> getAllCountries() {
    List<CountryModel> countries = new ArrayList<CountryModel>();
    countries.add(new CountryModel("USA", "United States")
                      .setPopulation(278357000));
    return countries;
  }
}
