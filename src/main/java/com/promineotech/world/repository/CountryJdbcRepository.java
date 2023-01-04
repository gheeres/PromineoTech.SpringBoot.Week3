package com.promineotech.world.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.promineotech.world.models.CountryModel;

@Repository
public class CountryJdbcRepository implements CountryRepository {
  //@Autowired
  private NamedParameterJdbcTemplate provider;
  
  public CountryJdbcRepository(NamedParameterJdbcTemplate provider) {
    this.provider = provider;
  }
  
  /**
   * Serializes a ResultSet to an instance of CountryModel
   * @param rs The ResultSet
   * @param rowNum The current row number
   * @return The instance of CountryModel
   */
  private CountryModel toCountryModel(ResultSet rs, int rowNum) {
    CountryModel country = null;
    try {
      country = new CountryModel(rs.getString("country_code"),
                                 rs.getString("country_name"))
                    .setPopulation(rs.getInt("country_population"));
    } catch (SQLException e) {
    }
    return country;
  }
  
  @Override
  public Optional<CountryModel> get(String code) {
    String sql = "SELECT country_code, country_name, country_population "
               + " FROM country WHERE country_code = :country_code";
    Map<String,Object> parameters = new HashMap<>();
    parameters.put("country_code", code);
    
    List<CountryModel> countries = provider.query(sql, parameters, (rs,rowNum) -> {
      return toCountryModel(rs, rowNum);
    });
    
    if (countries.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(countries.get(0));
  }

  @Override
  public Stream<CountryModel> all() {
    String sql = "SELECT country_code, country_name, country_population "
               + " FROM country";
    Map<String,Object> parameters = new HashMap<>();

    List<CountryModel> countries = provider.query(sql, parameters, (rs,rowNum) -> {
      return toCountryModel(rs,rowNum);
    });

    return countries.stream();
  }
}
