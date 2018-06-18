/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

import java.util.ArrayList;
import java.util.Date;

/**
 * @class Claim
 * 
 * This class represents claim in domain model
 * 
 * 
 * @author Xerocry
 */
public class Claim extends DomainObject {

    private Integer id;

    private String tourOwnerName;
    private Integer tourOwnerId;
    
    private ArrayList<Customer> customers;
    private Date requestDate;
    private String tourName;
    private String status;


    private Tour tourOperatorTour;
    private int duration;
    private int accomodation;
    
    private boolean loadTourOwner = false;
    private boolean loadTourName = false;
    
    
    
    /**
     * Constructor
     * 
     */
    public Claim(){
        customers = new ArrayList<>();
    }

    public Claim(Integer id, Integer tourOwnerId, Date requestDate, String status, Tour tourOperatorTour) {
        this.id = id;
        this.tourOwnerId = tourOwnerId;
        this.requestDate = requestDate;
        this.status = status;
        this.tourOperatorTour = tourOperatorTour;
    }


    public void setTourOwnerName(String tourOwnerName) {
        this.tourOwnerName = tourOwnerName;
    }

    /**
     * Retrives list of customers
     * 
     * @return list of customers 
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    
    /**
     * Checks if the customer is in customers list
     * 
     * @param customer Customer instance to check 
     * @return true if customer is in customers list, false otherwise
     */
    public boolean hasCustomer(Customer customer){
        return customers.contains(customer);
    }
    
    /**
     * Adds customer to list of customers if it not stored there yet
     * 
     * @param customer Customer instance to add to list
     */
    public void addCustomer(Customer customer){
        if(!this.hasCustomer(customer)){
            customers.add(customer);
        }
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
   
    
    /**
     * 
     * Method to get Date as String
     * (format YYYY-MM-DD not controlled)
     * 
     * @return string that represents date
     * 
     */
    public Date getRequestDate(){
        return requestDate;
    }
    
    
    
    /**
    *
    * Method to set requestDate from String
    * (format YYYY-MM-DD not controlled)
    * 
    * @return string that represents date
    * 
    */ 
    public void setRequestDate(Date date){
                
        requestDate = date;        
    }

    /**
     * Retrives tour name from database or cache 
     * 
     * @return tour name 
     */
    public String getTourName() {
        return tourOperatorTour.getTourName();
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }
    
    /**
     * Make and return short customers info
     * For example: Ivanov Ivan Ivanovich mapped to Ivanov I.I.
     * Another example: Sergeev Sergey mapped to Sergeev S.
     * Yet another example: Петров Петр Петрович, Смирнов Сергей mapped to Петров П.П., Смирнов С. 
     * 
     * @return short description of customers
     */
    public String getShortCustomersInfo(){
        
        StringBuilder builder = new StringBuilder();
        
        boolean firstCustomer = true;
        for(Customer customer : customers){
           if( firstCustomer ){
               firstCustomer = false;
           }
           else {
               builder.append(", ");
           }    
           String transName = customer.getRuName();
           if(transName.equals(Customer.DEFAULT_MSG) || transName.isEmpty() ){
               transName = customer.getTransborderName();
           }
           
           
            String[] nameParts = transName.split(" ");
            boolean firstPart = true;
            for( String part : nameParts ){
                 if(firstPart){
                     builder.append(part);
                     builder.append(" ");
                     firstPart = false;
                 }
                 else {
                     if (part.matches("^[a-zA-Zа-яА-Я]+")){
                         String initial = part.substring(0, 1).concat(".");
                         builder.append(initial);
                     }
                 }
            }
                     
           
        }
        
        return builder.toString();
        
    }

    public Integer getTourOwnerId() {
        return tourOwnerId;
    }

    public void setTourOwnerId(Integer tourOwnerId) {
        this.tourOwnerId = tourOwnerId;
    }

    public Tour getTourOperatorTour() {
        return tourOperatorTour;
    }

    public void setTourOperatorTour(Tour tourOperatorTour) {
        this.tourOperatorTour = tourOperatorTour;
    }

    public int getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(int accomodation) {
        this.accomodation = accomodation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
