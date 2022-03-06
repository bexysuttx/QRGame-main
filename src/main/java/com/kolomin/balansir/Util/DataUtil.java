package com.kolomin.balansir.Util;

import com.kolomin.balansir.Configuration.ConfigHandler;

import java.util.UUID;

/**
 * @author macbook on 16.02.2022
 */
public final class DataUtil {
    public static String normalizeName(String name) {
        return  name.toLowerCase();
    }

    public static String generatePath(){
        StringBuilder pattern = new StringBuilder("/b/");
        String uuid= UUID.randomUUID().toString().replaceAll("-","").substring(0,8);
        pattern.append(uuid);
        return pattern.toString();
    }

}
