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
        <title>JSP Page</title>
    </head>
    <body>
        <div style="border-style:solid; background-color:#428bca; margin: auto;" id="header">
            <div>
                <h2 style="text-align: center; font-size: 3.8vw;"><fmt:message key="head.profile"/></h2>
            </div>
        </div>
        
        <div id="menu-block" style="width: 20%; float: left;">
            <div id="menu" style="border-style:solid">
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=USER_PAGE"><fmt:message key="button.back"/></a>
                </h3>
            </div>
        </div>
        
        <div id="main" class="container" style="border-style: solid; width: 60%; float: left">
            <h3 style="font-size: 2.8vw;"><fmt:message key="label.info"/></h3>
            <h5 style="font-size: 1.5vw;"><fmt:message key="label.name"/>: ${user.name}</h5>
            <h5 style="font-size: 1.5vw;"><fmt:message key="label.surname"/>: ${user.surname}</h5>
            <hr color="black" style="float: none; width: 100%"/>
            <h5 style="font-size: 1.5vw;"><fmt:message key="label.login"/>: ${user.login}</h5>
            <h5 style="font-size: 1.5vw;"><fmt:message key="label.rights"/>: ${user.rights.name}</h5>
        </div>
    </body>
</html>
