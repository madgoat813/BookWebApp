/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.twm.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Taylor
 */
@SessionScoped
public class AuthorService implements Serializable{
    @Inject
    private AuthorDaoStrategy dao;
    
    //private AuthorDaoStrategy dao = new AuthorDao();

    public AuthorService() {
    }
    
    

    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }
    
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        
        return dao.getAuthorList();
    }
    
    public int deleteAuthorById (Object id) throws ClassNotFoundException, SQLException {
        return dao.deleteAuthorById(id);
    }
    
    public Author getAuthorById(String authorId) throws SQLException, ClassNotFoundException  {
        return dao.getAuthorById(Integer.parseInt(authorId));
    }
    
    public void saveOrUpdateAuthor(String authorId, String authorName) throws ClassNotFoundException, SQLException {
        Integer id = null;
        if (authorId == null || authorId.isEmpty()) {
            id = null;
        } else {
            id = Integer.parseInt(authorId);
        }
        dao.saveAuthor(id, authorName);
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService srv = new AuthorService();
        List<Author> authors = srv.getAuthorList();
        System.out.println(authors);
        
        //int msg = srv.deleteAuthorById(6);
//        System.out.println(msg);
//        authors = srv.getAuthorList();
//        System.out.println(authors);

//        String msg = srv.createNewAuthor("Taylor McFall", new Date());
//        System.out.println(msg);
//        authors = srv.getAuthorList();
//        System.out.println(authors);

//        String msg = srv.updateAuthorById(5,1,"Taylor McFall", new Date());
//        System.out.println(msg);
//        authors = srv.getAuthorList();
//        System.out.println(authors);

        Author author = srv.getAuthorById("1");
        System.out.println(author);
        authors = srv.getAuthorList();
        System.out.println(authors);
        
    }
}
