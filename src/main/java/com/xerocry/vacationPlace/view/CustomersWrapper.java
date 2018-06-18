/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.view;

import java.util.ArrayList;
import java.util.List;

/**
 * @class CustomersWrapper
 * Wrapper for 10 customer adapters 
 * 
 * @author Xerocry
 */
public class CustomersWrapper {
    
    private CustomerAdapter customer1 = new CustomerAdapter();
    private CustomerAdapter customer2 = new CustomerAdapter();
    private CustomerAdapter customer3 = new CustomerAdapter();
    private CustomerAdapter customer4 = new CustomerAdapter();
    private CustomerAdapter customer5 = new CustomerAdapter();
    private CustomerAdapter customer6 = new CustomerAdapter();
    private CustomerAdapter customer7 = new CustomerAdapter();
    private CustomerAdapter customer8 = new CustomerAdapter();
    private CustomerAdapter customer9 = new CustomerAdapter();
    private CustomerAdapter customer10 = new CustomerAdapter();

    /**
     * Constructor
     */
    public CustomersWrapper() {
    }

    public CustomerAdapter getCustomer1() {
        return customer1;
    }

    public void setCustomer1(CustomerAdapter customer1) {
        this.customer1 = customer1;
    }

    public CustomerAdapter getCustomer2() {
        return customer2;
    }

    public void setCustomer2(CustomerAdapter customer2) {
        this.customer2 = customer2;
    }

    public CustomerAdapter getCustomer3() {
        return customer3;
    }

    public void setCustomer3(CustomerAdapter customer3) {
        this.customer3 = customer3;
    }

    public CustomerAdapter getCustomer4() {
        return customer4;
    }

    public void setCustomer4(CustomerAdapter customer4) {
        this.customer4 = customer4;
    }

    public CustomerAdapter getCustomer5() {
        return customer5;
    }

    public void setCustomer5(CustomerAdapter customer5) {
        this.customer5 = customer5;
    }

    public CustomerAdapter getCustomer6() {
        return customer6;
    }

    public void setCustomer6(CustomerAdapter customer6) {
        this.customer6 = customer6;
    }

    public CustomerAdapter getCustomer7() {
        return customer7;
    }

    public void setCustomer7(CustomerAdapter customer7) {
        this.customer7 = customer7;
    }

    public CustomerAdapter getCustomer8() {
        return customer8;
    }

    public void setCustomer8(CustomerAdapter customer8) {
        this.customer8 = customer8;
    }

    public CustomerAdapter getCustomer9() {
        return customer9;
    }

    public void setCustomer9(CustomerAdapter customer9) {
        this.customer9 = customer9;
    }

    public CustomerAdapter getCustomer10() {
        return customer10;
    }

    public void setCustomer10(CustomerAdapter customer10) {
        this.customer10 = customer10;
    }
    
    /**
     * Retrives ist of customer adapters
     * 
     * 
     * @return list of customer adapters 
     */
    public List<CustomerAdapter> getCustomersAsList(){
        
        List<CustomerAdapter> customers = new ArrayList<CustomerAdapter>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);
        customers.add(customer6);
        customers.add(customer7);
        customers.add(customer8);
        customers.add(customer9);
        customers.add(customer10);
        
        return customers;
    }
    
    
    
}
