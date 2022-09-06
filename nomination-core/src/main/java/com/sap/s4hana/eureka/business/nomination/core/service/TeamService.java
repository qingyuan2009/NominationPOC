package com.sap.s4hana.eureka.business.nomination.core.service;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Team;
import com.sap.s4hana.eureka.business.nomination.core.repository.RoleRepository;
import com.sap.s4hana.eureka.business.nomination.core.repository.TeamRepository;
import com.sap.s4hana.eureka.framework.common.converter.ObjectMapper;
import com.sap.s4hana.eureka.framework.common.exception.BusinessException;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.query.Criteria;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.query.Order;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.query.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    TeamRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    public Team get(Long id) {
        return repository.load(id);
    }

    public Team findByNumber(String number) {
        return repository.findByTeamNumber(number);
    }

    public List<Team> getAll(Integer pageSize, Integer pageNo){
        Criteria criteria = new Criteria();
        criteria.orderBy(new Order(new Path(Long.class, "id"), false));
        criteria.top(pageSize);
        criteria.skip((pageNo-1)*pageSize);
        return repository.find(criteria);
    }

    public Long create(Team team) {
        Team _team = repository.findByTeamNumber(team.getTeamNumber());
        if (_team == null){
            return repository.create(team);
        }else{
            return _team.getId();
        }
    }

    public void update(String number, Team team) {
        Team _team = repository.findByTeamNumber(number);
        if (_team == null){
            throw new BusinessException("Team: " + number + " not found!");
        }else{
            _team.setTeamName(team.getTeamName());
            repository.update(_team);
        }
    }

    public void delete(String number) {
        Team _team = repository.findByTeamNumber(number);
        if (_team == null){
            throw new BusinessException("Team: " + number + " not found!");
        }else{
            repository.delete(_team);
        }
    }

}
