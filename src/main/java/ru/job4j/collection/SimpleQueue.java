package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        while (size != 0) {
            output.push(input.pop());
            size--;
        }
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        size++;
    }
}
