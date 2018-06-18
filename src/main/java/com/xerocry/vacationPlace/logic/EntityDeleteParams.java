/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

/**
 * @class EntityDeleteParams
 * 
 * Class encapsulates id of entity to remove from database
 * 
 * @author Xerocry
 */
public class EntityDeleteParams {
    
    public Integer deleteId;

    /**
     * Retrives the id of domain object to remove from database
     * 
     * @return id of domain object to remove from database 
     */
    public Integer getDeleteId() {
        return deleteId;
    }

    /**
     * 
     * @param deleteId id of domain Object to remove from database
     */
    public void setDeleteId(Integer deleteId) {
        this.deleteId = deleteId;
    }
    
    
    
}
