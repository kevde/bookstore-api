package com.app.darkspot.filters;

import com.app.darkspot.core.Book;
import com.app.darkspot.filters.DateFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class DateFilterTest {

    private static final Date VALID_DATE = new Date();
    private static final Date INVALID_DATE = new Date(1);
    private DateFilter filter;
    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void setUp() {
        book1 = Mockito.mock(Book.class);
        book2 = Mockito.mock(Book.class);
        book3 = Mockito.mock(Book.class);
        book1.creationDate = VALID_DATE;
        book2.creationDate = INVALID_DATE;
        book3.creationDate = VALID_DATE;
    }

    @Test
    public void should_books_filtered_by_valid_date() {
        // given
        filter = new DateFilter(VALID_DATE);
        Book[] oldBooks = {book1, book2, book3};

        // when
        Book[] filteredBooks = filter.getFilteredBooks(oldBooks);

        // then
        assertThat(book1, isIn(filteredBooks));
        assertThat(book2, not(isIn(filteredBooks)));
        assertThat(book3, isIn(filteredBooks));
    }

    @Test
    public void should_books_filtered_by_invalid_date() {
        // given
        filter = new DateFilter(INVALID_DATE);
        Book[] oldBooks = {book1, book2, book3};

        // when
        Book[] filteredBooks = filter.getFilteredBooks(oldBooks);

        // then
        assertThat(book1, not(isIn(filteredBooks)));
        assertThat(book2, isIn(filteredBooks));
        assertThat(book3, not(isIn(filteredBooks)));
    }
}