package com.jaeyeonling.study;

public class DefaultBookService implements BookService {

    public void rent(final Book book) {
        System.out.println(book.getTitle());
    }
}
