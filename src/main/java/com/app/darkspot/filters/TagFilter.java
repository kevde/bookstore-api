package com.app.darkspot.filters;

import com.app.darkspot.core.Book;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagFilter implements Filter {
    private final String tag;

    public TagFilter(String tag) {
        this.tag = tag;
    }

    public Book[] getFilteredBooks(Book[] books) {
        List<Book> streamedBooks = Arrays.asList(books);
        List<Book> filteredBooks = streamedBooks.stream()
                .filter((book) -> book.tag.equals(this.tag))
                .collect(Collectors.toList());
        return filteredBooks.toArray(new Book[0]);
    }
}
