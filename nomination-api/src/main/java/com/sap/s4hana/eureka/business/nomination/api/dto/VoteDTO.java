package com.sap.s4hana.eureka.business.nomination.api.dto;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.User;
import lombok.Data;

@Data
public class VoteDTO {

    private Long id;

    private String voteNumber;

    private UserDTO nominee;

    private PrizeDTO prize;

    private PeriodDTO period;

    private UserDTO voter;
}
