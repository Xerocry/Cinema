package com.xerocry.vacationPlace.repository;

import com.xerocry.vacationPlace.logic.*;
import com.xerocry.vacationPlace.repository.mappers.ClaimMapper;
import com.xerocry.vacationPlace.repository.mappers.CompanyMapper;
import com.xerocry.vacationPlace.repository.mappers.TourMapper;
import com.xerocry.vacationPlace.view.ClaimAddParams;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacationPlaceRepository implements Repository{

    private static ClaimMapper claimMapper;
    private static CompanyMapper companyMapper;
    private static TourMapper tourMapper;

    public VacationPlaceRepository() {
        try {
            if (claimMapper == null) claimMapper = new ClaimMapper();
            if (companyMapper == null) companyMapper = new CompanyMapper();
            if (tourMapper == null) tourMapper = new TourMapper();
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
    public List<Company> findCompanyByType(CompanyType companyType) {
        return null;
    }

    @Override
    public Company findCurrentCompany() {
        return null;
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
    public List<Company> findTourOperators() {
        return null;
    }

    @Override
    public List<Company> findTravelAgencies() {
        return null;
    }

    @Override
    public List<TourOperator> findAttendantOperators(TravelAgency agency) {
        return null;
    }

    @Override
    public List<TourOperator> findAttendantOperators(ArrayList<Integer> ids) {
        return null;
    }

    @Override
    public void addSubscription(Integer travelAgencyId, Integer tourOperatorId) {

    }

    @Override
    public void removeSubscription(Integer travelAgencyId, Integer tourOperatorId) {

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
    public List<Tour> findToursByType(TourType type) {
        return null;
    }

    @Override
    public List<Tour> findAll() {
        return null;
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
        return null;
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
