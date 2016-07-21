<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Subjects
        
        <c:forEach var="item" items="${subjectlist}">
            <br>
            <a href="./Controller?command=SHOW_TESTS&subject=${item.name}">${item.name}</a> 
        </c:forEach>
    </body>
</html>
