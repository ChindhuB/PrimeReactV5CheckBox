/*@author Chindhu Babu*/
/* Class to  Load Property File */
package config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigProperty {
    private static Properties properties= loadFromPropertiesFile();
    public static Properties getInstance() {
        return properties;
    }
    private static Properties loadFromPropertiesFile() {

        Properties properties = new Properties();

        try {
            String configPropertiesPath = /*configParameters.getRelativePath()+*/
                    PropertiesPath();
            properties.load(new FileInputStream(configPropertiesPath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception while loading the configProperties file");
        }

        return properties;
    }
    public static String PropertiesPath(){
        return System.getProperty("user.dir")+
                "//src//test//resources//config.properties";
    }
}
