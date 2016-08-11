<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundles.bundle" />
<!DOCTYPE html>

<html>
    <head>
        <style type="text/css">
            <%@include file="/resources/css/bootstrap.css"%>
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
         <div style="border-style:solid; background-color:#428bca; margin: auto;" id="header">
            <div>
                <h2 style="text-align: center; font-size: 3.8vw;">Your result</h2>
            </div>
        </div>
        
         <div id="menu-block" style="width: 20%; float: left;">
            <div id="menu" style="border-style:solid">
                <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">
                    <a href="./Controller?command=USER_PAGE">Back</a>
                </h3>
            </div>
        </div>
        
        <div id="main" class="container" style="width: 60%; float: left">
            <h1>Your Result is ${result}</h1>
        </div>
    </body>
</html>
