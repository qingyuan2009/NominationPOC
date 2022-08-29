package com.sap.s4hana.eureka.business.nomination.api.controller;

import com.sap.s4hana.eureka.business.nomination.api.dto.PeriodDTO;
import com.sap.s4hana.eureka.business.nomination.api.dto.PrizeDTO;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import com.sap.s4hana.eureka.business.nomination.core.service.PrizeService;
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

@RequestMapping(value = {"/api/prize"})
@Validated
@RestController
public class PrizeController {

    private static final String DEFAULT_PAGE_NO = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";

    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeController.class);

    @Autowired
    PrizeService service;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PrizeDTO> get(
            @PathVariable @Min(0) long id) {
        Prize prize = service.get(id);
        PrizeDTO prizeDTO = objectMapper.map(prize, PrizeDTO.class);
        return new ResponseEntity<>(prizeDTO, HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<PrizeDTO>> list(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) @Min(0) Integer pageSize,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NO) @Min(0) Integer pageNo) {
        List<Prize> prizes = service.getAll(pageSize, pageNo);
        return new ResponseEntity<>(objectMapper.map(prizes, PrizeDTO.class), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Long> create(
            @RequestBody PrizeDTO prizeDTO) {
        Prize prize = objectMapper.map(prizeDTO, Prize.class);
        Long id = service.create(prize);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-by-number", method = RequestMethod.GET)
    public ResponseEntity<PrizeDTO> getByNumber(
            @RequestParam String number) {
        Prize prize = service.findByNumber(number);
        PrizeDTO prizeDTO = objectMapper.map(prize, PrizeDTO.class);
        return new ResponseEntity<>(prizeDTO, HttpStatus.OK);
    }



}
