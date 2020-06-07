package com.brs.project.passmanager.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "T_PWSD_VAULT")
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USR_ID")
    private String usrId;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "USR_NM")
    private String userName;

    @Column(name = "USR_PSWD")
    private String password;

    @Column(name = "DT_CREATE")
    private Date dtCreate;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "DT_UPDATE")
    private Date dtUpdate;

    @Column(name = "UPDATE_BY")
    private String updateBy;

    @Column(name = "is_del")
    private String isDel;
}
