package com.change.hippo.util;

import java.util.ArrayList;
import java.util.List;

public class MetaInfoContainer<T> {

    private List<T> items;

    public MetaInfoContainer() {
        items = new ArrayList<>();
    }


    public MetaInfoContainer add(T t) {
        this.items.add(t);
        return this;
    }
}
