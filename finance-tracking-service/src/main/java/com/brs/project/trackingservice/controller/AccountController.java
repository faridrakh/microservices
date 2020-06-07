package com.brs.project.trackingservice.controller;

import com.brs.project.trackingservice.entity.AccountEntity;
import com.brs.project.trackingservice.service.AccountService;
import com.sytan.base.lib.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> doAddAccountApi(@RequestBody AccountEntity request) throws ApplicationException {
        Map<String,Object> response = new HashMap<>();
        accountService.processAddAccount(request);
        response.put("errorCode","000000000");
        response.put("errorDescription","SUCCESS");
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> doUpdateAccountApi(@PathVariable String id, @RequestBody Map<Object, Object> fields) throws ApplicationException {
        Map<String,Object> response = new HashMap<>();
        accountService.processUpdateAccount(id,fields);
        response.put("errorCode","000000000");
        response.put("errorDescription","SUCCESS");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{uid}/{aid}")
    public ResponseEntity<?> doGetAccountDetailApi(@PathVariable String uid,@PathVariable String aid) throws ApplicationException {
        Map<String,Object> response = new HashMap<>();
        AccountEntity account = accountService.processGetAccount(uid,aid);
        if(null != account) {
            response.put("account",account);
            response.put("errorCode","000000000");
            response.put("errorDescription","SUCCESS");
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{userId}/list/{page}")
    public ResponseEntity<?> doGetAccountListApi(@PathVariable String userId,@PathVariable Integer page) throws ApplicationException {
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("errorCode","000000000");
        response.put("errorDescription","SUCCESS");
        response.putAll(accountService.processGetAccountList(userId,page));
        if(((List<AccountEntity>)response.get("accountList")).size() < 0) {
            response.put("errorCode", "900000000");
            response.put("errorDescription", "NO DATA");
        }
        return ResponseEntity.ok().body(response);
    }
}
