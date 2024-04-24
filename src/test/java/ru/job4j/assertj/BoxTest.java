package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Sphere")
                .startsWith("S")
                .isNotBlank();
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(2, 5);
        String name = box.whatsThis();
        assertThat(name)
                .isEqualTo("Unknown object")
                .endsWith("ect")
                .isNotBlank();
    }
}