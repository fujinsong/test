package com.dtinone.utils;

import java.security.MessageDigest;

/**
 * ClassName: MD5
 * Description:
 * date: 2019/12/1 9:00
 * @author : 付 劲 松
 */
public class MD5 {
    private MD5() {
    }

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

            'A', 'B', 'C', 'D', 'E', 'F'};

    public static String toHexString(byte[] b) {

        //String to  byte

        StringBuilder sb = new StringBuilder(b.length * 2);

        for (int i = 0; i < b.length; i++) {

            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);

            sb.append(HEX_DIGITS[b[i] & 0x0f]);

        }

        return sb.toString();

    }

    public static String getMD5Str(String s) {

        try {

            // Create MD5 Hash

            MessageDigest digest = MessageDigest.getInstance("MD5");

            digest.update(s.getBytes("UTF-8"));

            byte messageDigest[] = digest.digest();

            return toHexString(messageDigest);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return "";
    }
}