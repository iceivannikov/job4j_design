package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3)
                .containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3)
                .containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 5, 1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveEvenNumbersThenContains1and3() {
        ListUtils.removeIf(input, i -> i % 2 == 0);
        assertThat(input).hasSize(2)
                .containsSequence(1, 3);
    }

    @Test
    void whenRemoveOddNumbersThenInputEmpty() {
        ListUtils.removeIf(input, i -> i % 2 != 0);
        assertThat(input).hasSize(0)
                .doesNotContain(1, 3)
                .isEmpty();
    }

    @Test
    void whenReplace3With2ThenInput1and2() {
        ListUtils.replaceIf(input, i -> i == 3, 2);
        assertThat(input).hasSize(2)
                .containsSequence(1, 2)
                .doesNotContain(3);
    }

    @Test
    void whenRemoveAllThenInputEmpty() {
        ListUtils.removeAll(input, new ArrayList<>(Arrays.asList(1, 3)));
        assertThat(input).hasSize(0)
                .doesNotContain(1, 3)
                .isEmpty();
    }

    @Test
    void whenRemove1ThenInputContains3() {
        ListUtils.removeAll(input, new ArrayList<>(List.of(1)));
        assertThat(input).hasSize(1)
                .containsSequence(3)
                .doesNotContainSequence(1);
    }
}