package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, User> map = previous.stream()
                .collect(Collectors.toMap(User::getId, user -> new User(user.getId(), user.getName())));
        int added = 0;
        int changed = 0;
        for (User user : current) {
            if (!map.containsKey(user.getId())) {
                added++;
            } else if (!Objects.equals(map.get(user.getId()).getName(), user.getName())) {
                changed++;
            }
            map.remove(user.getId());
        }
        int deleted = map.size();
        return new Info(added, changed, deleted);
    }
}
