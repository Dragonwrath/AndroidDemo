package com.example.boundary;


import java.nio.charset.Charset;
import java.util.Random;


class Boundary {

    public static void main(String[] args) {
        Boundary boundary = new Boundary(null);
        byte[] startingBoundary = boundary.getStartingBoundary();
        for (byte b : startingBoundary) {
            System.out.print(" " + b);
        }
        System.out.println();

        byte[] closingBoundary = boundary.getClosingBoundary();
        for (byte b : closingBoundary) {
            System.out.print(" " + b);
        }

    }
    private final static char[] MULTIPART_CHARS =
            "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();   //$NON-NLS-1$

    private final String boundary;
    private final byte[] startingBoundary;
    private final byte[] closingBoundary;

    Boundary(String boundary) {
        boundary = generateBoundary();
        this.boundary = boundary;
        final String starting = "--" + boundary +"\r\n";         //$NON-NLS-1$
        final String closing  = "--" + boundary + "--" +"\r\n";  //$NON-NLS-1$

        startingBoundary = starting.getBytes(Charset.forName("US-ASCII"));
        closingBoundary  = closing.getBytes(Charset.forName("US-ASCII"));
    }


    /* package */ String getBoundary() {
        return boundary;
    }

    /* package */ byte[] getStartingBoundary() {
        return startingBoundary;
    }

    /* package */ byte[] getClosingBoundary() {
        return closingBoundary;
    }

    private static String generateBoundary() {
        Random random = new Random();
        final int count = random.nextInt(11) + 30;
        StringBuilder buffer = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            buffer.append(MULTIPART_CHARS[random.nextInt(MULTIPART_CHARS.length)]);
        }
        System.out.println("buffer = " + buffer.toString());
        return buffer.toString();
    }
}
