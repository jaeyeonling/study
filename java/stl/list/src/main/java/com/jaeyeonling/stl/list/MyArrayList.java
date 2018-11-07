package com.jaeyeonling.stl.list;

public class MyArrayList<E> implements MyList<E> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public E[] toArray() {
        return null;
    }

    @Override
    public boolean add(E element) {
        return false;
    }

    @Override
    public boolean addAll(MyList<? extends E> list) {
        return false;
    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }

    @Override
    public boolean removeAll(MyList<? extends E> list) {
        return false;
    }

    @Override
    public boolean clear() {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }
}
