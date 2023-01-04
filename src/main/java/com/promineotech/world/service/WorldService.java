package com.promineotech.world.service;

import java.util.List;
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
}
