package com.sap.s4hana.eureka.business.nomination.core.repository;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.framework.rds.object.impl.repository.DefaultBusinessObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PeriodRepository extends DefaultBusinessObjectRepository<Period> {

    public Period findByPeriodNumber(String periodNumber) {
        return this.loadByBusinessKey(periodNumber);
    }
}
