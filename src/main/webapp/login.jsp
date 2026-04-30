<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <form action="LoginServlet" method="POST">
                <div class="form-group">
                    <label>Username:</label>
                    <input type="text" name="username" required>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" required>
                </div>
                <button type="submit">Login</button>
            </form>
            
            <c:if test="${not empty errorMessage}">
                <div style="color: red;">
                    ${errorMessage}
                </div>
            </c:if>
        </div>
    </body>
</html>
