package com.promineotech.world.service;

import java.util.List;
import java.util.Optional;
import com.promineotech.world.models.CountryInputModel;
import com.promineotech.world.models.CountryModel;

/**
 * Service layer for interacting with components of the world.
 */
public interface WorldService {
  /**
   * Gets all available countries.
   * @return The countries if found, otherwise an empty list.
   */
  List<CountryModel> getCountries();

  /**
   * Gets a country by its unique id.
   * @param code The ISO5166 code for the country.
   * @return The country if found, otherwise null.
   */
  CountryModel getCountry(String code);
  
  /**
   * Creates a new country.
   * @param input The new country information.
   * @return The create country if successful, otherwise returns null.
   */
  CountryModel createCountry(CountryInputModel input);
}
