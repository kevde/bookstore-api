package filters;

import core.Book;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorFilter implements Filter {
    private final String author;

    public AuthorFilter(String author) {
        this.author = author;
    }

    public Book[] getFilteredBooks(Book[] books) {
        List<Book> streamedBooks = Arrays.asList(books);
        List<Book> filteredBooks = streamedBooks.stream()
                .filter((book) -> book.author.equals(this.author))
                .collect(Collectors.toList());
        return filteredBooks.toArray(new Book[0]);
    }
}
