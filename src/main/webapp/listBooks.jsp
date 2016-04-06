<%-- 
    Document   : listBooks
    Created on : Apr 4, 2016, 5:02:46 PM
    Author     : Taylor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book List</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw==" 
              crossorigin="anonymous">
    </head>
    <body>
        <form method="POST" action="BookController?action=addEditDelete">
            <br/>
            <div class="alert alert-info" role="alert">All Books</div>
            <table class="table">
                <div class="col-md-2">
                    <input type="submit" class="btn btn-primary" value="new" name="submit">
                </div>
                <div class="col-md-5">
                    <input type="submit" class="btn btn-warning" value="edit" name="submit">
                </div>
                <div class="col-md-1">
                    <input type="submit" class="btn btn-danger" value="delete" name="submit">
                </div>
                <thead><td>ID</td><td>Name</td><td>Isbn</td><td>Author Id</td></thead>
                <c:forEach var="i" items="${books}" >
                    <tr>
                        <td><input type="checkbox" name="bookId" value="${i.bookId}" /></td>
                        <td>${ i.bookName }</td>
                        <td>${ i.isbn }</td>
                        <td>${ i.authorId }</td>
                    </c:forEach>
            </table>
            <div class="col-md-2">
                <input type="submit" class="btn btn-primary" value="new" name="submit">
            </div>
            <div class="col-md-5">
                <input type="submit" class="btn btn-warning" value="edit" name="submit">
            </div>
            <div class="col-md-1">
                <input type="submit" class="btn btn-danger" value="delete" name="submit">
            </div>
        </form>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" 
                integrity="sha256-KXn5puMvxCw+dAYznun+drMdG1IFl3agK0p/pqT9KAo= sha512-2e8qq0ETcfWRI4HJBzQiA3UoyFk6tbNyG+qSaIBZLyW9Xf3sWZHN/lxe9fTh1U45DpPf07yj94KsUHHWe4Yk1A==" 
        crossorigin="anonymous"></script>
    </body>
</html>

