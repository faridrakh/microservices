package com.brs.project.userservice.usergroup.entity;

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
@Table(name = "T_MT_USR_GRP")
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGroupMtn {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CD")
    private String cd;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DT_CREATE")
    private Date dtCreate;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "DT_UPDATE")
    private Date dtUpdate;

    @Column(name = "UPDATE_BY")
    private String updateBy;
}
