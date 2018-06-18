/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @class TourAddParams
 * 
 * Represents form data for tour addition
 * 
 * @author Xerocry
 */
public class TourAddParams {
    
    public static final String TYPE_TOUR = "tour";
    public static final String TYPE_HOTEL = "hotel";
    
    public static final String ROOM_SGL = "SGL";
    public static final String ROOM_SNGL = "SNGL";
    public static final String ROOM_DBL = "DBL";
    public static final String ROOM_TWIN = "TWIN";
    public static final String ROOM_TRPL = "TRPL";
    public static final String ROOM_QDPL = "QDPL";
    
    public static final String PRICE_UNIT_ONE_DAY = "1 day";
    public static final String PRICE_UNIT_ALL_PERIOD = "all period";
    
    ArrayList<String> food = new ArrayList<String>();
        
    public static final String FOOD_NOTHING = "nothing";
    public static final String FOOD_BB = "BB";
    public static final String FOOD_HB = "HB";
    public static final String FOOD_FB = "FB";
    
    public static final int MAX_DURATION = 90;
    public static final int MAX_ACCOMODATION = 10;
    
    public static final String CURRENCY_EURO = "euro";
    public static final String CURRENCY_DOLLAR = "$";
    public static final String CURRENCY_RUBLE = "руб.";
    
    private String selectedName;
    private String selectedType;
    private String selectedCountry;
    private String selectedTown;
    private String selectedHotel;
    
    HashMap<String,TourType> types = new HashMap<>();
    
    ArrayList<Integer> possibleDuration = new ArrayList<Integer>();
    
    private int selectedDuration;
    
    ArrayList<Integer> possibleAccomodation = new ArrayList<Integer>();
    
    private int selectedAccomodation;
    
    private String selectedBeginDate;
    private String selectedEndDate;
    
    ArrayList<String> rooms = new ArrayList<String>();
    
    private String selectedRoom;
    private String selectedAirCompany;
    private String selectedFood;
    
    private String selectedPriceAmount;
    
    ArrayList<String> currencies = new ArrayList<String>();
    
    private String selectedPriceCurrency;
    
    ArrayList<String> priceUnits = new ArrayList<String>();
    
    private String selectedPriceUnit;
    
    private String selectedDescription;
    
    /**
     * 
     * Constructor
     * Fills lists of possible accomodation, duration, rooms, food,
     *  price currencies, price units. 
     * 
     */
    public TourAddParams(){
        
        types.put(TYPE_TOUR, TourType.TOUR);
        types.put(TYPE_HOTEL, TourType.HOTEL);
        
        for(int i = 1; i <= MAX_DURATION; i++ ){
            possibleDuration.add(i);
        }
        
        for(int i = 1; i <= MAX_ACCOMODATION; i++ ){
            possibleAccomodation.add(i);
        }
        
        rooms.add(ROOM_SGL);
        rooms.add(ROOM_SNGL);
        rooms.add(ROOM_DBL);
        rooms.add(ROOM_TWIN);
        rooms.add(ROOM_TRPL);
        rooms.add(ROOM_QDPL);
      
        food.add(FOOD_NOTHING);
        food.add(FOOD_BB);
        food.add(FOOD_HB);
        food.add(FOOD_FB);
        
        currencies.add(CURRENCY_EURO);
        currencies.add(CURRENCY_DOLLAR);
        currencies.add(CURRENCY_RUBLE);
        
        priceUnits.add(PRICE_UNIT_ONE_DAY);
        priceUnits.add(PRICE_UNIT_ALL_PERIOD);
        
        
    }

