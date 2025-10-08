<%-- 
    Document   : type_list
    Created on : Oct 4, 2025
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Human Type</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body class="container">


        <h1 class="text-center">List of Human Type</h1>

        <table class="table table-striped">
            <tr>
                <th>Type ID</th>
                <th>Type Name</th>
                <th>Tools</th>
            </tr>

            <c:forEach var="i" items="${requestScope.ds}">
                <tr>
                    <td>${i.typeId}</td>
                    <td>${i.typeName}</td>
                    <td>
                        <a href="HumanTypeController?action=update&id=${i.typeId}" 
                           class="btn btn-primary btn-sm">Update</a>

                        <form method="post" action="delete_type" style="display:inline;">
                            <input type="hidden" name="id" value="${i.typeId}">
                            <button type="submit" 
                                    class="btn btn-danger btn-sm"
                                    onclick="return confirm('Are you sure you want to delete this type?');">
                                Delete
                            </button>
                        </form>


                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
