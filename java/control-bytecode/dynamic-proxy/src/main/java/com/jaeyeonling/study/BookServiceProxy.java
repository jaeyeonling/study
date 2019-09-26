package com.jaeyeonling.study;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookServiceProxy implements BookService {

    private final BookService bookService;

    @Override
    public void rent(final Book book) {
        System.out.println("aaa");
        bookService.rent(book);
        System.out.println("bbb");
    }
}
