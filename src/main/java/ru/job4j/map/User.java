package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Viktor", 5, birthday);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int backet1 = hash1 & 15;
        User user2 = new User("Viktor", 5, birthday);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int backet2 = hash2 & 15;
        map.put(user1, new Object());
        map.put(user2, new Object());
        Set<Map.Entry<User, Object>> entries = map.entrySet();
        for (Map.Entry<User, Object> entry : entries) {
            System.out.println(entry.getKey());
        }
        System.out.printf("user1 - hashcode: %s, hash: %s, backet: %s", hashCode1, hash1, backet1);
        System.out.println();
        System.out.printf("user1 - hashcode: %s, hash: %s, backet: %s", hashCode2, hash2, backet2);
    }
}
