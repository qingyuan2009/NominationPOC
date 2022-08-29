package com.sap.s4hana.eureka.business.nomination.core.domain.bo;

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

@Data
@Entity
@Table(name = "teams")
@ColumnTable
@BOImplementation(deletable = false)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Team extends DefaultBusinessObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @BOProperty
    private Long id;

    @BusinessKey
    @BOProperty
    private String teamNumber;

    @BOProperty
    private String teamName;

}
