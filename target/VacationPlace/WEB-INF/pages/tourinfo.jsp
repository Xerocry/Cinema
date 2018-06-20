<%-- 
    Document   : tour
    Created on : 09.04.2014, 16:38:50
    Author     : rudolph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tour Info</title>
        <link href="<c:url value="/webresources/styles/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/webresources/styles/css/claims.css"/>" rel="stylesheet">

    </head>
    <body>
        <h2>Tour Information</h2>
          <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">Tour Booking</a>
            </div>
            <div class="navbar-collapse collapse">
              <ul class="nav navbar-nav navbar-right">
                
                <li><a href="#">Subscriptions</a></li>
                <li><a href="welcome/claims/show">Claims</a></li> 
                <li><a href="welcome/showtours">Tours</a></li>
                <li><a href="welcome/logout">Log Out</a></li>
              </ul>
              <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
              </form>
            </div>
          </div>
        </div>
        
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">General Tour Information</h3>
                </div>
                <div class="panel-body">
                    <dl class="dl-horizontal">
                        
                        <c:forEach var="attrib" items="${tour.optionalAttributes}" >
                            <dt> ${attrib.key}  </dt>
                            <dd> ${attrib.value}  </dd>                        
                        </c:forEach>                           
                    </dl>

                </div>
            </div>
                          
        </div>
          
          
          
          
          
          
          
          
    </body>
</html>
