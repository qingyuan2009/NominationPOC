package com.sap.s4hana.eureka.business.nomination.core.service;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Team;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.User;
import com.sap.s4hana.eureka.business.nomination.core.repository.RoleRepository;
import com.sap.s4hana.eureka.business.nomination.core.repository.TeamRepository;
import com.sap.s4hana.eureka.business.nomination.core.repository.UserRepository;
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
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository repository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ObjectMapper objectMapper;

    public User get(Long id) {
        return repository.load(id);
    }

    public User findByNumber(String number) {
        return repository.findByUserNumber(number);
    }

    public List<User> getAll(Integer pageSize, Integer pageNo){
        Criteria criteria = new Criteria();
        criteria.orderBy(new Order(new Path(Long.class, "id"), false));
        criteria.top(pageSize);
        criteria.skip((pageNo-1)*pageSize);
        return repository.find(criteria);
    }

    public Long create(User user) {
        User _user = repository.findByUserNumber(user.getUserNumber());
        Team _team = teamRepository.findByTeamNumber(user.getTeam().getTeamNumber());
        Role _role = roleRepository.findByRoleNumber(user.getRole().getRoleNumber());
        if (_team != null) {
            user.setTeam(_team);
        }
        if (_role != null) {
            user.setRole(_role);
        }
        if (_user == null){
            return repository.create(user);
        }else{
            return _user.getId();
        }
    }

    public void update(String number, User user) {
        User _user = repository.findByUserNumber(number);
        if (_user == null){
            throw new BusinessException("User: " + number+ " not found!");
        }else{
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            _user.setLoginName(user.getLoginName());
            _user.setPassword(user.getPassword());
            Role _role = roleRepository.findByRoleNumber(user.getRole().getRoleNumber());
            if (_role == null){
                throw new BusinessException("Role: " + user.getRole().getRoleNumber()+ " not found!");
            }else{
                _user.setRole(_role);
            }
            Team _team = teamRepository.findByTeamNumber(user.getTeam().getTeamNumber());
            if (_team == null){
                throw new BusinessException("Team: " + user.getTeam().getTeamNumber() + " not found!");
            }else{
                _user.setTeam(_team);
            }
            repository.update(_user);
        }
    }

    public void delete(String number) {
        User _user = repository.findByUserNumber(number);
        if (_user == null){
            throw new BusinessException("User: " + number + " not found!");
        }else{
            repository.delete(_user);
        }
    }

}
