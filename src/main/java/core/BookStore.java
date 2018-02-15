package core;

import filters.Filter;

public class BookStore {

    private final Book[] books;

    public BookStore(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return this.books;
    }

    public Book[] filter(Filter filter) {
        return filter.getFilteredBooks(this.getBooks());
    }
}
