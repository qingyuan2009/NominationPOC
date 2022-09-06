package com.sap.s4hana.eureka.business.nomination.api.controller;

import com.sap.s4hana.eureka.business.nomination.api.dto.PrizeDTO;
import com.sap.s4hana.eureka.business.nomination.api.dto.RoleDTO;
import com.sap.s4hana.eureka.business.nomination.api.dto.TeamDTO;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Prize;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Role;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Team;
import com.sap.s4hana.eureka.business.nomination.core.service.RoleService;
import com.sap.s4hana.eureka.business.nomination.core.service.TeamService;
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

@RequestMapping(value = {"/api/team"})
@Validated
@RestController
public class TeamController {

    private static final String DEFAULT_PAGE_NO = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    TeamService service;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<TeamDTO> get(
            @PathVariable @Min(0) long id) {
        Team team = service.get(id);
        TeamDTO teamDTO = objectMapper.map(team, TeamDTO.class);
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<TeamDTO>> list(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) @Min(0) Integer pageSize,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NO) @Min(0) Integer pageNo) {
        List<Team> teams = service.getAll(pageSize, pageNo);
        return new ResponseEntity<>(objectMapper.map(teams, TeamDTO.class), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Long> create(
            @RequestBody TeamDTO teamDTO) {
        Team team = objectMapper.map(teamDTO, Team.class);
        Long id = service.create(team);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-by-number", method = RequestMethod.GET)
    public ResponseEntity<TeamDTO> getByNumber(
            @RequestParam String number) {
        Team team = service.findByNumber(number);
        TeamDTO teamDTO = objectMapper.map(team, TeamDTO.class);
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{number}" , method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> update(@PathVariable String number, @RequestBody Team team) {
        service.update(number, team);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(value = "/{number}" , method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable String number) {
        service.delete(number);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
