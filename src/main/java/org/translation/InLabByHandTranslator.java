package org.translation;

import java.util.ArrayList;
import java.util.List;

// ODO Task: modify this class so that it also supports the Spanish language code "es" and
//            one more language code of your choice. Each member of your group should add
//            support for one additional langauge code on a branch; then push and create a pull request on GitHub.

// Extra Task: if your group has extra time, you can add support for another country code in this class.

/**
 * An implementation of the Translator interface which translates
 * the country code "can" to several languages.
 */
public class InLabByHandTranslator implements Translator {
    /**
     * Returns the language abbreviations for all languages whose translations are
     * available for the given country.
     *
     * @param country the country
     * @return list of language abbreviations which are available for this country
     */

    public static final String CANADA = "can";
    public static final String ENGLISH = "en";
    public static final String GERMAN = "de";
    public static final String CHINESE = "zh";
    public static final String SPANISH = "es";
    public static final String FRENCH = "fr";

    @Override
    public List<String> getCountryLanguages(String country) {
        if (CANADA.equals(country)) {
            return new ArrayList<>(List.of(GERMAN, ENGLISH, CHINESE, SPANISH, FRENCH));
        }
        return new ArrayList<>();
    }

    /**
     * Returns the country abbreviations for all countries whose translations are
     * available from this Translator.
     *
     * @return list of country abbreviations for which we have translations available
     */
    @Override
    public List<String> getCountries() {
        return new ArrayList<>(List.of(CANADA));
    }

    /**
     * Returns the name of the country based on the specified country abbreviation and language abbreviation.
     *
     * @param country  the country
     * @param language the language
     * @return the name of the country in the given language or null if no translation is available
     */
    @Override
    public String translate(String country, String language) {
        if (!CANADA.equals(country)) {
            return null;
        }
        String translation = null;

        // Assign the appropriate translation based on the language
        switch (language) {
            case GERMAN:
                translation = "Kanada";
                break;
            case ENGLISH:
            case FRENCH:
                translation = "Canada";
                break;
            case CHINESE:
                translation = "加拿大";
                break;
            case SPANISH:
                translation = "Canadá";
                break;
            default:
                translation = null;
        }
        return translation;
    }
}
