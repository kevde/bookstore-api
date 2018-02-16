package com.app.darkspot.filters;

import com.app.darkspot.core.Book;
import com.app.darkspot.filters.PriceFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class PriceFilterTest {

    private static final Float FILTERED_PRICE = (float) 2000;
    private static final Float OTHER_PRICE = (float) 1000;
    private PriceFilter filter;
    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void setUp() {
        book1 = Mockito.mock(Book.class);
        book2 = Mockito.mock(Book.class);
        book3 = Mockito.mock(Book.class);
        when(book1.getPrice()).thenReturn(FILTERED_PRICE);
        when(book2.getPrice()).thenReturn(OTHER_PRICE);
        when(book3.getPrice()).thenReturn(FILTERED_PRICE);
    }

    @Test
    public void should_books_filtered_by_price() {
        // given
        filter = new PriceFilter(FILTERED_PRICE);
        Book[] oldBooks = {book1, book2, book3};

        // when
        Book[] filteredBooks = filter.getFilteredBooks(oldBooks);

        // then
        assertThat(book1, isIn(filteredBooks));
        assertThat(book2, not(isIn(filteredBooks)));
        assertThat(book3, isIn(filteredBooks));
    }

    @Test
    public void should_books_filtered_by_other_price() {
        // given
        filter = new PriceFilter(OTHER_PRICE);
        Book[] oldBooks = {book1, book2, book3};

        // when
        Book[] filteredBooks = filter.getFilteredBooks(oldBooks);

        // then
        assertThat(book1, not(isIn(filteredBooks)));
        assertThat(book2, isIn(filteredBooks));
        assertThat(book3, not(isIn(filteredBooks)));
    }
}