/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

import com.xerocry.vacationPlace.repository.mappers.CompanyMapper;

import java.util.List;

/**
 *
 * @class SubscriptionSelector
 * Represents form data for selection.
 * 
 * 
 * @author Xerocry
 */
public class SubscriptionSelector {
    
      
    private Integer selectedOperatorId;
    private Integer selectedAgencyId;
    
    private List<Company> operators;
    private List<Company> agencies;
    
    /**
     * Constructor
     * 
     */
    public SubscriptionSelector(){        
    }

    /**
     * Retrives selected tour operator id 
     * 
     * @return selected operator id 
     */
    public Integer getSelectedOperatorId() {
        return selectedOperatorId;
    }

    public void setSelectedOperatorId(Integer selectedOperatorId) {
        this.selectedOperatorId = selectedOperatorId;
    }

    public Integer getSelectedAgencyId() {
        return selectedAgencyId;
    }

    public void setSelectedAgencyId(Integer selectedAgencyId) {
        this.selectedAgencyId = selectedAgencyId;
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

    /**
     * 
     * Retrives list of operators as companies
     * 
     * @return list of operators as companies 
     */
    public List<Company> getOperators() {
        return operators;
    }

    
    public void setOperators(List<Company> operators) {
        this.operators = operators;
    }

    /**
     * Retrives list of agencies as companies
     * 
     * 
     * @return list of agencies as companies 
     */
    public List<Company> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<Company> agencies) {
        this.agencies = agencies;
    }

    /*
    private ArrayList<String> setAgenciesNames(ArrayList<TravelAgency> agencies){
        return (ArrayList<String>)
                FpTools.map(new Function<TravelAgency, String>(){
                        @Override
                        public String invoke(TravelAgency ag){
                            return ag.getName();
                        }
                    }, agencies );
    }
    
    private ArrayList<String> setOperatorsNames(ArrayList<TourOperator> operators){
        return (ArrayList<String>)
                FpTools.map(new Function<TourOperator, String>(){
                        @Override
                        public String invoke(TourOperator oper){
                            return oper.getName();
                        }                
                }, operators );
    }
    */ 
    
}
