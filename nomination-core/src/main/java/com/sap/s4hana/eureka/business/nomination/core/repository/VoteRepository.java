package com.sap.s4hana.eureka.business.nomination.core.repository;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Nomination;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Vote;
import com.sap.s4hana.eureka.framework.rds.object.impl.repository.DefaultBusinessObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public class VoteRepository extends DefaultBusinessObjectRepository<Vote> {

    public Vote findByVoteNumber(String voteNumber) {
        return this.loadByBusinessKey(voteNumber);
    }
}
