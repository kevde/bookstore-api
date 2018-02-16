package com.app.darkspot.controllers;

import com.app.darkspot.core.Book;
import com.app.darkspot.core.BookStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/list")
public class ListBooksController {
    private BookStore bookStore;

    public ListBooksController(BookStore bookStore){
        this.bookStore = bookStore;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book[] listBooks() {
        return this.bookStore.getBooks();
    }
}
