package com.xerocry.vacationPlace.controllers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.xerocry.vacationPlace.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    public MainController() {
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Model model){

        model.addAttribute("user", new User());
        System.out.println("Index method called");


        return "index.jsp";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String loginAdmin(User user,Model model){

        System.out.println("Login Admin() called");
        System.out.println("Login: " + user.getLogin());
        System.out.println("Pass: " + user.getPassword());


        boolean adminLoginRecv = "admin".equals(user.getLogin());
        boolean adminPasswordRecv = "admin".equals(user.getPassword());

        if(adminLoginRecv && adminPasswordRecv){

            ArrayList list = new ArrayList();
            for(int i = 0; i < 15; i++ ){

                HashMap<String, String> map = new HashMap<>();
                map.put("tourOperator","Neva" );
                map.put("travelAgency", "RussianTravelAgency");
                map.put("Date", "01.01.2014");
                map.put("Tour", "Sochi Russia");
                map.put("Customers","Ivanov");
                list.add(map);
            }

            model.addAttribute("claims", list);

            return "claims.jsp";
        }

        return "index.jsp";
    }

}
