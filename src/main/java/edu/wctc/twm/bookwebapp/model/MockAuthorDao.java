/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.twm.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author Taylor
 */
@Dependent
@Alternative
public class MockAuthorDao implements AuthorDaoStrategy, Serializable{
    
    private DBStrategy db;
    private List<Author> authors;

    public MockAuthorDao() {
        Author taylor = new Author(101);
        Author jim = new Author(102);
        Author jake = new Author(103);
       taylor.setAuthorName("Taylor McFall");
       jim.setAuthorName("Jim Lindenburg");
       jake.setAuthorName("Jake Boldt");
       authors = new ArrayList<Author>(3);
       authors.add(taylor);
       authors.add(jim);
       authors.add(jake);
    }
    
    public List getAuthors(){
        return authors;
    }
    
    public int deleteAuthorById(Object id) {
        return 1;
    }

    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        return authors;
    }

    @Override
    public DBStrategy getDb() {
        return db;
    }

    @Override
    public void setDb(DBStrategy db) {
        this.db = db;
    }

    @Override
    public void initDao(String driver, String url, String user, String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDriver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDriver(String driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUser(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPwd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPwd(String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

