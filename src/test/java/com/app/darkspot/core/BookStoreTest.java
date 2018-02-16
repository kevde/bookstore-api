package com.app.darkspot.core;

import com.app.darkspot.core.Book;
import com.app.darkspot.core.BookStore;
import com.app.darkspot.filters.Filter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

public class BookStoreTest {

    private static final Book FIRST_BOOK = Mockito.mock(Book.class);
    private static final Book SECOND_BOOK = Mockito.mock(Book.class);
    private static final Book THIRD_BOOK = Mockito.mock(Book.class);
    private static final Book[] books = {FIRST_BOOK, SECOND_BOOK, THIRD_BOOK};
    private static final String SAMPLE_ID = "sample_id";
    private static final String FIRST_ID = "first_id";
    private static final String SECOND_ID = "second_id";
    private static final String THIRD_ID = "third_id";
    private static final String NEW_TAG = "newtag";

    private BookStore bookStore;
    private String OLD_TAG = "oldtag";

    @Before
    public void setUp() {
        when(FIRST_BOOK.getId()).thenReturn(FIRST_ID);
        when(SECOND_BOOK.getId()).thenReturn(SECOND_ID);
        when(THIRD_BOOK.getId()).thenReturn(THIRD_ID);

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
    public void should_get_books_by_set_of_filters() {
        // given
        Filter filter1 = PowerMockito.mock(Filter.class);
        Filter filter2 = PowerMockito.mock(Filter.class);
        when(filter1.getFilteredBooks( this.bookStore.getBooks())).thenReturn(this.bookStore.getBooks());
        when(filter2.getFilteredBooks( this.bookStore.getBooks())).thenReturn(this.bookStore.getBooks());
        Filter[] filters = {filter1, filter2};

        // when
        Book[] filteredBooks = bookStore.filter(filters);

        // then
        verify(filter1, times(1)).getFilteredBooks(any(Book[].class));
        verify(filter2, times(1)).getFilteredBooks(any(Book[].class));

        assertThat(filteredBooks, equalTo(filter2.getFilteredBooks(filter1.getFilteredBooks(bookStore.getBooks()))));
    }

    @Test
    public void should_add_book() {
        // given
        Book newBook = Mockito.mock(Book.class);

        // when
        bookStore.add(newBook);

        // then
        assertThat(newBook, isIn(bookStore.getBooks()));
    }

    @Test
    public void should_remove_book() {
        // given
        Book newBook = Mockito.mock(Book.class);
        when(newBook.getId()).thenReturn(SAMPLE_ID);
        bookStore.add(newBook);

        // when
        bookStore.remove(SAMPLE_ID);

        // then
        assertThat(newBook, not(isIn(bookStore.getBooks())));
    }

    @Test
    public void should_rename_tag() {
        // given
        FIRST_BOOK.tag = OLD_TAG;

        // when
        Book book = bookStore.renameTag(FIRST_ID, NEW_TAG);

        // then
        assertThat(book.tag, equalTo(NEW_TAG));
        }
}