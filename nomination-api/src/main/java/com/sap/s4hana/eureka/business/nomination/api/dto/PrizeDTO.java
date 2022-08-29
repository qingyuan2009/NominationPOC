package com.sap.s4hana.eureka.business.nomination.api.dto;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PrizeDTO {

    private Long id;

    private String prizeNumber;

    private String prizeName;

    private String prizeDescription;

    private PeriodDTO period;

}
