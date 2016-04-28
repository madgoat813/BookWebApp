<%-- 
    Document   : header
    Created on : Mar 22, 2016, 10:50:43 PM
    Author     : Taylor
--%>

<nav class='navbar navbar-default'>
    <div class='container'>
        <div class="navbar-header">

            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <sec:authorize access="hasAnyRole('ROLE_MGR', 'ROLE_USER')">
            <a href="home.jsp" class="navbar-brand" style="text-align: center">Home</a>
            </sec:authorize>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav">
                <li>
                    <a href="AuthorController?action=list"><sec:authorize access="hasAnyRole('ROLE_MGR')">Authors</sec:authorize></a>
                </li>
                <li>
                    <a href="BookController?action=list"><sec:authorize access="hasAnyRole('ROLE_MGR')">Books</sec:authorize></a>
                </li>
                <li>
                    <a href="CheckoutController?action=list"><sec:authorize access="hasAnyRole('ROLE_MGR','ROLE_USER')">Shopping Cart</sec:authorize></a>
                </li>
            </ul>
        </div>
    </div>
</nav>
