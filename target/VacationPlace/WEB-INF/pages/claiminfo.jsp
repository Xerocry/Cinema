<%-- 
    Document   : claiminfo
    Created on : 06.04.2014, 21:43:10
    Author     : rudolph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Claim Info</title>
        <link href="<c:url value="/webresources/styles/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/webresources/styles/css/claims.css"/>" rel="stylesheet">
        <script  src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript" ></script> 
        <script  src="<c:url value="/webresources/styles/js/modal.js"/>" type="text/javascript"></script>
    </head>
    
    <body>
        <h2>Claim Information </h2>
        
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
                <li><a href="welcome/claims/show">Claims</a></li>
                <li><a href="#">Subscriptions</a></li>
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
                    <h3 class="panel-title">General Information</h3>
                </div>
                <div class="panel-body">
                    <dl class="dl-horizontal">
                        <dt> Requester  </dt>
                        <dd> ${claim.requesterName} </dd>                        
                        <dt> Request Date  </dt>
                        <dd> ${claim.requestDate} </dd>
                        <dt> Tour Name  </dt>
                        <dd> ${claim.tourName} </dd>                       
                    </dl>

                </div>
            </div>

            <c:forEach var="customer" items="${claim.customers}" >        
                    
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Customer Information</h3>
                    </div>
                    <div class="panel-body">
                        <dl class="dl-horizontal">
                            <dt> Russian Name  </dt>
                            <dd> ${customer.ruName}</dd>
                            <dt> Transborder Name  </dt>
                            <dd> ${customer.transborderName}</dd>
                            <dt> Birth Date  </dt>
                            <dd> ${customer.birthDate}</dd>
                            <dt> Sex  </dt>
                            <dd> ${customer.sex}</dd>
                            <dt> Phone  </dt>
                            <dd> ${customer.phone}</dd>
                            <dt> Transborder Passport  </dt>
                            <dd> ${customer.transborderPassport}</dd>
                            <dt> Transborder From Date  </dt>
                            <dd> ${customer.transborderFromDate}</dd>
                            <dt> Transborder To Date  </dt>
                            <dd> ${customer.transborderToDate}</dd>
                        </dl>
                    </div>
                </div>
                
             </c:forEach>   
            
        </div>
        
        
        
        
        
    </body>
</html>
