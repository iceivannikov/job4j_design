package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.*;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        checkNotNull(root);
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        int result = 0;
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            result++;
            List<Node<T>> children = node.getChildren();
            for (Node<T> child : children) {
                queue.push(child);
            }
        }
        return result;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     *
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        checkNotNull(root);
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        List<T> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            result.add(node.getValue());
            List<Node<T>> children = node.getChildren();
            for (Node<T> child : children) {
                queue.push(child);
            }
        }
        return result;
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     *
     * @param root   корень дерева
     * @param parent ключ узла-родителя
     * @param child  ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        checkNotNull(root);
        boolean result = false;
        Optional<Node<T>> optionalNode = findByKey(root, parent);
        if (optionalNode.isPresent()) {
            Node<T> parentNode = optionalNode.get();
            Optional<Node<T>> childNode = findByKey(parentNode, child);
            if (childNode.isEmpty()) {
                parentNode.getChildren().add(new Node<>(child));
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        checkNotNull(root);
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        Optional<Node<T>> result = Optional.empty();
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            if (key.equals(node.getValue())) {
                result = Optional.of(node);
                break;
            }
            if (node.getChildren() != null) {
                for (Node<T> child : node.getChildren()) {
                    stack.push(child);
                }
            }
        }
        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        checkNotNull(root);
        Optional<Node<T>> result = Optional.empty();
        if (key.equals(root.getValue())) {
            result = Optional.of(root);
        }
        Optional<Node<T>> optionalNode = findByKey(root, key);
        if (optionalNode.isPresent()) {
            result = optionalNode;
            Node<T> node = optionalNode.get();
            Optional<Node<T>> parentOptional = findParent(root, node);
            if (parentOptional.isPresent()) {
                Node<T> parent = parentOptional.get();
                parent.getChildren().remove(node);
            }
        }
        return result;
    }

    private Optional<Node<T>> findParent(Node<T> root, Node<T> node) {
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        Optional<Node<T>> result = Optional.empty();
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            for (Node<T> child : current.getChildren()) {
                if (child == node) {
                    result = Optional.of(current);
                    break;
                }
                queue.push(child);
            }
        }
        return result;
    }

    private void checkNotNull(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }
    }
}
