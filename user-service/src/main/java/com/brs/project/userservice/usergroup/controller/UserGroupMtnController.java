package com.brs.project.userservice.usergroup.controller;

import com.brs.project.userservice.usergroup.entity.UserGroupMtn;
import com.brs.project.userservice.usergroup.service.UserGroupMtnService;
import com.sytan.base.lib.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mtn")
public class UserGroupMtnController {

    private UserGroupMtnService userGroupMtnService;

    public UserGroupMtnController(UserGroupMtnService userGroupMtnService){
        this.userGroupMtnService = userGroupMtnService;
    }

    @GetMapping("/user-group-list")
    public ResponseEntity<?> doGetUserGroupListRsApi() throws ApplicationException {
        List<UserGroupMtn> list = userGroupMtnService.getUserGroupMtnList();
        Map<String, Object> response = new HashMap<>();
        response.put("userGroupMtnList", list);
        return ResponseEntity.status(200).body(response);
    }
}
