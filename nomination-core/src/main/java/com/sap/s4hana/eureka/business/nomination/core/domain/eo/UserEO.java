package com.sap.s4hana.eureka.business.nomination.core.domain.eo;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Team;
import lombok.Data;

@Data
public class UserEO {

    private Long id;

    private String userNumber;

    private String firstName;

    private String lastName;

    private String loginName;

    private String password;

    private RoleEO role;

    private TeamEO team;
}
