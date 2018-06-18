/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

/**
 * Customer's interface
 * 
 * 
 * @author Xerocry
 */
public interface CustomerInterface {

    String getBirthDate();

    String getRuName();

    String getSex();

    String getTransborderFromDate();

    String getTransborderName();

    String getTransborderPassport();

    String getTransborderToDate();
    
    String getPhone();

    void setBirthDate(String birthDate);

    void setRuName(String ruName);

    void setSex(String sex);

    void setTransborderFromDate(String transborderFromDate);

    void setTransborderName(String transborderName);

    void setTransborderPassport(String transborderPassport);

    void setTransborderToDate(String transborderToDate);
    
    void setPhone(String phone);
    
    boolean isValid();
    
}
