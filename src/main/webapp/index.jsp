<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundles.bundle" />

<!DOCTYPE html>
<html lang="${language}" >
    <c:if test="${language==null}">
        <fmt:setBundle basename="bundles.bundle" var="language" scope="session"/>
    </c:if>
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
                    <fmt:message key="label.autentication"/>
                </h2>
            </div>
        </div>
        
        <div id="menu-block" style="width: 20%; float: left;">
            <div id="menu">
                    <form>
                    <select class="selectpicker show-tick" id="language" name="language" onchange="submit()">
                        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>                        
                    </select>
                    </form>
            </div>     
        </div>
    
        <div class="container">
            <div class ="row">
                <div class="col-md-2 col-lg-3"></div>
                <div class ="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                    <div class="container-fluid space-top-lg">
                        <form action="./Controller" method="POST">
                            <input type="hidden" name="command" value="LOGIN_USER"/>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="login">
                                    <fmt:message key="label.login"/>:
                                </label>
                                <div class="col-sm-8" style="float: left">
                                    <input type="text" name="login" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    <fmt:message key="label.password"/>:
                                </label>
                                <div class="col-sm-8">
                                    <input type="password" name="password" class="form-control">
                                </div>
                            </div>
                            <p><input type="submit" name="submit" value="<fmt:message key="form.submit"/>" class="btn btn-primary btn-lg"/></p>
                        </form>
                    </div>
                </div>
           </div>
        </div>  
</body>
</html>

