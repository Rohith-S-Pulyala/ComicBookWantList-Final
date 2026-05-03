<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ComicBooks4Me - Login</title>
        <link rel="stylesheet" type="text/css" href="styles/main.css">
    </head>
    <body>
        
        
        <div class="login-container">
            <h2>Sign In</h2>
            
            <c:if test="${not empty param.msg}">
                <div style="color: green;">
                    ${param.msg}
                </div>
            </c:if>
            
            <c:if test="${not empty errorMessage}">
                <div style="color: red;">
                    ${errorMessage}
                </div>
            </c:if>
            
            <form action="LoginServlet" method="POST">
                <div class="form-group">
                    <label>Username:</label>
                    <input type="text" name="username" required>
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" name="password" required>
                </div>
                <button type="submit">Login</button>
            </form>
            
            
                
            <p>New Hero In Town? <a href="register.jsp">Sign Up!</a></p>
        </div>
    </body>
</html>
