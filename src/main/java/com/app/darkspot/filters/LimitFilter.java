package com.app.darkspot.filters;

import com.app.darkspot.core.Book;

import java.util.Arrays;

public class LimitFilter implements Filter {
    private final int limit;
    private int page;

    public LimitFilter(int limit) {
        this.limit = limit;
        this.page = 0;
    }

    @Override
    public Book[] getFilteredBooks(Book[] books) {
        int lastItemInPage = ((this.page + 1 ) * this.limit);
        int lastPageNumber = books.length >= lastItemInPage ? lastItemInPage : books.length;
        return Arrays.copyOfRange(books, this.page * this.limit, lastPageNumber);
    }

    public void setPage(int page) {
        this.page = page;
    }
}
