package ru.job4j.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleTreeTest {
    private Tree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new SimpleTree<>(1);
        tree.add(1, 2);
    }

    @Test
    void when6ElFindLastThen6() {
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenFindRootThenPresent() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.findBy(1)).isPresent();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenParentNotExistThenFalse() {
        assertThat(tree.add(3, 4)).isFalse();
    }

    @Test
    void whenAddRootNodeAgainThenFalse() {
        assertThat(tree.add(1, 1)).isFalse();
    }
}