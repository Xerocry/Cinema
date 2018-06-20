package com.xerocry.vacationPlace.logic;

import com.xerocry.vacationPlace.logic.companies.TourOperator;
import lombok.Data;

@Data
public class Subscription extends DomainObject {

//    private Integer id;
    private Integer requesterId;
    private Integer tourOperatorId;
    private TourOperator tourOperator;
    private Integer tourAmount;

    private SubscriptionStatus status;

}
