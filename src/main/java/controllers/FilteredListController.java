package controllers;

import core.Book;
import core.BookStore;
import filters.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/filtered")
public class FilteredListController {
    private BookStore bookStore;

    public FilteredListController(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book[] filterBooks(
            @QueryParam("tag") @DefaultValue("") String tag,
            @QueryParam("price") @DefaultValue("") String priceString,
            @QueryParam("date") @DefaultValue("") String dateString,
            @QueryParam("author") @DefaultValue("") String author,
            @QueryParam("limit") @DefaultValue("") String limitString,
            @QueryParam("page") @DefaultValue("") String pageString
    ) throws ParseException {
        List<Filter> filters = new ArrayList<>();
        if (!tag.isEmpty()) {
            filters.add(new TagFilter(tag));
        }

        if (!priceString.isEmpty()) {
            Float price = Float.parseFloat(priceString);
            filters.add(new PriceFilter(price));
        }

        if (!author.isEmpty()) {
            filters.add(new AuthorFilter(author));
        }

        if (!dateString.isEmpty()) {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
            filters.add(new DateFilter(date));
        }

        if (!limitString.isEmpty()) {
            LimitFilter limitFilter = new LimitFilter(Integer.parseInt(limitString));
            if (!pageString.isEmpty()) {
                limitFilter.setPage(Integer.parseInt(pageString));
            }
            filters.add(limitFilter);
        }

        Filter[] bookFilters = filters.toArray(new Filter[0]);
        return this.bookStore.filter(bookFilters);
    }
}
