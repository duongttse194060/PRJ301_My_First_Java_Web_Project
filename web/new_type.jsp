<%-- 
    Document   : new_type
    Created on : Oct 1, 2025
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Create New Human Type</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h2 class="text-center">Create New Human Type</h2>
            
            <!-- Form thêm human type -->
            <form method="post" action="new_type">
                <div class="form-group">
                    <label for="id">Type ID:</label>
                    <input type="text" class="form-control" id="id" name="id" required>
                </div>
                
                <div class="form-group">
                    <label for="typeName">Type Name:</label>
                    <input type="text" class="form-control" id="typeName" name="typeName" required>
                </div>
                
                <button type="submit" class="btn btn-primary">Save to Database</button>
            </form>
        </div>
    </body>
</html>
