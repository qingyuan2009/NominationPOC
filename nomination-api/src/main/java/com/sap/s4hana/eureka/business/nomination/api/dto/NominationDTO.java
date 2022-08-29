package com.sap.s4hana.eureka.business.nomination.api.dto;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NominationDTO {

    private Long id;

    private String nominationNumber;

    private UserDTO nominee;

    private PrizeDTO prize;

    private PeriodDTO period;

    private UserDTO nominator;

    private String comment;
}
