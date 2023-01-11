package com.promineotech.world.service;

import java.util.List;
import com.promineotech.world.models.CountryModel;

/**
 * Service layer for interacting with components of the world.
 */
public interface WorldService {
  /**
   * Gets all available countries.
   * @return The countries if found, otherwise and empty list.
   */
  List<CountryModel> getCountries();
}
