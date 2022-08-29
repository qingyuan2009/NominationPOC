package com.sap.s4hana.eureka.business.nomination.core.domain.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.s4hana.eureka.business.nomination.core.domain.constant.Constants;
import com.sap.s4hana.eureka.business.nomination.core.domain.eo.RoleEO;
import com.sap.s4hana.eureka.framework.rds.dialect.ColumnTable;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.annotation.BOImplementation;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.annotation.BOProperty;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.annotation.BusinessKey;
import com.sap.s4hana.eureka.framework.rds.object.event.annotation.DomainEvent;
import com.sap.s4hana.eureka.framework.rds.object.impl.bo.DefaultBusinessObject;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "periods")
@ColumnTable
@BOImplementation(deletable = false)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Period extends DefaultBusinessObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @BOProperty
    private Long id;

    @BusinessKey
    @BOProperty
    private String periodNumber;

    @BOProperty
    private String periodName;

    @BOProperty
    private Date norminationStart;

    @BOProperty
    private Date norminationEnd;

    @BOProperty
    private Date voteStart;

    @BOProperty
    private Date voteEnd;

    //@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "period", orphanRemoval = true)
    //@BOProperty
    //List<Prize> prizes;

}
