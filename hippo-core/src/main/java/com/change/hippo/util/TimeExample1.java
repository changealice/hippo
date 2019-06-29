package com.change.hippo.util;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class TimeExample1 {

    @Test
    public void test() {
        final ZonedDateTime now = ZonedDateTime.now();

        System.out.println(Timestamp.valueOf(now.toLocalDateTime()));
        System.out.println(Timestamp.from(now.toInstant()));
    }
}
