package com.example;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyClass {
    public static final String REGEX_CN_PHONE = "1[34578]\\d{9}";

    public static final String REX_PASSWORD = "[a-zA-Z]\\w{7,11}";

    public static final String REGEX_ACCOUNT = "[\\u4E00-\\u9FA5|a-zA-Z][\\u4E00-\\u9FA5|\\w]{5,11}";

    public static final String account =  "[\\w\u4E00-\u9FA5_]+";

    public static void main(String[] args) throws UnsupportedEncodingException {
			List<String> list = new ArrayList<>();
			for(int i = 0;i < 10;i++) {
				list.add(String.valueOf(i));
			}
			final String[] a = new String[13];
			final String[] strings = list.toArray(a);
			System.out.println("strings = " + Arrays.toString(strings));
			//        String s = "16381279189";
//        boolean matches = s.matches(REGEX_CN_PHONE);
//        System.out.println("matches = " + matches);
//
//        String s1 = new String("中国CCTV".getBytes(), "gbk");
//        boolean b = s1.matches(account);
//        System.out.println("b = " + b);
//
//        String s2 = "中国CCTV";
//        b = s2.matches(account);
//        System.out.println("b = " + b);
    }
}
