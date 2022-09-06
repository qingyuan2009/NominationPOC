package com.sap.s4hana.eureka.business.nomination.core.service;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.*;
import com.sap.s4hana.eureka.business.nomination.core.repository.NominationRepository;
import com.sap.s4hana.eureka.business.nomination.core.repository.PeriodRepository;
import com.sap.s4hana.eureka.business.nomination.core.repository.PrizeRepository;
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
public class NominationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NominationService.class);

    @Autowired
    NominationRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    ObjectMapper objectMapper;

    public Nomination get(Long id) {
        return repository.load(id);
    }

    public Nomination findByNumber(String number) {
        return repository.findByNominationNumber(number);
    }

    public List<Nomination> getAll(Integer pageSize, Integer pageNo){
        Criteria criteria = new Criteria();
        criteria.orderBy(new Order(new Path(Long.class, "id"), false));
        criteria.top(pageSize);
        criteria.skip((pageNo-1)*pageSize);
        return repository.find(criteria);
    }

    public Long create(Nomination nomination) {

        Nomination _nomination = repository.findByNominationNumber(nomination.getNominationNumber());
        User _nominee = userRepository.findByUserNumber(nomination.getNominee().getUserNumber());
        User _nominator = userRepository.findByUserNumber(nomination.getNominator().getUserNumber());
        Period _period = periodRepository.findByPeriodNumber(nomination.getPeriod().getPeriodNumber());
        Prize _prize = prizeRepository.findByPrizeNumber(nomination.getPrize().getPrizeNumber());
        if (_nominee != null) {
            nomination.setNominee(_nominee);
        }
        if (_nominator != null) {
            nomination.setNominator(_nominator);
        }
        if (_period != null) {
            nomination.setPeriod(_period);
        }
        if (_prize != null) {
            nomination.setPrize(_prize);
        }
        if (_nomination == null){
            return repository.create(nomination);
        }else{
            return _nomination.getId();
        }
    }

    public void update(String number, Nomination nomination) {
        Nomination _nomination = repository.findByNominationNumber(number);
        if (_nomination == null){
            throw new BusinessException("Nomination: " + number+ " not found!");
        }else{
            _nomination.setComment(nomination.getComment());
            User _nominee = userRepository.findByUserNumber(nomination.getNominee().getUserNumber());
            if (_nominee == null){
                throw new BusinessException("Nominee: " + nomination.getNominee().getUserNumber() + " not found!");
            }else{
                _nomination.setNominee(_nominee);
            }
            Prize _prize = prizeRepository.findByPrizeNumber(nomination.getPrize().getPrizeNumber());
            if (_prize == null){
                throw new BusinessException("Prize: " + nomination.getPrize().getPrizeNumber() + " not found!");
            }else{
                _nomination.setPrize(_prize);
            }
            Period _period = periodRepository.findByPeriodNumber(nomination.getPeriod().getPeriodNumber());
            if (_period == null){
                throw new BusinessException("Period: " + nomination.getPeriod().getPeriodNumber() + " not found!");
            }else{
                _nomination.setPeriod(_period);
            }
            User _nominator = userRepository.findByUserNumber(nomination.getNominator().getUserNumber());
            if (_nominator == null){
                throw new BusinessException("Nominator: " + nomination.getNominator().getUserNumber() + " not found!");
            }else{
                _nomination.setNominator(_nominator);
            }
            repository.update(_nomination);
        }
    }

    public void delete(String number) {
        Nomination _nomination = repository.findByNominationNumber(number);
        if (_nomination == null){
            throw new BusinessException("Nomination: " + number + " not found!");
        }else{
            repository.delete(_nomination);
        }
    }

}
