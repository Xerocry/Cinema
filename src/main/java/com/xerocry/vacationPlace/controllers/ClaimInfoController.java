/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.controllers;

import com.xerocry.vacationPlace.logic.Claim;
import com.xerocry.vacationPlace.repository.VacationPlaceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @class ClaimInfoController
 * 
 * This class represents a controller that handle requests 
 * for viewing concrete claims
 * 
 * 
 * @author Xerocry
 */
@RequestMapping(value = "/**/claiminfo")
@Controller
public class ClaimInfoController {
    
    /**
     * 
     * Constructor     
     * 
     */    
    public ClaimInfoController(){        
    }
    
    /**
     * 
     * @param id id of claim to show full claim information
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return name of jsp page to view
     */    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, Model model){
        
        model.addAttribute("id", id);

        VacationPlaceRepository claimRepository = new VacationPlaceRepository();
        Claim claim = claimRepository.findClaimById(id);        
       
        model.addAttribute("claim", claim);                    
                
        return "claiminfo";
    }
    
}
