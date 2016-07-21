<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
                <h2 style="text-align: center; font-size: 3.8vw;">Registration</h2>
            </div>
        </div>
        
        <div id="menu-block" style="width: 20%; float: left;">
            <div id="menu" style="border-style:solid">
                    <h3 style="text-align: center; width: 100%; font-size: 2.8vw;">Menu</h3>
            </div>
        </div>
        
        <div id="main" class="container" style="border-style: solid; width: 60%; float: left">
            <form action="./Controller" method="POST">
                <input type="hidden" name="command" value="LOGIN_USER"/>
                <p>Login: <input type="text" name="login" /></p>
                <p>Password: <input type="password" name="password"/></p>
                <p><input type="submit" name="submit" value="submit"/></p>
            </form>
        </div> 
        
</body>
</html>
