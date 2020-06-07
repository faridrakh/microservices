package com.brs.project.passmanager.controller;

import com.brs.project.passmanager.entity.PasswordEntity;
import com.brs.project.passmanager.service.PassManagerService;
import com.netflix.discovery.CommonConstants;
import com.sytan.base.lib.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class PassManagerController {
    private PassManagerService passManagerService;

    public PassManagerController(PassManagerService passManagerService){
        this.passManagerService = passManagerService;
    }

    @PostMapping()
    public ResponseEntity<?> doInsertPasswordApi(@RequestBody PasswordEntity request) throws ApplicationException {
        Map<String, Object> response = new HashMap<>();
        PasswordEntity pass = passManagerService.addNewPassword(request);
        response.put("passDetail", pass);
        response.put("errorCode", "000000000");
        response.put("errorDescription", "SUCCESS");
        return ResponseEntity.status(200).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> doUpdatePasswordApi(@PathVariable String id, @RequestBody Map<Object, Object> fields) throws ApplicationException {
        Map<String, Object> response = new HashMap<>();
        String result = passManagerService.updatePassword(id, fields);
        if("SUCCESS".equals(result)) {
            response.put("errorCode", "000000000");
            response.put("errorDescription", "SUCCESS");
        }
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> doDeletePasswordApi(@PathVariable String id) throws ApplicationException {
        Map<String, Object> response = new HashMap<>();
        String result = passManagerService.deletePassword(id);
        if("SUCCESS".equals(result)) {
            response.put("errorCode", "000000000");
            response.put("errorDescription", "SUCCESS");
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping(value = "/{userId}/list/{page}")
    public ResponseEntity<?> doGetPasswordListApi(@PathVariable String userId,@PathVariable Integer page) throws ApplicationException {
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("errorCode", "000000000");
        response.put("errorDescription", "SUCCESS");
        response.putAll(passManagerService.getPasswordList(userId,page));
        if(((List<PasswordEntity>)response.get("passList")).size() < 0) {
            //result.put("passVaultList",new ArrayList<>());
            response.put("errorCode", "900000000");
            response.put("errorDescription", "NO DATA");
        }
        return ResponseEntity.ok().body(response);
    }
}
