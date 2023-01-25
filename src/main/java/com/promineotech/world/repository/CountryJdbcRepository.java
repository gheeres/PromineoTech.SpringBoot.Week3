package com.promineotech.world.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.promineotech.world.models.CountryInputModel;
import com.promineotech.world.models.CountryModel;

/**
 * An implementation of the CountryRepository using MySQL/JDBC
 */
//@Component
@Repository
public class CountryJdbcRepository implements CountryRepository {
  private static final String DEFAULT_CONTINENT = "North America";
  
  //@Autowired
  private NamedParameterJdbcTemplate provider;
  private Environment env;
  
  public CountryJdbcRepository(NamedParameterJdbcTemplate provider, Environment env) {
    this.provider = provider;
    this.env = env;
  }

  /**
   * Converts a resultset into an instance of CountryModel
   * @param rs The resultset or current row
   * @param rowNum The current row number
   * @return The created CountryModel if valid, otherwise returns null.
   */
  private CountryModel toCountryModel(ResultSet rs, int rowNum) {
    try {
      CountryModel country = new CountryModel(rs.getString("country_code"), rs.getString("country_name"))
                                 .setPopulation(rs.getInt("country_population"));
      return country;
    } catch (SQLException e) {
      return null;
    }
  }
  
  @Override
  public Stream<CountryModel> getAllCountries() {
    String sql = "SELECT country_code,country_name,country_population "
               + "FROM country;";
    Map<String,Object> parameters = new HashMap<>();
    
    List<CountryModel> countries = provider.query(sql, parameters, (rs,rowNum) -> {
      CountryModel country = toCountryModel(rs,rowNum);
      return country;
    });
    
    return countries.stream();
  }

  @Override
  public Optional<CountryModel> getCountry(String code) {
    if ((code != null) && (! code.isEmpty())) {
      String sql = "SELECT country_code,country_name,country_population "
                 + "FROM country "
                 + "WHERE country_code = :country_code OR country_code2 = :country_code2;";
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("country_code", code);
      parameters.put("country_code2", code);

      List<CountryModel> countries = provider.query(sql,  parameters, (rs,rowNum) -> {
        return toCountryModel(rs, rowNum);
      });
      if (countries.size() >= 1) {
        return Optional.of(countries.get(0));
      }
    }
    return Optional.empty();
  }
  
  public Optional<CountryModel> createCountry(CountryInputModel input) {
    if ((input != null) && (input.isValid())) {
      String sql = "INSERT INTO country (country_code,country_code2,country_name,continent,country_population) "
                 + "VALUES (:country_code,:country_code2,:country_name,:continent,:country_population);";
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("country_code",  input.getCode());
      parameters.put("country_code2", input.getCode().substring(0, 2));
      parameters.put("country_name",  input.getName());
      parameters.put("continent", getDefaultContinent());
      parameters.put("country_population", input.getPopulation());
      
      int rows = provider.update(sql, parameters);
      if (rows == 1) {
        return getCountry(input.getCode());
      }
    }
    return Optional.empty();
  }

  /**
   * Gets the default continent to use if not specified.
   * @return
   */
  private String getDefaultContinent() {
    String continent = env.getProperty("app.defaults.continent", DEFAULT_CONTINENT);
    return continent;
  }

  @Override
  public Optional<CountryModel> deleteCountry(String code) {
    Optional<CountryModel> existing = getCountry(code);
    if (existing.isPresent()) {
      String sql = "DELETE FROM country WHERE country_code = :country_code;";
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("country_code", existing.get().getCode());
      
      int rows = provider.update(sql, parameters);
      if (rows == 1) {
        return existing;
      }
    }
    return Optional.empty();
  }
}
