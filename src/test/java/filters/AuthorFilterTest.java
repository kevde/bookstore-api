package filters;

import core.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class AuthorFilterTest {

    private static final String VALID_AUTHOR = "Steve";
    private static final String INVALID_AUTHOR = "John";
    private AuthorFilter filter;
    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void setUp() {
        book1 = Mockito.mock(Book.class);
        book2 = Mockito.mock(Book.class);
        book3 = Mockito.mock(Book.class);
        book1.author = VALID_AUTHOR;
        book2.author = INVALID_AUTHOR;
        book3.author = VALID_AUTHOR;
    }

    @Test
    public void should_books_filtered_by_valid_author() {
        // given
        filter = new AuthorFilter(VALID_AUTHOR);
        Book[] oldBooks = {book1, book2, book3};

        // when
        Book[] filteredBooks = filter.getFilteredBooks(oldBooks);

        // then
        assertThat(book1, isIn(filteredBooks));
        assertThat(book2, not(isIn(filteredBooks)));
        assertThat(book3, isIn(filteredBooks));
    }

    @Test
    public void should_books_filtered_by_invalid_author() {
        // given
        filter = new AuthorFilter(INVALID_AUTHOR);
        Book[] oldBooks = {book1, book2, book3};

        // when
        Book[] filteredBooks = filter.getFilteredBooks(oldBooks);

        // then
        assertThat(book1, not(isIn(filteredBooks)));
        assertThat(book2, isIn(filteredBooks));
        assertThat(book3, not(isIn(filteredBooks)));
    }
}