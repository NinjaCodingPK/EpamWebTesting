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
        <style type="text/css">
            #rans { 
                background-color: #32CD32;
                color: black;
            }
            #wans { 
                background-color: #FF4500;
                color: black;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test update</title>
    </head>
    <body>
        <div style="border-style:solid; background-color:#428bca; margin: auto;" id="header">
            <div>
                <h2 style="text-align: center; font-size: 3.8vw;">
                    <fmt:message key="label.testupdate"/>
                </h2>
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
            <form action = "./Controller" method="POST" class="form-horizontal">
                <input type="hidden" name="command" value="UPDATE_TEST" />
                <input type="hidden" name="subjectId" value="${test.id}" />
                <fmt:message key="label.subject"/>:
                <!--<div class="dropdown">-->
                    <select name="subject" class="selectpicker show-tick" onchange="submit()">
                        <c:forEach var="item" items="${subjects}">
                            <c:if test = "${test.subject.name == item.name}">
                                <option value="${item.name}" selected>"${item.name}"</option>
                            </c:if>
                            <c:if test = "${test.subject.name != item.name}">
                                <option value="${item.name}">"${item.name}"</option>
                            </c:if>
                        </c:forEach>
                    </select>
                <!--</div>-->
                <!--<button type="submit" class="btn btn-primary btn-lg">
                    <fmt:message key="button.apply"/>
                </button> -->
            </form>
                <table class="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="label.question"/></th>
                            <th><fmt:message key="label.toughness"/></th>
                            <th><fmt:message key="label.update"/></th>
                            <th><fmt:message key="label.delete"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${test.tasks}">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.text}</td>
                                <td>${item.toughness}</td>
                                <td><a href="./Controller?command=TO_UPDATE_TASK&taskId=${item.id}&testId=${test.id}"><fmt:message key="label.update"/></a></td>
                                <td><a href="./Controller?command=DELETE_TASK&taskId=${item.id}&testId=${test.id}"><fmt:message key="label.delete"/></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>        
            <form action = "./Controller" method="POST">
                <input type="hidden" name="command" value="TO_ADD_TASK"/>
                <input type="hidden" name="taskId" value="${test.id}"/>
                <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="button.addtask"/></button>
            </form>
        </div>      
    </body>
</html>
