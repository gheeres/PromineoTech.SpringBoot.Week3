package com.promineotech.world.repository;

import java.util.List;
import com.promineotech.world.models.CountryModel;

public interface CountryRepository {
  /**
   * Retrieves all the countries of the world.
   * @return The countries.
   */
  List<CountryModel> getAllCountries();
}
