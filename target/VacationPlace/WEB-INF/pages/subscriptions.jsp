<%-- 
    Document   : subscriptions
    Created on : 30.03.2014, 21:07:50
    Author     : rudolph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subscriptions</title>
        <link href="<c:url value="/webresources/styles/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/webresources/styles/css/subscriptions.css"/>" rel="stylesheet">
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
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">Tour Booking</a>
            </div>
            <div class="navbar-collapse collapse">
              <ul class="nav navbar-nav navbar-right">
                <li><a href="#" id="addsubscription">Add Subscription</a></li>                  
                <li><a href="#" id="removesubscription">Remove Subscription</a></li>                
                <li><a href="#" id="changesubscription">Change Subscription</a></li>
                <li><a href="welcome/claims/show">Claims</a></li>
                <li><a href="welcome/logout">Log Out</a></li>                
              </ul>
              <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
              </form>
            </div>
          </div>
        </div>

        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            
            <c:choose >
                <c:when test="${loggedAgency == true}">    
                <h2 class="sub-header">All Attendant operators for agency ${currentCompany.name}</h2>
                <div class="table-responsive">
                    <table class="table table-striped">

                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Attendant Operators</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>         
                        <c:set var="counter" scope="request" value="${1}"/>
                        <c:forEach var="subscription" items="${subscriptSelector.subscriptions}">
                        <tr>
                            <td> ${counter} </td>
                            <td> ${subscription.tourOperator.name}
                                 <c:set var="counter" scope="request" value="${counter + 1}"/>
                            </td>
                            <td> ${subscription.status.toString()} </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                     </table>
                 </div>
                 </c:when>
                <c:otherwise>
                        Operators view is not supported yet!!!
                </c:otherwise>    
             </c:choose >
          </div>



         <!-- Modal Window for Change Subscription -->
         <div class="modal fade" id="changesubscription_modal_div">
             <div class="modal-dialog">
                 <div class="modal-content">
                     <form:form id="changesubscript" action="welcome/changesubscriptionstatus"
                                commandName="subscriptSelector">
                         <div class="modal-header">
                             <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                             <h4 class="modal-title">Change Subscription Status</h4>
                         </div>
                         <div class="modal-body">
                             <table>

                                 <tr>
                                     <td> <form:label path="selectedOperator" cssClass="form-addsubscript"> Tour Operator </form:label></td>
                                     <td>
                                         <form:select path="selectedOperatorId"
                                                      cssClass="form-control" >
                                             <form:options items="${subscriptSelector.operators}"
                                                           itemValue="id" itemLabel="name"/>
                                         </form:select>
                                     </td>
                                     <td> <form:label path="selectedStatus" cssClass="form-addsubscript"> Status </form:label></td>
                                     <td>
                                         <form:select path="selectedStatus"
                                                      cssClass="form-control" >
                                             <form:options items="${statuses}"/>
                                         </form:select>
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

         <!--  handle of Addition Subscription -->
         <script>
             $(document).on('click',"#changesubscription", function(){
                 $('#changesubscription_modal_div').modal('show')
                 return false;
             });
         </script>







        
        
          <!-- Modal Window for Addition a New Subscription -->
          <div class="modal fade" id="addsubscription_modal_div">
            <div class="modal-dialog">
                <div class="modal-content">
                   <form:form id="addsubscript" action="welcome/addsubscription"
                              commandName="subscriptSelector">  
                   <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Create New Subscription</h4>
                    </div>
                    <div class="modal-body">
                        <table>                                                      
                           
                            <tr>
                                <td> <form:label path="selectedOperator" cssClass="form-addsubscript"> Tour Operator </form:label></td>
                                <td>
                                <form:select path="selectedOperatorId"
                                             cssClass="form-control" >
                                    <form:options items="${subscriptSelector.operators}"
                                                        itemValue="id" itemLabel="name"/>
                                </form:select>    
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
        
          <!--  handle of Addition Subscription -->
          <script>
          $(document).on('click',"#addsubscription", function(){          
                $('#addsubscription_modal_div').modal('show')
                return false;
            });
          </script>
          
           <!-- Modal Window for Removing Subscription -->
          <div class="modal fade" id="removesubscription_modal_div">
            <div class="modal-dialog">
                <div class="modal-content">
                   <form:form id="removesubscript" action="welcome/removesubscription"
                              commandName="subscriptRemover">
                   <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Remove Subscription</h4>
                    </div>
                    <div class="modal-body">
                        <table>                                                      
                                                        
                            <tr>
                                <td> <form:label path="selectedOperator" cssClass="form-removesubscript"> Tour Operator </form:label></td>
                                <td>
                                <form:select path="selectedOperatorId"                                             
                                             cssClass="form-control" >
                                    <form:options items="${subscriptRemover.operators}"
                                                        itemValue="id" itemLabel="name"/>
                                </form:select>     
                                </td>   
                            </tr>                       
                         </table>                            
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-primary" value="Delete"/>
                    </div>
                    </form:form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
          </div><!-- /.modal -->
        
           <!--  handle of Removing Subscription -->
          <script>
          $(document).on('click',"#removesubscription", function(){          
                $('#removesubscription_modal_div').modal('show')
                return false;
            });
          </script>
        </body>
</html>
