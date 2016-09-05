package models;

import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.common.BeanList;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Constraint;
import java.util.Date;

/**
 * Created by Harish on 9/1/2016.
 */
@Entity
public class Book extends Model {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @Constraints.Required
    private String name;
    private String category;
    private String publisher;
    private String author;
    private String ISBN;
    private String language;
    @Column(name="published_year")
    private int publishedYear;
    private String description;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getLanguage() {
        return language;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public static Find<Long,Book> find = new Find<Long,Book>(){};

    public static PagedList<Book> bookPage(int page,int pageSize,String sortBy,String order,String filter){
        return  find.where().ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagedList(page, pageSize);
    }



}
