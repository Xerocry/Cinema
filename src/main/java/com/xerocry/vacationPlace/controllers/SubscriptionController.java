/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.controllers;

import com.xerocry.vacationPlace.Util;
import com.xerocry.vacationPlace.logic.*;
import com.xerocry.vacationPlace.logic.companies.Company;
import com.xerocry.vacationPlace.logic.companies.TourOperator;
import com.xerocry.vacationPlace.logic.companies.TravelAgency;
import com.xerocry.vacationPlace.repository.VacationPlaceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @class SubscriptionController
 * 
 * This controller handles requests for subcpription/unsubscription to operators tours
 * 
 * 
 * @author Xerocry
 */
@Controller
public class SubscriptionController {
    
    /**
     *
     * Constructor 
     * 
     */    
    public SubscriptionController(){        
    }
    
    /**
     * This method filled the model by params using showAllSubscriptions method
     * 
     * 
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return name of jsp page to view
     */ 
    @RequestMapping( value="/**/showsubscriptions", method = RequestMethod.GET)
    public String showSubscriptions(Model model) throws SQLException {
                        
        this.showAllSubscriptions(model);
         
        return "subscriptions";
    }
    
    /**
     * This method adds subscription for tour operator tours. Tour operator selected in selector
     * 
     * 
     * @param selector encapsulate selected operator for subscription  
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return name of jsp page to view
     */
    @RequestMapping(value = "/**/addsubscription", method = RequestMethod.POST)
    public String addSubscriptions(SubscriptionSelector selector, Model model) throws SQLException {
                                
        VacationPlaceRepository repository = new VacationPlaceRepository();
        TourOperator selectedOperator = (TourOperator) repository.findCompanyById(selector.getSelectedOperatorId());

        TravelAgency currentCompany = (TravelAgency) repository.findCurrentCompany();
                
        currentCompany.addAttendantOperator(selectedOperator);
        
        this.showSubscriptions(model);
                        
        return "subscriptions";
        
    }
    
    /**
     * This method removes subscrition for tour operators tours. Tour operator selected in remover
     *
     * @param remover encapsulate selected operator for removing subscription
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return 
     */
    @RequestMapping(value = "/**/removesubscription", method = RequestMethod.POST)
    public String removeSubscriptions(SubscriptionSelector remover, Model model) throws SQLException {
        
        TourOperator selectedOperator = (TourOperator) remover.getSelectedOperator();

        VacationPlaceRepository repository = new VacationPlaceRepository();
        TravelAgency currentCompany = (TravelAgency) repository.findCurrentCompany();
                
        currentCompany.removeAttendantOperator(selectedOperator);
        
        this.showAllSubscriptions(model);
                        
        return "subscriptions";
        
    }

    @RequestMapping(value = "/**/changesubscriptionstatus", method = RequestMethod.POST)
    public String changeSubscriptionStatus(SubscriptionSelector changer, Model model) throws SQLException {

        model.addAttribute("statuses", Arrays.asList(SubscriptionStatus.APPROVED.toString(),
                SubscriptionStatus.REJECTED.toString(), SubscriptionStatus.WAITING_FOR_CORRECTION.toString()));
        TourOperator selectedOperator = (TourOperator) changer.getSelectedOperator();
        SubscriptionStatus selectedStatus = changer.getSelectedStatus();

        VacationPlaceRepository repository = new VacationPlaceRepository();
        TravelAgency currentCompany = (TravelAgency) repository.findCurrentCompany();

        repository.changeSubscriptionStatus(currentCompany.getId(), selectedOperator.getId(), selectedStatus);

        this.showAllSubscriptions(model);

        return "subscriptions";

    }
    
    /**
     *
     * This method fills the model by params sush as currentCompany instance, logged Agency flag,
     * selector to subscribe for operator tours, remover to remove subscription
     * 
     * @param model model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     */ 
    private void showAllSubscriptions(Model model) throws SQLException {

        VacationPlaceRepository repository = new VacationPlaceRepository();
        List<Company> operators = repository.findTourOperators();
                
        Company currentCompany = repository.findCurrentCompany();
        
        SubscriptionSelector selector = new SubscriptionSelector();       
        selector.setOperators(getOperatorsForAddition(currentCompany, operators));
        selector.setSelectedAgencyId(currentCompany.getId());
        List<Subscription> subscriptions = repository.findSubscriptions((TravelAgency) currentCompany);
        selector.setSubscriptions(subscriptions);
                        
        SubscriptionSelector remover = new SubscriptionSelector();
        remover.setOperators(getOperatorsForRemove(currentCompany));         
        
        model.addAttribute("currentCompany", currentCompany);
        model.addAttribute("subscriptions", selector.getSubscriptions());
        model.addAttribute("loggedAgency", Util.isLoggedAgency(currentCompany));
        model.addAttribute("subscriptSelector", selector);
        model.addAttribute("subscriptRemover", remover);
        
    }
    
    /**
     * This method retrives lists of companies-operators NOT attendante the concrete company
     * from all operators list to add subcription ( current agency has not subscribtion to this operators  )
     * 
     * @param company concrete company
     * @param allOperators list of all tour operators
     * @return list companies to subscribe
     */    
    private static List<Company> getOperatorsForAddition(Company company, List<Company> allOperators) throws SQLException {
                
        List<Company> operatorsForAddition = new ArrayList<>();
        if( company instanceof TravelAgency ){
            
            TravelAgency travelAgency = (TravelAgency) company;
            travelAgency.getAttendantOperators();   // for loading attendents from db
            for(Company operator : allOperators){
                if(!travelAgency.hasAttendantOperator((TourOperator) operator)){
                    operatorsForAddition.add(operator);
                }
            }
            
        }
        
        return  operatorsForAddition;
    }
    
    /**
     * This method retrives operators that attendate this current agency 
     *  to remove subscription ( current agency has subscribtion to this operators  )
     * 
     * @param company concrete company
     * @return list of companies to remove subcription
     */    
    private static List<Company> getOperatorsForRemove(Company company) throws SQLException {
        List<Company> operatorsForRemove = new ArrayList<Company>();
        if( company instanceof TravelAgency ){
            TravelAgency agency = (TravelAgency) company;
            operatorsForRemove.addAll(agency.getAttendantOperators());
        }
        
        return operatorsForRemove;
    }    
    
    
    
}
