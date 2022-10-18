package org.example;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesHandler {
    public static Properties loadProperties(String path) {
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(path);
            properties.load(input);
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
