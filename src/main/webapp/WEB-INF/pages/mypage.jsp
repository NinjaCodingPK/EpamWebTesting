<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundles.bundle" />

<html>
<head>
    <title>Main</title>
<!-- %@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %> -->
    <!--<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"> -->
    <style type="text/css">
        <%@include file="/resources/css/bootstrap.css"%>
    </style>
</head>
<body>
    <h1>Hello ${name}</h1>

    <table class="table">
        <tr>
            <td>Id</td>
        </tr>
        <c:forEach items="${list}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.text}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
