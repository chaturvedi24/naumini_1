package Utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private static Properties properties;

    public ConfigFileReader(String testRegion) {
        String propFilePath = "src\\test\\resources\\props\\config_"+testRegion+".properties";
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(propFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("File not found");
        }
    }


    public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }

}
