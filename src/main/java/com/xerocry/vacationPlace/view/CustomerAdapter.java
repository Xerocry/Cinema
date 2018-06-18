/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.view;


import com.xerocry.vacationPlace.logic.Customer;
import com.xerocry.vacationPlace.logic.CustomerInterface;

/**
 *
 * @author rudolph
 */
public class CustomerAdapter implements CustomerInterface {
    
    private Customer customer = null;
    private String lastName = "";
    private String firstName = "";
    private String fatherName = "";
    
    private String transborderLastName = "";
    private String transborderFirstName = "";
    private String transborderFatherName = "";
    
    /**
     * Constructor
     */    
    public CustomerAdapter(){
        this.customer = new Customer();
    }
    
    /**
     * Constructor
     * 
     * @param customer customer to set in adapter
     */
    public CustomerAdapter(Customer customer){
        this.customer = customer;
    }

    /**
     * Retrives customer without parameters from adapter
     * 
     * @return 
     */
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getTransborderLastName() {
        return transborderLastName;
    }

    public void setTransborderLastName(String transborderLastName) {
        this.transborderLastName = transborderLastName;
    }

    public String getTransborderFirstName() {
        return transborderFirstName;
    }

    public void setTransborderFirstName(String transborderFirstName) {
        this.transborderFirstName = transborderFirstName;
    }

    public String getTransborderFatherName() {
        return transborderFatherName;
    }

    public void setTransborderFatherName(String transborderFatherName) {
        this.transborderFatherName = transborderFatherName;
    }
    
    
    /**
     * Retrive customer with parameters from adapter
     * 
     * @return Customer instance with parameters from adapter
     */
    public Customer getAdaptedCustomer(){
        StringBuilder ruNameBuilder = new StringBuilder();
        ruNameBuilder.append(lastName)
               .append(" ")
               .append(firstName)
               .append(" ")
               .append(fatherName);
        
        customer.setRuName(ruNameBuilder.toString().trim());
        
        StringBuilder transborderNameBuilder = new StringBuilder();
        transborderNameBuilder.append(transborderLastName)
                              .append(" ")
                              .append(transborderFirstName)
                              .append(" ")
                              .append(transborderFatherName);
        
        customer.setTransborderName(transborderNameBuilder.toString().trim());
        return customer;
    }

    @Override
    public String getBirthDate() {
        return this.customer.getBirthDate();
    }

    @Override
    public String getRuName() {
        return this.customer.getRuName();
    }

    @Override
    public String getSex() {
        return this.customer.getSex();
    }

    @Override
    public String getTransborderFromDate() {
        return this.customer.getTransborderFromDate();
    }

    @Override
    public String getTransborderName() {
        return this.customer.getTransborderName();
    }

    @Override
    public String getTransborderPassport() {
        return this.customer.getTransborderPassport();
    }

    @Override
    public String getTransborderToDate() {
        return this.customer.getTransborderToDate();
    }

    @Override
    public void setBirthDate(String birthDate) {
        this.customer.setBirthDate(birthDate);
    }

    @Override
    public void setRuName(String ruName) {
        this.customer.setRuName(ruName);
    }

    @Override
    public void setSex(String sex) {
        this.customer.setSex(sex);
    }

    @Override
    public void setTransborderFromDate(String transborderFromDate) {
        this.customer.setTransborderFromDate(transborderFromDate);
    }

    @Override
    public void setTransborderName(String transborderName) {
        this.customer.setTransborderName(transborderName);
    }

    @Override
    public void setTransborderPassport(String transborderPassport) {
        this.customer.setTransborderPassport(transborderPassport);
    }

    @Override
    public void setTransborderToDate(String transborderToDate) {
        this.customer.setTransborderToDate(transborderToDate);
    }

    /**
     * Valid method
     * 
     * @return always true by default
     * 
     */
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getPhone() {
        return this.customer.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        this.customer.setPhone(phone);
    }
    
    
    
}
