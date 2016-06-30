package com.change.hippo.lombok;

/**
 * User: change.long
 * Date: 16/6/29
 * Time: 下午7:09
 */
public class LombokTests {

    public static void main(String[] args) {
        People people = new People();
        people.setId("1");
        people.setName("1name");
        System.out.println(people);
    }

}
