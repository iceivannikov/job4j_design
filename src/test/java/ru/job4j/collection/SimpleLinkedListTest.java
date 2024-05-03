package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleLinkedListTest {
    private SimpleLinked<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
    }

    @Test
    void checkIteratorSimple() {
        assertThat(list).hasSize(2);
        list.add(3);
        list.add(4);
        assertThat(list).hasSize(4);
    }

    @Test
    void whenAddAndGet() {
        list.add(3);
        list.add(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetFromOutOfBoundThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddIterHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOne() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void whenEmptyIterHashNextFalse() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenAddIterMultiHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOneNextTwo() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
    }

    @Test
    void whenGetIteratorTwiceThenEveryFromBegin() {
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(1);
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(2);
        assertThat(first.hasNext()).isFalse();
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(1);
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(2);
        assertThat(second.hasNext()).isFalse();
    }

    @Test
    void whenAddIterNextThrowsExceptionAfterLastElement() {
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.next();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenListModifiedDuringIterationThenConcurrentModificationExceptionThrown() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        list.add(3);
        assertThatThrownBy(iterator::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }
    @Test
    void whenGetFromEmptyListThenExceptionThrown() {
        SimpleLinkedList<Integer> emptyList = new SimpleLinkedList<>();
        assertThatThrownBy(() -> emptyList.get(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenElementsAddedListIsNotEmpty() {
        SimpleLinkedList<Integer> emptyList = new SimpleLinkedList<>();
        emptyList.add(1);
        assertThat(emptyList.iterator().hasNext()).isTrue();
    }

    @Test
    void whenIteratingAllElementsIterated() {
        Iterator<Integer> iterator = list.iterator();
        List<Integer> elements = new ArrayList<>();
        while (iterator.hasNext()) {
            elements.add(iterator.next());
        }
        assertThat(elements).containsExactly(1, 2);
    }

    @Test
    void whenIteratingOnceAllElementsIterated() {
        Iterator<Integer> first = list.iterator();
        List<Integer> elementsFirst = new ArrayList<>();
        while (first.hasNext()) {
            elementsFirst.add(first.next());
        }
        assertThat(elementsFirst).containsExactly(1, 2);
        Iterator<Integer> second = list.iterator();
        List<Integer> elementsSecond = new ArrayList<>();
        while (second.hasNext()) {
            elementsSecond.add(second.next());
        }
        assertThat(elementsSecond).containsExactly(1, 2);
    }

    @Test
    void whenIteratorFinishedHasNextFalse() {
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext()).isFalse();
    }
}