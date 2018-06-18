/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.controllers;

import com.xerocry.vacationPlace.logic.Tour;
import com.xerocry.vacationPlace.logic.TourOperator;
import com.xerocry.vacationPlace.logic.TravelAgency;
import com.xerocry.vacationPlace.repository.VacationPlaceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @class AjaxController
 * 
 * Controller to handle ajax requests
 *
 * @author Xerocry
 */
@Controller
public class AjaxController {

   /**
    * 
    * Constructor
    *
    * 
    */ 
    public AjaxController() {
    }
    
        /**
         * Method gets tours provided by operator
         * 
         *@param operatorId string id of tour operator
         *@return list of tours provied by operator with defined id
         * 
         */    
        @RequestMapping(value = "**/toursfromoperator", method = RequestMethod.GET)
	public @ResponseBody
	List<Tour> toursFromOperators(
			@RequestParam(value = "operatorId", required = true) String operatorId) {
		
            long id = Long.parseLong(operatorId);
            System.out.println("****[AJAX]****************************************");
            System.out.println(" GET AJAX REQUEST");
            System.out.println(" recieved id = " + id);
            System.out.println("**************************************************");

            VacationPlaceRepository repository = new VacationPlaceRepository();
            List<Tour> allTours = repository.findAll();
            List<Tour> foundTours = new ArrayList<Tour>();
            for(Tour tour : allTours){
                if(tour.getTourOperatorId() == id){
                    foundTours.add(tour);
                }
            }

            System.out.println("FoundTours size = " +  foundTours.size());

            return foundTours;
    }
     
        /**
         * Method gets list of attendant tour operators
         * 
         * 
         * @return list of tour operators which attendant concrete current company
         * 
         * 
         */
        @RequestMapping(value = "**/findtouroperators", method = RequestMethod.GET)
	public @ResponseBody
	List<TourOperator> findAttendantTourOperators(){
         
        System.out.println("****[AJAX]****************************************");
        System.out.println(" GET AJAX FIND OPERATORS REQUEST");        
        System.out.println("**************************************************");

            VacationPlaceRepository companyRepository = new VacationPlaceRepository();
        try {
        
        TravelAgency agency = (TravelAgency) companyRepository.findCurrentCompany();
        return agency.getAttendantOperators();
        
        }
        catch(ClassCastException ex) {
            return new ArrayList<>();
        }
        
         
     }
        
}
