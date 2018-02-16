package com.app.darkspot.filters;

import com.app.darkspot.core.Book;

public interface Filter {

    public Book[] getFilteredBooks(Book[] books);
}
