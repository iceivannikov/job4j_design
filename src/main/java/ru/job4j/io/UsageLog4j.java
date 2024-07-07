package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte first = 1;
        short second = 2;
        int three = 3;
        long four = 4L;
        float five = 5.0f;
        double six = 6.0;
        char seven = '7';
        boolean eight = true;
        LOG.debug("first: {}, second: {}, three: {}, four: {}, five: {}, six: {}, seven: {}, eight: {}",
                first, second, three, four, five, six, seven, eight);
    }
}
