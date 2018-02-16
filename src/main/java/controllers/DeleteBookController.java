package controllers;

import core.BookStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/delete")
public class DeleteBookController {
    private BookStore bookStore;

    public DeleteBookController(BookStore bookStore){
        this.bookStore = bookStore;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String removeBook(
            @QueryParam("id") String id) {
        this.bookStore.remove(id);
        return "Book " + id + "successfully removed";
    }

}
