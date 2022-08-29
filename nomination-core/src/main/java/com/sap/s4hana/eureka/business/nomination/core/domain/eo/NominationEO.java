package com.sap.s4hana.eureka.business.nomination.core.domain.eo;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.User;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.annotation.BOProperty;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.annotation.BusinessKey;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
public class NominationEO {

    private Long id;

    private String nominationNumber;

    private UserEO nominee;

    private PrizeEO prize;

    private PeriodEO period;

    private UserEO nominator;

    private String comment;
}
