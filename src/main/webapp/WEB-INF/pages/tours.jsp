<%-- 
    Document   : tours
    Created on : 14.06.2014, 14:57:27
    Author     : rudolph
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tours</title>
        <link href="<c:url value="/webresources/styles/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/webresources/styles/css/tours.css"/>" rel="stylesheet">
       <!-- <script  src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" type="text/javascript" ></script> --> 
       <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
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
                <c:if test="${loggedAgency == false}"> 
                <li><a href="#" id="addtour" >Add Tour</a></li>
                </c:if>
                <li><a href="#" id="search" >Search Tours</a></li>             
                <li><a href="welcome/showtours">All Tours</a></li>
                <li><a href="welcome/claims/show">Claims</a></li> 
                <li><a href="welcome/logout">Log Out</a></li>
              </ul>
              <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
              </form>
            </div>
          </div>
        </div>
        
        <div class="col-sm-15 col-sm-offset-5 col-md-12 col-md-offset-0 main">
              <h3 class="sub-header">Tours</h3>
              <div class="table-responsive">
                  <table class="table table-striped">
                      
                      <thead>
                          <tr>
                              <th width="100">Tour Id </th> 
                              <th width="250">Tour Name</th>
                              <th width="100">Beg date</th>
                              <th width="100">End date</th>                              
                              <th>Type</th>
                              <th>country</th>
                              <th width="120">town </th>
                              <th width="120">hotel </th>
                              <th>priceAmout  </th>
                              <th>priceCurrency </th>
                              <th>priceUnit </th>
                              <c:if test="${loggedAgency == false}">
                              <th> Delete </th>
                              </c:if>
                          </tr>  
                      </thead>
                      
                      <tbody>
                          <c:forEach var="tour" items="${tours}">
                          <tr> 
                              <td> ${tour.getId()} </td>
                              <td> <a href="welcome/tourinfo/${tour.getId()}">${tour.getName()} </a></td>
                              <td> ${tour.getBeginDate()} </td>
                              <td> ${tour.getEndDate()} </td>                              
                              <td> ${tour.getType()} </td>
                              <td> ${tour.getCountry()} </td>
                              <td> ${tour.getTown()} </td>
                              <td> ${tour.getHotel()} </td>
                              <td> ${tour.getPriceAmount()} </td>
                              <td> ${tour.getPriceCurrency()} </td>
                              <td> ${tour.getPriceUnit()} </td>
                              <c:if test="${loggedAgency == false}">
                                  <form:form  action="welcome/deletetour" command="tourDeleteParams" method="POST">
                                      
                                      <td> 
                                           <input name="deleteId" type="hidden" value="${tour.getTourOperatorTourId()}"/>
                                           <input type="submit" class="btn btn-danger" value="delete"/>
                                      </td>
                                  </form:form> 
                              </c:if>
                          </tr>
                          </c:forEach>
                      </tbody>

                   </table>
               </div>
            </div>
        
        
        
          <!-- Modal Window for Searching Tours -->
          <div class="modal fade" id="searchtours_modal_div">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form:form id="searchtours" action="welcome/searchtours" method="POST" commandName="tourSearcher">  
                   <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Search Tours</h4>
                    </div>
                    <div class="modal-body">
                        <table>
                            <tr>
                                <td> <form:label path="selectedType" cssClass="form-searchtours">Type</form:label> </td>
                                <td>
                                <form:select path="selectedType"                                    
                                             items="${tourSearcher.getTypes()}"
                                             cssClass="form-control" />
                                </td>
                            </tr>    
                            <tr>                                
                                <td> <form:label path="selectedCountry" cssClass="form-searchtours">Country</form:label></td>
                                <td> <form:input path="selectedCountry" cssClass="form-control"/></td>    
                            </tr>
                            <tr>                                
                                <td> <form:label path="selectedTown" cssClass="form-searchtours">Town</form:label></td>
                                <td> <form:input path="selectedTown" cssClass="form-control"/></td>    
                            </tr>
                            <tr>
                                <td> <form:label path="beginDateLowerBound" cssClass="form-searchtours">Begin Date Lower Bound</form:label> </td>                                
                                <td> <input type="date" name="beginDateLowerBound" class="form-control"/> </td>
                                <td> <form:label path="beginDateUpperBound" cssClass="form-searchtours">Begin Date Upper Bound</form:label></td>
                                <td> <input type="date" name="beginDateUpperBound" class="form-control"/> </td>
                            </tr>
                            <tr>
                                <td> <form:label path="endDateLowerBound" cssClass="form-searchtours">End Date Lower Bound</form:label>  </td>
                                <td> <input type="date" name="endDateLowerBound" class="form-control"/></td>
                                <td> <form:label path="endDateUpperBound" cssClass="form-searchtours">End Date Upper Bound</form:label>   </td>
                                <td> <input type="date" name="endDateUpperBound" class="form-control"/></td>                                    
                            </tr>
                            <tr>
                                <td> <form:label path="sortFieldKey" cssClass="form-searchtours"> Sort By </form:label></td>
                                <td> <form:select path="sortFieldKey"                                    
                                             items="${tourSearcher.getSortFields()}"
                                             cssClass="form-control" /> </td>
                                <td> <form:label path="sortDirectionKey" cssClass="form-searchtours"> Sort Direction </form:label></td>
                                <td> <form:select path="sortDirectionKey"                                    
                                             items="${tourSearcher.getSortDirs()}"
                                             cssClass="form-control" /> </td>
                            <tr>    
                                
                           <!-- <tr>                                
                                <td> <--form:label path="selectedHotel" cssClass="form-searchtours">Hotel<--/form:label></td>
                                <td> <--form:input path="selectedHotel" cssClass="form-control"/></td>    
                            </tr>
                            <tr>                                
                                <td> <--form:label path="selectedPriceAmount" cssClass="form-searchtours">Amount<--/form:label></td>
                                <td> <--form:input path="selectedPriceAmount" cssClass="form-control"/></td>
                                
                                <td> <--form:label path="selectedAmountCondition" cssClass="form-searchtours">Amount Condition<--/form:label></td>
                                <td>
                                <--form:select path="selectedAmountCondition"                                    
                                             items="$ {tourSearcher.getAmountConditions()}"
                                             cssClass="form-control" />
                                </td>
                            </tr>
                            <tr>                                
                                <td> <--form:label path="selectedPriceCurrency" cssClass="form-searchtours">Currency<--/form:label></td>
                                <td> <--form:input path="selectedPriceCurrency" cssClass="form-control"/></td>    
                            </tr>
                            <tr>                                
                                <td> <--form:label path="selectedPriceUnit" cssClass="form-searchtours">Unit<--/form:label></td>
                                <td> <--form:input path="selectedPriceUnit" cssClass="form-control"/></td>    
                            </tr> -->
                         </table>                            
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input  type="submit" class="btn btn-primary" value="Search"/>
                    </div>
                    </form:form> 
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
          </div><!-- /.modal -->
          
          <script>              
          $(document).on('click',"#search", function(){          
                $('#searchtours_modal_div').modal('show')
                return false;
            });            
          </script>
          
         
           <!-- Modal Window for Add Tours -->
          <div class="modal fade bs-example-modal-lg" id="addtour_modal_div">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <form:form id="addtourform" action="welcome/addtour" method="POST" commandName="tourAddParams">  
                   <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Add Tour</h4>
                    </div>
                    <div class="modal-body">
                        <table>
                            <tr>                                
                                <td> <form:label path="selectedName" cssClass="form-searchtours">Name</form:label></td>
                                <td> <form:input path="selectedName" cssClass="form-control"/></td>
                                <td> <form:label path="selectedType" cssClass="form-searchtours">Type</form:label> </td>
                                <td>
                                <form:select path="selectedType"                                    
                                             items="${tourAddParams.getTypes()}"
                                             cssClass="form-control" />
                                </td>
                                <td> <form:label path="selectedDuration" cssClass="form-searchtours">Duration</form:label> </td>
                                <td>
                                <form:select path="selectedDuration"                                    
                                             items="${tourAddParams.getPossibleDuration()}"
                                             cssClass="form-control" />
                                </td>
                            </tr>                                                        
                            <tr>                                
                                <td> <form:label path="selectedCountry" cssClass="form-searchtours">Country</form:label></td>
                                <td> <form:input path="selectedCountry" cssClass="form-control"/></td>
                                <td> <form:label path="selectedTown" cssClass="form-searchtours">Town</form:label></td>
                                <td> <form:input path="selectedTown" cssClass="form-control"/></td>
                                <td> <form:label path="selectedHotel" cssClass="form-searchtours">Hotel</form:label></td>
                                <td> <form:input path="selectedHotel" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td> <form:label path="selectedBeginDate" cssClass="form-searchtours">BeginDate</form:label></td>
                                <td> <form:input path="selectedBeginDate" cssClass="form-control"/></td>
                                <td> <form:label path="selectedEndDate" cssClass="form-searchtours">EndDate</form:label></td>
                                <td> <form:input path="selectedEndDate" cssClass="form-control"/></td>
                                <td> <form:label path="selectedAirCompany" cssClass="form-searchtours">AirCompany</form:label></td>
                                <td> <form:input path="selectedAirCompany" cssClass="form-control"/></td>
                            </tr>
                            <tr>               
                                
                                <td> <form:label path="selectedAccomodation" cssClass="form-searchtours">Accomodation</form:label> </td>
                                <td>
                                <form:select path="selectedAccomodation"                                    
                                             items="${tourAddParams.getPossibleAccomodation()}"
                                             cssClass="form-control" />
                                </td>
                                <td> <form:label path="selectedRoom" cssClass="form-searchtours">Room</form:label> </td>
                                <td>
                                <form:select path="selectedRoom"                                    
                                             items="${tourAddParams.getRooms()}"
                                             cssClass="form-control" />
                                </td>
                                <td> <form:label path="selectedFood" cssClass="form-searchtours">Food</form:label> </td>
                                <td>
                                <form:select path="selectedFood"                                    
                                             items="${tourAddParams.getFood()}"
                                             cssClass="form-control" />
                                </td> 
                            </tr>
                            <tr>
                                <td> <form:label path="selectedPriceAmount" cssClass="form-searchtours">Price Amount</form:label></td>
                                <td> <form:input path="selectedPriceAmount" cssClass="form-control"/></td>
                                <td> <form:label path="selectedPriceCurrency" cssClass="form-searchtours">Price Currency</form:label> </td>
                                <td>
                                <form:select path="selectedPriceCurrency"                                    
                                             items="${tourAddParams.getCurrencies()}"
                                             cssClass="form-control" />
                                </td>
                                <td> <form:label path="selectedPriceUnit" cssClass="form-searchtours">Price Unit</form:label> </td>
                                <td>
                                <form:select path="selectedPriceUnit"                                    
                                             items="${tourAddParams.getPriceUnits()}"
                                             cssClass="form-control" />
                                </td>                                   
                            </tr>
                            <tr>
                                <td> <form:label path="selectedDescription" cssClass="form-searchtours">Description</form:label></td>
                                <td colspan="5">
                                <form:textarea path="selectedDescription" rows="3" cols="90" cssStyle="resize:none" cssClass="form-control"/>
                                </td>
                            </tr> 
                        </table>                      
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input  type="submit" class="btn btn-primary" value="Add"/>
                    </div>
                    </form:form> 
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
          </div><!-- /.modal --> 
          
          
           
          <script>              
          $(document).on('click',"#addtour", function(){          
                $('#addtour_modal_div').modal('show')
                return false;
            });            
          </script>
          
         
                
        
    </body>
</html>
