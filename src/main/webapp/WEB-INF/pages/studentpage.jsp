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
                <h2 style="text-align: center; font-size: 3.8vw;"><fmt:message key="head.studpage"/></h2>
            </div>
        </div>
        
        <div id="menu-block" style="width: 20%; float: left;">
            <div id="menu" style="border-style:solid">
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=SHOW_PROFILE"><fmt:message key="button.profile"/></a>
                </h3>
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=SHOW_STATISTIC"><fmt:message key="label.statistic"/></a>
                </h3>
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=LOGOUT"><fmt:message key="button.logout"/></a>
                </h3>
            </div>
        </div>
        
        <div id="main" class="container" style="border-style: solid; width: 60%; float: left">
            <h3 style="font-size: 2.8vw;"><fmt:message key="label.subject"/></h3>
            <c:forEach var="item" items="${subjectlist}">
                <br>
                <h5 style="font-size: 1.5vw;">
                    <a href="./Controller?command=SHOW_TESTS_BY_SUBJECT&subject=${item.id}">
                        ${item.name}
                    </a> 
                </h5>
            </c:forEach>
            <hr color="black" style="float: none; width: 100%"/>
            <br>
            <h3 style="font-size: 2.8vw;"><fmt:message key="label.tutors"/></h3>
            <c:forEach var="item" items="${tutorlist}">
                <br>
                <h5 style="font-size: 1.5vw;">
                    <a href="./Controller?command=SHOW_TESTS_BY_TUTOR&tutor=${item.id}">
                    ${item.name} ${item.surname}
                    </a> 
                </h5>
            </c:forEach>
        </div>         
</body>
</html>
