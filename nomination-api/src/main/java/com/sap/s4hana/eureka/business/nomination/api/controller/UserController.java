package com.sap.s4hana.eureka.business.nomination.api.controller;

import com.sap.s4hana.eureka.business.nomination.api.dto.RoleDTO;
import com.sap.s4hana.eureka.business.nomination.api.dto.TeamDTO;
import com.sap.s4hana.eureka.business.nomination.api.dto.UserDTO;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Team;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.User;
import com.sap.s4hana.eureka.business.nomination.core.service.RoleService;
import com.sap.s4hana.eureka.business.nomination.core.service.UserService;
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

@RequestMapping(value = {"/api/user"})
@Validated
@RestController
public class UserController {

    private static final String DEFAULT_PAGE_NO = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> get(
            @PathVariable @Min(0) long id) {
        User user = service.get(id);
        UserDTO userDTO = objectMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> list(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) @Min(0) Integer pageSize,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NO) @Min(0) Integer pageNo) {
        List<User> users = service.getAll(pageSize, pageNo);
        return new ResponseEntity<>(objectMapper.map(users, UserDTO.class), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Long> create(
            @RequestBody UserDTO userDTO) {
        User user = objectMapper.map(userDTO, User.class);
        Long id = service.create(user);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-by-number", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getByNumber(
            @RequestParam String number) {
        User user = service.findByNumber(number);
        UserDTO userDTO = objectMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{number}" , method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> update(@PathVariable String number, @RequestBody User user) {
        service.update(number, user);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(value = "/{number}" , method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable String number) {
        service.delete(number);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
