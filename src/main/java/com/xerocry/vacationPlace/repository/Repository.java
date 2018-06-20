/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.repository;

import com.xerocry.vacationPlace.logic.*;
import com.xerocry.vacationPlace.logic.companies.Company;
import com.xerocry.vacationPlace.logic.companies.CompanyType;
import com.xerocry.vacationPlace.logic.companies.TourOperator;
import com.xerocry.vacationPlace.logic.companies.TravelAgency;
import com.xerocry.vacationPlace.view.ClaimAddParams;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @class Repository
 * This class represents repository
 * 
 * 
 * @author Xerocry
 */
public interface Repository {


    public List<Subscription> findSubscriptions(TravelAgency agency) throws SQLException;

    /**
     * Finds list of claims by operator id
     *
     *
     * @param operatorId id of operator
     * @return list of claims for operator with operatorId
     */
    public List<Claim> findClaimsForOperator(Integer operatorId);


    /**
     * Finds list of claims by direction
     *
     * @param direction ClaimDirection instance
     * @param companyId company id
     * @return list of claims
     */
    public List<Claim> findClaimsByDirection(ClaimDirection direction, Integer companyId);

    /**
     * Finds list of claims by agency id
     *
     *
     * @param agencyId id of agency
     * @return list of claims for agency with agency id is agencyId
     */
    public List<Claim> findClaimsFromAgency(Integer agencyId);

    /**
     * Finds  claims for current company
     *
     * @param company company to find claims for
     * @return list of claims for company
     */
    public List<Claim> findClaimsByCompany(Company company);

    /**
     * Finds claim by id
     *
     * @param claimId id of claim to find
     * @return claim with claimId if found claim or null if not found claim
     */
    public Claim findClaimById(Integer claimId);

    /**
     * Adds claim to database
     *
     * @param params instance of ClaimAddParams parameters for claim addition
     */
    public void addClaim(ClaimAddParams params);

    /**
     * Removes claim by id from database
     *
     * @param id of claim to remove
     */
    public void deleteClaim(Integer id);

    /**
     * Rettrives list of companies with selected companyType
     *
     * @param companyType type of company 
     * @return list of companies with selected companyType
     */
    public List<Company> findCompanyByType(CompanyType companyType) throws SQLException;

    /**
     * Retrives current company
     *
     * @return current logged Company instance 
     */
    public Company findCurrentCompany();

    /**
     * Retrives company by id
     *
     * @param id id of company to find
     * @return company with id
     */
    public Company findCompanyById(Integer id);

    /**
     * Retrives all companies
     *
     * @return list of all available companies
     */
    public List<Company> findAllCompanies();

    /**
     * Retrives all available tour operators 
     *
     * @return list of all available tour operators 
     */
    public List<Company> findTourOperators() throws SQLException;

    /**
     * Retrives all available travel agencies
     *
     * @return  list of all available travel agencies
     */
    public List<Company> findTravelAgencies();

    /**
     * Retrives list of attendant tour operators
     *
     * @param agency selected agency for finding attendant operators
     * @return list of attendant tour operators
     */
    public List<TourOperator> findAttendantOperators(TravelAgency agency) throws SQLException;

    /**
     * Retrives list of attendant tour operators
     *
     * @param ids list of attendant operators ids
     * @return list of attendant tour operators
     */
    public List<TourOperator> findAttendantOperators(ArrayList<Integer> ids);

    /**
     * Adds subscription of travel agency for tour operators tours
     *
     *
     * @param travelAgencyId id of travel agency
     * @param tourOperatorId id of tour operator
     */
    public void addSubscription(Integer travelAgencyId, Integer tourOperatorId ) throws SQLException;

    /**
     * Removes subscription of travel agency for tour operators tours
     *
     * @param travelAgencyId id of travel agency
     * @param tourOperatorId of tour operator
     */
    public void removeSubscription(Integer travelAgencyId, Integer tourOperatorId) throws SQLException;

    /**
     * Finds Tour By tourId as subscribedCompanyId 
     *
     *
     * @param subscribedCompanyId
     * @param tourId id of tour to find
     * @return Tour instance with tourId
     */
    public Tour findTourById(Integer subscribedCompanyId ,Integer tourId);

    /**
     * Finds tour by id as current Company requester
     *
     *
     * @param id  id of tour to find
     * @return Tour instance with concrete id 
     */
    public Tour findTourByIdAsUser(Integer id);

    /**
     * Retrives Tour instance from string dataSource
     *
     *
     * @param dataSource string data to parse
     * @return Tour instance
     */
    public Tour mapToTour(String dataSource);

    /**
     * Retrives list of tours from string data 
     *
     * @param dataSource string data to parse
     * @return list of tours
     */
    public List<Tour> mapToTourList(String dataSource);

    /**
     * Finds tours by type
     *
     * @param type type of tour
     * @return list of tours with selected type
     */
    public List<Tour> findToursByType(TourType type) throws SQLException;

    /**
     * Retrives all tours and hotels
     *
     * @return List of tours and hotels
     */
    public List<Tour> findAll() throws SQLException;

    /**
     * Retrives list of tours by parameters
     *
     *
     * @param params parameters for searching tours
     * @return list that matches parameters
     */
    public List<Tour> findByParams(TourSearchParams params);

    /**
     * Adds tour with parameters to database
     *
     *
     * @param params parameters of tour to be added
     */
    public void addTourWithParams(TourAddParams params);

    /**
     * Removes tour with id (tourOperatorTourId) from database
     *
     *
     * @param id tourOperatorTourId of tour to remove from database
     */
    public void deleteTourById(Integer id);

    /**
     * Sort list of tours by id. Sort Order is decrease
     *
     *
     * @param tours tours list to sort
     * @return sorted list of tours
     */
    public List<Tour> sortTours(List<Tour> tours);
        
    
    User logInUser(String login, String realPassword);
    User findUserById(int id);
    User findUserByLogin(String login);

    
    void update(Object obj);
    void updateAll();
    void clearAll();
    void dropAll();

    void changeSubscriptionStatus(Integer currentCompany, Integer selectedOperator, SubscriptionStatus selectedStatus) throws SQLException;
}
