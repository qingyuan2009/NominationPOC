package com.sap.s4hana.eureka.business.nomination.api.dto;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PeriodDTO {

    private Long id;

    private String periodNumber;

    private String periodName;

    private Date norminationStart;

    private Date norminationEnd;

    private Date voteStart;

    private Date voteEnd;

}
