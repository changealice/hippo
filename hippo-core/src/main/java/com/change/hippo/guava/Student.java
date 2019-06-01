package com.change.hippo.guava;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private int id;
    private String name;
    private int age;
}
