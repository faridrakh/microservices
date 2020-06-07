package com.brs.project.trackingservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_ACCOUNT")
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name="USER_ID")
    private String usrId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DT_CREATE")
    private Date dtCreate;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "DT_UPDATE")
    private Date dtUpdate;

    @Column(name = "UPDATE_BY")
    private String updateBy;
}
