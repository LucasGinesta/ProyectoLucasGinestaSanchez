package com.example.proyecto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PaisesModel implements Serializable {

    private Name name;
    private String region;
    private String continent;
    private int population;
    private Flags flags;
    private String mapUrl;
    private Map<String, String> languages;
    private List<String> timezones;
    private Map<String, Demonym> demonyms; //Demónimos (habitantes del país)
    private boolean landlocked; //Si el país es sin salida al mar
    private double area;
    private Map<String, Currency> currencies; //Monedas
    private List<Double> latlng; //Coordenadas geográficas [latitud, longitud]

    // Constructor actualizado
    public PaisesModel(Name name, String region, String continent, int population, Flags flags, String mapUrl,
                       Map<String, String> languages, List<String> timezones, Map<String, Demonym> demonyms,
                       boolean landlocked, double area, Map<String, Currency> currencies, List<Double> latlng) {
        this.name = name;
        this.region = region;
        this.continent = continent;
        this.population = population;
        this.flags = flags;
        this.mapUrl = mapUrl;
        this.languages = languages;
        this.timezones = timezones;
        this.demonyms = demonyms;
        this.landlocked = landlocked;
        this.area = area;
        this.currencies = currencies;
        this.latlng = latlng;
    }

    // Getters y setters
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public Map<String, Demonym> getDemonyms() {
        return demonyms;
    }

    public void setDemonyms(Map<String, Demonym> demonyms) {
        this.demonyms = demonyms;
    }

    public boolean isLandlocked() {
        return landlocked;
    }

    public void setLandlocked(boolean landlocked) {
        this.landlocked = landlocked;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    // Clases internas
    public static class Currency {
        private String name;
        private String symbol;

        public Currency(String name, String symbol) {
            this.name = name;
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }

    public static class Demonym {
        private String male;
        private String female;

        public String getMale() {
            return male;
        }

        public String getFemale() {
            return female;
        }

        public void setMale(String male) {
            this.male = male;
        }

        public void setFemale(String female) {
            this.female = female;
        }
    }

    public static class Name {
        private String common;
        private String official;

        public String getCommon() {
            return common;
        }

        public String getOfficial() {
            return official;
        }

        public void setCommon(String common) {
            this.common = common;
        }

        public void setOfficial(String official) {
            this.official = official;
        }
    }

    public static class Flags {
        private String png;

        public String getPng() {
            return png;
        }

        public void setPng(String png) {
            this.png = png;
        }
    }
}
