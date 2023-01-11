package com.promineotech.world.service;

import java.util.List;
import com.promineotech.world.models.CountryInputModel;
import com.promineotech.world.models.CountryModel;

public interface WorldService {
  /**
   * Gets all of the countries.
   * @return The countries.
   */
  List<CountryModel> getCountries();
  
  /**
   * Retries a country by it's unique identifier.
   * @param code The unique ISO-3 designation.,
   * @return The country if found, otherwise returns null;
   */
  CountryModel getCountryByCode(String code);
  
  /**
   * Adds or creates a country with the specified values
   * @param input The country information.
   * @return The created country if successful, otherwise null.
   */
  CountryModel addCountry(CountryInputModel input);
  
  /**
   * Removes or deletes a country by its unique id.
   * @param code The unique ISO-3166 designation.,
   * @return The deleted country if successful, otherwise null.
   */
  CountryModel deleteCountry(String code);
}
