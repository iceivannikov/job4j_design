package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExampleLevelTwo {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[^abc]");
        String text = """
                hello peter123
                123hello 123 peter
                hello pet123er
                job4j
                aabbcc
                java""";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }
}
