<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!-- Redirects to login.jsp for username and password -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
    My FIRST capability so far is Form Handling and Validation. 
    This is for when the user enters a wrong data type when 
    inputting the issue number. They could input a string like
    sixty one and the validator in the servlet catches the 
    NumberFormatException.
--%>

<%--
    My SECOND capability so far is integrating the web app with a database 
    using MySQL for Data Storage and retrieval. I made a table for comics to 
    put in the want list and another to get information on the stores that they 
    are located in. I gave the comics and stores a one to many relationship 
    because one comic can be in many stores as copies of itself. 
    My only regret was that I named the want list table want_list 
    instead of comics.
--%>

<%--
    My THIRD capability so far is adding Authentication and Session Management.
    This capability is added so people can log in on their own accounts and 
    view their own want list. Their want list data can be password protected 
    so no one else will add a comic that they will not want. If the user logs 
    out and logs back in, their data will be safe.
--%>

<%--
    My FOURTH capability so far has already been added with my first capability, 
    that is Dynamic Web Content to be added such as Expression Language and Java
    Standard Tag Library. This is to showcase the data processed to my Want List 
    through the servlet and originally from or added to the integrated database.
--%>

<%--
    My FIFTH capability so far is responsive design for the Want List Table. 
    The table when showed on mobile devices will have each row in the stacked cards style.
    I am also working on responsive design for index.jsp which currently is where the
    user adds comics to their want list as well as the store they plan to get it from.
--%>

<%--
    My SIXTH capability so far was supposed to be deploying to the tomcat server, 
    yet it is adding an update and delete feature. However, I still need to add 
    more columns and update the update feature. I want to add more finishing 
    touches before I deploy to Tomcat. The project will be deployed this week, 
    before May 3rd, the Final Project Due Date. But as of right now, 
    I need to add some finishing touches, such as updating, deleting,
    adding more columns. Updating and Deleting are there because the reader may 
    not want to read the comic anymore or got the wrong information on it.
    I have a ways to go in my organization of this web app before 
    I can deploy to Tomcat.
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ComicBooks4Me - Add</title>
        <link rel="stylesheet" type="text/css" href="styles/main.css">
    </head>
    <body>
        <div>
            <%-- Displays the message sent from the Servlet--%>
            <p style="color: red;">${feedback}</p>
        </div>
        
        <a href="ComicServlet">View Want List</a>
        
        <form action="ComicServlet" method="POST">
            
            <h3>Comic Information</h3>
            
            <div class="form-group">
                <label>Comic Title:</label>
                <input type="text" name="comicTitle" required>
            </div>
            
            <div class="form-group">
                <label>Issue Number:</label>
                <input type="text" name="issueNumber">
            </div>

            <div class="form-group">
                <label>Publisher:</label>
                <input type="text" name="publisher">
            </div>

            <div class="form-group">
                <label>Author:</label>
                <input type="text" name="author">
            </div>

            <div class="form-group">
                <label>Illustrator:</label>
                <input type="text" name="illustrator">
            </div>

            <div class="form-group">
                <label>Variant Cover:</label>
                <select name="isVariant">
                    <option value="false">No</option>
                    <option value="true">Yes</option>
                </select>
            </div>

            <h3>Store Information</h3>
            
            <div class="form-group">
                <label>Store Name:</label>
                <input type="text" name="storeName">
            </div>
            
            <div class="form-group">
                <label>Owner Name:</label>
                <input type="text" name="ownerName">
            </div>

            <div class="form-group">
                <label>Additional Info:</label>
                <textarea name="storeInfo"></textarea>
            </div>
            
            <button type="submit">Add To Want List</button>
        </form>
    </body>
</html>
