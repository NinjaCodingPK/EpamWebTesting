<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundles.bundle" />
<!DOCTYPE html>

<html>
    <head>
        <style type="text/css">
            <%@include file="/resources/css/bootstrap.css"%>
        </style>
        <title>Tests</title>
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
                    <a href="./Controller?command=USER_PAGE"><fmt:message key="button.back"/></a>
                </h3>
            </div>
        </div>
        
        <div id="main" class="container" style="width: 60%; float: left">
            <table class="table">
                <tr> 
                    <th><fmt:message key="label.tutor"/></th>
                    <th><fmt:message key="label.toughness"/></th>
                    <th><fmt:message key="label.subject"/></th>
                    <th><fmt:message key="label.action"/></th>
                </tr>
                <c:forEach var="test" items="${testlist}">
                    <tr>
                        <td>${test.key.tutor.name} ${test.key.tutor.surname}</td>
                        <td>${test.value}</td>
                        <td>${test.key.subject.name}</td>
                        <td><a href="./Controller?command=GET_TEST&testId=${test.key.id}">
                            <fmt:message key="label.start"/>
                        </a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
