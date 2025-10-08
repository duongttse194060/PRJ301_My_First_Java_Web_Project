<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="text-danger">Something went wrong!</h2>
    <p>${requestScope.error}</p>
    <a href="list_type" class="btn btn-primary">Back to List</a>
</body>
</html>
