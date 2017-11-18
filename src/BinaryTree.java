import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private Node<T> deleteRec(Node<T> node, T delValue) {
        if (node == null)
            return null;
        int comparison = delValue.compareTo(node.value);
        if (comparison < 0) {
            node.left = deleteRec(node.left, delValue);
        } else if (comparison > 0) {
            node.right = deleteRec(node.right, delValue);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<T> l = node.left;
                Node<T> r = node.right;
                node = minValue(node.right);
                node.right = deleteRec(r, node.value);
                node.left = l;
            }
        }
        return node;
    }
    private void delete(T delValue) {
        root = deleteRec(root, delValue);
    }

    Node<T> minValue(Node<T> node)
    {
        if (node.left == null)
            return  node;
        else return minValue(node.left);
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            delete((T) o);
            size--;
            return true;
        }
        return false;
    }



    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }


    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current;
        private Stack<Node<T>> nodes;
        private int count;

        private BinaryTreeIterator() {
            current = null;
            nodes = new Stack<>();
            count = 0;
            Node<T> node = root;
            while (node != null) {
                nodes.push(node);
                node = node.left;
            }
        }

        private Node<T> findNext() {
            if (nodes.empty()) return null;
            Node<T> node = nodes.pop();

            if (node.right != null) {
                Node<T> nodeNext = node.right;
                while (nodeNext != null) {
                    nodes.push(nodeNext);
                    nodeNext = nodeNext.left;
                }
            }
            count++;
            return node;
        }

        @Override
        public boolean hasNext() {
            return !(count==size);
        }

        @Override
        public T next() {
            if (hasNext()) current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}