<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
                <h2 style="text-align: center; font-size: 3.8vw;">Choose a test</h2>
            </div>
        </div>
        
        <div id="menu-block" style="width: 20%; float: left;">
            <div id="menu" style="border-style:solid">
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=SHOW_PROFILE">Profile</a>
                </h3>
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=LOGOUT">Logout</a>
                </h3>
            </div>
        </div>
        
        <div id="main" class="container" style="border-style: solid; width: 60%; float: left">
            <h3 style="font-size: 2.8vw;">Subjects</h3>
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
            <h3 style="font-size: 2.8vw;">Tutors</h3>
            <c:forEach var="item" items="${tutorslist}">
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
