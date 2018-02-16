package com.app.darkspot.controllers;

import com.app.darkspot.core.Book;
import com.app.darkspot.core.BookStore;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AddBookControllerTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig()
                .register(new AddBookController(new BookStore(new Book[0])))
                .register(JacksonFeature.class);
    }

    @Test
    public void addBook() {
        MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
        formData.add("name", "Kevin");
        formData.add("price", "20000");
        formData.add("author", "Stephen King");
        formData.add("tag", "Science Fiction");
        Response response = target("add").request().post(Entity.form(formData));
        System.out.print(response);
        assertThat(response.getStatus(), equalTo(200));
    }
}