package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class AvlTreeTest {
    @Test
    void simmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        IntStream.range(1, 8).forEach(tree::insert);
        assertThat(tree.inSymmetricalOrder()).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void containsReturnsTrueForExistingElements() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(10, 20, 30).forEach(tree::insert);
        assertThat(tree.contains(10)).isTrue();
        assertThat(tree.contains(20)).isTrue();
        assertThat(tree.contains(30)).isTrue();
    }

    @Test
    void containsReturnsFalseForNonExistingElements() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(10, 20).forEach(tree::insert);
        assertThat(tree.contains(30)).isFalse();
        assertThat(tree.contains(40)).isFalse();
    }

    @Test
    void insertAddsElementsAndReturnsTrue() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.insert(10)).isTrue();
        assertThat(tree.insert(20)).isTrue();
        assertThat(tree.insert(30)).isTrue();
    }

    @Test
    void insertReturnsFalseForDuplicates() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        assertThat(tree.insert(10)).isFalse();
    }

    @Test
    void removeDeletesElementAndReturnsTrue() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(10, 20, 30).forEach(tree::insert);
        assertThat(tree.remove(20)).isTrue();
        assertThat(tree.contains(20)).isFalse();
    }

    @Test
    void removeReturnsFalseForNonExistingElements() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(10, 20).forEach(tree::insert);
        assertThat(tree.remove(30)).isFalse();
    }

    @Test
    void minimumReturnsSmallestElement() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(10, 5, 20).forEach(tree::insert);
        assertThat(tree.minimum()).isEqualTo(5);
    }

    @Test
    void minimumReturnsNullForEmptyTree() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.minimum()).isNull();
    }

    @Test
    void maximumReturnsLargestElement() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(10, 5, 20).forEach(tree::insert);
        assertThat(tree.maximum()).isEqualTo(20);
    }

    @Test
    void maximumReturnsNullForEmptyTree() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.maximum()).isNull();
    }

    @Test
    void inSymmetricalOrderReturnsSortedElements() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(4, 2, 6, 1, 3, 5, 7).forEach(tree::insert);
        assertThat(tree.inSymmetricalOrder()).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void inPreOrderReturnsRootLeftRightOrder() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(4, 2, 6, 1, 3, 5, 7).forEach(tree::insert);
        assertThat(tree.inPreOrder()).containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void inPostOrderReturnsLeftRightRootOrder() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(4, 2, 6, 1, 3, 5, 7).forEach(tree::insert);
        assertThat(tree.inPostOrder()).containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void treeRemainsBalancedAfterComplexOperations() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(30, 10, 20, 40).forEach(tree::insert);
        tree.remove(10);
        assertThat(tree.inPreOrder()).containsExactly(30, 20, 40);
    }

    @Test
    void leftRotationMaintainsCorrectStructure() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(1, 2, 3).forEach(tree::insert);
        assertThat(tree.inPreOrder()).containsExactly(2, 1, 3);
    }

    @Test
    void rightRotationMaintainsCorrectStructure() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(3, 2, 1).forEach(tree::insert);
        assertThat(tree.inPreOrder()).containsExactly(2, 1, 3);
    }

    @Test
    void leftRightRotationMaintainsCorrectStructure() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(3, 1, 2).forEach(tree::insert);
        assertThat(tree.inPreOrder()).containsExactly(2, 1, 3);
    }

    @Test
    void rightLeftRotationMaintainsCorrectStructure() {
        AvlTree<Integer> tree = new AvlTree<>();
        Arrays.asList(1, 3, 2).forEach(tree::insert);
        assertThat(tree.inPreOrder()).containsExactly(2, 1, 3);
    }

    @Test
    void largeTreeRemainsBalanced() {
        AvlTree<Integer> tree = new AvlTree<>();
        IntStream.range(0, 100).forEach(tree::insert);
        assertThat(tree.inSymmetricalOrder()).isSorted();
        tree.remove(50);
        assertThat(tree.contains(50)).isFalse();
    }
}