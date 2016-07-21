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
                <h2 style="text-align: center; font-size: 3.8vw;">Authentication</h2>
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
                                <label class="col-sm-4 control-label">Login</label>
                                <div class="col-sm-8" style="float: left">
                                    <input type="text" name="login" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Password</label>
                                <div class="col-sm-8">
                                    <input type="password" name="password" class="form-control">
                                </div>
                            </div>
                
                            <p><input type="submit" name="submit" value="submit" class="btn btn-primary btn-lg"/></p>
                        </form>
                    </div>
                </div>
           </div>
        </div>  
</body>
</html>

