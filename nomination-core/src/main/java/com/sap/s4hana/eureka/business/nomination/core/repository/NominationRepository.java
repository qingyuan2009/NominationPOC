package com.sap.s4hana.eureka.business.nomination.core.repository;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Nomination;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.framework.rds.object.impl.repository.DefaultBusinessObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NominationRepository extends DefaultBusinessObjectRepository<Nomination> {

    public Nomination findByNominationNumber(String nominationNumber) {
        return this.loadByBusinessKey(nominationNumber);
    }
}
