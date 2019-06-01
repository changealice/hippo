package com.change.hippo;

import java.util.Optional;

public class OptionalBasicExample {

    public static void main(String[] args) {
//        System.out.println("ofNullable on Non-Empty Optional: " + Optional.ofNullable(null));

        Integer i = 1;
        String str = "hello";
        StringBuffer sb = new StringBuffer("1213");
        Object[] obj = new Object[]{1};


        fun(i,str,sb,obj);
        System.out.println(i);
        System.out.println(str);
        System.out.println(sb);
        System.out.println(obj[0]);


    }

    private static void fun(Integer i, String str, StringBuffer sb, Object[] obj) {
        i = 2;
        str = "world";
        sb.append("1214");

        obj[0] = 2;
    }
}
