/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

/**
 * 
 * @class  User
 * 
 * Represents user (registered company administrator)
 * 
 * @author Xerocry
 */
public class User {
    
    private String login;
    private Integer id;
    private String password;
    
    /**
     * 
     * Constructor
     * 
     */
    public User(){        
    }

    /**
     * Retrives login as string
     * 
     * 
     * @return login as string 
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login for user
     * 
     * @param login login to be set 
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retrive password for user as string
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password for user
     * 
     * 
     * @param password password for user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
