package com.promineotech.world.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CountryInputModel {
  private String code;
  private String name;
  private Integer population;
  
  public String getCode() {
    return code;
  }
  public CountryInputModel setCode(String code) {
    this.code = code;
    return this;
  }
  
  @JsonIgnore
  public String getCode2() {
    String code = getCode();
    if ((code != null) && (code.length() >= 2)) {
      return code.substring(0, 2);
    }
    return null;
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
   * Checks to see if the model is valid.
   * @return True if valid, false if otherwise.
   */
  @JsonIgnore
  public boolean isValid() {
    return (this.getCode() != "") &&
           (this.getName() != "");
  }
}
