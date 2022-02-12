package com.kolomin.balansir.Util;

import java.util.Random;

public final class PasswordUtils {

    public static String generatePassword(String template,int length) {
        char[] pass= template.trim().toCharArray();
        Random r= new Random();
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<length;i++) {
            builder.append(pass[r.nextInt(pass.length)]);
        }
        return builder.toString();
    }
}
