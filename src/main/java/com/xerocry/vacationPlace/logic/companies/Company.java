/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

/**
 *
 * @class Company
 * 
 * This class represents company. 
 * This class is designed as base for other different company types
 * 
 * @author Xerocry
 */
public abstract class Company extends DomainObject {
    
    private String name;
    
    public Company(){        
    }
                
    public String getName() { return name; }
    
    public void setName(String companyName) { name = companyName; }
    
    public abstract String getType();

    @Override
    public String toString() {
        return name;
    }
    
    
    
}
