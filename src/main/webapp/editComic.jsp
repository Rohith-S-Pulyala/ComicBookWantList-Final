<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ComicBooks4Me - Edit</title>
        <link rel="stylesheet" type="text/css" href="styles/main.css">
    </head>
    <body>        
        <form action="ComicServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="comicId" value="${comic.id}">
            
            <label>Title:</label> 
            <input type="text" name="title" value="${comic.title}">
            
            <label>Issue:</label> 
            <input type="text" name="issueNumber" value="${comic.issueNumber}">

            <label>Store:</label>
            <input type="text" name="storeName" value="${comic.storeName}">

            <label>Notes:</label>
            <textarea name="storeInfo">${comic.storeInfo}</textarea>
            
            <button type="submit">Save Changes</button>
            <a href="ComicServlet">Cancel</a>
        </form>
    </body>
</html>
