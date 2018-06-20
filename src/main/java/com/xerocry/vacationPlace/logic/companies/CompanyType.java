/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic.companies;

/**
 *
 * Company type enum
 * 
 * All = -1
 * AGENCY = 0
 * OPERATOR = 1
 * 
 * @author Xerocry
 */
public enum CompanyType {
        
    ALL(-1), AGENCY(0), OPERATOR(1);
    
    private int type;
    
    /**
     * Constructor
     * 
     * @param type type of company as number 
     */
    CompanyType(int type){
        this.type = type;
    }
    
    /**
     * 
     * @return int value of company type 
     */
    public int getIntValue(){
        return type;
    }
}
