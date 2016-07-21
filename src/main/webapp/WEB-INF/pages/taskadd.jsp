<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            <%@include file="/resources/css/bootstrap.css"%>
        </style>
        <style type="text/css">body { padding-top: 70px; }</style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class = "navbar navbar-inverse navbar-fixed-top">
            <div class ="container">
                <div class ="row">
                    <div class="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                        Question
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class ="row">
                <div class="col-md-2 col-lg-3"></div>
                <div class ="col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2">
                    <div class="text-center">
                        <h3 class="hidden-close">Please create your task</h3>
                    </div>
                    <form action = "./Controller" method="POST">
                        <input type="hidden" name="command" value="ADD_TASK" />
                    
                        
                        
                        <p><input type="submit" class="btn btn-lg btn-primary"></p>
                    </form>
                </div>
           </div>
        </div>  
    </body>
</html>