package com.change.hippo.rpc;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午8:05
 */
public interface HelloService {


    public Person save(String name);

    class Person {
        public String name;
        public String password;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
