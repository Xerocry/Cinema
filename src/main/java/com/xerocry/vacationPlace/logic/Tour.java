/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xerocry.vacationPlace.logic;

import lombok.Data;

import java.util.*;

/**
 * @author Xerocry
 * @class Tour
 * Represents tour
 */
@Data
public class Tour extends DomainObject {

    public static final String DEFAULT_MSG = "undefined";
    private Integer tourOperatorTourId = 0;

    private String lastModified;
    private Integer parentTourId = 0;

    private Integer tourOperatorId = 0;

    private String beginDate;
    private String endDate;
    private String country;
    private String tourName;

    /**
     * Constructor
     */
    public Tour() {
    }

    public Tour(String country, String tourName) {
        this.country = country;
        this.tourName = tourName;
    }

    /**
     * Retrives unique name of tour
     * Unique name of tour is built by concatination of tour Id,
     * separator i.e. --- and tour name
     *
     * @return unique name of tour
     */
    public String getUniqueName() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getId());
        builder.append(" -- ");
        builder.append(this.getTourName());
        return builder.toString();
    }

}
