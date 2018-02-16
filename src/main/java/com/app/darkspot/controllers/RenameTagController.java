package com.app.darkspot.controllers;

import com.app.darkspot.core.Book;
import com.app.darkspot.core.BookStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/rename")
public class RenameTagController {
    private BookStore bookStore;

    public RenameTagController(BookStore bookStore){
        this.bookStore = bookStore;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Book renameTag(
            @FormParam("id") String id,
            @FormParam("tag") String tag
    ) {
        Book book = this.bookStore.renameTag(id, tag);
        return book;
    }

}
