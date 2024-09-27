package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    // ODO Task: pick appropriate instance variables for this class
    private final List<String> countries;
    private final Map<String, List<String>> countryLanguages;
    private final Map<String, Map<String, String>> translations;

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */

    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        countries = new ArrayList<>();
        countryLanguages = new HashMap<>();
        translations = new HashMap<>();
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject countryObject = jsonArray.getJSONObject(i);

                String countryCode = countryObject.getString("alpha3");
                countries.add(countryCode);

                JSONArray languages = countryObject.names();
                List<String> languagesList = new ArrayList<>();
                Map<String, String> translationMap = new HashMap<>();
                for (int j = 0; j < languages.length(); j++) {
                    String languageCode = languages.getString(j);
                    if (!languageCode.equals("alpha2") && !languageCode.equals("alpha3")
                            && !languageCode.equals("id")) {
                        languagesList.add(languageCode);
                        translationMap.put(languageCode, countryObject.getString(languageCode));
                    }
                }
                countryLanguages.put(countryCode, languagesList);
                translations.put(countryCode, translationMap);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        List<String> languages = countryLanguages.get(country);
        if (languages != null) {
            return new ArrayList<>(languages);
        }
        else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getCountries() {
        return new ArrayList<>(countries);
    }

    @Override
    public String translate(String country, String language) {
        Map<String, String> translationMap = translations.get(country);
        if (translationMap != null) {
            return translationMap.get(language);
        }
        else {
            return null;
        }
    }
}
