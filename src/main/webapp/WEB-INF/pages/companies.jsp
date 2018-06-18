<%-- 
    Document   : companies
    Created on : 24.03.2014, 20:48:41
    Author     : rudolph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Companies</title>
        <link href="<c:url value="/webresources/styles/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/webresources/styles/css/companies.css"/>" rel="stylesheet">
        <script  src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript" ></script> 
        <script  src="<c:url value="/webresources/styles/js/modal.js"/>" type="text/javascript"></script>

    </head> 
    <body>
        
        
         
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
                <li><a href="#" id="addcompany">Add Company</a></li>                  
                <li><a href="<c:url value="/showallcompanies"/>">All Companies</a></li>
                <li><a href="<c:url value="/showoperators"/>">Operators</a></li>
                <li><a href="<c:url value="/showagencies"/>">Agencies</a></li>
                <li><a href="<c:url value="/showsubscriptions" />" >Subsriptions</a></li> 
                <li><a href="#">Help</a></li>
              </ul>
              <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
              </form>
            </div>
          </div>
        </div>

        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">All Companies</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Company</th>
                            <th>Type</th>
                        </tr>  
                    </thead>
                    <tbody>         
                    <c:forEach var="company" items="${companies}">
                    <tr>
                        <td><a href="welcome/claims/${company.id}"> ${company.name}  </a></td>
                        <td> ${company.type} </td>
                    </tr>              
                    </c:forEach>
                    </tbody>

                 </table>
             </div>
          </div>
        
         
        
        
          <!-- Modal Window for Addition a New Company -->
          <div class="modal fade" id="addcompany_modal_div">
            <div class="modal-dialog">
                <div class="modal-content">
                   <form:form id="addcomp" action="welcome/addcompany"
                              commandName="companyCreator"> 
                   <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Create New Company</h4>
                    </div>
                    <div class="modal-body">
                        <table>
                            <tr>
                                <td> <form:label path="selectedCompanyName" cssClass="form-addcomp">Company Name</form:label></td>
                                <td> <form:input path="selectedCompanyName" cssClass="form-control"/></td>    
                            </tr>
                            <tr>
                                <td> <form:label path="selectedCompanyType" cssClass="form-addcomp">Company Type</form:label></td>
                                <td>
                                <form:select path="selectedCompanyType"                                    
                                             items="${companyCreator.companyTypes}"
                                             cssClass="form-control" />                                
                                </td>   
                            </tr>    
                         </table>                            
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-primary" value="Create"/>
                    </div>
                    </form:form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
          </div><!-- /.modal -->
        
          <!--  handle of Addition Company -->
          <script>
          $(document).on('click',"#addcompany", function(){          
                $('#addcompany_modal_div').modal('show')
                return false;
            });
          </script>  
        
        
    </body>
</html>
