package com.app.darkspot.filters;

import com.app.darkspot.core.Book;
import com.app.darkspot.filters.TagFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class TagFilterTest {

    private static final String VALID_TAG = "VALID";
    private static final String INVALID_TAG = "INVALID";
    private TagFilter filter;
    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void setUp() {
        book1 = Mockito.mock(Book.class);
        book2 = Mockito.mock(Book.class);
        book3 = Mockito.mock(Book.class);
        book1.tag = VALID_TAG;
        book2.tag = INVALID_TAG;
        book3.tag = VALID_TAG;
    }

    @Test
    public void should_books_filtered_by_valid_tag() {
        // given
        filter = new TagFilter(VALID_TAG);
        Book[] oldBooks = {book1, book2, book3};

        // when
        Book[] filteredBooks = filter.getFilteredBooks(oldBooks);

        // then
        assertThat(book1, isIn(filteredBooks));
        assertThat(book2, not(isIn(filteredBooks)));
        assertThat(book3, isIn(filteredBooks));
    }

    @Test
    public void should_books_filtered_by_invalid_tag() {
        // given
        filter = new TagFilter(INVALID_TAG);
        Book[] oldBooks = {book1, book2, book3};

        // when
        Book[] filteredBooks = filter.getFilteredBooks(oldBooks);

        // then
        assertThat(book1, not(isIn(filteredBooks)));
        assertThat(book2, isIn(filteredBooks));
        assertThat(book3, not(isIn(filteredBooks)));
    }
}