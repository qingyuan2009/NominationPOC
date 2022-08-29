package com.sap.s4hana.eureka.business.nomination.api.controller;

import com.sap.s4hana.eureka.business.nomination.api.dto.BundleDTO;
import com.sap.s4hana.eureka.business.nomination.api.dto.PeriodDTO;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Bundle;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Nomination;
import com.sap.s4hana.eureka.business.nomination.core.domain.bo.Period;
import com.sap.s4hana.eureka.business.nomination.core.service.BundleService;
import com.sap.s4hana.eureka.business.nomination.core.service.PeriodService;
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

@RequestMapping(value = {"/api/upload"})
@Validated
@RestController
public class BundleController {

    private static final String DEFAULT_PAGE_NO = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";

    private static final Logger LOGGER = LoggerFactory.getLogger(BundleController.class);

    @Autowired
    BundleService service;

    @Autowired
    private ObjectMapper objectMapper;


    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> create(
            @RequestBody BundleDTO bundleDTO) {
        Bundle bundle = objectMapper.map(bundleDTO, Bundle.class);
        service.create(bundle);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }


}
