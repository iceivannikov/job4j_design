package ru.job4j.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {
    private Node root;

    public boolean put(T key, V value) {
        boolean result = false;
        if (Objects.nonNull(key)) {
            root = put(root, key, value);
            result = true;
        }
        return result;
    }

    private Node put(Node node, T key, V value) {
        if (Objects.isNull(node)) {
            node = new Node(key, value);
        } else {
            int cmp = getCmp(node, key);
            if (cmp < 0) {
                node.left = put(node.left, key, value);
            } else if (cmp > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.value = value;
            }
            updateHeight(node);
            node = balance(node);
        }
        return node;
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && contains(key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T key) {
        if (Objects.nonNull(node)) {
            int cmp = getCmp(node, key);
            if (cmp < 0) {
                node.left = remove(node.left, key);
            } else if (cmp > 0) {
                node.right = remove(node.right, key);
            } else {
                node = removeFoundNode(node);
            }
            if (Objects.nonNull(node)) {
                updateHeight(node);
                node = balance(node);
            }
        }
        return node;
    }

    private Node removeFoundNode(Node node) {
        if (Objects.isNull(node.left)) {
            return node.right;
        }
        if (Objects.isNull(node.right)) {
            return node.left;
        }
        if (node.left.height > node.right.height) {
            Node heir = maximum(node.left);
            node.key = heir.key;
            node.value = heir.value;
            node.left = remove(node.left, heir.key);
        } else {
            Node heir = minimum(node.right);
            node.key = heir.key;
            node.value = heir.value;
            node.right = remove(node.right, heir.key);
        }
        return node;
    }

    private Node minimum(Node node) {
        return Objects.nonNull(node.left) ? minimum(node.left) : node;
    }

    private Node maximum(Node node) {
        return Objects.nonNull(node.right) ? maximum(node.right) : node;
    }

    public V get(T key) {
        Node result = Objects.nonNull(key) ? find(root, key) : null;
        return Objects.nonNull(result) ? result.value : null;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    private Node find(Node node, T key) {
        Node result = null;
        if (Objects.nonNull(node)) {
            if (Objects.equals(node.key, key)) {
                result = node;
            } else {
                int cmp = getCmp(node, key);
                if (cmp < 0) {
                    result = find(node.left, key);
                } else if (cmp > 0) {
                    result = find(node.right, key);
                }
            }
        }
        return result;
    }

    private int getCmp(Node node, T key) {
        return key.compareTo(node.key);
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node supporting = node.right;
        node.right = supporting.left;
        supporting.left = node;
        updateHeight(node);
        updateHeight(supporting);
        return supporting;
    }

    private Node rightRotation(Node node) {
        Node supporting = node.left;
        node.left = supporting.right;
        supporting.right = node;
        updateHeight(node);
        updateHeight(supporting);
        return supporting;
    }

    public Set<T> keySet() {
        List<T> list = new ArrayList<>();
        inOrderKeyTraversal(root, list);
        return new LinkedHashSet<>(list);
    }

    public Collection<V> values() {
        List<V> list = new ArrayList<>();
        inOrderValueTraversal(root, list);
        return list;
    }

    private void inOrderKeyTraversal(Node node, List<T> list) {
        if (Objects.nonNull(node)) {
            inOrderKeyTraversal(node.left, list);
            list.add(node.key);
            inOrderKeyTraversal(node.right, list);
        }
    }

    private void inOrderValueTraversal(Node node, List<V> result) {
        if (Objects.nonNull(node)) {
            inOrderValueTraversal(node.left, result);
            result.add(node.value);
            inOrderValueTraversal(node.right, result);
        }
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}