<%-- 
    Document   : editPage
    Created on : Feb 29, 2016, 4:28:20 AM
    Author     : Taylor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Author</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw==" 
              crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Edit Author Name</h1>

        <form method="POST" action="AuthorController" class="col-md-4">
            <br/>
            <div class="alert alert-info" role="alert">Edit Author</div>
            <table class="table">
                <tr>
                <thead><td>ID</td></thead>
                <td><input type="text" name="authorId" value="${author.authorId}" class="form-control" readonly/></td>
                </tr>
                <tr>
                <thead><td>Name</td></thead>
                <td><input type="text" name="authorName" value="${author.authorName}" class="form-control"/></td>
                </tr>
                <tr>
                <thead><td>Date Added</td></thead>
                <td><input type="text" name="dateAdded" value="${author.dateAdded}" class="form-control" readonly/></td>
                </tr>
                <tr>
                    <input type="submit" value="save" name="action" class="btn btn-primary"/>&nbsp;
                    <input type="submit" value="cancel" name="action" class="btn btn-danger"/>
                </tr>
            </table>
                
            



        </form>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" 
                integrity="sha256-KXn5puMvxCw+dAYznun+drMdG1IFl3agK0p/pqT9KAo= sha512-2e8qq0ETcfWRI4HJBzQiA3UoyFk6tbNyG+qSaIBZLyW9Xf3sWZHN/lxe9fTh1U45DpPf07yj94KsUHHWe4Yk1A==" 
        crossorigin="anonymous"></script>
    </body>
</html>
