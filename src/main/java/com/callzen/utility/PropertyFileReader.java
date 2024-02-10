package com.callzen.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
    private Properties prop;
    private FileInputStream fis;
    public Properties prop(String str) {
        prop = new Properties();
        try {
            fis = new FileInputStream(
                    System.getProperty("user.dir") + "/resource/ConfigDirectory/" + str + ".properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
