<%-- 
    Document   : home
    Created on : Feb 6, 2016, 6:26:55 PM
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
        <title>JSP Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw==" 
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <br/>
            <div class="alert alert-info" role="alert">All Authors</div>
            <table class="table">
                <div class="col-md-1">
                    <button type="button" class="btn btn-primary">add</button>
                </div>
                <div class="col-sm-7">
                    <button type="button" class="btn btn-warning">edit</button>
                </div>
                <div class="col-md-4">
                    <button type="button" class="btn btn-danger">delete</button>
                </div>
                <thead><td>ID</td><td>Name</td><td>Date Added</td></thead>
                <c:forEach var="i" items="${authors}" >
                    <tr>
                        <td><c:out value="${ i.authorID }"/></td>
                        <td><c:out value="${ i.authorName }"/></td>
                        <td><c:out value="${ i.dateAdded }"/></td>
                    </tr>
                </c:forEach>
            </table>
            <div class="col-md-1">
                <button type="button" class="btn btn-primary">add</button>
            </div>
            <div class="col-sm-7">
                <button type="button" class="btn btn-warning">edit</button>
            </div>
            <div class="col-md-4">
                <button type="button" class="btn btn-danger">delete</button>
            </div>

        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" 
                integrity="sha256-KXn5puMvxCw+dAYznun+drMdG1IFl3agK0p/pqT9KAo= sha512-2e8qq0ETcfWRI4HJBzQiA3UoyFk6tbNyG+qSaIBZLyW9Xf3sWZHN/lxe9fTh1U45DpPf07yj94KsUHHWe4Yk1A==" 
        crossorigin="anonymous"></script>
    </body>
</html>
