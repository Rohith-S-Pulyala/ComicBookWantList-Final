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
            </div>
            <a href="LogoutServlet" class="btn-logout">Sign Out</a>
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
                    <th>Publisher</th>
                    <th>Author</th>
                    <th>Illustrator</th>
                    <th>Variant?</th>
                    <th>Store</th>
                    <th>Owner</th>
                    <th>Notes</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Java Standard Tag Library embed to add comics to the list. -->
                <c:forEach var="comic" items="${myComics}">
                    <tr>
                        <td><c:out value="${comic.title}" /></td>
                        <td><c:out value="${comic.issueNumber}" /></td>
                        <td><c:out value="${comic.publisher}" /></td>
                        <td><c:out value="${comic.author}" /></td>
                        <td><c:out value="${comic.illustrator}" /></td>
                        <td>${comic.isVariant ? "Yes" : "No"}</td>
                        <td><c:out value="${comic.storeName}"  default="N/A" /></td>
                        <td><c:out value="${comic.ownerName}" default="N/A" /></td>
                        <td style="max-width: 200px; overflow: hidden; text-overflow: ellipsis;">
                            <c:out value="${comic.storeInfo}"  default="No additional details"/>
                        </td>
                        <td>
                            <a href="ComicServlet?action=edit&id=${comic.id}">Edit</a>
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
        <a href="index.jsp">Add Comic</a>
    </body>
</html>
