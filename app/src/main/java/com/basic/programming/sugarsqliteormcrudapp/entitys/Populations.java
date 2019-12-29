package com.basic.programming.sugarsqliteormcrudapp.entitys;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Unique;

public class Populations extends SugarRecord {

    @Unique
    @Column(name = "population_id")
    private Long populationId;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "country_population")
    private Double countryPopulation;
    @Column(name = "country_ranking")
    private Integer countryRanking;

    public Populations() {
    }

    public Populations(String countryName, Double countryPopulation, Integer countryRanking) {
        this.countryName = countryName;
        this.countryPopulation = countryPopulation;
        this.countryRanking = countryRanking;
    }

    public Long getPopulationId() {
        return populationId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(Double countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public Integer getCountryRanking() {
        return countryRanking;
    }

    public void setCountryRanking(Integer countryRanking) {
        this.countryRanking = countryRanking;
    }
}
