/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.twm.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
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
    
    public int deleteAuthorByID (Object id) throws ClassNotFoundException, SQLException {
        return dao.deleteAuthorById(id);
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService srv = new AuthorService();
        List<Author> authors = srv.getAuthorList();
        System.out.println(authors);
        
    }
}
