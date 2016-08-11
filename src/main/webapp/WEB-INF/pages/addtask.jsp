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
        <title>JSP Page</title>
    </head>
    <body>
        <div style="border-style:solid; background-color:#428bca; margin: auto;" id="header">
            <div>
                <h2 style="text-align: center; font-size: 3.8vw;">
                    <fmt:message key="label.taskcreation"/>
                </h2>
            </div>
        </div>
        
        <div class="container">
            <div class ="row">
                <div class="col-md-2 col-lg-3"></div>
                <div class ="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                    <div class="text-center">
                        <h3 class="hidden-close"><fmt:message key="label.plscreateyourtask"/></h3>
                    </div>
                    <div class="container-fluid space-top-lg">
                        <form action = "./Controller" method="POST" class="form-horizontal">
                            <input type="hidden" name="command" value="ADD_TASK" />
                            <input type="hidden" name="taskId" value="${task.id}" />
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><fmt:message key="label.textquest"/></label>
                                <div class="col-sm-8">
                                    <input type="text" name="question" class="form-control" value="${task.text}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><fmt:message key="label.toughness"/></label>
                                <div class="col-sm-8">
                                    <!--<input type="number" name="toughness" class="form-control" value="${task.toughness}">-->
                                    <input type="number" min="1" max="100" name="toughness" class="form-control" value="${task.toughness}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" ><fmt:message key="label.rightansw"/></label>
                                <div class="col-sm-8">
                                    <input type="text" name="answ" class="form-control" id="rans" value ="${answers[0]}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><fmt:message key="label.wrongansw"/> 1</label>
                                <div class="col-sm-8">
                                    <input type="text" name="answ" class="form-control" id="wans" value ="${answers[1]}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><fmt:message key="label.wrongansw"/> 2</label>
                                <div class="col-sm-8">
                                    <input type="text" name="answ" class="form-control" id="wans" value ="${answers[2]}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label"><fmt:message key="label.wrongansw"/> 3</label>
                                <div class="col-sm-8">
                                    <input type="text" name="answ" class="form-control" id="wans" value ="${answers[3]}">
                                </div>
                            </div>
                            <div class="form-group" style="margin-bottom: 0">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="button.apply"/></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
           </div>
        </div>  
    </body>
</html>
