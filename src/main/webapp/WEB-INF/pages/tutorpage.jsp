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
                <h2 style="text-align: center; font-size: 3.8vw;">My tests</h2>
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
            <table class="table">
                <tr> <th>Tutor</th><th>Subject</th><th>toughness</th><th>Update</th><th>Delete</th></tr>
                <c:forEach var="test" items="${resultlist}">
                    <tr>
                        <td>${test.key.tutor.name} ${test.key.tutor.surname}</td>
                        <td>${test.key.subject.name}</td>
                        <td>${test.value}</td>
                        <td><a href="./Controller?command=TO_UPDATE_TEST&testId=${test.key.id}">Update</a></td>
                        <td><a href="./Controller?command=DELETE_TEST&testId=${test.key.id}">Delete</a></td>          
                    </tr>
                </c:forEach>
            </table>          
            <form action = "./Controller" method="POST" class="form-horizontal">
                <input type="hidden" name="command" value="ADD_TEST" />
                <button type="submit" class="btn btn-primary btn-lg">AddTest</button>
            </form>
            
        </div>         
</body>
</html>
