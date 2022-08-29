package com.sap.s4hana.eureka.business.nomination.core.repository;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import com.sap.s4hana.eureka.framework.rds.object.impl.repository.DefaultBusinessObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PrizeRepository extends DefaultBusinessObjectRepository<Prize> {

    public Prize findByPrizeNumber(String prizeNumber) {
        return this.loadByBusinessKey(prizeNumber);
    }
}
