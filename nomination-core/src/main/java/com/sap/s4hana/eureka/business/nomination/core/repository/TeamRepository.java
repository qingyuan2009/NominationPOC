package com.sap.s4hana.eureka.business.nomination.core.repository;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Team;
import com.sap.s4hana.eureka.framework.rds.object.impl.repository.DefaultBusinessObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepository extends DefaultBusinessObjectRepository<Team> {

    public Team findByTeamNumber(String teamNumber) {
        return this.loadByBusinessKey(teamNumber);
    }
}
