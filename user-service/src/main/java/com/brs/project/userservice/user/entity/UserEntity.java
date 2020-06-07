package com.brs.project.userservice.user.entity;

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
@Table(name = "T_USR")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@ToString
public class UserEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "DT_CREATE")
    private Date dtCreate;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "DT_UPDATE")
    private Date dtUpdate;

    @Column(name = "UPDATE_BY")
    private String updateBy;

    @Column(name="USR_GRP_ID")
    private String userGrpId;
}
