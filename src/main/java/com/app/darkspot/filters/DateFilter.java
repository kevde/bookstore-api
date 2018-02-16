package com.app.darkspot.filters;

import com.app.darkspot.core.Book;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DateFilter implements Filter {
    private final Date creationDate;

    public DateFilter(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Book[] getFilteredBooks(Book[] books) {
        List<Book> streamedBooks = Arrays.asList(books);
        List<Book> filteredBooks = streamedBooks.stream()
                .filter((book) -> book.creationDate.equals(this.creationDate))
                .collect(Collectors.toList());
        return filteredBooks.toArray(new Book[0]);
    }
}
