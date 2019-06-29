package com.change.hippo.util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileToString1 {

    @Test
    public void java8() {
        final String path = "/Users/changealice/1.txt";
        try (Stream<String> lines = Files.lines(Paths.get(path))) {

            final String content = lines.collect(Collectors.joining(System.lineSeparator()));
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void java7() throws IOException {
        final String path = "/Users/changealice/1.txt";
        final List<String> content = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        System.out.println(content);
    }

    @Test
    public void apache_common_io() throws IOException {
        final String path = "/Users/changealice/1.txt";
        final List<String> content = FileUtils.readLines(new File(path), StandardCharsets.UTF_8);
        System.out.println(content);
    }
}
