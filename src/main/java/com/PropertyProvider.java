package com;

import java.util.ResourceBundle;

public class PropertyProvider {
    private static String CONFIG_PATH = "config";
    private static ResourceBundle bundle = ResourceBundle.getBundle(CONFIG_PATH);
    public static String getProperty(String propertyName) {
        return bundle.getString(propertyName);
    }
}

