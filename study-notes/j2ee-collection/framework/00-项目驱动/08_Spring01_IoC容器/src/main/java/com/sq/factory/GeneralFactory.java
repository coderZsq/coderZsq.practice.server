package com.sq.factory;

import com.sq.dao.PersonDao;
import com.sq.service.PersonService;

import java.io.InputStream;
import java.util.Properties;

public class GeneralFactory {
    private static Properties properties;
    static {
        try (InputStream is = GeneralFactory.class.getClassLoader().getResourceAsStream("factory.properties")){
            properties = new Properties();
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T get(String name) {
        try {
            // 类名
            String clsName = properties.getProperty(name);
            Class cls = Class.forName(clsName);
            // 实例化
            return (T) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // public static PersonService getService() {
    //     return get("service");
    // }
    //
    // public static PersonDao getDao() {
    //     return get("dao");
    // }
}
