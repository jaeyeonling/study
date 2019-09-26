package com.jaeyeonling.study;

import lombok.Builder;
import lombok.Getter;

public class Book {

    @Getter
    private final String title;

    @Builder
    public Book(String title) {
        this.title = title;
    }
}
