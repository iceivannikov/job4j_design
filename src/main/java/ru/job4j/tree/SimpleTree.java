package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;
    private final Set<E> elements = new HashSet<>();

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
        elements.add(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> optionalNode = findBy(parent);
        if (optionalNode.isPresent()) {
            Node<E> parentNode = optionalNode.get();
            if (!elements.contains(child)) {
                parentNode.children.add(new Node<>(child));
                elements.add(child);
                result = true;
            }
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(e -> e.value.equals(value));
    }

    @Override
    public boolean isBinary() {
        return findByPredicate(e -> e.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                result = Optional.of(element);
            }
            data.addAll(element.children);
        }
        return result;
    }
}
