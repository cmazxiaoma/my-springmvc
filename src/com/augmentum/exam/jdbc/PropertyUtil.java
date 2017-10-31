package com.augmentum.exam.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private static Properties properties;

    static {
        InputStream is = PropertyUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
