package com.sap.s4hana.eureka.business.nomination.api.controller;

import com.sap.s4hana.eureka.business.nomination.api.dto.NominationDTO;
import com.sap.s4hana.eureka.business.nomination.api.dto.RoleDTO;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Nomination;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.User;
import com.sap.s4hana.eureka.business.nomination.core.service.NominationService;
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

@RequestMapping(value = {"/api/nomination"})
@Validated
@RestController
public class NominationController {

    private static final String DEFAULT_PAGE_NO = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";

    private static final Logger LOGGER = LoggerFactory.getLogger(NominationController.class);

    @Autowired
    NominationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<NominationDTO> get(
            @PathVariable @Min(0) long id) {
        Nomination nomination = service.get(id);
        NominationDTO nominationDTO = objectMapper.map(nomination, NominationDTO.class);
        return new ResponseEntity<>(nominationDTO, HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<NominationDTO>> list(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) @Min(0) Integer pageSize,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NO) @Min(0) Integer pageNo) {
        List<Nomination> nominations = service.getAll(pageSize, pageNo);
        return new ResponseEntity<>(objectMapper.map(nominations, NominationDTO.class), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Long> create(
            @RequestBody NominationDTO nominationDTO) {
        Nomination nomination = objectMapper.map(nominationDTO, Nomination.class);
        Long id = service.create(nomination);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-by-number", method = RequestMethod.GET)
    public ResponseEntity<NominationDTO> getByNumber(
            @RequestParam String number) {
        Nomination nomination = service.findByNumber(number);
        NominationDTO nominationDTO = objectMapper.map(nomination, NominationDTO.class);
        return new ResponseEntity<>(nominationDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{number}" , method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> update(@PathVariable String number, @RequestBody Nomination nomination) {
        service.update(number, nomination);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(value = "/{number}" , method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable String number) {
        service.delete(number);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
