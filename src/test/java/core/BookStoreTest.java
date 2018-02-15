package core;

import filters.Filter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BookStoreTest {

    private static final Book FIRST_BOOK = Mockito.mock(Book.class);
    private static final Book SECOND_BOOK = Mockito.mock(Book.class);
    private static final Book THIRD_BOOK = Mockito.mock(Book.class);
    private static final Book[] books = {FIRST_BOOK, SECOND_BOOK, THIRD_BOOK};

    private BookStore bookStore;

    @Before
    public void setUp() {
        bookStore = new BookStore(books);
    }

    @Test
    public void should_get_books() {
        // given

        // when
        Book[] returnedBooks = bookStore.getBooks();

        // then
        assertThat(returnedBooks, equalTo(books));
    }

    @Test
    public void should_get_books_by_filter() {
        // given
        Filter filter = PowerMockito.mock(Filter.class);

        // when
        Book[] filteredBooks = bookStore.filter(filter);

        // then
        verify(filter, times(1)).getFilteredBooks(bookStore.getBooks());
        assertThat(filteredBooks, equalTo(filter.getFilteredBooks(bookStore.getBooks())));
    }

    @Test
    public void should_add_book() {

    }
}