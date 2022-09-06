package com.sap.s4hana.eureka.business.nomination.core.service;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.*;
import com.sap.s4hana.eureka.business.nomination.core.repository.PeriodRepository;
import com.sap.s4hana.eureka.business.nomination.core.repository.PrizeRepository;
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
public class PrizeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeService.class);

    @Autowired
    PrizeRepository repository;

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    ObjectMapper objectMapper;

    public Prize get(Long id) {
        return repository.load(id);
    }

    public Prize findByNumber(String number) {
        return repository.findByPrizeNumber(number);
    }

    public List<Prize> getAll(Integer pageSize, Integer pageNo){
        Criteria criteria = new Criteria();
        criteria.orderBy(new Order(new Path(Long.class, "id"), false));
        criteria.top(pageSize);
        criteria.skip((pageNo-1)*pageSize);
        return repository.find(criteria);
    }

    public Long create(Prize prize) {
        Prize _prize = repository.findByPrizeNumber(prize.getPrizeNumber());
        Period _period = periodRepository.findByPeriodNumber(prize.getPeriod().getPeriodNumber());
        if (_period != null) {
            prize.setPeriod(_period);
        }
        if (_prize == null){
            return repository.create(prize);
        }else{
            return _prize.getId();
        }
    }

    public void update(String number, Prize prize) {
        Prize _prize = repository.findByPrizeNumber(number);
        if (_prize == null){
            throw new BusinessException("Prize: " + number+ " not found!");
        }else{
            _prize.setPrizeName(prize.getPrizeName());
            _prize.setPrizeDescription(prize.getPrizeDescription());
            Period _peirod = periodRepository.findByPeriodNumber(prize.getPeriod().getPeriodNumber());
            if (_peirod == null){
                throw new BusinessException("Period: " + prize.getPeriod().getPeriodNumber()+ " not found!");
            }else{
                _prize.setPeriod(_peirod);
            }
            repository.update(_prize);
        }
    }

    public void delete(String number) {
        Prize _prize = repository.findByPrizeNumber(number);
        if (_prize == null){
            throw new BusinessException("Prize: " + number + " not found!");
        }else{
            repository.delete(_prize);
        }
    }

}
