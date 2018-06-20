/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.repository.mappers;

import com.xerocry.vacationPlace.logic.*;
import com.xerocry.vacationPlace.logic.companies.Company;
import com.xerocry.vacationPlace.logic.companies.TourOperator;
import com.xerocry.vacationPlace.repository.DataGateway;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xerocry
 * @class TourMapper
 */
public class SubscriptionMapper implements AbstractMapper<Subscription> {

    protected Map<Integer, Subscription> loadedMap = new HashMap<>();
    private Connection connection;
    private CompanyMapper companyMapper;

    /**
     * @return CompanyMapper instance
     */
    public SubscriptionMapper() throws SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
        companyMapper = CompanyMapper.getMapper();
    }

    /**
     * Add new subscription
     *
     * @param subscription company data for parsing
     * @return null
     */
    public void addSubscription(Subscription subscription) throws SQLException {
        if (loadedMap.values().contains(subscription)) {
            update(subscription);
        } else {
            String insertSQL = "INSERT INTO subscription(subscription.tourOperatorId, subscription.requesterId, status) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, subscription.getTourOperatorId());
            preparedStatement.setInt(2, subscription.getRequesterId());
            preparedStatement.setInt(3, subscription.getStatus().ordinal());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                subscription.setId(resultSet.getInt(1));
            }

            loadedMap.put(subscription.getId(), subscription);
        }
    }

    /**
     * Remove subscription
     *
     * @param subscription company data for parsing
     * @return null
     */
    public void removeSubscription(Subscription subscription) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM subscription WHERE tourOperatorId = ? AND requesterId = ?");
        preparedStatement.setInt(1, subscription.getTourOperatorId());
        preparedStatement.setInt(2, subscription.getRequesterId());

        preparedStatement.executeUpdate();
        loadedMap.remove(subscription.getId());

    }

    public List<TourOperator> findAttendants(Company company) throws SQLException {
        List<TourOperator> companies = new ArrayList<>();

        String selectSQL = "SELECT tourOperatorId FROM subscription WHERE requesterId = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, company.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            TourOperator tourOperator = (TourOperator) companyMapper.findById(resultSet.getInt("tourOperatorId"));
            companies.add(tourOperator);
            companyMapper.loadedMap.put(resultSet.getInt("tourOperatorId"), tourOperator);
        }

        return companies;
    }

    public List<Subscription> findByAgency(Company company) throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();

        String selectSQL = "SELECT * FROM subscription WHERE requesterId = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, company.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Subscription subscription = findById(resultSet.getInt("id"));
            subscriptions.add(subscription);
            loadedMap.put(subscription.getId(), subscription);
        }

        return subscriptions;
    }

    public Subscription findByParameters(Integer companyId, Integer tourId) throws SQLException {
        String selectSQL = "SELECT * FROM subscription WHERE requesterId = ? AND tourOperatorId = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, companyId);
        preparedStatement.setInt(2, tourId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) return null;

        Integer tourOperatorId = resultSet.getInt("tourOperatorId");
        TourOperator tourOperator = (TourOperator) companyMapper.findById(tourOperatorId);
        Integer requesterId = resultSet.getInt("requesterId");
        Integer status = resultSet.getInt("status");

        Subscription subscription = new Subscription();
        subscription.setRequesterId(requesterId);
        subscription.setTourOperator(tourOperator);
        subscription.setTourOperatorId(tourOperatorId);
        switch (status) {
            case 0:
                subscription.setStatus(SubscriptionStatus.APPROVED);
            case 1:
                subscription.setStatus(SubscriptionStatus.REJECTED);
            case 2:
                subscription.setStatus(SubscriptionStatus.WAITING_FOR_CORRECTION);

        }

        loadedMap.put(id, subscription);

        return subscription;
    }

    @Override
    public Subscription findById(int id) throws SQLException {
        for (int loadedSubscrId : loadedMap.keySet()) {
            if (loadedSubscrId == id)
                return loadedMap.get(loadedSubscrId);
        }

        // Book not found, extract from database
        String selectSQL = "SELECT * FROM subscription WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) return null;


        Integer tourOperatorId = resultSet.getInt("tourOperatorId");
        TourOperator tourOperator = (TourOperator) companyMapper.findById(tourOperatorId);
        Integer requesterId = resultSet.getInt("requesterId");
        Integer status = resultSet.getInt("status");

        Subscription subscription = new Subscription();
        subscription.setId(id);
        subscription.setRequesterId(requesterId);
        subscription.setTourOperator(tourOperator);
        subscription.setTourOperatorId(tourOperatorId);
        switch (status) {
            case 0:
                subscription.setStatus(SubscriptionStatus.APPROVED);
            case 1:
                subscription.setStatus(SubscriptionStatus.REJECTED);
            case 2:
                subscription.setStatus(SubscriptionStatus.WAITING_FOR_CORRECTION);

        }

        loadedMap.put(id, subscription);

        return subscription;
    }

    @Override
    public Map<Integer, Subscription> findAll() throws SQLException {
        Map<Integer, Subscription> allSubscriptions = new HashMap<>();

        String selectSQL = "SELECT id FROM subscription;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSQL);

        while (resultSet.next()) {
            allSubscriptions.put(resultSet.getInt("id"), findById(resultSet.getInt("id")));
        }

        return allSubscriptions;
    }

    public void updateStatus(Subscription item) throws SQLException {
        String updateSQL = "UPDATE subscription SET status = ? " +
                "WHERE subscription.tourOperatorId = ? AND subscription.requesterId = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
        preparedStatement.setInt(1, item.getStatus().ordinal());
        preparedStatement.setInt(2, item.getTourOperatorId());
        preparedStatement.setInt(3, item.getRequesterId());
        preparedStatement.execute();
    }

    @Override
    public void update(Subscription item) throws SQLException {
        if (loadedMap.values().contains(item)) {
            String updateSQL = "UPDATE subscription SET  subscription.tourOperatorId = ?, subscription.requesterId = ?, status = ? " +
                    "WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setInt(1, item.getTourOperatorId());
            preparedStatement.setInt(2, item.getRequesterId());
            preparedStatement.setInt(3, item.getStatus().ordinal());
            preparedStatement.setInt(4, item.getId());
            preparedStatement.execute();

            loadedMap.replace(item.getId(), item);
        } else {
            addSubscription(item);
        }
    }

    @Override
    public void update() throws SQLException {
        for (Subscription subscription : loadedMap.values())
            update(subscription);
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
     * Loads domain object from loaded map by id
     *
     * @param id id to load domain object from map
     * @return instance of class that extends DomainObject if object is found, null otherwise
     */
    public Subscription loadDomainObject(Integer id) {
        if (this.isLoadedDomainObject(id)) {
            return loadedMap.get(id);
        }
        return null;
    }

    public boolean isLoadedDomainObject(Integer id) {
        return loadedMap.containsKey(id);
    }
}
