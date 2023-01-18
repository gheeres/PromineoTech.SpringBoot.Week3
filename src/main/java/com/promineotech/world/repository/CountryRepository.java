package com.promineotech.world.repository;

import java.util.Optional;
import java.util.stream.Stream;
import com.promineotech.world.models.CountryInputModel;
import com.promineotech.world.models.CountryModel;

public interface CountryRepository {
  /**
   * Retrieves all the countries of the world.
   * @return The countries.
   */
  Stream<CountryModel> getAllCountries();

  /**
   * Gets a country by it's unique id.
   * @param code The ISO 3166 code of the country.
   * @return The country if found, otherwise and empty optional.
   */
  Optional<CountryModel> getCountry(String code);
  
  /**
   * Creates a new country.
   * @param input The new country information.
   * @return The create country if successful, otherwise an empty optional.
   */
  Optional<CountryModel> createCountry(CountryInputModel input);
}
