package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/for_config/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Viktor Ivannikov");
    }

    @Test
    void whenHasCommentsAndEmptyStrings() {
        String path = "./data/for_config/with_comments_and_empty_strings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value(null)).isEqualTo(null);
    }

    @Test
    void whenStartWithSignEquals() {
        String path = "./data/for_config/start_with_sign_equals.properties";
        Config config = new Config(path);
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                config::load);
        assertThat(exception.getMessage())
                .isEqualTo("line begins with an = sign without a key, "
                        + "or ends with an = sign without a value");
    }

    @Test
    void whenEndWithSignEquals() {
        String path = "./data/for_config/end_with_sign_equals.properties";
        Config config = new Config(path);
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                config::load
        );
        assertThat(exception.getMessage())
                .isEqualTo("line begins with an = sign without a key, "
                        + "or ends with an = sign without a value");
    }

    @Test
    void whenNotContainsSignEquals() {
        String path = "./data/for_config/not_contains_sign_equals.properties";
        Config config = new Config(path);
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                config::load
        );
        assertThat(exception.getMessage())
                .isEqualTo("line does not contain an =");
    }

    @Test
    void whenContainsOnlySignEquals() {
        String path = "./data/for_config/contains_only_sign_equals.properties";
        Config config = new Config(path);
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                config::load
        );
        assertThat(exception.getMessage()).isEqualTo("Line contains only the = sign");
    }

    @Test
    void whenEndEqualsThanValid() {
        String path = "./data/for_config/valid_value_end_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Viktor Ivannikov=");
    }

    @Test
    void whenHasTwoEqualsThanValid() {
        String path = "./data/for_config/valid_value_with_two_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Viktor Ivannikov=1");
    }
}