package controllers;

import core.Book;
import core.BookStore;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AddBookControllerTest extends JerseyTest {

    private BookStore bookStore = new BookStore(new Book[0]);

    @Override
    protected Application configure() {
        return new ResourceConfig().register(new AddBookController(bookStore)).getApplication();
    }

    @Test
    public void addBook() {
        BookRequest bookRequest = new BookRequest("name", (float) 20000, "Sample Author", "name");
        Entity<BookRequest> request = Entity.entity(bookRequest, MediaType.APPLICATION_JSON);
        Response book = target("add").request().post(request);
        assertThat(book.getEntity(), hasProperty("id", equalTo("2")));
    }
}

class BookRequest {
    private final String name;
    private final String tag;
    private final String author;
    private final Float price;

    public BookRequest(String name, Float price, String author, String tag) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.tag = tag;
    }
}