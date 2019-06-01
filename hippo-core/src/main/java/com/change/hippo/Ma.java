package com.change.hippo;

import java.util.LinkedList;

public class Ma {

    public static void main(String[] args) {
        LinkedList<Object> list = new LinkedList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add(null);
        list.add(null);
        list.add(null);

        System.out.println(list);


        list.remove(null);
        System.out.println(list);
        list.remove("2");
        System.out.println(list);
    }
}
