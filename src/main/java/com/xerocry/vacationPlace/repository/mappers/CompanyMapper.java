/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.repository.mappers;

import com.xerocry.vacationPlace.logic.companies.Company;
import com.xerocry.vacationPlace.logic.companies.CompanyCreator;
import com.xerocry.vacationPlace.logic.companies.CompanyType;
import com.xerocry.vacationPlace.repository.DataGateway;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Xerocry
 */
public class CompanyMapper implements AbstractMapper<Company> {

    protected Map<Integer, Company> loadedMap = new HashMap<>();
    private Connection connection;

    private static CompanyMapper mapper;

    static {
        try {
            mapper = new CompanyMapper();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrives TourMapper instance
     *
     * @return TourMapper instance
     */
    public static CompanyMapper getMapper(){
        return mapper;
    }

    /**
     *
     * @return  CompanyMapper instance
     */
    public CompanyMapper() throws SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
    }

    /**
     * Add new company
     *
     * @param company company data for parsing
     * @return null
     */
    public void addCompany(Company company) throws SQLException {
        if (loadedMap.values().contains(company)) {
            update(company);
        } else {
            String insertSQL = "INSERT INTO company(company.companyName, company.companyType) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getType());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                company.setId(resultSet.getInt(1));
            }

            loadedMap.put(company.getId(), company);
        }
    }

    /**
     * Retrieves Company instance by id
     *
     * @param id company id
     * @return Company instance or null
     */
    @Override
    public Company findById(int id) throws SQLException {
        for (int loadedUserId : loadedMap.keySet()) {
            if (loadedUserId == id)
                return loadedMap.get(loadedUserId);
        }

        // Company not found, extract from database
        String selectSQL = "SELECT * FROM company WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) return null;

        int companyType = resultSet.getInt("companyType");
        String companyName = resultSet.getString("companyName");

        CompanyCreator creator = new CompanyCreator();
        Company newCompany = creator.createCompany(companyType);
        newCompany.setId(id);
        newCompany.setName(companyName);

        loadedMap.put(id, newCompany);

        return newCompany;
    }


    public List<Company> findByType(CompanyType type) throws SQLException {
        List<Company> allUsers = new ArrayList<>();

        String selectSQL = "SELECT * FROM company where companyType =?;";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, type.getIntValue());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            allUsers.add(findById(resultSet.getInt("id")));
        }

        return allUsers;
    }



    /**
     * Retrieves list of Company instances
     *
     * @return list of Company instances
     */
    @Override
    public Map<Integer, Company> findAll() throws SQLException {
        Map<Integer, Company> allUsers = new HashMap<>();

        String selectSQL = "SELECT id FROM company;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSQL);

        while (resultSet.next()) {
            allUsers.put(resultSet.getInt("id"), findById(resultSet.getInt("id")));
        }

        return allUsers;
    }

    @Override
    public void update(Company item) throws SQLException {
        if (loadedMap.values().contains(item)) {
            String updateSQL = "UPDATE company SET company.companyType = ? WHERE companyName = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, item.getType());
            preparedStatement.execute();
            int realId = findByName(item.getName()).getId();
            item.setId(realId);
            loadedMap.replace(realId, item);
        } else {
            addCompany(item);
        }
    }


    @Override
    public void update() throws SQLException {
        for (Company company : loadedMap.values())
            update(company);
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void clear() {
        loadedMap.clear();
    }

    /**
     * Retrives Company instance by name
     *
     * @param companyName name of company
     * @return Company instance or null
     */
    public Company findByName(final String companyName) throws SQLException {
        for (Company loadedCompany : loadedMap.values()) {
            if (loadedCompany.getName().equals(companyName))
                return loadedCompany;
        }

        // User not found, extract from database
        String selectSQL = "SELECT * FROM company WHERE companyName = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, companyName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) return null;

        int companyType = resultSet.getInt("companyType");
        int id = resultSet.getInt("id");

        CompanyCreator creator = new CompanyCreator();
        Company newCompany = creator.createCompany(companyType);
        newCompany.setId(id);
        newCompany.setName(companyName);

        loadedMap.put(id, newCompany);

        return newCompany;
    }

    /**
     * Loads domain object from loaded map by id
     *
     * @param id id to load domain object from map
     * @return instance of class that extends DomainObject if object is found, null otherwise
     */
    public Company loadDomainObject(Integer id){
        if(this.isLoadedDomainObject(id)){
            return loadedMap.get(id);
        }
        return null;
    }

    public boolean isLoadedDomainObject(Integer id){
        return loadedMap.containsKey(id);
    }
}
