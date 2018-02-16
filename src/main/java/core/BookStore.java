package core;

import filters.Filter;

import java.util.ArrayList;
import java.util.Arrays;

public class BookStore {

    private Book[] books;

    public BookStore(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return this.books;
    }

    public Book[] filter(Filter filter) {
        return filter.getFilteredBooks(this.getBooks());
    }

    public Book[] filter(Filter[] filters) {
        Book[] books = this.getBooks();
        for (Filter filter: filters) {
            books = filter.getFilteredBooks(books);
        }
        return books;
    }

    public void add(Book newBook) {
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(this.books));
        books.add(newBook);
        this.books = books.toArray(new Book[0]);
    }

    public void remove(String id) {
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(this.books));
        this.books = books.stream().filter((book -> !book.getId().equals(id))).toArray(Book[]::new);
    }

    public Book renameTag(String id, String newTag) {
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(this.books));
        Book foundBook = books.stream().filter((book -> book.getId().equals(id))).findFirst().get();
        foundBook.tag = newTag;
        return foundBook;
    }
}