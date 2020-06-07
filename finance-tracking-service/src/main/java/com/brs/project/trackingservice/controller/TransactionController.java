package com.brs.project.trackingservice.controller;

import com.brs.project.trackingservice.entity.TransactionEntity;
import com.brs.project.trackingservice.service.TransactionService;
import com.sytan.base.lib.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> doAddTransactionApi(@RequestBody TransactionEntity request) throws ApplicationException {
        Map<String,Object> response = new HashMap<>();
        transactionService.processInsertTrxn(request);
        response.put("transaction",request);
        response.put("errorCode","000000000");
        response.put("errorDescription","SUCCESS");
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> doUpdateTransactionApi(@PathVariable String id, @RequestBody Map<Object, Object> fields) throws ApplicationException {
        Map<String,Object> response = new HashMap<>();
        transactionService.processUpdateTrxn(id,fields);
        response.put("transaction",fields);
        response.put("errorCode","000000000");
        response.put("errorDescription","SUCCESS");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> doGetTransactionDetailApi(@PathVariable String id) throws ApplicationException {
        Map<String,Object> response = new HashMap<>();
        TransactionEntity transaction = transactionService.processGetTrxn(id);
        if(null != transaction) {
            response.put("transaction",transaction);
        }
        response.put("errorCode","000000000");
        response.put("errorDescription","SUCCESS");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{userId}/list/{page}")
    public ResponseEntity<?> doGetTransactionListApi(@PathVariable String userId,@PathVariable Integer page) throws ApplicationException {
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("errorCode","000000000");
        response.put("errorDescription","SUCCESS");
        response.putAll(transactionService.processGetTrxnList(userId,page));
        if(((List<TransactionEntity>)response.get("transactionList")).size() < 0) {
            response.put("errorCode", "900000000");
            response.put("errorDescription", "NO DATA");
        }
        return ResponseEntity.ok().body(response);
    }
}
