package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("three")
                .contains("five", Index.atIndex(4))
                .containsAnyOf("zero", "second", "six")
                .isInstanceOf(List.class)
                .doesNotContain("first", Index.atIndex(1));
        assertThat(list).last()
                .isNotNull();
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .contains("three")
                .containsAnyOf("zero", "second", "six")
                .isInstanceOf(Set.class);
        assertThat(set).isNotNull()
                .allMatch(e -> e.length() > 1);
        assertThat(set).isNotEmpty()
                .contains("four")
                .doesNotContain("six")
                .containsAnyOf("five", "six", "seven");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("second", "three", "four")
                .containsValues(3, 1, 2)
                .doesNotContainKey("six")
                .doesNotContainValue(10)
                .containsEntry("three", 2);
    }
}