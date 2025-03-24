package ru.job4j.collection.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvlTree<T extends Comparable<T>> {
    private Node root;

    public boolean contains(T value) {
        return find(root, value) != null;
    }

    private Node find(Node node, T value) {
        Node result = null;
        if (Objects.nonNull(node)) {
            if (Objects.equals(node.key, value)) {
                result = node;
            } else {
                int comparisonResult = value.compareTo(node.key);
                if (comparisonResult < 0) {
                    result = find(node.left, value);
                } else if (comparisonResult > 0) {
                    result = find(node.right, value);
                }
            }
        }
        return result;
    }

    public boolean insert(T value) {
        boolean result = false;
        if (Objects.nonNull(value) && !contains(value)) {
            root = insert(root, value);
            result = true;
        }
        return result;
    }

    private Node insert(Node node, T value) {
        Node result = new Node(value);
        if (Objects.nonNull(node)) {
            int comparisonResult = value.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = insert(node.left, value);
            } else {
                node.right = insert(node.right, value);
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    public boolean remove(T element) {
        boolean result = false;
        if (Objects.nonNull(element) && Objects.nonNull(root) && contains(element)) {
            root = remove(root, element);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }
        int comparisonResult = element.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
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

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        return Objects.nonNull(node.left) ? minimum(node.left) : node;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        return Objects.nonNull(node.right) ? maximum(node.right) : node;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        return inSymmetricalOrder(root, result);
    }

    private List<T> inSymmetricalOrder(Node root, List<T> list) {
        if (Objects.nonNull(root)) {
            inSymmetricalOrder(root.left, list);
            list.add(root.key);
            inSymmetricalOrder(root.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        return inPreOrder(root, result);
    }

    private List<T> inPreOrder(Node root, List<T> list) {
        if (Objects.nonNull(root)) {
            list.add(root.key);
            inPreOrder(root.left, list);
            inPreOrder(root.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        return inPostOrder(root, result);
    }

    private List<T> inPostOrder(Node root, List<T> list) {
        if (Objects.nonNull(root)) {
            inPostOrder(root.left, list);
            inPostOrder(root.right, list);
            list.add(root.key);
        }
        return list;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private int balanceFactor;
        private T key;
        private int height;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return String.valueOf(key);
        }
    }

    public static void main(String[] args) {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 0; i < 30; i++) {
            tree.insert(i);
        }
        System.out.println(tree);
        tree.remove(5);
        System.out.println(tree);
    }
}
