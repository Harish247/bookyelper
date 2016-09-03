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
    public Long id;
    @Constraints.Required
    public String name;
    public String category;
    public String publisher;

    @Column(name="published_year")
    public int publishedYear;

    public static Find<Long,Book> find = new Find<Long,Book>(){};

    public static BeanList<Book> bookPage(){
        return (BeanList<Book>) find.all();
    }



}
