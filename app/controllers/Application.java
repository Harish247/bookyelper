package controllers;

import com.google.inject.Inject;
import play.mvc.*;
import play.data.*;

import static play.data.Form.*;

import views.html.*;

import models.*;

/**
 * Manage a database of computers
 */
public class Application extends Controller {

    @Inject private FormFactory formFactory;


    /**
     * This result directly redirect to application home.
     */
    public Result GO_HOME = redirect(
        routes.Application.list(0, "name", "asc", "")
    );


    public Result book_home =redirect(
            routes.Application.bookList(0,"name","asc","")
    );
    /**
     * Handle default path requests, redirect to computers list
     */
    public Result index() {
        return book_home;
    }

    /**
     * Display the paginated list of computers.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Computer.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }

    public Result bookList(int page,String sortBy,String order,String filter){
        return ok(
                booklist.render(Book.bookPage(page, 10, sortBy, order, filter),
                        sortBy, order, filter
                )
        );
    }
    /*public Result bookList(){
        return ok(
                booklist.render(Book.bookPage())
        );
    }*/

    /*public Result getBookDetails(Long id) {
        Form<Book> bookForm = form(Book.class).fill(
                Book.find.byId(id)
        );
        return ok(
                editForm.render(id, bookForm)
        );
    }*/
    /**
     * Display the 'edit form' of a existing Computer.
     *
     * @param id Id of the computer to edit
     */
    public Result edit(Long id) {
        Form<Computer> computerForm = form(Computer.class).fill(
            Computer.find.byId(id)
        );
        return ok(
            editForm.render(id, computerForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the computer to edit
     */
    public Result update(Long id) {
        Form<Computer> computerForm = form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(editForm.render(id, computerForm));
        }
        computerForm.get().update();
        flash("success", "Computer " + computerForm.get().name + " has been updated");
        return GO_HOME;
    }





    
    /**
     * Display the 'new computer form'.
     */
    public Result create() {
        Form<Computer> computerForm = form(Computer.class);
        return ok(
            createForm.render(computerForm)
        );
    }

    /**
     * Handle the 'new computer form' submission 
     */
    public Result save() {
        Form<Computer> computerForm = form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(createForm.render(computerForm));
        }
        computerForm.get().save();
        flash("success", "Computer " + computerForm.get().name + " has been created");
        return GO_HOME;
    }


    public Result createBook(){
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(
                bookCreateForm.render(bookForm)
        );
    }


   public Result saveBook(){
       Form<Book>  bookForm = formFactory.form(Book.class).bindFromRequest();
       if(bookForm.hasErrors()){
           return badRequest(bookCreateForm.render(bookForm));
       }
       bookForm.get().save();
       return book_home;
   }

   public Result updateBook(Long id){
       Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
       if(bookForm.hasErrors()){
           return badRequest(bookEditForm.render(id,bookForm));
       }
       Book book = bookForm.get();
       book.setId(id);
       book.update();
       return book_home;
   }

   public Result editBook(Long id){
       Form<Book> bookForm = formFactory.form(Book.class).fill(
               Book.find.byId(id)
       );
       return ok(bookEditForm.render(id,bookForm));
   }

   public Result deleteBook(Long id){
       Book.find.ref(id).delete();
       return book_home;
   }

    /**
     * Handle computer deletion
     */
    public Result delete(Long id) {
        Computer.find.ref(id).delete();
        flash("success", "Computer has been deleted");
        return GO_HOME;
    }
    

}
            
