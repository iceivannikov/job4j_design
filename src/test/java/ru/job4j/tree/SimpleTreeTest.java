package ru.job4j.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleTreeTest {
    private Tree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new SimpleTree<>(1);
    }

    @Test
    void when6ElFindLastThen6() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenFindRootThenPresent() {
        assertThat(tree.findBy(1)).isPresent();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenParentNotExistThenFalse() {
        tree.add(1, 2);
        assertThat(tree.add(3, 4)).isFalse();
    }

    @Test
    void whenAddRootNodeAgainThenFalse() {
        tree.add(1, 2);
        assertThat(tree.add(1, 1)).isFalse();
    }

    @Test
    void whenTreeIsBinaryThenTrue() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenTreeIsNotBinaryThenFalse() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        assertThat(tree.isBinary()).isFalse();
    }

    @Test
    void whenTreeHasOnlyRootAndBinaryThenTrue() {
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenTreeHasMultiLevelAndBinaryThenTrue() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary()).isTrue();
    }
}