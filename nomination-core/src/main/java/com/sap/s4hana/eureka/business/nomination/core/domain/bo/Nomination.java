package com.sap.s4hana.eureka.business.nomination.core.domain.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.s4hana.eureka.framework.rds.dialect.ColumnTable;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.annotation.BOImplementation;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.annotation.BOProperty;
import com.sap.s4hana.eureka.framework.rds.object.common.bo.annotation.BusinessKey;
import com.sap.s4hana.eureka.framework.rds.object.impl.bo.DefaultBusinessObject;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "nominations")
@ColumnTable
@BOImplementation(deletable = false)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Nomination extends DefaultBusinessObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @BOProperty
    private Long id;

    @BusinessKey
    @BOProperty
    private String nominationNumber;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @BOProperty
    private User nominee;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @BOProperty
    private Prize prize;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @BOProperty
    private Period period;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @BOProperty
    private User nominator;

    @BOProperty
    private String comment;

}
