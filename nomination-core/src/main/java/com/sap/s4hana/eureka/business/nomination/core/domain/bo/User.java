package com.sap.s4hana.eureka.business.nomination.core.domain.bo;

import com.sap.s4hana.eureka.business.nomination.core.domain.constant.Constants;
import com.sap.s4hana.eureka.business.nomination.core.domain.eo.RoleEO;
import com.sap.s4hana.eureka.business.nomination.core.domain.eo.UserEO;
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
@Table(name = "users")
@ColumnTable
@BOImplementation(deletable = false)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DomainEvent(topic = Constants.EVENT_TOPIC_USER_CREATED, eventType = User.EVENT_TYPE_USER_CREATED,  dtoClass = UserEO.class)
public class User extends DefaultBusinessObject {

    public final static String EVENT_TYPE_USER_CREATED = "UserCreated";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @BOProperty
    private Long id;

    @BusinessKey
    @BOProperty
    private String userNumber;

    @BOProperty
    private String firstName;

    @BOProperty
    private String lastName;

    @BOProperty
    private String loginName;

    @BOProperty
    private String password;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @BOProperty
    private Role role;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @BOProperty
    private Team team;

}
