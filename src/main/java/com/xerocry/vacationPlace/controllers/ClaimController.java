/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.controllers;

import com.xerocry.vacationPlace.Util;
import com.xerocry.vacationPlace.logic.Claim;
import com.xerocry.vacationPlace.logic.companies.Company;
import com.xerocry.vacationPlace.logic.EntityDeleteParams;
import com.xerocry.vacationPlace.logic.companies.TravelAgency;
import com.xerocry.vacationPlace.repository.VacationPlaceRepository;
import com.xerocry.vacationPlace.view.ClaimAddParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @class ClaimController
 * 
 * This controller recieves requests about claims
 * 
 * 
 * @author Xerocry
 */
@RequestMapping(value = "/**/claims")
@Controller
public class ClaimController {

   /**
    *
    * Constructor 
    * 
    */ 
    public ClaimController() {
    }
    
   /**
    *
    * This method fills the model by current company name, logged agency flag, 
    * all claims addressed to current company, instance of ClaimAddParams class for claim addition,
    * instance of EntityDeleteParams class for claim removing
    * 
    * @param  model model of domain i.e. key-value storage of entities to view and fill in jsp pages 
    * @return name of jsp page to view 
    * 
    */ 
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show( Model model ){
        
         System.out.println("CURRENT COMPANY CREATE ");
        VacationPlaceRepository repository = new VacationPlaceRepository();
         
         Company currentCompany = repository.findCurrentCompany();
         
         List<Claim> allClaims = findClaimsByCompany(currentCompany);         
         
         model.addAttribute("currentCompany", currentCompany.getName());
         model.addAttribute("loggedAgency", Util.isLoggedAgency(currentCompany));
         model.addAttribute("allClaims", allClaims );
         model.addAttribute("claimAddParams", new ClaimAddParams());
         model.addAttribute("claimDeleteParams", new EntityDeleteParams());
         
         return "claims";
    }
    
   /**    
    * This method handle data from claim addition form and add claim to database
    * 
    * @param claimAddParams data from claim addition form
    * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages 
    * @return name of jsp page to view
    * 
    */    
    @RequestMapping(value = "/**/addclaim", method = RequestMethod.POST)
    public String addClaim(ClaimAddParams claimAddParams, Model model){
                
        String lastName = claimAddParams.getCustomersWrapper().getCustomer2().getLastName();
        String firstName = claimAddParams.getCustomersWrapper().getCustomer2().getFirstName();
        String fatherName = claimAddParams.getCustomersWrapper().getCustomer2().getFatherName();
        String transLastName = claimAddParams.getCustomersWrapper().getCustomer2().getTransborderLastName();
        String transFirstName = claimAddParams.getCustomersWrapper().getCustomer2().getTransborderFirstName();
        String transFatherName = claimAddParams.getCustomersWrapper().getCustomer2().getTransborderFatherName();
        String birthDate = claimAddParams.getCustomersWrapper().getCustomer2().getBirthDate();
        String passport =  claimAddParams.getCustomersWrapper().getCustomer2().getTransborderPassport();
        String fromDate = claimAddParams.getCustomersWrapper().getCustomer2().getTransborderFromDate();
        String toDate = claimAddParams.getCustomersWrapper().getCustomer2().getTransborderToDate();
        
        String sex = claimAddParams.getCustomersWrapper().getCustomer2().getSex();
        String phone = claimAddParams.getCustomersWrapper().getCustomer2().getPhone();
        
        String startDate = claimAddParams.getStartDate();
        Boolean selfClaim = claimAddParams.isSelfClaim();
        
        System.out.println("********[Comon Form Fields] ************************");
        System.out.println("StartDate = " + startDate);
        System.out.println("Self claim = " + selfClaim);
        System.out.println("********[Customers Fields] ************************");        
        System.out.println("Customer 2 Last name = " + lastName);
        System.out.println("Customer 2 First name = " + firstName);
        System.out.println("Customer 2 Father name = " + fatherName);
        System.out.println("Customer 2 TransLastName =  " + transLastName);
        System.out.println("Customer 2 TransFirstName =  " + transFirstName);
        System.out.println("Customer 2 TransFatherName =  " + transFatherName);
        System.out.println("Customer 2 birthDate = " + birthDate);
        System.out.println("Customer 2 passport = " + passport);
        System.out.println("Customer 2 fromDate = " + fromDate);
        System.out.println("Customer 2 toDate = " + toDate);
        System.out.println("Customer 2 sex = " + sex);
        System.out.println("Customer 2 phone = " + phone);
        System.out.println("***************************************************");

        VacationPlaceRepository repository = new VacationPlaceRepository();
        repository.addClaim(claimAddParams);
                
        return this.show( model );
    }
    
    /**
     *
     * This method determinates claim to remove and removes claim from database
     * 
     * @param claimDeleteParams data that determinates claim to remove from database
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages 
     * @return name of jsp page to view
     * 
     */ 
    @RequestMapping(value = "/**/deleteclaim", method = RequestMethod.POST)
    public String deleteClaim(EntityDeleteParams claimDeleteParams, Model model){
                 
        System.out.println("****[Delete Claim]************************************************");
        System.out.println("Id for delete = " + claimDeleteParams.getDeleteId());        
        System.out.println("******************************************************************");

        VacationPlaceRepository repository = new VacationPlaceRepository();
        repository.deleteClaim(claimDeleteParams.getDeleteId());
        
        return this.show( model );
    }
    
    /**    
     * This method finds all claims for concrete company
     * 
     * @param company company which adressed the found claims
     * @return list of claims adressed to concrete company
     *  
     * 
     */     
    private List<Claim> findClaimsByCompany(Company company){
        
        System.out.println(" findClaimsByCompany INVOKED !!!!");
        System.out.println("Company id = " + company.getId());

        VacationPlaceRepository claimRepository = new VacationPlaceRepository();
        List<Claim> claims = null;
        if(company instanceof TravelAgency){
            claims = claimRepository.findClaimsFromAgency(company.getId());
            System.out.println(" TravelAgency.class BRANCH INVOKED");
        }
        else { //TourOperator.class == company.getClass
            claims = claimRepository.findClaimsForOperator(company.getId());
            System.out.println(" TourOperator.class BRANCH INVOKED");
        }
        
        return claims;
    }
    
}
