<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundles.bundle" />

<html>
<head>
    <title>Welcome</title>
    <style type="text/css">
        <%@include file="/resources/css/bootstrap.css"%>
    </style>
</head>
<body>
    <div style="border-style:solid; background-color:#428bca; margin: auto;" id="header">
            <div>
                <h2 style="text-align: center; font-size: 3.8vw;">
                    <fmt:message key="head.admin.page"/>
                </h2>
            </div>
        </div>
        
        <div id="menu-block" style="width: 20%; float: left;">
            <div id="menu" style="border-style:solid">
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=SHOW_PROFILE"><fmt:message key="button.profile"/></a>
                </h3>
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=LOGOUT"><fmt:message key="button.logout"/></a>
                </h3>
            </div>
        </div>
        
        <div id="main" class="container" style="width: 60%; float: left">
            <table class="table">
                <tr> 
                    <th><fmt:message key="label.subject"/></th>
                    <th><fmt:message key="label.delete"/></th></tr>
                    <c:forEach var="subject" items="${subjectlist}">
                    <tr>
                        <td>${subject.name}</td>
                        <td><a href="./Controller?command=DELETE_SUBJECT&subjectId=${subject.id}">
                                <fmt:message key="label.delete"/>
                            </a></td>          
                    </tr>
                    </c:forEach>
            </table>
            <form action = "./Controller" method="POST" class="form-horizontal">
                <input type="hidden" name="command" value="ADD_SUBJECT" />
                <input type="text" name="subjectname"/>
                <button type="submit" class="btn btn-primary btn-lg">
                    <fmt:message key="button.addsubj"/>
                </button>
            </form>
        </div>         
</body>
</html>
