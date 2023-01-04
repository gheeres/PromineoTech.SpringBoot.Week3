package com.promineotech.world.repository;

import java.util.Optional;
import java.util.stream.Stream;
import com.promineotech.world.models.CountryModel;

public interface CountryRepository {
  /**
   * Retrieves the country with the specified id.
   * @param code The unique id of the country.
   * @return The country if found, otherwise returns a null/empty Optional.
   */
  Optional<CountryModel> get(String code);
  
  /**
   * Returns all of the available countries.
   * @return The collection of countries.
   */
  Stream<CountryModel> all();
}
