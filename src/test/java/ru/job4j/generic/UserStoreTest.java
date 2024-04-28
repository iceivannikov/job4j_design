package ru.job4j.generic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserStoreTest {
    private Store<User> store;

    @BeforeEach
    public void init() {
        store = new MemStore<>();
        store.add(new User("1", "Petr"));
    }

    @Test
    void whenAddAndFindThenUsernameIsPetr() {
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        User result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        store.add(new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        store.replace("1", new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        store.replace("10", new User("10", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        store.delete("1");
        User result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        store.delete("10");
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        boolean result = store.replace("1", new User("1", "Maxim"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        boolean result = store.replace("10", new User("10", "Maxim"));
        assertThat(result).isFalse();
    }
}