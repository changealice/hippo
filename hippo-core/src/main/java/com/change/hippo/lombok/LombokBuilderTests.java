package com.change.hippo.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * User: change.long
 * Date: 16/6/29
 * Time: 下午7:20
 */
public class LombokBuilderTests {


    public static void main(String[] args) throws IOException {
        User.UserBuilder builder = User.builder().id("123123");
        User user = new User("id");

        System.out.println(builder);
        System.out.println(user);
    }

    @Builder
    @ToString
    @EqualsAndHashCode
    @Slf4j
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String id;
    }

}
