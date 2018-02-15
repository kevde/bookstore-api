package filters;

import core.Book;

public interface Filter {

    public Book[] getFilteredBooks(Book[] books);
}
