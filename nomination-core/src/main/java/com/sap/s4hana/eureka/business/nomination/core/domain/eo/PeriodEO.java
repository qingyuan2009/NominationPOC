package com.sap.s4hana.eureka.business.nomination.core.domain.eo;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.annotation.BOProperty;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.annotation.BusinessKey;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
public class PeriodEO {

    private Long id;

    private String periodNumber;

    private String periodName;

    private Date norminationStart;

    private Date norminationEnd;

    private Date voteStart;

    private Date voteEnd;

}
