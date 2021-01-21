package com.example.yunusermanager.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {



    public String getPropertiesKeyValue(String cfgPath,String cfgKey){
        Properties properties=new Properties();
        InputStream inputStream= PropertiesUtil.class.getClassLoader().getResourceAsStream(cfgPath);
        String property = null;
        try {
            properties.load(inputStream);
             property = properties.getProperty(cfgKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
