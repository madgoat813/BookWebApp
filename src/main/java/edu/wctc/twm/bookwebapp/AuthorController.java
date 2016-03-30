package edu.wctc.twm.bookwebapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.wctc.twm.bookwebapp.model.Author;
import edu.wctc.twm.bookwebapp.model.AuthorService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Taylor
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String LIST_PAGE = "/index.jsp";
    private static final String ADD_PAGE = "/addPage.jsp";
    private static final String EDIT_PAGE = "/editPage.jsp";
    private static final String EDIT_ERROR = "/testPage.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_EDIT_DELETE_ACTION = "addEditDelete";
    private static final String SUBMIT_ACTION = "submit";
    private static final String ACTION_PARAM = "action";
    private static final String SAVE_ACTION = "save";
    private static final String ADD_ACTION = "add";
    private static final String CANCEL_ACTION = "cancel";

    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private String dbJndiName;

    @Inject
    private AuthorService aServe;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        String destination = LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);

        // use init parameters to config database connection
        configDbConnection();

        try {
            // use init parameters to config database connection
            configDbConnection();
            
            OUTER:
            switch (action) {
                case LIST_ACTION:
                    this.refreshList(request, aServe);
                    destination = LIST_PAGE;
                    break;
                case ADD_EDIT_DELETE_ACTION:
                    String subAction = request.getParameter(SUBMIT_ACTION);
                    switch (subAction) {
                        case "new": {
                            // must be add or edit, go to addEdit page
                            String[] authorIds = request.getParameterValues("authorId");
                            destination = ADD_PAGE;
                            break;
                        }
                        case "edit": {
                            String[] authorIds = request.getParameterValues("authorId");
                            if (authorIds == null || authorIds.length > 1) {
                                this.refreshList(request, aServe);
                                destination = EDIT_ERROR;
                                break OUTER;
                            }
                            String authorId = authorIds[0];
                            Author author = aServe.getAuthorById(authorId);
                            request.setAttribute("author", author);
                            destination = EDIT_PAGE;
                            break;
                        }
                        default: {
                            // must be DELETE
                            // get array based on records checked
                            String[] authorIds = request.getParameterValues("authorId");
                            for (String id : authorIds) {
                                aServe.deleteAuthorById(id);
                            }
                            this.refreshList(request, aServe);
                            destination = LIST_PAGE;
                            break;
                        }
                    }
                    break;
                case SAVE_ACTION:
                    String authorName = request.getParameter("authorName");
                    String authorId = request.getParameter("authorId");
                    aServe.saveAuthor(authorId, authorName);
                    this.refreshList(request, aServe);
                    destination = LIST_PAGE;
                    break;
                case ADD_ACTION:
                    String aName = request.getParameter("authorName");
                    aServe.addAuthor(aName);
                    this.refreshList(request, aServe);
                    destination = LIST_PAGE;
                    break;
                case CANCEL_ACTION:
                    this.refreshList(request, aServe);
                    destination = LIST_PAGE;
                    break;
                default:
                    // no param identified in request, must be an error
                    request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                    destination = "/testPage.jsp";
                    break;
            }

        } catch (Exception e) {

        }

        // Forward to destination page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);

        dispatcher.forward(request, response);

    }

    private void configDbConnection() throws NamingException, ClassNotFoundException, SQLException {
        if (dbJndiName == null) {
            aServe.getDao().initDao(driverClass, url, userName, password);
        } else {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dbJndiName);
            aServe.getDao().initDao(ds);
        }
    }

    private void refreshList(HttpServletRequest request, AuthorService aServe) throws Exception {
        List<Author> authors = aServe.getAuthorList();
        request.setAttribute("authors", authors);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
//        driverClass = getServletContext().getInitParameter("db.driver.class");
//        url = getServletContext().getInitParameter("db.url");
//        userName = getServletContext().getInitParameter("db.username");
//        password = getServletContext().getInitParameter("db.password");
        dbJndiName = getServletContext().getInitParameter("db.jndi.name");
    }
}
