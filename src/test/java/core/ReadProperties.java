package core;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private static ReadProperties instance;
    private final Properties properties;

    private ReadProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ReadProperties getInstance() {
        if (instance == null) {
            instance = new ReadProperties();
        }
        return instance;
    }

    public String getUsername(){
        return properties.getProperty("username");
    }
    public String getPassword(){
        return properties.getProperty("password");
    }
    public String getBrowserName() {
        return properties.getProperty("browser");
    }
    public int getTimeOut() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}
