/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

import com.xerocry.vacationPlace.logic.companies.Company;
import com.xerocry.vacationPlace.repository.mappers.CompanyMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @class SubscriptionSelector
 * Represents form data for selection.
 * 
 * 
 * @author Xerocry
 */
@Getter
@Setter
public class SubscriptionSelector {
    
      
    private Integer selectedOperatorId;
    private Integer selectedAgencyId;
    private Integer selectedSubscriptionId;
    private SubscriptionStatus selectedStatus;

    private List<Company> operators;
    private List<Company> agencies;
    private List<Subscription> subscriptions;

    /**
     * Constructor
     * 
     */
    public SubscriptionSelector(){        
    }

    /**
     * Retrives selected operator as company
     *
     * @return selected operator as company
     */
    public Company getSelectedOperator() {
        return CompanyMapper.getMapper().
                    loadDomainObject(selectedOperatorId);
    }

    /**
     * Retrives selected agency as company
     *
     * @return selected agency as company
     */
    public Company getSelectedAgency() {
        return CompanyMapper.getMapper().
                    loadDomainObject(selectedAgencyId);
    }
    
}
