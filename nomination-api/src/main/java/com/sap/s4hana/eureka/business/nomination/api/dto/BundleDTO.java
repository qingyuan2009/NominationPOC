package com.sap.s4hana.eureka.business.nomination.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class BundleDTO {

    List<UserDTO> users;

    List<PeriodDTO> periods;

    List<PrizeDTO> prizes;

    List<NominationDTO> nominations;

    List<VoteDTO> votes;
}
