package com.sap.s4hana.eureka.business.nomination.api.controller;

import com.sap.s4hana.eureka.business.nomination.api.dto.NominationDTO;
import com.sap.s4hana.eureka.business.nomination.api.dto.PeriodDTO;
import com.sap.s4hana.eureka.business.nomination.api.dto.RoleDTO;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Nomination;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.service.PeriodService;
import com.sap.s4hana.eureka.business.nomination.core.service.RoleService;
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

@RequestMapping(value = {"/api/period"})
@Validated
@RestController
public class PeriodController {

    private static final String DEFAULT_PAGE_NO = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodController.class);

    @Autowired
    PeriodService service;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PeriodDTO> get(
            @PathVariable @Min(0) long id) {
        Period period = service.get(id);
        PeriodDTO periodDTO = objectMapper.map(period, PeriodDTO.class);
        return new ResponseEntity<>(periodDTO, HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<PeriodDTO>> list(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) @Min(0) Integer pageSize,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NO) @Min(0) Integer pageNo) {
        List<Period> periods = service.getAll(pageSize, pageNo);
        return new ResponseEntity<>(objectMapper.map(periods, PeriodDTO.class), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Long> create(
            @RequestBody PeriodDTO periodDTO) {
        Period period = objectMapper.map(periodDTO, Period.class);
        Long id = service.create(period);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-by-number", method = RequestMethod.GET)
    public ResponseEntity<PeriodDTO> getByNumber(
            @RequestParam String number) {
        Period period = service.findByNumber(number);
        PeriodDTO periodDTO = objectMapper.map(period, PeriodDTO.class);
        return new ResponseEntity<>(periodDTO, HttpStatus.OK);
    }


}
