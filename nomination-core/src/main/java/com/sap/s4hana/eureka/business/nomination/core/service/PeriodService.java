package com.sap.s4hana.eureka.business.nomination.core.service;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Nomination;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Team;
import com.sap.s4hana.eureka.business.nomination.core.repository.PeriodRepository;
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
public class PeriodService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodService.class);

    @Autowired
    PeriodRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    public Period get(Long id) {
        return repository.load(id);
    }

    public Period findByNumber(String number) {
        return repository.findByPeriodNumber(number);
    }

    public List<Period> getAll(Integer pageSize, Integer pageNo){
        Criteria criteria = new Criteria();
        criteria.orderBy(new Order(new Path(Long.class, "id"), false));
        criteria.top(pageSize);
        criteria.skip((pageNo-1)*pageSize);
        return repository.find(criteria);
    }

    public Long create(Period period) {
        Period _period = repository.findByPeriodNumber(period.getPeriodNumber());
        if (_period == null){
            return repository.create(period);
        }else{
            return _period.getId();
        }
    }

    public void update(String number, Period period) {
        Period _period = repository.findByPeriodNumber(number);
        if (_period == null){
            throw new BusinessException("Period: " + number + " not found!");
        }else{
            _period.setPeriodName(period.getPeriodName());
            _period.setNorminationStart(period.getNorminationStart());
            _period.setNorminationEnd(period.getNorminationEnd());
            _period.setVoteStart(period.getVoteStart());
            _period.setVoteEnd(period.getVoteEnd());
            repository.update(_period);
        }
    }

    public void delete(String number) {
        Period _period = repository.findByPeriodNumber(number);
        if (_period == null){
            throw new BusinessException("Period: " + number + " not found!");
        }else{
            repository.delete(_period);
        }
    }

}
