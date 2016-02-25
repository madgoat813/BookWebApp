/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.twm.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Taylor
 */
@Dependent
public class AuthorDao implements AuthorDaoStrategy, Serializable{
    @Inject
    private DBStrategy db;
    private String driver;
    private String url;
    private String user;
    private String pwd;

    public AuthorDao() {
    }

    @Override
    public void initDao(String driver, String url, String user, String pwd) {
        setDriver(driver);
        setUrl(url);
        setUser(user);
        setPwd(pwd);
    }
    
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public DBStrategy getDb() {
        return db;
    }

    public void setDb(DBStrategy db) {
        this.db = db;
    }
    
    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
       db.openConnection(driver, url, user, pwd);
       
       List<Map<String,Object>> rawData = db.findAllRecords("author",0);
       
       List<Author> authors = new ArrayList<>();
       
       for(Map rec : rawData) {
           Author author = new Author();
           Integer id = new Integer(rec.get("author_id").toString());
           author.setAuthorID(id);
           String name = rec.get("author_name") == null ? "" : rec.get("author_name").toString();
           author.setAuthorName(name);
           Date date = rec.get("date_added") == null ? null : (Date)rec.get("date_added");
           author.setDateAdded(date);
           authors.add(author);
       }
       
       db.closeConnection();
       
       return authors;
       
       
    }
    
    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException {
        db.openConnection(driver, url, user, pwd);
        int result = db.deleteById("author", "author_id", id);
        db.closeConnection();
        return result;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
        List<Author> authors = dao.getAuthorList();
        System.out.println(authors);
    }

    
}
