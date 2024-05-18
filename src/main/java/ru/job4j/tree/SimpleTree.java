package ru.job4j.tree;

import java.util.*;

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
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.value.equals(value)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }
}
