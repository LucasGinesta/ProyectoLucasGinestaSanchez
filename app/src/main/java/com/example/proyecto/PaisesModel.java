package com.example.proyecto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Representa el modelo de datos de un país con detalles como nombre, región, población,
 * idiomas, monedas, etc.
 */
public class PaisesModel implements Serializable {

    private Name name;
    private String region;
    private List<String> continents; // Lista de continentes
    private int population;
    private Flags flags;
    private Map<String, String> languages;
    private Map<String, Demonyms> demonyms; // Demónimos por idioma (habitantes del país)
    private double area;
    private Map<String, Currency> currencies; // Monedas con detalles
    private List<Double> latlng; // Coordenadas geográficas [latitud, longitud]

    /**
     * Constructor para crear un objeto PaisesModel con los valores proporcionados.
     *
     * @param name       Nombre del país.
     * @param region     Región del país.
     * @param continents Lista de continentes a los que pertenece el país.
     * @param population Población del país.
     * @param flags      Banderas del país.
     * @param languages  Map de idiomas hablados en el país.
     * @param demonyms   Map de demónimos, diferenciados por género.
     * @param area       Área del país.
     * @param currencies Map de monedas y sus detalles.
     * @param latlng     Coordenadas geográficas (latitud y longitud).
     */
    public PaisesModel(Name name, String region, List<String> continents, int population, Flags flags,
                       Map<String, String> languages, Map<String, Demonyms> demonyms,
                       double area, Map<String, Currency> currencies, List<Double> latlng) {
        this.name = name;
        this.region = region;
        this.continents = continents;
        this.population = population;
        this.flags = flags;
        this.languages = languages;
        this.demonyms = demonyms;
        this.area = area;
        this.currencies = currencies;
        this.latlng = latlng;
    }

    // Getters y setters

    /**
     * Obtiene el nombre del país.
     *
     * @return Nombre del país.
     */
    public Name getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Obtiene la región del país.
     *
     * @return Región del país.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets region.
     *
     * @param region the region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Obtiene la lista de continentes a los que pertenece el país.
     *
     * @return Lista de continentes.
     */
    public List<String> getContinents() {
        return continents;
    }

    /**
     * Sets continents.
     *
     * @param continents the continents
     */
    public void setContinents(List<String> continents) {
        this.continents = continents;
    }

    /**
     * Obtiene la población del país.
     *
     * @return Población del país.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Sets population.
     *
     * @param population the population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Obtiene las banderas del país.
     *
     * @return Banderas del país.
     */
    public Flags getFlags() {
        return flags;
    }

    /**
     * Sets flags.
     *
     * @param flags the flags
     */
    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    /**
     * Obtiene los idiomas hablados en el país.
     *
     * @return Map de idiomas.
     */
    public Map<String, String> getLanguages() {
        return languages;
    }

    /**
     * Sets languages.
     *
     * @param languages the languages
     */
    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    /**
     * Obtiene los demónimos del país.
     *
     * @return Map de demónimos por idioma.
     */
    public Map<String, Demonyms> getDemonyms() {
        return demonyms;
    }

    /**
     * Sets demonyms.
     *
     * @param demonyms the demonyms
     */
    public void setDemonyms(Map<String, Demonyms> demonyms) {
        this.demonyms = demonyms;
    }

    /**
     * Obtiene el área del país.
     *
     * @return Área del país.
     */
    public double getArea() {
        return area;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Obtiene las monedas utilizadas en el país.
     *
     * @return Map de monedas.
     */
    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    /**
     * Sets currencies.
     *
     * @param currencies the currencies
     */
    public void setCurrencies(Map<String, Currency> currencies) {
        this.currencies = currencies;
    }

    /**
     * Obtiene las coordenadas geográficas del país (latitud y longitud).
     *
     * @return Lista con las coordenadas geográficas [latitud, longitud].
     */
    public List<Double> getLatlng() {
        return latlng;
    }

    /**
     * Sets latlng.
     *
     * @param latlng the latlng
     */
    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    /**
     * Representa una moneda con su nombre y símbolo.
     */
    public static class Currency {
        private String name;
        private String symbol;

        /**
         * Constructor para crear un objeto Currency con los valores proporcionados.
         *
         * @param name   Nombre de la moneda.
         * @param symbol Símbolo de la moneda.
         */
        public Currency(String name, String symbol) {
            this.name = name;
            this.symbol = symbol;
        }

        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Sets name.
         *
         * @param name the name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Gets symbol.
         *
         * @return the symbol
         */
        public String getSymbol() {
            return symbol;
        }

        /**
         * Sets symbol.
         *
         * @param symbol the symbol
         */
        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return "Currency{\n" +
                    "name='" + name + '\'' +
                    ", \nsymbol='" + symbol + '\'' +
                    '}';
        }
    }

    /**
     * Representa los demónimos de un país, con forma femenina y masculina.
     */
    public static class Demonyms {
        private String f; // Forma femenina
        private String m; // Forma masculina

        /**
         * Constructor para crear un objeto Demonyms con las formas femenina y masculina.
         *
         * @param f Forma femenina del demónimo.
         * @param m Forma masculina del demónimo.
         */
        public Demonyms(String f, String m) {
            this.f = f;
            this.m = m;
        }

        /**
         * Gets f.
         *
         * @return the f
         */
        public String getF() {
            return f;
        }

        /**
         * Sets f.
         *
         * @param f the f
         */
        public void setF(String f) {
            this.f = f;
        }

        /**
         * Gets m.
         *
         * @return the m
         */
        public String getM() {
            return m;
        }

        /**
         * Sets m.
         *
         * @param m the m
         */
        public void setM(String m) {
            this.m = m;
        }

        @Override
        public String toString() {
            return "Demonyms{\nf='" + f + "', \nm='" + m + "\n'}";
        }
    }

    /**
     * Representa el nombre del país, tanto común como oficial.
     */
    public static class Name {
        private String common;
        private String official;

        /**
         * Gets common.
         *
         * @return the common
         */
        public String getCommon() {
            return common;
        }

        /**
         * Gets official.
         *
         * @return the official
         */
        public String getOfficial() {
            return official;
        }

        /**
         * Sets common.
         *
         * @param common the common
         */
        public void setCommon(String common) {
            this.common = common;
        }

        /**
         * Sets official.
         *
         * @param official the official
         */
        public void setOfficial(String official) {
            this.official = official;
        }
    }

    /**
     * Representa la bandera de un país.
     */
    public static class Flags {
        private String png;

        /**
         * Gets png.
         *
         * @return the png
         */
        public String getPng() {
            return png;
        }

        /**
         * Sets png.
         *
         * @param png the png
         */
        public void setPng(String png) {
            this.png = png;
        }
    }
}
