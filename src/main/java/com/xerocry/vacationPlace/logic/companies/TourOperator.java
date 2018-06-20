/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic.companies;

import java.util.ArrayList;

/**
 * @class TourOperator
 * 
 * Represents tour operator
 * 
 * @author Xerocry
 */
public class TourOperator extends Company {
    
    private ArrayList<String> subscriptedCompanies;
    
    /**
     * Constructor
     * 
     */
    public TourOperator(){
        subscriptedCompanies = new ArrayList<String>();
    }
    
    /**
     * Adds subcription for company 
     * 
     * @deprecated method is deprecated
     * @param companyName 
     */
    public void addSubscription(String companyName){
        if(!this.hasSubscription(companyName)){
            subscriptedCompanies.add(companyName);
        }
    }
    
    /**
     * Removes subscription for travelagency
     * 
     * @deprecated method is deprecated
     * 
     * @param companyName 
     */
    public void removeSubscription(String companyName){
        subscriptedCompanies.remove(companyName);
    }
    
    /**
     * Checks company for subscription
     * @deprecated method is deprecated
     * 
     * @param companyName name of concrete company
     * @return true if subscripted companies contains concrete company, false otherwise
     */
    public boolean hasSubscription(String companyName){
        return subscriptedCompanies.contains(companyName);
    }
    /**
     * Removes subscription for company
     * 
     * @deprecated method is deprecated
     * 
     * @param companyName
     * @return true if list contains companyName,otherwise false
     */
    boolean removeSubsctiption(String companyName){
        return subscriptedCompanies.remove(companyName);
    }

    /**
     * Retrives type as string
     * 
     * @return type as string 
     */
    @Override
    public String getType() {
        return "operator";
    }

    /**
     * Retrives list of subscripted companies
     * 
     * @deprecated method is deprecated
     * 
     * @return list of subscripted companies
     */
    public ArrayList<String> getSubscriptedCompanies() {
        return subscriptedCompanies;
    }

    /**
     * Sets subscripted companies
     * 
     * @deprecated method is deprecated
     * 
     * @param subscriptedCompanies companies to subcribe
     */
    public void setSubscriptedCompanies(ArrayList<String> subscriptedCompanies) {
        this.subscriptedCompanies = subscriptedCompanies;
    }
    
    
    
}
