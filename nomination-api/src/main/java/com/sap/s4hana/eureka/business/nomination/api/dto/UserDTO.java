package com.sap.s4hana.eureka.business.nomination.api.dto;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String userNumber;

    private String firstName;

    private String lastName;

    private String loginName;

    private String password;

    private RoleDTO role;

    private TeamDTO team;
}
