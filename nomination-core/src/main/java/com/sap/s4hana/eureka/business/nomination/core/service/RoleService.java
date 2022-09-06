package com.sap.s4hana.eureka.business.nomination.core.service;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.repository.RoleRepository;
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
public class RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    RoleRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    public Role get(Long id) {
        return repository.load(id);
    }

    public Role findByNumber(String number) {
        return repository.findByRoleNumber(number);
    }

    public List<Role> getAll(Integer pageSize, Integer pageNo){
        Criteria criteria = new Criteria();
        criteria.orderBy(new Order(new Path(Long.class, "id"), false));
        criteria.top(pageSize);
        criteria.skip((pageNo-1)*pageSize);
        return repository.find(criteria);
    }

    public Long create(Role role) {
        Role _role = repository.findByRoleNumber(role.getRoleNumber());
        if (_role == null){
            return repository.create(role);
        }else{
            return _role.getId();
        }
    }

    public void update(String number, Role role) {
        Role _role = repository.findByRoleNumber(number);
        if (_role == null){
            throw new BusinessException("Role: " + number + " not found!");
        }else{
            _role.setRoleName(role.getRoleName());
            repository.update(_role);
        }
    }

    public void delete(String number) {
        Role _role = repository.findByRoleNumber(number);
        if (_role == null){
            throw new BusinessException("Role: " + number + " not found!");
        }else{
            repository.delete(_role);
        }
    }

}
