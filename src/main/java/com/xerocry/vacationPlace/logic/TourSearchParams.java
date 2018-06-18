/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

import java.util.*;

/**
 *
 * @class TourSearchParams
 * 
 * Represents tour search form data
 * 
 * 
 * @author Xerocry
 */
public class TourSearchParams {
    
    public static final String TYPE_ALL = "all";
    public static final String TYPE_TOUR = "tour";
    public static final String TYPE_HOTEL = "hotel";
    
    public static final String AMOUNT_GREATER = ">";
    public static final String AMOUNT_LESSER = "<";
    public static final String AMOUNT_GREATER_EQUALS = ">=";
    public static final String AMOUNT_LESSER_EQUALS = "<=";
    public static final String AMOUNT_EQUALS = "=";
    
    public static final String NO_SORT = "nosort";
    public static final String SORT_BY_COUNTRY = "country";
    public static final String SORT_BY_TOWN = "town";
    public static final String SORT_BY_BEGIN_DATE = "beginDate";
    public static final String SORT_BY_END_DATE = "endDate";
    
    public static final String SORT_DIR_ASC = "asc";
    public static final String SORT_DIR_DSC = "desc";
    
    private String selectedType = TYPE_ALL;
    private Map<String,TourType> types = new HashMap<String,TourType>();
    private String selectedCountry = "";
    private String selectedTown = "";
    private String selectedHotel = "";
    private String selectedPriceAmount = "";
    private List<String> amountConditions = new ArrayList<String>();
    private String selectedAmountCondition = AMOUNT_GREATER;
    private String selectedPriceCurrency = "";
    private String selectedPriceUnit = "";
    
    private String beginDateLowerBound = "";
    private String beginDateUpperBound = "";
    
    private String endDateLowerBound = "";
    private String endDateUpperBound = "";
    
    private Map<String,String> sortBy = new HashMap<String,String>();
    private Map<String,String> sortDir = new HashMap<String,String>();
    
    private String sortFieldKey = NO_SORT;
    private String sortDirectionKey = SORT_DIR_ASC;
    
    /**
     * 
     * Constructor
     * 
     */
    public TourSearchParams() {
        
        types.put(TYPE_TOUR, TourType.TOUR);
        types.put(TYPE_HOTEL, TourType.HOTEL);
        
        sortBy.put("No sort",NO_SORT);
        sortBy.put(SORT_BY_COUNTRY, SORT_BY_COUNTRY);
        sortBy.put(SORT_BY_TOWN, SORT_BY_TOWN);
        sortBy.put(SORT_BY_BEGIN_DATE, SORT_BY_BEGIN_DATE);
        sortBy.put(SORT_BY_END_DATE, SORT_BY_END_DATE);
        
        sortDir.put("Increase Order", SORT_DIR_ASC);
        sortDir.put("Decrease Order", SORT_DIR_DSC);
        
        amountConditions.add(AMOUNT_GREATER);
        amountConditions.add(AMOUNT_LESSER);
        amountConditions.add(AMOUNT_GREATER_EQUALS);
        amountConditions.add(AMOUNT_LESSER_EQUALS);
        amountConditions.add(AMOUNT_EQUALS);
    }

    public Set<String> getSortFields(){
        return sortBy.keySet();
    }
    
    public String getSortField(){
        return sortBy.get(sortFieldKey);
    }
    
    public Set<String> getSortDirs(){
        return sortDir.keySet();
    }
    
    public String getSortDirection(){
        return sortDir.get(sortDirectionKey);
    }
    
    
    public String getSelectedType() {
        return selectedType;
    }
   
     public TourType getSelectedTourType() {
        return types.get(selectedType);
    }
    
    public void setSelectedType(String type){
        selectedType = type;
    }
    
    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public String getSelectedTown() {
        return selectedTown;
    }

    public void setSelectedTown(String selectedTown) {
        this.selectedTown = selectedTown;
    }

    public String getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(String selectedHotel) {
        this.selectedHotel = selectedHotel;
    }
    
    public Set<String> getTypes(){
        return types.keySet();
    }

    public String getSelectedPriceAmount() {
        return selectedPriceAmount;
    }

    public void setSelectedPriceAmount(String selectedPriceAmount) {
        this.selectedPriceAmount = selectedPriceAmount;
    }

    public String getSelectedPriceCurrency() {
        return selectedPriceCurrency;
    }

    public void setSelectedPriceCurrency(String selectedPriceCurrency) {
        this.selectedPriceCurrency = selectedPriceCurrency;
    }

    public String getSelectedPriceUnit() {
        return selectedPriceUnit;
    }

    public void setSelectedPriceUnit(String selectedPriceUnit) {
        this.selectedPriceUnit = selectedPriceUnit;
    }

    public List<String> getAmountConditions() {
        return amountConditions;
    }

    public void setAmountConditions(List<String> amountConditions) {
        this.amountConditions = amountConditions;
    }

    public String getSelectedAmountCondition() {
        return selectedAmountCondition;
    }

    public void setSelectedAmountCondition(String selectedAmountCondition) {
        this.selectedAmountCondition = selectedAmountCondition;
    }

    public String getBeginDateLowerBound() {
        return beginDateLowerBound;
    }

    public void setBeginDateLowerBound(String beginDateLowerBound) {
        this.beginDateLowerBound = beginDateLowerBound;
    }

    public String getBeginDateUpperBound() {
        return beginDateUpperBound;
    }

    public void setBeginDateUpperBound(String beginDateUpperBound) {
        this.beginDateUpperBound = beginDateUpperBound;
    }

    public String getEndDateLowerBound() {
        return endDateLowerBound;
    }

    public void setEndDateLowerBound(String endDateLowerBound) {
        this.endDateLowerBound = endDateLowerBound;
    }

    public String getEndDateUpperBound() {
        return endDateUpperBound;
    }

    public void setEndDateUpperBound(String endDateUpperBound) {
        this.endDateUpperBound = endDateUpperBound;
    }

    public String getSortFieldKey() {
        return sortFieldKey;
    }

    public void setSortFieldKey(String sortFieldKey) {
        this.sortFieldKey = sortFieldKey;
    }

    public String getSortDirectionKey() {
        return sortDirectionKey;
    }

    public void setSortDirectionKey(String sortDirectionKey) {
        this.sortDirectionKey = sortDirectionKey;
    }

    

    

    
    
}
