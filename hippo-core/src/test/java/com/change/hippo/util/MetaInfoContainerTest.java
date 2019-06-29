package com.change.hippo.util;

import org.junit.Test;

public class MetaInfoContainerTest {


    @Test
    public void add() {
        final MetaInfoContainer<Object> container = new MetaInfoContainer<>();
        container.add(1);
        container.add(1);
        container.add(1);
        container.add(1);


    }
}