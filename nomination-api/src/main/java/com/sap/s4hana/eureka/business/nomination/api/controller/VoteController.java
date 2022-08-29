package com.sap.s4hana.eureka.business.nomination.api.controller;

import com.sap.s4hana.eureka.business.nomination.api.dto.UserDTO;
import com.sap.s4hana.eureka.business.nomination.api.dto.VoteDTO;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.User;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Vote;
import com.sap.s4hana.eureka.business.nomination.core.service.VoteService;
import com.sap.s4hana.eureka.framework.common.converter.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RequestMapping(value = {"/api/vote"})
@Validated
@RestController
public class VoteController {

    private static final String DEFAULT_PAGE_NO = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    VoteService service;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<VoteDTO> get(
            @PathVariable @Min(0) long id) {
        Vote vote = service.get(id);
        VoteDTO voteDTO = objectMapper.map(vote, VoteDTO.class);
        return new ResponseEntity<>(voteDTO, HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<VoteDTO>> list(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) @Min(0) Integer pageSize,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NO) @Min(0) Integer pageNo) {
        List<Vote> votes = service.getAll(pageSize, pageNo);
        return new ResponseEntity<>(objectMapper.map(votes, VoteDTO.class), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Long> create(
            @RequestBody VoteDTO voteDTO) {
        Vote vote = objectMapper.map(voteDTO, Vote.class);
        Long id = service.create(vote);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-by-number", method = RequestMethod.GET)
    public ResponseEntity<VoteDTO> getByNumber(
            @RequestParam String number) {
        Vote vote = service.findByNumber(number);
        VoteDTO voteDTO = objectMapper.map(vote, VoteDTO.class);
        return new ResponseEntity<>(voteDTO, HttpStatus.OK);
    }


}
