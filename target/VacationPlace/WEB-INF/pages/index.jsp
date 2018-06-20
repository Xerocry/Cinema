<%-- 
    Document   : index
    Created on : 10.03.2014, 0:18:41
    Author     : rudolph
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title> Welcome to the VacationPlaceAgency </title>
        <link href="<c:url value="/webresources/styles/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/webresources/styles/css/signin.css"/>" rel="stylesheet">   

    </head>
    
    <body> 
        
        <div class="container">
            
        <form:form cssClass="form-signin"
                   commandName="user"
                   method="POST" modelAttribute="user">
            <h2 class="form-signin-heading"> Please login </h2>
            <table>
            <tr>
                <td> <form:label cssClass="form-signin" path="login">Login</form:label></td>
                <td> <form:input  cssClass="form-control" path="login"/></td>    
            </tr>
            <tr>
                <td> <form:label    cssClass="form-signin" path="password">Password</form:label></td>
                <td> <form:password cssClass="form-control" path="password"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Submit"/>
                </td>
            </tr>    
            </table>    
            
        </form:form>
        
        </div>
     
    </body>    
</html>