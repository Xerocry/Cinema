<%-- 
    Document   : claims
    Created on : 10.03.2014, 21:10:46
    Author     : rudolph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Claims</title>
        <link href="<c:url value="/webresources/styles/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/webresources/styles/css/claims.css"/>" rel="stylesheet">
        <script  src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript" ></script> 
        <script  src="<c:url value="/webresources/styles/js/modal.js"/>" type="text/javascript"></script>
        <script  src="<c:url value="/webresources/styles/js/tab.js"/>" type="text/javascript"></script>
        <script  src="<c:url value="/webresources/styles/js/validate.js"/>" type="text/javascript"></script> 

    </head>
    <body>
        <h2> Company:  ${currentCompany}</h2>
        
        
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
                <li><a href="welcome/showsubscriptions">Subscriptions</a></li>
                <c:if test="${loggedAgency == true}">
                <li><a href="#" id="addclaimopen">Add Claim</a></li>
                </c:if>
                <li><a href="welcome/showtours">Tours</a></li>
                <li><a href="welcome/claims/show">Claims Update</a></li>
                <li><a href="welcome/logout">Log Out</a></li>
              </ul>
              <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
              </form>
            </div>
          </div>
        </div>
       
       
        <div class="tab-content">
          <div class="tab-pane active" id="allclaims">              
              <div class="col-sm-15 col-sm-offset-5 col-md-12 col-md-offset-0 main">
              <h3 class="sub-header">All Claims</h3>
              <div class="table-responsive">
                  <table class="table table-striped">
                      <thead>
                          <tr>
                              <th> Claim Id </th>
                              <th>Requester</th>
                              <c:choose>                              
                                  <c:when test="${loggedAgency == true}">
                                      <th>Tour Owner</th>
                                  </c:when>
                                  <c:otherwise>
                                      <th>Recipient</th>
                                  </c:otherwise>
                              </c:choose>                              
                              <th>Request Date</th>
                              <th>Tour Name</th>
                              <!-- <th>Tour Duration (Days) </th> -->
                              <th>Customers Data  </th>
                              <th colspan="2"> Actions </th>
                              <th></th>
                          </tr>  
                      </thead>
                      <tbody>         
                          <c:forEach var="claim" items="${allClaims}">
                            <tr>
                                <td> ${claim.id} </td>
                                <td> ${claim.requesterName} </td>                                
                                <td> ${claim.tourOwnerName} </td>
                                <td> ${claim.requestDate} </td>
                                
                                <td> <a href="welcome/tourinfo/${claim.tourId}"> ${claim.tourName} </a> </td>
                                
                               <!-- <td> $ {claim.duration} </td> -->
                                <td> ${claim.getShortCustomersInfo()} </td>
                                <td> <a href="welcome/claiminfo/${claim.id}" class="btn btn-info"> watch </a> </td>
                                <td>                                                                  
                                    <c:if test="${loggedAgency == false}">
                                    <form:form  action="welcome/claims/deleteclaim" command="claimDeleteParams" method="POST">                             
                                           <input name="deleteId" type="hidden" value="${claim.id}"/>
                                           <input type="submit" class="btn btn-danger" value="delete"/>                                      
                                    </form:form>
                                    </c:if>      
                                </td>    
                            </tr>              
                          </c:forEach>
                      </tbody>

                   </table>
                  
               </div>
            </div>
           
          </div>
            
            <c:url var="findToursByOperatorURL" value="/toursfromoperator" />
            <!-- ******************************************************************* -->
            <script type="text/javascript">
                $(document).ready(function() { 
                        $('#selectedOperId').change(
                                        function() {
                                                $.getJSON('${findToursByOperatorURL}', {
                                                        operatorId : $(this).val(),
                                                        ajax : 'true'
                                                }, function(data) {
                                                        //alert(data);
                                                        var html = '<option value=""> Select Tour</option>';
                                                        var len = data.length;
                                                        for ( var i = 0; i < len; i++) {
                                                                html += '<option value="' + data[i].id + '">'
                                                                                + data[i].uniqueName + '</option>';
                                                        }
                                                        html += '</option>';

                                                        $('#selectedTourId').html(html);
                                                });
                                        });
                });
            </script>
            
            <c:url var="findToursOperatorURL" value="/findtouroperators" />
            <!-- ******************************************************************* -->
            <script type="text/javascript">
                $(document).ready(
                                function() {
                                        $.getJSON('${findToursOperatorURL}', {
                                                ajax : 'true'
                                        }, function(data) {
                                                //alert(data);
                                                var html = '<option value="-1">Select Operator</option>';
                                                var len = data.length;
                                                for ( var i = 0; i < len; i++) {
                                                        html += '<option value="' + data[i].id + '">'
                                                                        + data[i].name + '</option>';
                                                }
                                                html += '</option>';

                                                $('#selectedOperId').html(html);
                                        });
                                });
            </script>
         
        </div>
        
        
          <!-- Modal Window for Add Claims -->
          <div class="modal fade bs-example-modal-lg" id="addclaim_modal_div">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                   <form:form action="welcome/claims/addclaim" method="POST" commandName="claimAddParams" >  
                   <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Add Claim</h4>
                   </div>
                   <div class="modal-body">
                        
                            
                        <table>
                            
                            <tr>
                                <td> <form:label path="selectedOperatorId" cssClass="form-addclaim"> Tour Operator </form:label> </td>
                                <td> <form:select path="selectedOperatorId" id="selectedOperId"                                         
                                             cssClass="form-control" >                                        
                                </form:select></td>
                        
                            </tr>
                            <tr>
                                <td> <form:label path="selectedTourId" cssClass="form-addclaim"> Tour </form:label> </td>
                                <td>
                                <form:select id="selectedTourId" path="selectedTourId" cssClass="form-control">
                                    <form:option value="-1"> Select Tour</form:option>
                                </form:select>
                                </td>
                            </tr>
                            
                         </table>
                                
                                
                         <table>                            
                            <tr>
                                <td> <form:label path="startDate" cssClass="form-addclaim"> Start Date </form:label>   </td>
                                <td> <input type="date" name="startDate" id="startDate" class="form-control" />   </td>
                                <td> <form:label path="accomodation" cssClass="form-addclaim"> Accomodation </form:label>   </td>
                                <td> <form:select path="accomodation" items="${claimAddParams.getPossibleAccomodation()}" cssClass="form-control"/>   </td>
                                <td> <form:label path="duration" cssClass="form-addclaim"> Duration </form:label> </td>
                                <td> <form:select path="duration" items="${claimAddParams.getPossibleDuration()}" cssClass="form-control"/></td>
                                <td> <form:label path="selfClaim" cssClass="form-addclaim"> Self Claim </form:label></td>
                                <td> <form:checkbox path="selfClaim" cssClass="form-control"/> </td>
                            </tr>                            
                          </table>  
                          <div id="addClaimStatus"></div>  
                                
                            
                       
                   <!-- *** Customers Data ********************************************************************** -->
                   
                        <div id="div_inputs">
                            <div id="customer1">
                                <hr> <p> Customer #1<hr>    
                               
                            <table>
                                <tr>
                                    <td><form:label path="customersWrapper.customer1.lastName" cssClass="form-addclaim"> lastName </form:label></td>
                                    <td><form:input path="customersWrapper.customer1.lastName" id="lastName1" cssClass="form-control" /></td>                
                                                               
                                    <td><form:label path="customersWrapper.customer1.firstName" cssClass="form-addclaim"> firstName </form:label></td>
                                    <td><form:input path="customersWrapper.customer1.firstName" id="firstName1" cssClass="form-control" /></td>
                                    
                                    <td><form:label path="customersWrapper.customer1.fatherName" cssClass="form-addclaim"> fatherName </form:label></td>
                                    <td><form:input path="customersWrapper.customer1.fatherName" id="fatherName1" cssClass="form-control" /></td>
                                </tr>
                                
                                <tr>
                                    <td><form:label path="customersWrapper.customer1.customer.phone" cssClass="form-addclaim"> phone </form:label></td>
                                    <td><form:input path="customersWrapper.customer1.customer.phone" id="phone1" cssClass="form-control" /></td>
                                    
                                    <td><form:label path="customersWrapper.customer1.customer.transborderPassport" cssClass="form-addclaim"> passport </form:label></td>
                                    <td><form:input path="customersWrapper.customer1.customer.transborderPassport" id="transborderPassport1" cssClass="form-control" /></td>
                                
                                    <td> <form:label path="customersWrapper.customer1.customer.sex" cssClass="form-addclaim">sex</form:label> </td>
                                    <td>
                                    <form:select path="customersWrapper.customer1.customer.sex"                                    
                                                 items="${claimAddParams.getSex()}"
                                                 cssClass="form-control" />
                                    </td>
                                </tr>    
                                
                            </table>
                            
                            <table>                            
                                
                                <tr>
                                    <td><form:label path="customersWrapper.customer1.transborderLastName" cssClass="form-addclaim"> transborderLastName </form:label></td>
                                    <td><form:input path="customersWrapper.customer1.transborderLastName" id="transborderLastName1" cssClass="form-control" /></td>                
                                                               
                                    <td><form:label path="customersWrapper.customer1.transborderFirstName" cssClass="form-addclaim"> transborderFirstName </form:label></td>
                                    <td><form:input path="customersWrapper.customer1.transborderFirstName" id="transborderFirstName1" cssClass="form-control" /></td>
                                </tr>
                                
                                <tr>
                                    <td><form:label path="customersWrapper.customer1.transborderFatherName" cssClass="form-addclaim"> transborderFatherName </form:label></td>
                                    <td><form:input path="customersWrapper.customer1.transborderFatherName" id="transborderFatherName1" cssClass="form-control" /></td>
                                 
                                    <td><form:label path="customersWrapper.customer1.customer.birthDate" cssClass="form-addclaim"> birthDate </form:label></td>
                                    <td><input type="date" name="customersWrapper.customer1.customer.birthDate" id="birthDate1" class="form-control" /></td>                
                                                               
                                   
                                </tr>
                                <tr>
                                    <td><form:label path="customersWrapper.customer1.customer.transborderFromDate" cssClass="form-addclaim"> From Date </form:label></td>
                                    <td><input type="date" name="customersWrapper.customer1.customer.transborderFromDate" id="transborderFromDate1" class="form-control" /></td>
                                    
                                    <td><form:label path="customersWrapper.customer1.customer.transborderToDate" cssClass="form-addclaim"> To Date </form:label></td>
                                    <td><input type="date" name="customersWrapper.customer1.customer.transborderToDate" id="transborderToDate1" class="form-control" /></td> 
                                                                        
                                </tr>
                            </table>
                           
                            <div id="addCustomerStatus1"></div>
                        </div>
                     
                   </div>         
                    <table align="center">
                         <tr> <td> <input  type="button" value="Add Customer" class="btn btn-primary" onclick="return addCustomerFormBlock();"/> </td>
                         </tr>
                     </table>
                                    
                   
        
                   </div> <!--/.modal-body -->    
                   <!-- *********************************************************************************** --> 
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input  type="submit" class="btn btn-primary" value="Add Claim" onclick="return isCorrectClaimAddForm()"/>
                    </div>
                    </form:form> 
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
          </div><!-- /.modal -->
        
         <script>              
          $(document).on('click',"#addclaimopen", function(){          
                $('#addclaim_modal_div').modal('show')
                return false;
            });            
         </script>
         
          <script>
                var col_inputs = 1;
                var customerNumber = 2;
                var blocksLimit = 10;

                function addCustomerFormBlock(){
                     $('<div id=customer' + customerNumber + '>').appendTo("#div_inputs");

                     $('<hr>').appendTo("#customer" + customerNumber);    
                     $('<p> Customer #' + customerNumber + '<hr>').appendTo("#customer" + customerNumber);
                     
                     $('<table>').appendTo("#customer" + customerNumber);
                     $('<tr>').appendTo("#customer" + customerNumber);
                    
                     $('<td><label name="customersWrapper.customerX.lastName" class="form-addclaim"> lastName </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input name="customersWrapper.customerX.lastName" id="lastNameX" class="form-control"/> </td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     
                     $('<td><label name="customersWrapper.customerX.firstName" class="form-addclaim"> firstName </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input name="customersWrapper.customerX.firstName" id="firstNameX" class="form-control"/> </td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
          
                     $('<td><label name="customersWrapper.customerX.fatherName" class="form-addclaim"> fatherName </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input name="customersWrapper.customerX.fatherName" id="fatherNameX" class="form-control"/> </td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
        
                     $('</tr>').appendTo("#customer" + customerNumber);
                     
                     $('<tr>').appendTo("#customer" + customerNumber);                        
                     $('<td><label name="customersWrapper.customerX.customer.phone" class="form-addclaim"> phone </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input name="customersWrapper.customerX.customer.phone" id="phoneX" class="form-control" /></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);                                          
                     
                      
                     $('<td><label name="customersWrapper.customerX.customer.transborderPassport" class="form-addclaim"> passport </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input name="customersWrapper.customerX.customer.transborderPassport" id="transborderPassportX" class="form-control" /></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                               
                     $('<td><label name="customersWrapper.customerX.customer.sex" class="form-addclaim"> sex </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><select name="customersWrapper.customerX.customer.sex" class="form-control"> <option value="Male" selected="selected">Male</option> <option value="Female">Female</option> </select></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                  
                     $('</tr>').appendTo("#customer" + customerNumber);
                     
                     $('</table>').appendTo("#customer" + customerNumber);
                     $('<table>').appendTo("#customer" + customerNumber);
                     
                     $('<tr>').appendTo("#customer" + customerNumber);
                     $('<td><label name="customersWrapper.customerX.transborderLastName" class="form-addclaim"> transborderLastName </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input name="customersWrapper.customerX.transborderLastName" id="transborderLastNameX" class="form-control"/> </td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     
                     $('<td><label name="customersWrapper.customerX.transborderFirstName" class="form-addclaim"> transborderFirstName </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input name="customersWrapper.customerX.transborderFirstName" id="transborderFirstNameX" class="form-control"/> </td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('</tr>').appendTo("#customer" + customerNumber);
                     
                     $('<tr>').appendTo("#customer" + customerNumber);   
                     $('<td><label name="customersWrapper.customerX.transborderFatherName" class="form-addclaim"> transborderFatherName </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input name="customersWrapper.customerX.transborderFatherName" id="transborderFatherNameX" class="form-control"/> </td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
        
                     $('<td><label name="customersWrapper.customerX.customer.birthDate" class="form-addclaim"> birthDate </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input type="date" name="customersWrapper.customerX.customer.birthDate" id="birthDateX" class="form-control" /></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                    
                     $('</tr>').appendTo("#customer" + customerNumber);
                     $('<tr>').appendTo("#customer" + customerNumber);
                     
                     $('<td><label name="customersWrapper.customerX.customer.transborderFromDate" class="form-addclaim"> From Date </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input type="date" name="customersWrapper.customerX.customer.transborderFromDate" id="transborderFromDateX" class="form-control" /></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     
                     $('<td><label name="customersWrapper.customerX.customer.transborderToDate" class="form-addclaim"> To Date </label></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     $('<td><input type="date" name="customersWrapper.customerX.customer.transborderToDate" id="transborderToDateX" class="form-control" /></td>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                   
                     $('</tr>').appendTo("#customer" + customerNumber);
                     
                     $('</table>').appendTo("#customer" + customerNumber);
                     $('<div id="addCustomerStatusX"></div>'.replace(/[X]/g,customerNumber)).appendTo("#customer" + customerNumber);
                     
                     $('<a style="color:red;" onclick="return deleteCustomerFormBlock(this)" href="#">[—]</a>').appendTo("#customer" + customerNumber);
                     
                     $('</div>').appendTo("#customer" + customerNumber);

                     col_inputs++;
                     customerNumber++;
                     return false;
                }

                function deleteCustomerFormBlock(a){

                    if( col_inputs > 1 ){
                             // Получаем доступ к ДИВу, содержащему поле
                             var contDiv = a.parentNode;

                             contDiv.parentNode.removeChild(contDiv);
                             col_inputs--;
                             customerNumber--;
                    }
                    return false;
                }
             </script>                 
         
         
               
        
    </body>
</html>
