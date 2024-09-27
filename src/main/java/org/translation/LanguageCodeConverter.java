package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting language codes to their names.
 */
public class LanguageCodeConverter {

    // ODO Task: pick appropriate instance variables to store the data necessary for this class
    private Map<String, String> languageMap;
    private int numLanguages;
    /**
     * Default constructor which will load the language codes from "language-codes.txt"
     * in the resources folder.
     */

    public LanguageCodeConverter() {
        this("language-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the language code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public LanguageCodeConverter(String filename) {
        languageMap = new HashMap<>();
        numLanguages = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            // ODO Task: use lines to populate the instance variable
            //           tip: you might find it convenient to create an iterator using lines.iterator()
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String code = parts[0].trim();
                    String language = parts[1].trim();
                    languageMap.put(code, language);
                    numLanguages++;
                }
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the language for the given language code.
     * @param code the language code
     * @return the name of the language corresponding to the code
     */
    public String fromLanguageCode(String code) {
        return languageMap.getOrDefault(code, "Unknown language code");
    }

    /**
     * Returns the code of the language for the given language name.
     * @param language the name of the language
     * @return the 2-letter code of the language
     */
    public String fromLanguage(String language) {
        for (Map.Entry<String, String> entry : languageMap.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(language)) {
                return entry.getKey();
            }
        }
        return "Unknown language";
    }

    /**
     * Returns how many languages are included in this code converter.
     * @return how many languages are included in this code converter.
     */
    public int getNumLanguages() {
        return numLanguages;
    }
}
