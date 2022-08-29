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
@Table(name = "roles")
@ColumnTable
@BOImplementation(deletable = false)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DomainEvent(topic = Constants.EVENT_TOPIC_ROLE_CREATED, eventType = Role.EVENT_TYPE_ROLE_CREATED,  dtoClass = RoleEO.class)
public class Role extends DefaultBusinessObject {

    public final static String EVENT_TYPE_ROLE_CREATED = "RoleCreated";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @BOProperty
    private Long id;

    @BusinessKey
    @BOProperty
    private String roleNumber;

    @BOProperty
    private String roleName;

}
