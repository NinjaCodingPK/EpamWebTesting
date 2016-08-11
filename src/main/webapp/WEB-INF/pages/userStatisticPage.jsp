<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundles.bundle" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistic page</title>
        <style type="text/css">
            <%@include file="/resources/css/bootstrap.css"%>
        </style>
    </head>
    <body>
        <div style="border-style:solid; background-color:#428bca; margin: auto;" id="header">
            <div>
                <h2 style="text-align: center; font-size: 3.8vw;">Statistic</h2>
            </div>
        </div>
        
        <div id="menu-block" style="width: 20%; float: left;">
            <div id="menu" style="border-style:solid">
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=USER_PAGE">Back</a>
                </h3>
            </div>
        </div>
        
        <div id="main" class="container" style="width: 60%; float: left">
            <table class="table">
                <tr> <th>Tutor</th><th>Subject</th><th>Result</th></tr>
                <c:forEach var="test" items="${testlist}">
                    <tr>
                        <td>${test.key.tutor.name} ${test.key.tutor.surname}</td>
                        <td>${test.key.subject.name}</td>
                        <td>${test.value}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
