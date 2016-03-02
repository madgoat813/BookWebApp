/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.twm.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
public class AuthorDao implements AuthorDaoStrategy, Serializable {

    private static final String TABLE_NAME = "author";
    private static final String PK_COLUMN_NAME = "author_id";
    private static final String AUTHOR_NAME = "author_name";
    private static final String DATE_ADDED = "date_added";

    @Inject
    private DBStrategy db;
    private String driver;
    private String url;
    private String user;
    private String pwd;

//    private DBStrategy db = new MySqlDBStrategy();
//    private String driver = "com.mysql.jdbc.Driver";
//    private String url = "jdbc:mysql://localhost:3306/book";
//    private String user = "root";
//    private String pwd = "admin";
    
    
    
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

        List<Map<String, Object>> rawData = db.findAllRecords("author", 0);

        List<Author> authors = new ArrayList<>();

        for (Map rec : rawData) {
            Author author = new Author();
            Integer id = new Integer(rec.get("author_id").toString());
            author.setAuthorId(id);
            String name = rec.get("author_name") == null ? "" : rec.get("author_name").toString();
            author.setAuthorName(name);
            Date date = rec.get("date_added") == null ? null : (Date) rec.get("date_added");
            author.setDateAdded(date);
            authors.add(author);
        }

        db.closeConnection();

        return authors;

    }

    @Override
    public Author getAuthorById(Integer authorId) throws SQLException, ClassNotFoundException {
        db.openConnection(driver, url, user, pwd);

        Map<String, Object> rawRec = db.findRecordById("author", "author_id", authorId);
        Author author = new Author();
        author.setAuthorId((Integer) rawRec.get("author_id"));
        author.setAuthorName(rawRec.get("author_name").toString());
        author.setDateAdded((Date) rawRec.get("date_added"));
        db.closeConnection();
        return author;
    }

    @Override
    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException {
        db.openConnection(driver, url, user, pwd);
        Object primaryKey = id;
        int result = db.deleteRecordById("author", "author_id", primaryKey);
        db.closeConnection();
        return result;
    }

    public boolean saveAuthor(Integer authorId, String authorName) throws ClassNotFoundException, SQLException {
        db.openConnection(driver, url, user, pwd);
        boolean result = false;
        
        if(authorId == null || authorId.equals(0)) {
            // must be a new record
            result = db.insertRecord("author", Arrays.asList("author_name","date_added"), 
                                      Arrays.asList(authorName,new Date()));
        } else {
            // must be an update of an existing record
            int recsUpdated = db.updateRecords("author", Arrays.asList("author_name"), 
                                       Arrays.asList(authorName),
                                       "author_id", authorId);
            if(recsUpdated > 0) {
                result = true;
            }
        }
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();

        List<Author> authors = dao.getAuthorList();
        
        System.out.println(authors);
        
       // int deleteComplete = dao.deleteAuthorById(1);
        //System.out.println(deleteComplete);
        //List<Author> author = dao.getAuthorList();
       // System.out.println(author);
       
//        Author ranAuthor = new Author();
//        ranAuthor.setAuthorName("Lucifer");
//        ranAuthor.setDateAdded(new Date());
//        int result = dao.createNewAuthor(ranAuthor);
//        System.out.println(result);
//        authors = dao.getAuthorList();
//        System.out.println(authors);
        
//        List<String> colNames = new ArrayList<>(Arrays.asList("author_id"));
//        List<Object> colValues = new ArrayList<>(Arrays.asList(4));
        //int results = dao.updateAuthorById(4, 1, "Taylor McFall", "1986-03-20");
        //System.out.println(results);
       // authors = dao.getAuthorList();
        //System.out.println(authors);
        
        Author author = dao.getAuthorById(3);
        System.out.println(author);
        authors = dao.getAuthorList();
        System.out.println(authors);
    }
}
