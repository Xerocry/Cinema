/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.view;


import com.xerocry.vacationPlace.logic.TourOperator;
import com.xerocry.vacationPlace.logic.TravelAgency;
import com.xerocry.vacationPlace.repository.VacationPlaceRepository;

import java.util.ArrayList;
import java.util.List;


/**
 * @class
 * 
 * Represents claim addition parameters in form 
 * 
 * 
 * @author Xerocry
 */
public class ClaimAddParams {
    
    public static final int MAX_DURATION = 90;
    public static final int MAX_ACCOMODATION = 10;
        
    int MAX_CUSTOMERS_NUMBER = 10;
    
    private String startDate = "";
    
    private int accomodation = 1;
    
    private int duration = 1;
    
    private long selectedOperatorId;
    
    private long selectedTourId;
    
    private boolean selfClaim;
    
    private List<CustomerAdapter> customers = new ArrayList();
    
    private List<Integer> possibleAccomodation = new ArrayList();
    
    private List<Integer> possibleDuration = new ArrayList();
    
    private CustomersWrapper customersWrapper = new CustomersWrapper();
    
    private List<String> sex = new ArrayList<String>();
    
    {
        sex.add("Male");
        sex.add("Female");
        
        for(int i = 1; i <= MAX_ACCOMODATION; i++){
            possibleAccomodation.add(i);
        }
        
        for(int i = 1; i <= MAX_DURATION; i++){
            possibleDuration.add(i);
        }
    }
    
    public ClaimAddParams(){          
    }
    
    public CustomerAdapter getCustomerAdapter(int i){
        return customers.get(i);
    }
    
    public String getCustomerFirstName(int i){
        return customers.get(i).getFirstName();
    }
    
    public void setCustomerFirstName(int i, String name){
        customers.get(i).setFirstName(name);
    }

    public void setCustomers(List<CustomerAdapter> customers) {
        this.customers = customers;
    }
    
    public CustomersWrapper getCustomersWrapper() {
        return customersWrapper;
    }

    public void setCustomersWrapper(CustomersWrapper customersWrapper) {
        this.customersWrapper = customersWrapper;
    }

    public List<String> getSex() {
        return sex;
    }

    public void setSex(List<String> sex) {
        this.sex = sex;
    }

    public int getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(int accomodation) {
        this.accomodation = accomodation;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Integer> getPossibleAccomodation() {
        return possibleAccomodation;
    }

    public void setPossibleAccomodation(List<Integer> possibleAccomodation) {
        this.possibleAccomodation = possibleAccomodation;
    }

    public List<Integer> getPossibleDuration() {
        return possibleDuration;
    }

    public void setPossibleDuration(List<Integer> possibleDuration) {
        this.possibleDuration = possibleDuration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public long getSelectedOperatorId() {
        return selectedOperatorId;
    }

    public void setSelectedOperatorId(long selectedOperatorId) {
        this.selectedOperatorId = selectedOperatorId;
    }
    
    public ArrayList<TourOperator> getAttendentOperators(){
                
        VacationPlaceRepository companyRepository = new VacationPlaceRepository();
        TravelAgency agency = (TravelAgency) companyRepository.findCurrentCompany();
        return agency.getAttendantOperators();
    }

    public long getSelectedTourId() {
        return selectedTourId;
    }

    public void setSelectedTourId(long selectedTourId) {
        this.selectedTourId = selectedTourId;
    }

    public boolean isSelfClaim() {
        return selfClaim;
    }

    public void setSelfClaim(boolean selfClaim) {
        this.selfClaim = selfClaim;
    }
    
    
    
    
}
