package service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * configure the web services and properties
 */

@ApplicationPath("/resource")

public class Config extends Application {
    private static final String PROPERTIES_PATH = "/home/bzz/webapp/turnierverwaltung.properties";
    private static Properties properties = null;
    /**
     * define all provider classes
     *
     * @return set of classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet providers = new HashSet<Class<?>>();
        providers.add(TeamService.class);
        providers.add(TestService.class);
        providers.add(TournamentService.class);
        return providers;
    }

    public static void setProperties(Properties properties) {
        Config.properties = properties;
    }

    public static String getProperty(String property) {
        if (Config.properties ==null) {
            setProperties(new Properties());
            readProperties();
        }
        String value = Config.properties.getProperty(property);
        if (value==null) return "";
        return value;
    }

    private static void readProperties(){
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(PROPERTIES_PATH);
            properties.load(inputStream);
            if (inputStream != null) inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }

    }

}