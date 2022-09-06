package com.sap.s4hana.eureka.business.nomination.core.service;

import com.sap.s4hana.eureka.business.nomination.core.domain.bo.*;
import com.sap.s4hana.eureka.business.nomination.core.repository.PeriodRepository;
import com.sap.s4hana.eureka.business.nomination.core.repository.PrizeRepository;
import com.sap.s4hana.eureka.business.nomination.core.repository.UserRepository;
import com.sap.s4hana.eureka.business.nomination.core.repository.VoteRepository;
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
public class VoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    VoteRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    ObjectMapper objectMapper;

    public Vote get(Long id) {
        return repository.load(id);
    }

    public Vote findByNumber(String number) {
        return repository.findByVoteNumber(number);
    }

    public List<Vote> getAll(Integer pageSize, Integer pageNo){
        Criteria criteria = new Criteria();
        criteria.orderBy(new Order(new Path(Long.class, "id"), false));
        criteria.top(pageSize);
        criteria.skip((pageNo-1)*pageSize);
        return repository.find(criteria);
    }

    public Long create(Vote vote) {
        Vote _vote = repository.findByVoteNumber(vote.getVoteNumber());
        User _nominee = userRepository.findByUserNumber(vote.getNominee().getUserNumber());
        User _voter = userRepository.findByUserNumber(vote.getVoter().getUserNumber());
        Period _period = periodRepository.findByPeriodNumber(vote.getPeriod().getPeriodNumber());
        Prize _prize = prizeRepository.findByPrizeNumber(vote.getPrize().getPrizeNumber());
        if (_nominee != null) {
            vote.setNominee(_nominee);
        }
        if (_voter != null) {
            vote.setVoter(_voter);
        }
        if (_period != null) {
            vote.setPeriod(_period);
        }
        if (_prize != null) {
            vote.setPrize(_prize);
        }
        if (_vote == null){
            return repository.create(vote);
        }else{
            return _vote.getId();
        }
    }

    public void update(String number, Vote vote) {
        Vote _vote = repository.findByVoteNumber(number);
        if (_vote == null){
            throw new BusinessException("Vote: " + number+ " not found!");
        }else{
            User _nominee = userRepository.findByUserNumber(vote.getNominee().getUserNumber());
            if (_nominee == null){
                throw new BusinessException("Nominee: " + vote.getNominee().getUserNumber() + " not found!");
            }else{
                _vote.setNominee(_nominee);
            }
            Prize _prize = prizeRepository.findByPrizeNumber(vote.getPrize().getPrizeNumber());
            if (_prize == null){
                throw new BusinessException("Prize: " + vote.getPrize().getPrizeNumber() + " not found!");
            }else{
                _vote.setPrize(_prize);
            }
            Period _period = periodRepository.findByPeriodNumber(vote.getPeriod().getPeriodNumber());
            if (_period == null){
                throw new BusinessException("Period: " + vote.getPeriod().getPeriodNumber() + " not found!");
            }else{
                _vote.setPeriod(_period);
            }
            User _voter = userRepository.findByUserNumber(vote.getVoter().getUserNumber());
            if (_voter == null){
                throw new BusinessException("Voter: " + vote.getVoter().getUserNumber() + " not found!");
            }else{
                _vote.setVoter(_voter);
            }
            repository.update(_vote);
        }
    }

    public void delete(String number) {
        Vote _vote = repository.findByVoteNumber(number);
        if (_vote == null){
            throw new BusinessException("Vote: " + number + " not found!");
        }else{
            repository.delete(_vote);
        }
    }

}
