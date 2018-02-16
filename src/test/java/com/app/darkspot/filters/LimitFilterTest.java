package com.app.darkspot.filters;

import com.app.darkspot.core.Book;
import com.app.darkspot.filters.LimitFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class LimitFilterTest {

    private LimitFilter filter;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Book book6;
    private Book book7;
    private Book book8;
    private Book book9;

    @Before
    public void setUp() {
        book1 = Mockito.mock(Book.class);
        book2 = Mockito.mock(Book.class);
        book3 = Mockito.mock(Book.class);
        book4 = Mockito.mock(Book.class);
        book5 = Mockito.mock(Book.class);
        book6 = Mockito.mock(Book.class);
        book7 = Mockito.mock(Book.class);
        book8 = Mockito.mock(Book.class);
        book9 = Mockito.mock(Book.class);
    }

    @Test
    public void should_books_filtered_by_limit_number() {
        // given
        int limit = 5;
        filter = new LimitFilter(limit);
        Book[] oldBooks = {book1, book2, book3, book4,book5,book6,book7,book8,book9};

        // when
        Book[] filteredBooks = filter.getFilteredBooks(oldBooks);

        // then
        assertThat(filteredBooks.length, equalTo(limit));
    }

    @Test
    public void should_change_page() {
        // given
        int limit = 5;
        filter = new LimitFilter(limit);
        Book[] oldBooks = {book1, book2, book3, book4,book5,book6,book7,book8,book9};

        // when
        filter.setPage(1);
        Book[] filteredBooks = filter.getFilteredBooks(oldBooks);

        // then
        assertThat(filteredBooks.length, equalTo(4));
    }
}