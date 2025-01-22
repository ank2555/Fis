package com.Scenario1.Utils;



import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

import static org.hamcrest.Matchers.*;


public abstract class FrameworkUtility {

    protected static Properties properties;

    public static String readConfigurationFile(String key) {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH));

        } catch (Exception e) {
            System.out.println("Cannot find key: " + key + " in Config file due to exception : " + e);
        }
        return properties.getProperty(key).trim();
    }



}
