package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkLengthZero() {
        NameLoad nameLoad = new NameLoad();
        String[] value = {};
        assertThatThrownBy(() -> nameLoad.parse(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkContainsEqualSign() {
        NameLoad nameLoad = new NameLoad();
        String[] value = {"key:value"};
        assertThatThrownBy(() -> nameLoad.parse(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol '='")
                .hasMessageContaining(value[0]);
    }

    @Test
    void checkStartsWithEqualSign() {
        NameLoad nameLoad = new NameLoad();
        String[] value = {"=key=value"};
        assertThatThrownBy(() -> nameLoad.parse(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining(value[0]);
    }

    @Test
    void checkEndsWithEqualSign() {
        NameLoad nameLoad = new NameLoad();
        String[] value = {"keyvalue="};
        assertThatThrownBy(() -> nameLoad.parse(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining(value[0]);
    }
}