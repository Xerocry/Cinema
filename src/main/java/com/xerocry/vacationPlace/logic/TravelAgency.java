/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

import com.xerocry.vacationPlace.repository.VacationPlaceRepository;

import java.util.ArrayList;

/**
 * @class TravelAgency
 * 
 * Represents travel agency with attendant tour operators
 * Agency subscripts for operators service
 * 
 * @author Xerocry
 */
public class TravelAgency extends Company {

    private ArrayList<TourOperator> attendantOperators = new ArrayList<TourOperator>();
    
    /**
     * Constructor
     * 
     */
    public TravelAgency(){        
    }
            
    /**
     * Retrives type as string
     * 
     * @return type as string
     */
    @Override
    public String getType() {
        return "agency";
    }

    /**
     * Retrives list of attendant tour operators
     * 
     * @return list of attendant tour operators
     */
    public ArrayList<TourOperator> getAttendantOperators() {
                
        VacationPlaceRepository repository = new VacationPlaceRepository();
        repository.findAttendantOperators(this);         
        
        return attendantOperators;
    }

       
    /**
     * Sets attendant operoators
     * 
     * 
     * @param attendantOperators  list of attendant operators
     */
    public void setAttendantOperators(ArrayList<TourOperator> attendantOperators) {
        this.attendantOperators = attendantOperators;
    }
    
    /**
     * Adds attendant operator from cache or database (subscribes for operator tours) 
     * 
     * @param operator operator to be added
     */
    public void addAttendantOperator(TourOperator operator){
        if(!this.hasAttendantOperator(operator)){
            VacationPlaceRepository repository = new VacationPlaceRepository();
            repository.addSubscription(this.getId(), operator.getId());
            attendantOperators.add(operator);
        }
    }
    
    /**
     * Checks concrete operator for attendance
     * 
     * @param operator to be checked 
     * @return true if attendant operators contains operator, otherwise false
     */
    public boolean hasAttendantOperator(TourOperator operator){
                                
        return attendantOperators.contains(operator);
    }
    
    /**
     * Removes attendant operator from attendant operators and database (removes subscription )
     *  
     * 
     * @param operator to be removed
     * @return true if operator was in attendant operators, otherwise falls 
     */
    public boolean removeAttendantOperator(TourOperator operator){
        VacationPlaceRepository repository = new VacationPlaceRepository();
        repository.removeSubscription(this.getId(), operator.getId());

        return attendantOperators.remove(operator);
    }
    
}
