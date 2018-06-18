/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.controllers;

import com.xerocry.vacationPlace.logic.Company;
import com.xerocry.vacationPlace.logic.Tour;
import com.xerocry.vacationPlace.logic.TourOperator;
import com.xerocry.vacationPlace.repository.VacationPlaceRepository;
import com.xerocry.vacationPlace.repository.mappers.TourMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @class TourInfoController
 * 
 * This controller handles requests for viewing tour information
 * 
 * @author Xerocry
 */
@RequestMapping(value = "/**/tourinfo")
@Controller
public class TourInfoController {
    
    /**
     * Constructor
     * 
     * 
     */    
    public TourInfoController(){        
    }
    
    /**
     * This method fills the model by Tour instance, and show tour information
     * 
     * 
     * @param id id of tour to show information
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return name of jsp page to view
     */    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showInfo(@PathVariable("id") Integer id, Model model){
       
       
        Tour tour = this.getSelectedTour(id);
        
        model.addAttribute("tour", tour);
        
        return "tourinfo";
    }
    
    /**
     * This method retrives selected tour by id
     * 
     * 
     * @param id id of selected tour
     * @return tour which has selected id
     */
    public Tour getSelectedTour(Integer id){

        VacationPlaceRepository repository = new VacationPlaceRepository();
        Company currentCompany = repository.findCurrentCompany();
        
        if(currentCompany instanceof TourOperator){
            System.out.println("************[IS LOADED TOUR]************************************");
            Tour loadedTour = repository.findTourById(currentCompany.getId(), id);
            System.out.println("Operator Id = " + loadedTour.getTourOperatorId());
            System.out.println("Current company Id = " + currentCompany.getId());
            if(loadedTour.getTourOperatorId().equals(currentCompany.getId())) {
                return loadedTour;
            }
        }
        
        System.out.println("************[OTHER BRANCH]************************************");

        return repository.findTourByIdAsUser(id);
    }
    
}
