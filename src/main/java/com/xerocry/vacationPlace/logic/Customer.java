/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

/**
 * @class Customer
 * 
 * This class represents a customer
 * 
 * @author Xerocry
 */
public class Customer implements CustomerInterface {
    
    public static final String DEFAULT_MSG = "undefined";
    
    private String ruName;                  // must be filled 
    private String transborderName;
    private String birthDate;
    private String sex;
    private String phone;                   // must be filled 
    private String transborderPassport;
    private String transborderFromDate;
    private String transborderToDate;
    
    /**
     * Constructor      
     * 
     */
    public Customer(){
        
        ruName = DEFAULT_MSG;
        transborderName = DEFAULT_MSG;
        birthDate = DEFAULT_MSG;
        sex = DEFAULT_MSG;
        phone = DEFAULT_MSG;
        transborderPassport = DEFAULT_MSG;
        transborderFromDate = DEFAULT_MSG;
        transborderToDate = DEFAULT_MSG;
    }

    /**
     * Retrives customer's russtian name
     * 
     * @return customer's russian name 
     */
    @Override
    public String getRuName() {
        return ruName;
    }

    @Override
    public void setRuName(String ruName) {
        this.ruName = ruName;
    }

    /**
     * Retrives customer's transborder name.
     * Transborder name include only latin characters.
     * 
     * @return customer's transborder name or DEFAULT_MSG if transborder name is empty
     */
    @Override
    public String getTransborderName() {
        return transborderName.isEmpty() ? DEFAULT_MSG : transborderName;
    }

    @Override
    public void setTransborderName(String transborderName) {
        this.transborderName = transborderName;
    }

    /**
     * Retrives customers birth date as string
     * 
     * @return customer's birth date or DEFAULT_MSG if birth date is empty
     */
    @Override
    public String getBirthDate() {
        return birthDate.isEmpty() ? DEFAULT_MSG : birthDate;
    }

    @Override
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
 

    /**
     * Retrives customers sex
     * 
     * @return customer's sex or DEFAULT_MSG if sex is empty
     */
    @Override
    public String getSex() {
        return sex.isEmpty() ? DEFAULT_MSG : sex;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Retrives customers transborder passport
     * 
     * @return customer's transborder passport or DEFAULT_MSG if transborder passport is empty
     */
    @Override
    public String getTransborderPassport() {
        return transborderPassport.isEmpty() ? DEFAULT_MSG : transborderPassport;
    }

    @Override
    public void setTransborderPassport(String transborderPassport) {
        this.transborderPassport = transborderPassport;
    }

    /**
     * Retrives customers transborder from date
     * 
     * @return customer's transborder from date or DEFAULT_MSG if transborder from date is empty
     */
    @Override
    public String getTransborderFromDate() {
        return transborderFromDate.isEmpty() ? DEFAULT_MSG : transborderFromDate;
    }

    @Override
    public void setTransborderFromDate(String transborderFromDate) {
        this.transborderFromDate = transborderFromDate;
    }

    /**
     * Retrives customers transborder to date
     * 
     * @return customer's transborder to date or DEFAULT_MSG if transborder to date is empty
     */
    @Override
    public String getTransborderToDate() {
        return transborderToDate.isEmpty() ? DEFAULT_MSG : transborderToDate;
    }

    @Override
    public void setTransborderToDate(String transborderToDate) {
        this.transborderToDate = transborderToDate;
    }

    /**
     * This method checks is the customer valid
     * Customer is valid iff he has filled ruName and phone 
     * Other fields user may be filled optionally
     * 
     * @return true if customer is valid, false otherwise
     */
    @Override
    public boolean isValid() {
        return !(   ruName.equals(Customer.DEFAULT_MSG) ||
                    phone.equals(Customer.DEFAULT_MSG));
    }

    /**
     * Retrives customers phone
     * 
     * @return customer's phone
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    
}
