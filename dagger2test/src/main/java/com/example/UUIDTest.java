package com.example;

import java.util.UUID;
import java.util.regex.Pattern;

public class UUIDTest {

    public static void main(String[] args) throws NumberFormatException {

        String regEx = "\\n{5}";
        String test ="12345";
        boolean matches = Pattern.matches(regEx, test);
        System.out.println("matches = " + matches);
        matches = Pattern.matches(regEx,test + "0");
        System.out.println("matches = " + matches);
        matches = test.matches(regEx);
        System.out.println("matches = " + matches);

    }

    private static void test() throws NumberFormatException {
        long deviceId = 0xabcdef;
        long simId    = 0x987654;
        UUID uuid = new UUID(deviceId, simId);
        System.out.println("uuid.toString() = " + uuid.toString());

        System.out.println("uuid.r1 = " + UUID.randomUUID());
        System.out.println("uuid.r2 = " + UUID.randomUUID());
        System.out.println("uuid.r3 = " + UUID.randomUUID());


        String s = "e3464bb46601738a";
        String up = s.toUpperCase();
        String num="";
        char c = up.charAt(0);

        if (c >= 'A'  & c <= 'F'){
            String substring = Integer.toBinaryString(c - 55).substring(1);
            int i = Integer.parseInt(substring,2);
            num = "-"+i+up.substring(1);
        }
        System.out.println("num = " + num);
        System.out.println("'A' = " + (int)'A');
        System.out.println("'0' = " + (int)'0');
        System.out.println("'1' = " + (int)'1');
        long test = parseLong(num, 16);
        System.out.println("Long.parseLong(\"e3464bb46601738a\",16) = " + test);
    }

    public static long parseLong(String s, int radix)
            throws NumberFormatException
    {
        if (s == null) {
            throw new NumberFormatException("null");
        }

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " less than Character.MIN_RADIX");
        }
        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " greater than Character.MAX_RADIX");
        }

        long result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        long limit = -Long.MAX_VALUE;
        long multmin;
        int digit;

        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Long.MIN_VALUE;
                } else if (firstChar != '+')
                    throw NumberFormatException.forInputString(s);

                if (len == 1) // Cannot have lone "+" or "-"
                    throw NumberFormatException.forInputString(s);
                i++;
            }
            multmin = limit / radix;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(i++),radix);
                if (digit < 0) {
                    throw NumberFormatException.forInputString(s);
                }
                if (result < multmin) {
                    throw NumberFormatException.forInputString(s);
                }
                result *= radix;
                if (result < limit + digit) {
                    throw NumberFormatException.forInputString(s);
                }
                result -= digit;
            }
        } else {
            throw NumberFormatException.forInputString(s);
        }
        return negative ? result : -result;
    }
    static class NumberFormatException extends Exception {

        public NumberFormatException(String message) {
            super(message);
        }

        static  NumberFormatException forInputString(String s) {
            return new NumberFormatException("For input string: \"" + s + "\"");
        }
    }
}
