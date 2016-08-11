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
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="bundles.bundle" />
    <div style="border-style:solid; background-color:#428bca; margin: auto;" id="header">
            <div>
                <h2 style="text-align: center; font-size: 3.8vw;">
                    <fmt:message key="head.tutor.page"/>
                </h2>
            </div>
        </div>
        
        <div id="menu-block" style="width: 20%; float: left;">
            <div id="menu" style="border-style:solid">
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=SHOW_PROFILE">
                       <fmt:message key="button.profile"/> 
                    </a>
                </h3>
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=LOGOUT">
                        <fmt:message key="button.logout"/> 
                    </a>
                </h3>
            </div>
        </div>
        
        <div id="main" class="container" style="width: 60%; float: left">
            <table class="table">
                <tr> 
                    <th><fmt:message key="label.tutor"/></th>
                    <th><fmt:message key="label.subject"/></th>
                    <th><fmt:message key="label.toughness"/></th>
                    <th><fmt:message key="label.update"/></th>
                    <th><fmt:message key="label.delete"/></th>
                </tr>
                <c:forEach var="test" items="${resultlist}">
                    <tr>
                        <td>${test.key.tutor.name} ${test.key.tutor.surname}</td>
                        <td>${test.key.subject.name}</td>
                        <td>${test.value}</td>
                        <td><a href="./Controller?command=TO_UPDATE_TEST&testId=${test.key.id}">
                                <fmt:message key="label.update"/>
                            </a>
                        </td>
                        <td><a href="./Controller?command=DELETE_TEST&testId=${test.key.id}">
                                <fmt:message key="label.delete"/>
                            </a>
                        </td>          
                    </tr>
                </c:forEach>
            </table>          
            <form action = "./Controller" method="POST" class="form-horizontal">
                <input type="hidden" name="command" value="ADD_TEST" />
                <button type="submit" class="btn btn-primary btn-lg">
                    <fmt:message key="button.addtest"/>
                </button>
            </form>
            
        </div>         
</body>
</html>
