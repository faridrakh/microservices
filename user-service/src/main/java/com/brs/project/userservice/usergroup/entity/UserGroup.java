package com.brs.project.userservice.usergroup.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_USR_GRP")
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGroup {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "MT_GRP_ID")
    private String mtGrpId;

    private String role;
    private String roleName;
}
