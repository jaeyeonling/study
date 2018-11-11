package com.jaeyeonling.stl.list;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {
    private static final int DEFAULT_CAPACITY = 10;

    //
    //

    private int size;
    private Object[] elements;

    //
    //

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(final int capacity) {
        this.elements = new Object[capacity];
    }

    //
    //

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public E[] toArray() {
        return (E[]) Arrays.copyOf(this.elements, this.size);
    }

    @Override
    public boolean add(final E element) {
        this.elements[this.size++] = element;

        if (this.elements.length == this.size) {
            this.elements = Arrays.copyOf(this.elements, (this.size + DEFAULT_CAPACITY));
        }

        return true;
    }

    @Override
    public boolean addAll(final MyList<? extends E> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }

        return true;
    }

    @Override
    public boolean remove(final E element) {
        for (int i = 0; i < this.elements.length; i++) {
            if (element.equals(this.elements[i])) {
                return remove(i);
            }
        }

        return false;
    }

    @Override
    public boolean remove(final int index) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        this.elements[index] = null;

        for (int i = index; i < this.elements.length - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.size--;

        return true;
    }

    @Override
    public boolean removeAll(final MyList<? extends E> list) {
        for (int i = 0; i < list.size(); i++) {
            remove(list.get(i));
        }

        return true;
    }

    @Override
    public boolean clear() {
        for (int i = 0; i < this.elements.length; i++) {
            this.elements[i] = null;
        }

        this.size = 0;

        return true;
    }

    @Override
    public E get(final int index) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        return (E) this.elements[index];
    }
}
