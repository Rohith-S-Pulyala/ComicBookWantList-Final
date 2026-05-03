<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ComicBooks4Me - Register</title>
    </head>
    <body>
        <div class="login-container">
            <form action="RegisterServlet" method="POST">
                <h2>Create an Account</h2>
                
                <c:if test="${not empty errorMessage}">
                    <div style="color: red;">
                        ${errorMessage}
                    </div>
                </c:if>
                
                <div class="form-group">
                    <label>Username:</label>
                    <input type="text" name="username" required>
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" required>
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" name="password" required>
                </div>
                <button type="submit">Register</button>
            </form>
            
            <p>Already have an account? <a href="login.jsp">Login Here!</a></p>
        </div>
    </body>
</html>
