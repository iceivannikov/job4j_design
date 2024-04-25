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

    @Test
    void whenVertices0Then0() {
        Box box = new Box(0, 10);
        int result = box.getNumberOfVertices();
        assertThat(result)
                .isEqualTo(0)
                .isLessThan(1)
                .isGreaterThan(-1);
    }

    @Test
    void whenVertices4Then4() {
        Box box = new Box(4, 4);
        int result = box.getNumberOfVertices();
        assertThat(result)
                .isEqualTo(4)
                .isLessThan(5)
                .isGreaterThan(3);
    }

    @Test
    void whenVertices8ThenTrue() {
        Box box = new Box(8, 8);
        boolean result = box.isExist();
        assertThat(result)
                .isEqualTo(true)
                .isTrue();
    }

    @Test
    void whenVerticesMinus1ThenFalse() {
        Box box = new Box(-1, 8);
        boolean result = box.isExist();
        assertThat(result)
                .isEqualTo(false)
                .isFalse();
    }

    @Test
    void whenVertices0AndEdge5ThenArea314Dot15() {
        Box box = new Box(0, 5);
        double result = box.getArea();
        assertThat(result)
                .isEqualTo(314.16, offset(0.01))
                .isCloseTo(314.16, offset(0.01))
                .isGreaterThan(313);
    }

    @Test
    void whenVertices4AndEdge10ThenArea173Dot20() {
        Box box = new Box(4, 10);
        double result = box.getArea();
        assertThat(result)
                .isEqualTo(173.20, offset(0.01))
                .isCloseTo(173.20, offset(0.01))
                .isLessThan(174);
    }
}