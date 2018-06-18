/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

import java.util.ArrayList;

/**
 *
 * 
 * 
 * @author Xerocry
 */
public class CompanyCreator {
    
    public static final String OPERATOR = "operator";
    public static final String AGENCY = "agency";
    
    public static final int OPERATOR_TYPE = 1;
    public static final int AGENCY_TYPE = 0;
    
    private ArrayList<String> companyTypes;
    
    private String selectedCompanyType;
    private String selectedCompanyName;
    
    
    /**
     * Retrive lists of company types as strings
     * 
     * @return return list of strings that represents company types 
     */
    public ArrayList<String> getCompanyTypes() {
        return companyTypes;
    }

    public void setCompanyTypes(ArrayList<String> companyTypes) {
        this.companyTypes = companyTypes;
    }

    public String getSelectedCompanyType() {
        return selectedCompanyType;
    }

    public void setSelectedCompanyType(String selectedCompanyType) {
        this.selectedCompanyType = selectedCompanyType;
    }

    /**
     * Retrives selected company name 
     * 
     * @return selected company name 
     */
    public String getSelectedCompanyName() {
        return selectedCompanyName;
    }

    public void setSelectedCompanyName(String selectedCompanyName) {
        this.selectedCompanyName = selectedCompanyName;
    }
    
    /**
     * Constructor
     * 
     */
    public CompanyCreator(){
        companyTypes = new ArrayList<String>();
        companyTypes.add(OPERATOR);
        companyTypes.add(AGENCY);
    }
    
    /**
     * Factory method to create different companies with one interface
     * 
     * @return new company (agency or operator)
     */
    public Company createCompany(){
        
        Company company = null;
        
        if(selectedCompanyType.equals(OPERATOR)){
            company = new TourOperator();
        }
        else if (selectedCompanyType.equals(AGENCY)) { 
            company = new TravelAgency();            
        }
        
        if(companyTypes.contains(selectedCompanyType)){
            company.setName(selectedCompanyName);
        }
        
        return company;
        
    }
    
    /**
     * Parametrized Factory method to create different companies with one interface
     * 
     * @param companyType type to select what company will be created
     * @return new company (operator or agency) or null if no matching with company types
     */
    public Company createCompany(int companyType){
        
         Company company = null;
         
         if( companyType == OPERATOR_TYPE ){
             company = new TourOperator();
         }
         else if( companyType == AGENCY_TYPE ){
             company = new TravelAgency();
         }
         
         return company;        
    }
    
    
    
}
