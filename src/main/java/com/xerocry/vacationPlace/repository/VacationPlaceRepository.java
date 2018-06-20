package com.xerocry.vacationPlace.repository;

import com.xerocry.vacationPlace.Util;
import com.xerocry.vacationPlace.logic.*;
import com.xerocry.vacationPlace.logic.companies.Company;
import com.xerocry.vacationPlace.logic.companies.CompanyType;
import com.xerocry.vacationPlace.logic.companies.TourOperator;
import com.xerocry.vacationPlace.logic.companies.TravelAgency;
import com.xerocry.vacationPlace.repository.mappers.ClaimMapper;
import com.xerocry.vacationPlace.repository.mappers.CompanyMapper;
import com.xerocry.vacationPlace.repository.mappers.SubscriptionMapper;
import com.xerocry.vacationPlace.repository.mappers.TourMapper;
import com.xerocry.vacationPlace.view.ClaimAddParams;

import java.sql.SQLException;
import java.util.*;

public class VacationPlaceRepository implements Repository {

    private static ClaimMapper claimMapper;
    private static CompanyMapper companyMapper;
    private static TourMapper tourMapper;
    private static SubscriptionMapper subscriptionMapper;

    public VacationPlaceRepository() {
        try {
            if (claimMapper == null) claimMapper = new ClaimMapper();
            if (companyMapper == null) companyMapper = new CompanyMapper();
            if (tourMapper == null) tourMapper = new TourMapper();
            if (subscriptionMapper == null) subscriptionMapper = new SubscriptionMapper();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Claim> findClaimsForOperator(Integer operatorId) {
        return null;
    }

    @Override
    public List<Claim> findClaimsByDirection(ClaimDirection direction, Integer companyId) {
        return null;
    }

    @Override
    public List<Claim> findClaimsFromAgency(Integer agencyId) {
        return null;
    }

    @Override
    public List<Claim> findClaimsByCompany(Company company) {
        return null;
    }

    @Override
    public Claim findClaimById(Integer claimId) {
        return null;
    }

    @Override
    public void addClaim(ClaimAddParams params) {

    }

    @Override
    public void deleteClaim(Integer id) {

    }

    @Override
    public List<Company> findCompanyByType(CompanyType companyType) throws SQLException {
        return companyMapper.findByType(companyType);
    }

    @Override
    public Company findCurrentCompany() {
        return this.findCompanyById(Util.getUserIdAsInteger());
    }

    @Override
    public Company findCompanyById(Integer id) {
        try {
            return companyMapper.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Company> findAllCompanies() {
        try {
            return new ArrayList<>(companyMapper.findAll().values());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Company> findTourOperators() throws SQLException {
        return this.findCompanyByType(CompanyType.OPERATOR);
    }

    @Override
    public List<Company> findTravelAgencies() {
        return null;
    }

    @Override
    public List<TourOperator> findAttendantOperators(TravelAgency agency) throws SQLException {
        List<TourOperator> attendants = subscriptionMapper.findAttendants(agency);
        agency.setAttendantOperators(attendants);

        return attendants;
    }

    @Override
    public List<Subscription> findSubscriptions(TravelAgency agency) throws SQLException {
        return subscriptionMapper.findByAgency(agency);
    }

    @Override
    public List<TourOperator> findAttendantOperators(ArrayList<Integer> ids) {
        List<TourOperator> attendants = new ArrayList();
        Iterator<Integer> it = ids.iterator();
        for (; it.hasNext(); ) {
            Integer id = it.next();
            System.out.println("OPERATOR ID = " + id + " DOMAIN OBJ =  "
                    + companyMapper.loadDomainObject(id).toString());
            Company company = companyMapper.loadDomainObject(id);
            if (company.getClass() == TourOperator.class) {
                attendants.add((TourOperator) company);
            }
            it.remove();
        }

        if (ids.isEmpty()) {
            return attendants;
        }

        for (Integer id : ids) {
            TourOperator operator = (TourOperator) this.findCompanyById(id);
            if (operator != null) {
                attendants.add(operator);
            }
        }

        return attendants;
    }

    @Override
    public void addSubscription(Integer travelAgencyId, Integer tourOperatorId) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setRequesterId(travelAgencyId);
        subscription.setTourOperatorId(tourOperatorId);
        subscription.setStatus(SubscriptionStatus.WAITING_FOR_CORRECTION);
        subscriptionMapper.addSubscription(subscription);
    }

    @Override
    public void removeSubscription(Integer travelAgencyId, Integer tourOperatorId) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setRequesterId(travelAgencyId);
        subscription.setTourOperatorId(tourOperatorId);
        subscription.setStatus(SubscriptionStatus.REJECTED);
        subscriptionMapper.removeSubscription(subscription);

    }

    public Subscription findSubscription(Integer requesterId, Integer tourId) throws SQLException {
        return subscriptionMapper.findByParameters(requesterId, tourId);
    }

    @Override
    public void changeSubscriptionStatus(Integer currentCompany, Integer selectedOperatorId,
                                         SubscriptionStatus selectedStatus) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setRequesterId(currentCompany);
        subscription.setTourOperatorId(selectedOperatorId);
        subscription.setStatus(selectedStatus);
        subscriptionMapper.updateStatus(subscription);
    }

    @Override
    public Tour findTourById(Integer subscribedCompanyId, Integer tourId) {

        try {
            return tourMapper.findById(tourId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Tour findTourByIdAsUser(Integer id) {
        return null;
    }

    @Override
    public Tour mapToTour(String dataSource) {
        return null;
    }

    @Override
    public List<Tour> mapToTourList(String dataSource) {
        return null;
    }

    @Override
    public List<Tour> findToursByType(TourType type) throws SQLException {
        return null;
    }

    @Override
    public List<Tour> findAll() throws SQLException {
        List<Tour> tours = new ArrayList<>(tourMapper.findAll().values());
        return sortTours(tours);
    }

    @Override
    public List<Tour> findByParams(TourSearchParams params) {
        return null;
    }

    @Override
    public void addTourWithParams(TourAddParams params) {

    }

    @Override
    public void deleteTourById(Integer id) {

    }

    @Override
    public List<Tour> sortTours(List<Tour> tours) {
        class TourComparator implements Comparator<Tour> {

            @Override
            public int compare(Tour o1, Tour o2) {
                if (o1.getId() > o2.getId()) return -1;
                if (o1.getId() < o2.getId()) return 1;
                return 0;
            }
        }

        Collections.sort(tours, new TourComparator());

        return tours;
    }

    @Override
    public User logInUser(String login, String realPassword) {
        return null;
    }

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }

    @Override
    public void update(Object obj) {
        String name = obj.getClass().getSimpleName();
        if (name.equals("Travel Agency") || name.equals("Tour Operator") || name.equals("User")) {
            name = "User";
        }
        try {
            switch (name) {
//                case "User":
//                    User user = (User) obj;
//                    tourMapper.update(user);
//                    break;
                case "Tour":
                    Tour tour = (Tour) obj;
                    tourMapper.update(tour);
                    break;
                case "Company":
                    Company company = (Company) obj;
                    companyMapper.update(company);
                    break;
                case "Claim":
                    Claim claim = (Claim) obj;
                    claimMapper.update(claim);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAll() {
        try {
            claimMapper.update();
            tourMapper.update();
            companyMapper.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void clearAll() {
        claimMapper.clear();
        tourMapper.clear();
        companyMapper.clear();
    }

    @Override
    public void dropAll() {
        try {
            DataGateway.getInstance().dropAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clearAll();

    }
}
