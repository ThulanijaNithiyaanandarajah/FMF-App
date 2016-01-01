package com.example.user.autocomplete;

/**
 * Created by USER on 12/31/2015.
 */
public class WorldPopulation {

    private String iata ="";

    private String label="";

    private String alias_s="";

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getAlias_s() {
        return alias_s;

    }

    public void setAlias_s(String alias_s) {
        this.alias_s = alias_s;
    }

    public String getIata() {
        return iata;

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setIata(String iata) {

        this.iata = iata;
    }

    private String country="";


    private String city="";

    public WorldPopulation()

    {

    }
/*
    public void setRank(String _rank)

    {

        this.rank=_rank;

    }

    public void setCountry(String _country)

    {

        this.country=_country;

    }

    public void setPopulation(String _population)

    {

        this.population=_population;

    }

    public void setFlag(String _flag)

    {

        this.flag=_flag;

    }

    public String getRank()

    {

        return this.rank;

    }

    public String getCountry()

    {

        return this.country;

    }

    public String getPopulation()

    {

        return this.population;

    }

    public String getFlag()

    {

        return this.flag;

    }
*/
}