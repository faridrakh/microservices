package com.brs.project.userservice.user.controller;

import com.brs.project.userservice.user.entity.UserEntity;
import com.brs.project.userservice.user.service.UserService;
import com.brs.project.userservice.usergroup.entity.UserGroup;
import com.brs.project.userservice.usergroup.service.UserGroupService;
import com.sytan.base.lib.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private UserGroupService userGroupService;

    public UserController(UserService userService, UserGroupService userGroupService){
        this.userService = userService;
        this.userGroupService = userGroupService;
    }

    @PostMapping()
    public ResponseEntity<?> doAddUserApi(@RequestBody UserEntity request) throws ApplicationException {
        Map<String, Object> response = new HashMap<>();
        UserEntity user;
        user = userService.addUser(request);
        response.put("user", user);
        response.put("errorCode", "000000000");
        response.put("errorDescription", "SUCCESS");
        return ResponseEntity.status(200).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> doUpdateUserApi(@PathVariable String id,@RequestBody Map<Object, Object> fields) {
        Map<String, Object> response = new HashMap<>();
        String result = userService.updateUser(id, fields);
        if("SUCCESS".equals(result)) {
            response.put("errorCode", "000000000");
            response.put("errorDescription", "SUCCESS");
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> doGetUserDetailApi(@PathVariable String id) throws ApplicationException {
        Map<String, Object> response = new HashMap<>();
        UserEntity user;
        UserGroup userGroup;
        user = userService.getUser(id);
        userGroup = userGroupService.getUserGrp(user.getUserGrpId());
        if(null != user) {
            response.put("user", user);
        }
        if(null != userGroup) {
            response.put("userGroup", userGroup);
        }
        response.put("errorCode", "000000000");
        response.put("errorDescription", "SUCCESS");
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/check-user/{userName}")
    public ResponseEntity<?> doCheckUserName(@PathVariable String userName) {
        Map<String, Object> response = new HashMap<>();
        UserEntity user;
        user = userService.getUserByUserName(userName);
        if(null != user) {
            response.put("errorCode", "100000001");
            response.put("errorDescription", "Username already used");
        } else {
            response.put("errorCode", "000000000");
            response.put("errorDescription", "SUCCESS");
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity<?> doCheckUserEmail(@PathVariable String email) {
        Map<String, Object> response = new HashMap<>();
        UserEntity user;
        user = userService.getUserByEmail(email);
        if(null != user) {
            response.put("errorCode", "100000002");
            response.put("errorDescription", "Email already used");
        } else {
            response.put("errorCode", "000000000");
            response.put("errorDescription", "SUCCESS");
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/user-for-auth/{userName}")
    public ResponseEntity<?> doGetUserName(@PathVariable String userName) throws ApplicationException {
        Map<String, Object> response = new HashMap<>();
        UserEntity user;
        UserGroup userGroup;
        user = userService.getUserByUserName(userName);
        userGroup = userGroupService.getUserGrp(user.getUserGrpId());
        if(null != user) {
            response.put("errorCode", "000000000");
            response.put("errorDescription", "SUCCESS");
            response.put("userName", user.getUserName());
            response.put("password", user.getPassword());
        }
        if(null != userGroup) {
            response.put("userRole", userGroup.getRole());
        }
        return ResponseEntity.status(200).body(response);
    }
}
