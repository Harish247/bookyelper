package models;

import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Constraint;
import java.util.Date;

/**
 * Created by Harish on 9/1/2016.
 */
@Entity
public class Book {
    private static final long serialVersionUID = 1L;
    @Id
    public Long id;
    @Constraints.Required
    public String name;
    public String category;
    public String publisher;
    public int published_year;

    public static Model.Finder<Long,Book> find = new Model.Finder<Long,Book>(Long.class, Book.class);

    public static PagedList<Book> page(){
        return (PagedList<Book>) find.all();
    }



}
