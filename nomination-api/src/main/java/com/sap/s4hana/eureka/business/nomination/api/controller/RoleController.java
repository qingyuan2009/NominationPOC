package com.sap.s4hana.eureka.business.nomination.api.controller;

import com.sap.s4hana.eureka.business.nomination.api.dto.RoleDTO;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
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
import java.util.Optional;
import java.util.UUID;

@RequestMapping(value = {"/api/role"})
@Validated
@RestController
public class RoleController {

    private static final String DEFAULT_PAGE_NO = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService service;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RoleDTO> get(
            @PathVariable @Min(0) long id) {
        Role role = service.get(id);
        RoleDTO roleDTO = objectMapper.map(role, RoleDTO.class);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<RoleDTO>> list(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) @Min(0) Integer pageSize,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NO) @Min(0) Integer pageNo) {
        List<Role> roles = service.getAll(pageSize, pageNo);
        return new ResponseEntity<>(objectMapper.map(roles, RoleDTO.class), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Long> create(
            @RequestBody RoleDTO roleDTO) {
        Role role = objectMapper.map(roleDTO, Role.class);
        Long id = service.create(role);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-by-number", method = RequestMethod.GET)
    public ResponseEntity<RoleDTO> getByNumber(
            @RequestParam String number) {
        Role role = service.findByNumber(number);
        RoleDTO roleDTO = objectMapper.map(role, RoleDTO.class);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{number}" , method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> update(@PathVariable String number, @RequestBody Role role) {
        service.update(number, role);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(value = "/{number}" , method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable String number) {
        service.delete(number);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
