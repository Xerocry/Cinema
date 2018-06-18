/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.controllers;

import com.xerocry.vacationPlace.Util;
import com.xerocry.vacationPlace.logic.Claim;
import com.xerocry.vacationPlace.logic.Company;
import com.xerocry.vacationPlace.logic.EntityDeleteParams;
import com.xerocry.vacationPlace.logic.User;
import com.xerocry.vacationPlace.repository.VacationPlaceRepository;
import com.xerocry.vacationPlace.view.ClaimAddParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author Xerocry
 * @class WelcomeController
 * <p>
 * This controller handles login and logout requests
 */
@Controller
public class WelcomeController {


    /**
     * Constructor
     */
    public WelcomeController() {
    }


    /**
     * This method invokes when client connects to web-server where runs this application
     *
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return name of jsp page to view
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("user", new User());
        System.out.println("Index method called");


        return "index";
    }


    /**
     * @param user  User instance
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return name of jsp page to view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String loginAdmin(HttpSession session, User user, Model model) {

        System.out.println("Login Admin() called");
        System.out.println("Login: " + user.getLogin());
        System.out.println("Pass: " + user.getPassword());
        Integer companyId;
        try {
            companyId = Integer.parseInt(user.getLogin());
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException ex");
            return "index";
        }


        session.setAttribute(Util.USER_TAG, user.getLogin());
        session.setAttribute(Util.PASSWD_TAG, user.getPassword());

        VacationPlaceRepository vPlaceRepository = new VacationPlaceRepository();

        Company currentCompany = vPlaceRepository.findCompanyById(companyId);
        if (currentCompany == null) {
            System.out.println("Current Company not Found");
            return "index";
        }

        List<Claim> allClaims = vPlaceRepository.findClaimsByCompany(currentCompany);
        model.addAttribute("currentCompany", currentCompany);
        model.addAttribute("loggedAgency", Util.isLoggedAgency(currentCompany));
        model.addAttribute("allClaims", allClaims);
        model.addAttribute("claimAddParams", new ClaimAddParams());
        model.addAttribute("claimDeleteParams", new EntityDeleteParams());

        return "claims";
    }

    /**
     * Logout method
     *
     * @param model model of domain i.e. key-value storage of entities to view and fill in jsp pages
     * @return name of jsp page to view
     */
    @RequestMapping(value = "/**/logout", method = RequestMethod.GET)
    public String logout(Model model) {

        Util.logout();
        model.addAttribute("user", new User());

        return "index";
    }


}
        
