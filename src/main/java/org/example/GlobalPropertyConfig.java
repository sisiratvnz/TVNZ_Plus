package org.example;

import java.util.Properties;

public class GlobalPropertyConfig {
    protected static String url = null;
    public static Properties getGlobalProperties() {
        return PropertiesHandler.loadProperties("src/main/resources/global.properties");
    }
    public static String getURL(){
        switch (getGlobalProperties().getProperty("env")){
            case "prod":
                url = getGlobalProperties().getProperty("produrl");
                break;
            case "stage":
                url = getGlobalProperties().getProperty("stageurl");
                break;
            case "test":
                url = getGlobalProperties().getProperty("testurl");
                break;
            case "dev":
                url = getGlobalProperties().getProperty("devurl");
                break;
            default:
                url = getGlobalProperties().getProperty("localhost");
        }
        return  url;
    }
}
