package filters;

import core.Book;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PriceFilter implements Filter {
    private final Float price;

    public PriceFilter(Float price) {
        this.price = price;
    }

    public Book[] getFilteredBooks(Book[] books) {
        List<Book> streamedBooks = Arrays.asList(books);
        List<Book> filteredBooks = streamedBooks.stream()
                .filter((book) -> book.getPrice().equals(this.price))
                .collect(Collectors.toList());
        return filteredBooks.toArray(new Book[0]);
    }
}
