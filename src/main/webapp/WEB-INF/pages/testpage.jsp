<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundles.bundle" />
<!DOCTYPE html>

<html>
    <head>
        <style type="text/css">
            <%@include file="/resources/css/bootstrap.css"%>
        </style>
        <style type="text/css">body { padding-top: 70px; }</style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test</title>
    </head>
    <body>
        <div class = "navbar navbar-inverse navbar-fixed-top">
            <div class ="container">
                <div class ="row">
                    <div class="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class ="row">
                <form action = "./Controller" method="POST">
                    <input type="hidden" name="command" value="SHOW_RESULT" />
                    <input type="hidden" name="testId" value="${testId}" />
                    <div class ="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                        <c:forEach var="item" items="${test.tasks}">         
                            <input type="hidden" name="question_id" value="${item.id}" />
                            <p class="lead">${item.text}</p>
                            <ul class ="list-group space-top">
                                <c:forEach var="answ" items="${item.answers}">
                                    <li class="list-group-item bghover">
                                        <div class ="radio">
                                            <label><input type="radio" name="question${item.id}" value="${answ.id}">${answ.text}</label>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>            
                        </c:forEach>
                        <p><input type="submit" class="btn btn-lg btn-primary"></p>
                    </div>
                </form>
           </div>
        </div>  
    </body>
</html>