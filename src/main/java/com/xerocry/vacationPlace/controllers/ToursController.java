/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.controllers;

import com.xerocry.vacationPlace.Util;
import com.xerocry.vacationPlace.logic.EntityDeleteParams;
import com.xerocry.vacationPlace.logic.Tour;
import com.xerocry.vacationPlace.logic.TourAddParams;
import com.xerocry.vacationPlace.logic.TourSearchParams;
import com.xerocry.vacationPlace.repository.VacationPlaceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.List;

/**
 * @class ToursController
 * 
 * Controller that handles requests for viewing, searching, addition and removing tours 
 * 
 * 
 * @author Xerocry
 */
@Controller
public class ToursController {
    
   
    /**
     * This method shows tours.
     * This method fills model by TourSearchParams instance to search tours,
     * TourAddParams instance to add tours, EntityDeleteParams instance to delete tours,
     * loggedAgency flag to determinate is logged agency or not
     * 
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages 
     * @return name of jsp page to view
     */
    @RequestMapping( value = "/**/showtours", method = RequestMethod.GET)
    public String showTours(Model model) throws SQLException {
               
        VacationPlaceRepository repository = new VacationPlaceRepository();
        List<Tour> allTours = repository.findAll();
        
        model.addAttribute("tours", allTours);
        model.addAttribute("tourSearcher", new TourSearchParams() );
        model.addAttribute("tourAddParams", new TourAddParams());
        model.addAttribute("tourDeleteParams", new EntityDeleteParams());
        model.addAttribute("loggedAgency", Util.isLoggedAgency());
        
        return "tours";
    }
    
    
    /**
     * 
     * This method retrives tours by user criterias.
     * 
     * @param tourSearcher search parameters that were inputed in form by user
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return name of jsp page to view
     */
    @RequestMapping(value = "/**/searchtours", method = RequestMethod.POST)
    public String showFoundedTours(TourSearchParams tourSearcher, Model model ){
        
        System.out.println("****************************************************");
        System.out.println("Selected type = " + tourSearcher.getSelectedType());
        System.out.println("Selected country = " + tourSearcher.getSelectedCountry());
        System.out.println("Selected town = " + tourSearcher.getSelectedTown());
        System.out.println("Selected hotel = " + tourSearcher.getSelectedHotel());
        //System.out.println("Selected priceAmount = " + tourSearcher.getSelectedPriceAmount());
        //System.out.println("Selected priceCurrency = " + tourSearcher.getSelectedPriceUnit());
        //System.out.println("Selected priceUnit = " + tourSearcher.getSelectedPriceUnit());
        System.out.println("Beg date lower bound = " + tourSearcher.getBeginDateLowerBound());
        System.out.println("Beg date upper bound = " + tourSearcher.getBeginDateUpperBound());
        System.out.println("End date lower bound = " + tourSearcher.getEndDateLowerBound());
        System.out.println("End date upper bound = " + tourSearcher.getEndDateUpperBound());
        System.out.println("Sort Field = " + tourSearcher.getSortField());
        System.out.println("Sort Direction = " + tourSearcher.getSortDirection());
        System.out.println("****************************************************");

        VacationPlaceRepository repository = new VacationPlaceRepository();
        List<Tour> foundTours = repository.findByParams(tourSearcher);
        
        model.addAttribute("tours", foundTours);
        model.addAttribute("tourSearcher", new TourSearchParams() );
        model.addAttribute("tourAddParams", new TourAddParams());
        model.addAttribute("tourDeleteParams", new EntityDeleteParams());
        model.addAttribute("loggedAgency", Util.isLoggedAgency());
                
        return "tours";
    }
    
    /**
     * This method add tour to the database.
     * 
     * 
     * @param tourAddParams tour addition parameters that were inputed in form by user
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return name of jsp page to view
     */
    @RequestMapping(value = "/**/addtour", method = RequestMethod.POST)
    public String addTour(TourAddParams tourAddParams, Model model ) throws SQLException {

        VacationPlaceRepository repository = new VacationPlaceRepository();
        
        System.out.println("*[for Addition]***************************************************");
        System.out.println("Selected type = " + tourAddParams.getSelectedType());
        System.out.println("Selected country = " + tourAddParams.getSelectedCountry());
        System.out.println("Selected town = " + tourAddParams.getSelectedTown());
        System.out.println("Selected hotel = " + tourAddParams.getSelectedHotel());
        System.out.println("Selected accomodation  = " + tourAddParams.getSelectedAccomodation());
        System.out.println("Selected duration  = " + tourAddParams.getSelectedDuration());
        System.out.println("Selected beginDate  = " + tourAddParams.getSelectedBeginDate());
        System.out.println("Selected endDate  = " + tourAddParams.getSelectedEndDate());
        System.out.println("Selected room  = " + tourAddParams.getSelectedRoom());
        System.out.println("Selected food  = " + tourAddParams.getSelectedFood());
        System.out.println("Selected price Amount  = " + tourAddParams.getSelectedPriceAmount());
        System.out.println("Selected price Currency  = " + tourAddParams.getSelectedPriceCurrency());
        System.out.println("Selected price Unit  = " + tourAddParams.getSelectedPriceUnit());
        System.out.println("Selected price Description  = " + tourAddParams.getSelectedDescription());
        
        System.out.println("****************************************************");
        
        repository.addTourWithParams(tourAddParams);
        
        List<Tour> allTours = repository.findAll();        
        model.addAttribute("tours", allTours);
        model.addAttribute("tourSearcher", new TourSearchParams() );
        model.addAttribute("tourAddParams", new TourAddParams());
        model.addAttribute("tourDeleteParams", new EntityDeleteParams());
        model.addAttribute("loggedAgency", Util.isLoggedAgency());
        
        
        return "tours";        
    }
    
    /**
     * This method removes claim from database 
     * 
     * 
     * @param tourDeleteParams parameters to remove claim from database
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return name of jsp page to view
     */
     @RequestMapping(value = "/**/deletetour", method = RequestMethod.POST)
     public String deleteTour(EntityDeleteParams tourDeleteParams, Model model) throws SQLException {
                 
        System.out.println("****[Delete Tour]************************************************");
        System.out.println("Id for delete = " + tourDeleteParams.getDeleteId());        
        System.out.println("*****************************************************************");

         VacationPlaceRepository repository = new VacationPlaceRepository();
        repository.deleteTourById(tourDeleteParams.getDeleteId());
                
        List<Tour> allTours = repository.findAll();        
        model.addAttribute("tours", allTours);
        model.addAttribute("tourSearcher", new TourSearchParams() );
        model.addAttribute("tourAddParams", new TourAddParams());
        model.addAttribute("tourDeleteParams", new EntityDeleteParams());
        model.addAttribute("loggedAgency", Util.isLoggedAgency());
        
        return "tours";   
     }
    
}
