package com.change.hippo.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.Map;

public class StringTests {

    public static void main(String[] args) {
        boolean isNullOrEmpty = Strings.isNullOrEmpty("");
        System.out.println("input " + (isNullOrEmpty ? "is" : "is not") + " null or empty.");


        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,word,,世界，水平");

        for (String item : splitResults) {
            System.out.println(item);
        }

        String toSplitString = "a=b;c=d,e=f";
        Map<String, String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=').split(toSplitString);
        for (Map.Entry<String, String> entry : kvs.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }

        final Student student = new Student.StudentBuilder().id(1).name("change").build();

        System.out.println(MoreObjects.toStringHelper(student.getClass())
                .add("id", student.getId())
                .add("name", student.getName())
                .omitNullValues());

    }
}