    /**
     * Retrives selected tour name 
     * 
     * @return selected tour name 
     */
    public String getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;
    }

    /**
     * Retrives selected tour type as string
     * 
     * @return selected tour type as string
     */
    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    /**
     *  Retrives selected country
     * 
     * 
     * @return selected country 
     */
    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    /**
     * Retrives selected town 
     * 
     * @return selected town 
     */
    public String getSelectedTown() {
        return selectedTown;
    }

    public void setSelectedTown(String selectedTown) {
        this.selectedTown = selectedTown;
    }

    /**
     * Retrives selected hotel
     * 
     * @return selected hotel
     */
    public String getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(String selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    
    /**
     * Retrives selected duration
     * 
     * @return selected duration 
     */
    public int getSelectedDuration() {
        return selectedDuration;
    }

    public void setSelectedDuration(int selectedDuration) {
        this.selectedDuration = selectedDuration;
    }

    /**
     * Retrives selected accomodation
     * 
     * @return accomodation
     */
    public int getSelectedAccomodation() {
        return selectedAccomodation;
    }

    public void setSelectedAccomodation(int selectedAccomodation) {
        this.selectedAccomodation = selectedAccomodation;
    }

    /**
     * Retrives selected begin date
     * 
     * @return selected begin date
     */
    public String getSelectedBeginDate() {
        return selectedBeginDate;
    }

    public void setSelectedBeginDate(String selectedBeginDate) {
        this.selectedBeginDate = selectedBeginDate;
    }

    /**
     * Retrives selected end date
     * 
     * @return selected end date
     */
    public String getSelectedEndDate() {
        return selectedEndDate;
    }

    public void setSelectedEndDate(String selectedEndDate) {
        this.selectedEndDate = selectedEndDate;
    }

    /**
     * Retrives selected room
     * 
     * @return selected room
     */
    public String getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(String selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    /**
     * Retrives selected aircompany 
     * 
     * @return selected aircompany 
     */
    public String getSelectedAirCompany() {
        return selectedAirCompany;
    }

    public void setSelectedAirCompany(String selectedAirCompany) {
        this.selectedAirCompany = selectedAirCompany;
    }

    /**
     * Retrives selected food
     * 
     * @return selected food
     */
    public String getSelectedFood() {
        return selectedFood;
    }

    public void setSelectedFood(String selectedFood) {
        this.selectedFood = selectedFood;
    }

    /**
     * Retrives selected price amount
     * 
     * @return selected price amount
     */
    public String getSelectedPriceAmount() {
        return selectedPriceAmount;
    }

    public void setSelectedPriceAmount(String selectedPriceAmount) {
        this.selectedPriceAmount = selectedPriceAmount;
    }

    /**
     * Retrives selected price currency
     * 
     * @return selected price currency
     */
    public String getSelectedPriceCurrency() {
        return selectedPriceCurrency;
    }

    public void setSelectedPriceCurrency(String selectedPriceCurrency) {
        this.selectedPriceCurrency = selectedPriceCurrency;
    }

    /**
     * Retrives selected price unit
     * 
     * @return selected price unit
     */
    public String getSelectedPriceUnit() {
        return selectedPriceUnit;
    }

    public void setSelectedPriceUnit(String selectedPriceUnit) {
        this.selectedPriceUnit = selectedPriceUnit;
    }

    /**
     * Retrives list of available rooms types as strings 
     * 
     * @return list of available rooms types as strings  
     */
    public ArrayList<String> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<String> rooms) {
        this.rooms = rooms;
    }

    /**
     * Retrives list of available price units 
     * 
     * @return list of available price units 
     */
    public ArrayList<String> getPriceUnits() {
        return priceUnits;
    }

    public void setPriceUnits(ArrayList<String> priceUnits) {
        this.priceUnits = priceUnits;
    }
    
    /**
     * Retrives set of tour types 
     * 
     * @return set of tour types 
     */
    public Set<String> getTypes(){
        return types.keySet();
    }

    /**
     * Retrives list of food types
     * 
     * @return list of food types
     */
    public ArrayList<String> getFood() {
        return food;
    }

    public void setFood(ArrayList<String> food) {
        this.food = food;
    }

    /**
     * Retrives list of possible duration
     * 
     * @return list of possible duration 
     */
    public ArrayList<Integer> getPossibleDuration() {
        return possibleDuration;
    }

    public void setPossibleDuration(ArrayList<Integer> possibleDuration) {
        this.possibleDuration = possibleDuration;
    }

    /**
     * Retrives list of possible accomodation
     * 
     * 
     * @return list possible accomodation
     */
    public ArrayList<Integer> getPossibleAccomodation() {
        return possibleAccomodation;
    }

    public void setPossibleAccomodation(ArrayList<Integer> possibleAccomodation) {
        this.possibleAccomodation = possibleAccomodation;
    }

    /**
     * Retrives list of available currencies
     * 
     * @return list of available currencies
     */
    public ArrayList<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<String> currencies) {
        this.currencies = currencies;
    }

    /**
     * Retrive tour description
     * 
     * @return tour description 
     */
    public String getSelectedDescription() {
        return selectedDescription;
    }

    public void setSelectedDescription(String selectedDescription) {
        this.selectedDescription = selectedDescription;
    }
    
    /**
     * Retrive selected tour type as TourType
     * 
     * @return selected tour type as TourType
     */
    public TourType getTourType(){
        return types.get(selectedType);
    }
    
}
