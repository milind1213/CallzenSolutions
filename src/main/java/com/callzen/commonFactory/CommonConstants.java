package com.callzen.commonFactory;
import com.callzen.utility.PropertyFileReader;
import org.testng.annotations.BeforeSuite;

public final class CommonConstants {
    public static String BROWSER;
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;
    public static boolean RUN_MODE;
    public static PropertyFileReader Config = new PropertyFileReader();

    @BeforeSuite(alwaysRun = true)
    public static PropertyFileReader loadConfigProperties() {
        Config = new PropertyFileReader();
        BROWSER = Config.prop("config").getProperty("browserName");
        URL = Config.prop("config").getProperty("url");
        USERNAME = Config.prop("config").getProperty("userName");
        PASSWORD = Config.prop("config").getProperty("password");
        RUN_MODE = Boolean.parseBoolean(Config.prop("config").getProperty("headless"));
        return Config;
    }

}
