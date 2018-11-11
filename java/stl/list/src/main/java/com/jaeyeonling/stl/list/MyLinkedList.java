package com.jaeyeonling.stl.list;

import java.util.Objects;

public class MyLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    //
    //

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Object[] toArray() {
        final Object[] array = new Object[this.size];

        int count = 0;
        for (Node<E> node = this.head; node != null; node = node.next) {
            array[count++] = node.item;
        }

        return array;
    }

    @Override
    public boolean add(final E element) {
        final Node<E> tail = this.tail;
        final Node<E> newNode = new Node<>(tail, element);

        this.tail = newNode;

        if (Objects.isNull(this.head)) {
            this.head = newNode;
        }
        if (Objects.nonNull(tail)) {
            tail.next = newNode;
        }

        size++;

        return true;
    }

    @Override
    public boolean addAll(final MyList<? extends E> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }

        return true;
    }

    @Override
    public boolean remove(final E element) {
        for (Node<E> node = this.head; node != null; node = node.next) {
            if (Objects.isNull(element) ? Objects.isNull(node.item) : element.equals(node.item)) {
                return unlink(node);
            }
        }

        return false;
    }

    @Override
    public boolean remove(int index) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        int count = 0;
        for (Node<E> node = this.head; node != null; node = node.next) {
            if (count++ == index) {
                return unlink(node);
            }
        }

        return false;
    }

    @Override
    public boolean removeAll(MyList<? extends E> list) {
        for (int i = 0; i < list.size(); i++) {
            remove(list.get(i));
        }

        return true;
    }

    @Override
    public boolean clear() {
        for (Node<E> node = this.head; node != null; ) {
            final Node<E> next = node.next;
            node.item = null;
            node.next = null;
            node.prev = null;
            node = next;
        }

        this.head = this.tail = null;
        this.size = 0;

        return true;
    }

    @Override
    public E get(int index) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        int count = 0;
        for (Node<E> node = this.head; node != null; node = node.next) {
            if (count++ == index) {
                return node.item;
            }
        }

        return null;
    }

    //
    //

    private boolean unlink(final Node<E> node) {
        final Node<E> next = node.next;
        final Node<E> prev = node.prev;

        if (prev == null) {
            this.head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            this.tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.item = null;
        size--;

        return true;
    }

    //
    //

    private static class Node<E> {
        private Node<E> prev;
        private E item;
        private Node<E> next;

        private Node(
                final Node<E> prev,
                final E item
        ) {
            this(prev, item, null);
        }

        private Node(
                final Node<E> prev,
                final E item,
                final Node<E> next
        ) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}
