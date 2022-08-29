package com.sap.s4hana.eureka.business.nomination.core.domain.eo;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.User;
import lombok.Data;

@Data
public class VoteEO {

    private Long id;

    private String voteNumber;

    private UserEO nominee;

    private PrizeEO prize;

    private PeriodEO period;

    private UserEO voter;

}
