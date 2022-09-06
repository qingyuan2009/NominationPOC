package com.sap.s4hana.eureka.business.nomination.core.service;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.*;
import com.sap.s4hana.eureka.business.nomination.core.repository.*;
import com.sap.s4hana.eureka.framework.common.converter.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BundleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BundleService.class);

    @Autowired
    RoleService roleService;

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @Autowired
    PeriodService periodService;

    @Autowired
    PrizeService prizeService;

    @Autowired
    NominationService nominationService;

    @Autowired
    VoteService voteService;

    @Autowired
    ObjectMapper objectMapper;

    public void create(Bundle bundle) {
        Iterable<Role> roles = bundle.getRoles();
        Iterable<Team> teams = bundle.getTeams();
        Iterable<User> users = bundle.getUsers();
        Iterable<Period> periods = bundle.getPeriods();
        Iterable<Prize> prizes = bundle.getPrizes();
        Iterable<Nomination> nominations = bundle.getNominations();
        Iterable<Vote> votes = bundle.getVotes();

        for (Role role:roles) {
            roleService.create(role);
        }
        for (Team team:teams) {
            teamService.create(team);
        }
        for (User user:users) {
            userService.create(user);
        }
        for (Period period:periods) {
            periodService.create(period);
        }
        for (Prize prize:prizes) {
            prizeService.create(prize);
        }
        for (Nomination nomination:nominations) {
            nominationService.create(nomination);
        }
        for (Vote vote:votes) {
            voteService.create(vote);
        }

    }

}
