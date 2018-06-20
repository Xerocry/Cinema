/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var correct = true; // flag of form AddClaim has valid input params ( init by first time )

function isCorrectClaimAddForm(){
   
    correct = true;     // assign flag true-value when this function is called
    
    document.getElementById("addClaimStatus").innerHTML = "";

    var selectedOperatorId = document.getElementById("selectedOperId").value;
    if(!isCorrectTourOperator(selectedOperatorId)){
        var msg = getErrorMsg("selectedOperId");
        document.getElementById("addClaimStatus").innerHTML += msg;
        return false;
    }

    var selectedTourId = document.getElementById("selectedTourId").value;
    if(!isCorrectTour(selectedTourId)){
        var msg = getErrorMsg("selectedTourId");
        document.getElementById("addClaimStatus").innerHTML += msg;
        return false;
    }

    var startDate = document.getElementById("startDate").value;
    if(!isCorrectDate(startDate)){
        var msg = getErrorMsg("startDate");
        document.getElementById("addClaimStatus").innerHTML += msg;
        correct = false;
    }

    var maxCustomersBlocks = 10;    // max possible number of customers

    clearCustomersStatuses(maxCustomersBlocks);           
    validateCustomersParams(isCorrectName,"lastName",maxCustomersBlocks);
    validateCustomersParams(isCorrectName,"firstName",maxCustomersBlocks);
    validateCustomersParams(isWeakCorrectName,"fatherName",maxCustomersBlocks);
    validateCustomersParams(isWeakCorrectName,"transborderLastName",maxCustomersBlocks);
    validateCustomersParams(isWeakCorrectName,"transborderFirstName",maxCustomersBlocks);
    validateCustomersParams(isWeakCorrectName,"transborderFatherName",maxCustomersBlocks);
    validateCustomersParams(isWeakCorrectDate,"birthDate",maxCustomersBlocks);
    validateCustomersParams(isWeakCorrectDate,"transborderFromDate",maxCustomersBlocks);
    validateCustomersParams(isWeakCorrectDate,"transborderToDate",maxCustomersBlocks);
    validateCustomersParams(isCorrectPhone,"phone",maxCustomersBlocks);
    
    return correct;
    
}

function clearCustomersStatuses(maxCustomersBlocks){
    try {          
        for(var i = 1; i < maxCustomersBlocks; i++){
            document.getElementById("addCustomerStatus" + i).innerHTML = '';
        }
    }
    catch( exception ){
        // does nothing
        //alert("Was init exception !");
    }    
    return 0;
}

function validateCustomersParams(correctParamFunc,paramPrefix,maxCustomersBlocks){
    try {
        for(var i = 1; i < maxCustomersBlocks; i++){
             if(document.getElementById(paramPrefix + i) != ''){
                 var param = document.getElementById(paramPrefix + i).value;
                 if(!correctParamFunc(param)){
                      var msg = getErrorMsg(paramPrefix);
                      document.getElementById("addCustomerStatus" + i).innerHTML += msg;
                      correct = false;
                 }
             }
        }
    }
    catch( exception ){
        // does nothing
        //alert("Univers func. Was " + paramPrefix + " exception!");
    }
    
    return 0;
}

function getErrorMsg(paramPrefix){
    var errors = {
        "selectedOperId" : '<div style="color:red;"> Please Select Operator. </div>',
        "selectedTourId" : '<div style="color:red;"> Please Select Tour. </div>',
        "startDate" : '<div style="color:red;"> Incorrect Start Date. Use format YYYY-MM-DD. This field can not be empty </div>',
                
        "lastName" : '<div style="color:red;"> Incorrect lastName. Do not use digits and special chars or empty names </div>',
        "firstName" : '<div style="color:red;"> Incorrect firstName. Do not use digits and special chars or empty names </div>',
        "fatherName" : '<div style="color:red;"> Incorrect fatherName. Do not use digits and special chars </div>',
        "transborderLastName" : '<div style="color:red;"> Incorrect transborder lastName. Do not use digits and special chars </div>',
        "transborderFirstName" : '<div style="color:red;"> Incorrect transborder firstName. Do not use digits and special chars </div>',
        "transborderFatherName" : '<div style="color:red;"> Incorrect transborder fatherName. Do not use digits and special chars </div>',
        "birthDate" : '<div style="color:red;"> Incorrect Birth Date. Use format YYYY-MM-DD.  </div>',
        "transborderFromDate" : '<div style="color:red;"> Incorrect From Date. Use format YYYY-MM-DD.  </div>',
        "transborderToDate" : '<div style="color:red;"> Incorrect To Date. Use format YYYY-MM-DD.  </div>',
        "phone" : '<div style="color:red;"> Incorrect Phone. Use digits, +,(), and - </div>'
    }
    
    return errors[paramPrefix];
    
}


function isCorrectDate(dateStr){
    //alert(dateStr);
    var parts = dateStr.split("-");
    if(parts.length != 3){
        //alert("parts.length !== 3");
        return false;
    }
    if(new Date(dateStr) == 'Invalid Date' ){
        //alert("Invalid Date");
        return false;
    }
    return true;
}

function isWeakCorrectDate(dateStr){
    if(isEmpty(dateStr)){         
         return true;;
    }
    return isCorrectDate(dateStr);
}

function isCorrectPhone(phoneStr){
    phonePattern = /^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$/;
    return phonePattern.test(phoneStr);   
}

function isCorrectTour(tourValue){
    return (tourValue != "") && (tourValue != " ") && (tourValue != "-1");
}

function isCorrectTourOperator(tourOperatorValue){
    return tourOperatorValue != "-1";
}

function isCorrectName(name){
    
    if(isEmpty(name)){
         //alert("empty name");
         return false;
    }
    
    allSpacesPattern = /^[\s]+$/;
    if(allSpacesPattern.test(name)){
       //alert("Has all spaces"); 
       return false; 
    }
   
    return isWeakCorrectName(name);
}

function isEmpty(str){
    return str == "";
}

function isWeakCorrectName(name){
    
    hasDigitsPattern = /^.*[\d]+.*$/;
    if(hasDigitsPattern.test(name)){
        //alert("Has digists");
        return false;
    }
    
    hasSpecialCharsPattern = /^.*[\@\#\&\?\*\!\(\)\+\=\%\$\<\>\~]+.*$/;
    if(hasSpecialCharsPattern.test(name)){
        //alert("Has special chars");
        return false;
    }
    return true;
}

