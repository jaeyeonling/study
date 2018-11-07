package com.jaeyeonling.stl.list;

public interface MyList<E> {
    int size();
    boolean isEmpty();

    E[] toArray();

    boolean add(final E element);
    boolean addAll(final MyList<? extends E> list);

    boolean remove(final E element);
    boolean remove(final int index);
    boolean removeAll(final MyList<? extends E> list);
    boolean clear();

    E get(final int index);
}
