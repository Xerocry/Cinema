/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.repository.mappers;

import com.xerocry.vacationPlace.Util;
import com.xerocry.vacationPlace.logic.Claim;
import com.xerocry.vacationPlace.logic.Tour;
import com.xerocry.vacationPlace.repository.DataGateway;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @class ClaimMapper
 * 
 * 
 * @author Xerocry
 */
public class ClaimMapper implements AbstractMapper<Claim> {

    private static Map<Integer, Claim> loadedClaimMap = new HashMap<>();
    private Connection connection;
    private TourMapper tourMapper;

    public ClaimMapper() throws SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
        tourMapper = new TourMapper();
//        userMapper = new UserMapper();
    }

    /**
     * Add new claim
     *
     * @param claim company data for parsing
     * @return null
     */
    public void addClaim(Claim claim) throws SQLException {
        if (loadedClaimMap.values().contains(claim)) {
            update(claim);
        } else {
            String insertSQL = "INSERT INTO claim(tourOwnerId, requestDate, customerData, tourOperatorTourId, status) " +
                    "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, claim.getTourOwnerId());
            preparedStatement.setString(2, Util.getStringFromFormattedDate(claim.getRequestDate()));
            preparedStatement.setString(3, claim.getShortCustomersInfo());
            if (claim.getTourOperatorTour() == null) {
                preparedStatement.setNull(4, Types.INTEGER);
            } else {
                preparedStatement.setInt(4, claim.getTourOperatorTour().getId());
            }
            preparedStatement.setString(5, claim.getStatus());


            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                claim.setId(resultSet.getInt(1));
            }

            loadedClaimMap.put(claim.getId(), claim);

//            bookMapper.update(bookExchange.getBook());
//            userMapper.update(bookExchange.getOwner());
            if (claim.getTourOperatorTour() != null) tourMapper.update(claim.getTourOperatorTour());
        }
    }

    @Override
    public Claim findById(int id) throws SQLException {
        for (int loadedBookExchangeId : loadedClaimMap.keySet()) {
            if (loadedBookExchangeId == id)
                return loadedClaimMap.get(loadedBookExchangeId);
        }

        // BookExchange not found, extract from database
        String selectSQL = "SELECT * FROM claim WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) return null;

        int tourId = resultSet.getInt("tourOperatorTourId");
        String status = resultSet.getString("status");
        Date requestDate = Util.getDateFromFormattedString(resultSet.getString("requestDate"));

        Tour tour = tourMapper.findById(tourId);

        Claim newClaim = new Claim(id, tour.getTourOperatorTourId(), requestDate, status, tour);

        loadedClaimMap.put(id, newClaim);

        return newClaim;
    }

    @Override
    public Map<Integer, Claim> findAll() throws SQLException {
        Map<Integer, Claim> allClaims = new HashMap<>();

        String selectSQL = "SELECT id FROM claim;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSQL);

        while (resultSet.next()) {
            allClaims.put(resultSet.getInt("id"), findById(resultSet.getInt("id")));
        }

        return allClaims;
    }

    @Override
    public void update(Claim item) throws SQLException {
        if (loadedClaimMap.values().contains(item)) {
            String updateSQL = "UPDATE claim SET  tourId = ?, tourOwnerId = ?, requestDate = ?, customerData = ?, " +
                    "status = ?  WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setInt(1, item.getTourOperatorTour().getId());
            preparedStatement.setInt(2, item.getTourOwnerId());
            preparedStatement.setString(3, Util.getStringFromFormattedDate(item.getRequestDate()));
            if (item.getShortCustomersInfo() == null) {
                preparedStatement.setNull(4, Types.INTEGER);
            } else {
                preparedStatement.setString(4, item.getShortCustomersInfo());
            }
            preparedStatement.setString(5, item.getStatus());
            preparedStatement.execute();

            loadedClaimMap.replace(item.getId(), item);

            tourMapper.update(item.getTourOperatorTour());
//            if (item.getReader() != null) userMapper.update(item.getReader());
        } else {
            addClaim(item);
        }
    }

    @Override
    public void update() throws SQLException {
        tourMapper.update();
        for (Claim claim : loadedClaimMap.values()) {
            update(claim);
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        tourMapper.closeConnection();
        connection.close();
    }

    @Override
    public void clear() {
        tourMapper.clear();
        loadedClaimMap.clear();
    }
}
