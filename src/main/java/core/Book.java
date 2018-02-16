package core;

import java.io.File;
import java.util.Date;

public class Book {

    private final String id;
    private final String name;
    private final Float price;
    public String author;
    public String tag;
    public File document;
    public Date creationDate;

    public Book(String id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Book(String id, String name, Float price, String tag) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tag = tag;
    }

    public Book withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Book withAuthor(String author) {
        this.author = author;
        return this;
    }

    public Book addDocument(File file) {
        this.document = file;
        return this;
    }

    public Book renameTag(String newTag) {
        this.tag = newTag;
        return this;
    }

    public Float getPrice() {
        return this.price;
    }

    public String getId() {
        return this.id;
    }
}
