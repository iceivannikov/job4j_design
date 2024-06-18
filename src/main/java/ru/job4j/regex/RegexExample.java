package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Я учусь на Job4j");

        String textOne = "Я учусь на Job4j";
        Matcher matcherOne = pattern.matcher(textOne);
        boolean isPresentOne = matcherOne.matches();
        System.out.println(isPresentOne);

        String textTwo = "Я учусь на курсе Job4j";
        Matcher matcherTwo = pattern.matcher(textTwo);
        boolean isPresentTwo = matcherTwo.matches();
        System.out.println(isPresentTwo);

        Pattern pattern1 = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);

        String textOne1 = "Job4j";
        Matcher matcherOne1 = pattern1.matcher(textOne1);
        boolean isPresentOne1 = matcherOne1.matches();
        System.out.println(isPresentOne1);

        String textTwo1 = "job4j";
        Matcher matcherTwo1 = pattern1.matcher(textTwo1);
        boolean isPresentTwo1 = matcherTwo1.matches();
        System.out.println(isPresentTwo1);

        Pattern pattern2 = Pattern.compile("Job4j");
        String text = "Я учусь на курсе Job4j";
        Matcher matcher = pattern2.matcher(text);
        boolean isPresent = matcher.find();
        System.out.println(isPresent);

        Pattern pattern3 = Pattern.compile("Job4j");
        String text3 = "Job4j и Job4j и Job4j";
        Matcher matcher3 = pattern3.matcher(text3);
        while (matcher3.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }

        Pattern pattern4 = Pattern.compile("Job4j");
        String text4 = "Job4j1 и Job4j2 и Job4j3";
        Matcher matcher4 = pattern4.matcher(text4);
        while (matcher4.find()) {
            System.out.println("Найдено совпадение. iStart: " + matcher4.start()
                    + " iEnd: " + matcher4.end());
        }

        Pattern pattern5 = Pattern.compile("123");
        String text5 = "1231 и 1232 и 1233";
        Matcher matcher5 = pattern5.matcher(text5);
        String result = matcher5.replaceAll("Job4j");
        System.out.println(result);
    }
}
