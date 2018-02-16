package controllers;

import core.Book;
import core.BookStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.UUID;

@Path("/add")
public class AddBookController {
    private BookStore bookStore;

    public AddBookController(BookStore bookStore){
        this.bookStore = bookStore;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Book addBook(
            @FormParam("name") String name,
            @FormParam("author") String author,
            @FormParam("price") Float price,
            @FormParam("tag") String tag
            ) {
        String id = UUID.randomUUID().toString();
        Book newBook = new Book(id, name, price, tag)
                .withAuthor(author)
                .withCreationDate(new Date());
        this.bookStore.add(newBook);
        return newBook;
    }

}
