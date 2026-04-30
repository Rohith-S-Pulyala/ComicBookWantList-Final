<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!-- Redirects to login.jsp for username and password -->


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ComicBooks4Me - Want List</title>
        <link rel="stylesheet" type="text/css" href="styles/main.css">
    </head>
    <body>
        <header>
            <div class="user-info">
                <p>Welcome, <strong>${sessionScope.user}</strong>!</p>
                <a href="LogoutServlet" class="btn-logout">Sign Out</a>
            </div>
        </header>
        
        <h1>Your Current Want List</h1>
        
        <c:if test="${not empty feedback}">
            <div style="background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; 
                    padding: 15px; margin-bottom: 20px; border-radius: 4px;">
                ${sessionScope.feedback}
            </div>
                <c:remove var="feedback" scope="session" />
        </c:if>
        
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Issue</th>
                    <th>Store</th>
                    <th>Notes</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Java Standard Tag Library embed to add comics to the list. -->
                <c:forEach var="comic" items="${myComics}">
                    <tr>
                        <td>${comic.title}</td>
                        <td>${comic.issueNumber}</td>
                        <td>${comic.storeName != null ? comic.storeName : "Generic Store"}</td>
                        <td>${comic.storeInfo != null ? comic.storeInfo : "No additional details"}</td>
                        <td>
                            <a href="ComicServlet?action=edit&id=${comic.id}">Edit</q>
                            |
                            <a href="ComicServlet?action=delete&id=${comic.id}"
                               style="color: red;"
                               onclick="return confirm ('Are you sure? It is ${comic.title} #${comic.issueNumber} though.')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <a href="index.jsp">Add Another Comic</a>
    </body>
</html>
