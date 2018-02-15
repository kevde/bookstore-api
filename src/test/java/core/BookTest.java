package core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class BookTest {
    private static final String NAME = "Sample Book";
    private static final String ID = "1";
    private static final Float PRICE = (float) 2000;
    private static final String OLD_TAG = "Old Tag";
    private static final String NEW_TAG = "New Tag";
    private Book book;

    @Before
    public void setUp() {
        book = new Book(ID, NAME, PRICE);
    }

    @Test
    public void should_add_document() {
        // given
        File mockFile = Mockito.mock(File.class);

        // when
        Book returnedBook = book.addDocument(mockFile);

        // then
        assertThat(returnedBook.document, equalTo(mockFile));
    }

    @Test
    public void should_rename_tag() {
        // given
        book = new Book(ID, NAME, PRICE, OLD_TAG);

        // when
        Book modifiedBook = book.renameTag(NEW_TAG);

        // then
        assertThat(modifiedBook.tag, equalTo(NEW_TAG));
    }

    @Test
    public void should_update_authors() {
        // given
        String author = "Stephen";

        // when
        Book returnedBook = book.withAuthor(author);

        // then
        assertThat(returnedBook.author, equalTo(author));
    }

    @Test
    public void should_create_with_creation_date() {
        // given
        Date mockDate = Mockito.mock(Date.class);

        // when
        Book returnedBook = book.withCreationDate(mockDate);

        // then
        assertThat(returnedBook.creationDate, equalTo(mockDate));
    }
}