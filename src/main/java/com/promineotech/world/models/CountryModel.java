package com.promineotech.world.models;

/**
 * Represents a country.
 */
public class CountryModel {
  private String code;
  private String name;
  private Integer population;

  public CountryModel(String code, String name) {
    setCode(code);
    setName(name);
  }
  
  public String getCode() {
    return code;
  }
  public CountryModel setCode(String code) {
    this.code = code;
    return this;
  }
  
  public String getName() {
    return name;
  }
  public CountryModel setName(String name) {
    this.name = name;
    return this;
  }
  
  public Integer getPopulation() {
    return population;
  }
  public CountryModel setPopulation(Integer population) {
    this.population = population;
    return this;
  }
}
