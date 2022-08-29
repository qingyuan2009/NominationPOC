package com.sap.s4hana.eureka.business.nomination.core.repository;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.User;
import com.sap.s4hana.eureka.framework.rds.object.impl.repository.DefaultBusinessObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends DefaultBusinessObjectRepository<User> {

    public User findByUserNumber(String userNumber) {
        return this.loadByBusinessKey(userNumber);
    }
}
