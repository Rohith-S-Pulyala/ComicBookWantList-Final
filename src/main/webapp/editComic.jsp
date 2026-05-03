<!--Editing Comic fields-->

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
            
            <h3>Edit Comic</h3>
            
            <div class="form-group">
                <label>Title:</label> 
                <input type="text" name="title" value="${comic.title}">
            </div>
            
            <div class="form-group">
                <label>Issue:</label> 
                <input type="text" name="issueNumber" value="${comic.issueNumber}">
            </div>

            <div class="form-group">
                <label>Publisher:</label> 
                <input type="text" name="publisher" value="${comic.publisher}">
            </div>    
            
            <div class="form-group">
                <label>Author:</label> 
                <input type="text" name="author" value="${comic.author}">
            </div>
            
            <div class="form-group">
                <label>Illustrator:</label> 
                <input type="text" name="illustrator" value="${comic.illustrator}">
            </div>
            
            <div class="form-group">
                <label>Variant Cover:</label> 
                <select name="isVariant">
                    <option value="false">No</option>
                    <option value="true">Yes</option>
                </select>
            </div>
            
            
            <div class="form-group">
                <label>Store:</label>
                <input type="text" name="storeName" value="${comic.storeName}">
            </div>
            
            <div class="form-group">
                <label>Owner:</label>
                <input type="text" name="ownerName" value="${comic.ownerName}">
            </div>            

            <div class="form-group">
                <label>Notes:</label>
                <textarea name="storeInfo">${comic.storeInfo}</textarea>
            </div>            
            
            <button type="submit">Save Changes</button>
            <a href="ComicServlet">Cancel</a>
        </form>
    </body>
</html>
