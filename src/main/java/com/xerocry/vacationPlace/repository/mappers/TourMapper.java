/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.repository.mappers;

import com.xerocry.vacationPlace.logic.Company;
import com.xerocry.vacationPlace.logic.DomainObject;
import com.xerocry.vacationPlace.logic.Tour;
import com.xerocry.vacationPlace.repository.DataGateway;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Xerocry
 * @class TourMapper
 */
public class TourMapper implements AbstractMapper<Tour> {

    protected Map<Integer, Tour> loadedMap = new HashMap<>();
    private Connection connection;

    /**
     * @return CompanyMapper instance
     */
    public TourMapper() throws SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
    }

    /**
     * Add new tour
     *
     * @param tour company data for parsing
     * @return null
     */
    public void addTour(Tour tour) throws SQLException {
        if (loadedMap.values().contains(tour)) {
            update(tour);
        } else {
            String insertSQL = "INSERT INTO tour(tour.tourOperatorId, tour.tourOperatorTourId, " +
                    "beginDate, endDate, country, tourName) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tour.getTourOperatorId());
            preparedStatement.setInt(2, tour.getTourOperatorTourId());
            preparedStatement.setString(3, tour.getBeginDate());
            preparedStatement.setString(4, tour.getEndDate());
            preparedStatement.setString(5, tour.getCountry());
            preparedStatement.setString(6, tour.getTourName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                tour.setId(resultSet.getInt(1));
            }

            loadedMap.put(tour.getId(), tour);
        }
    }

    @Override
    public Tour findById(int id) throws SQLException {
        for (int loadedTourId : loadedMap.keySet()) {
            if (loadedTourId == id)
                return loadedMap.get(loadedTourId);
        }

        // Book not found, extract from database
        String selectSQL = "SELECT * FROM tour WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) return null;


        Integer tourOperatorTourId = resultSet.getInt("tourOperatorTourId");
        Integer tourOperatorId = resultSet.getInt("tourOperatorId");
        String beginDate = resultSet.getString("beginDate");
        String endDate = resultSet.getString("endDate");
        String country = resultSet.getString("country");
        String tourName = resultSet.getString("tourName");

        Tour someTour = new Tour(country, tourName);
        someTour.setTourOperatorTourId(tourOperatorTourId);
        someTour.setTourOperatorId(tourOperatorId);
        someTour.setBeginDate(beginDate);
        someTour.setEndDate(endDate);


        loadedMap.put(id, someTour);

        return someTour;
    }

    @Override
    public Map<Integer, Tour> findAll() throws SQLException {
        Map<Integer, Tour> allTours = new HashMap<>();

        String selectSQL = "SELECT id FROM tour;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSQL);

        while (resultSet.next()) {
            allTours.put(resultSet.getInt("id"), findById(resultSet.getInt("id")));
        }

        return allTours;
    }

    @Override
    public void update(Tour item) throws SQLException {
        if (loadedMap.values().contains(item)) {
            String updateSQL = "UPDATE tour SET  tour.tourOperatorId = ?, tour.tourOperatorTourId = ?, beginDate = ?, endDate = ?, " +
                    "tour.tourName = ?, tour.country = ?  WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setInt(1, item.getTourOperatorId());
            preparedStatement.setInt(2, item.getTourOperatorTourId());
            preparedStatement.setString(3, item.getBeginDate());
            preparedStatement.setString(4, item.getEndDate());
            preparedStatement.setString(5, item.getTourName());
            preparedStatement.setString(6, item.getCountry());
            preparedStatement.setInt(7, item.getId());
            preparedStatement.execute();

            loadedMap.replace(item.getId(), item);
        } else {
            addTour(item);
        }
    }

    @Override
    public void update() throws SQLException {
        for (Tour tour : loadedMap.values())
            update(tour);
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
    public Tour loadDomainObject(Integer id){
        if(this.isLoadedDomainObject(id)){
            return loadedMap.get(id);
        }
        return null;
    }

    public boolean isLoadedDomainObject(Integer id){
        return loadedMap.containsKey(id);
    }
}
