package com.neuedu.util;

import java.util.Random;

public class Code {
    private Code() {
    }
    public static String CodeUtil() {
        Random r = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            String c = String.valueOf(r.nextInt(10));
            stringBuffer.append(c);
        }
        System.out.println(stringBuffer);
        return stringBuffer.toString();
    }
}
