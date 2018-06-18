/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

/**
 * @class DomainObject
 * 
 * Represents object of domain model
 * This class is base for other entities of domain model
 * 
 * @author Xerocry
 */
public abstract class DomainObject {
    
    private Integer id;
    
    /**
     * Constructor
     * 
     */
    public  DomainObject(){        
    }

    /**
     * Retrives id of domain object
     * 
     * @return id of domain object 
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id of domain object
     * 
     * @param id id that will be assigned to domain object 
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
}
