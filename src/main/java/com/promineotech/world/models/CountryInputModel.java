package com.promineotech.world.models;

/**
 * Information needed to create a new country.
 */
public class CountryInputModel {
  private String code;
  private String name;
  private Integer population;
  
  public CountryInputModel(String code, String name) {
    setCode(code);
    setName(name);
  }
  
  public String getCode() {
    return code;
  }
  public CountryInputModel setCode(String code) {
    this.code = code;
    return this;
  }
  
  public String getName() {
    return name;
  }
  public CountryInputModel setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getPopulation() {
    return population;
  }
  public CountryInputModel setPopulation(Integer population) {
    this.population = population;
    return this;
  }
  
  /**
   * Checks to see if the data is valid.
   * @return True if valid, false if otherwise.
   */
  public boolean isValid() {
    return getCode() != null && (! getCode().isEmpty()) &&
           getName() != null && (! getName().isEmpty());
  }
}
