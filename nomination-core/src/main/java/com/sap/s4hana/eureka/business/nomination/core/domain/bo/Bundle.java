package com.sap.s4hana.eureka.business.nomination.core.domain.bo;

import lombok.Data;

import java.util.List;

@Data
public class Bundle {

    List<Role> roles;

    List<Team> teams;

    List<User> users;

    List<Period> periods;

    List<Prize> prizes;

    List<Nomination> nominations;

    List<Vote> votes;

}
