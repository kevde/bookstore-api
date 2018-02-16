package com.app.darkspot;

import com.app.darkspot.controllers.*;
import com.app.darkspot.core.Book;
import com.app.darkspot.core.BookStore;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/")
public class Main extends ResourceConfig {

    public Main() {
        BookStore bookStore = new BookStore(new Book[0]);
        register(new AddBookController(bookStore))
                .register(new DeleteBookController(bookStore))
                .register(new FilteredListController(bookStore))
                .register(new ListBooksController(bookStore))
                .register(new RenameTagController(bookStore))
                .register(JacksonFeature.class);
        ;
    }
}
