package com.friendly.passbook.controller;

import com.friendly.passbook.dto.AccountDto;
import com.friendly.passbook.service.AccountService;
import com.friendly.passbook.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class BalanceController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<ApiResponse<AccountDto>> getBalance(@PathVariable String accountNumber) {
        try {
            AccountDto dto = accountService.getAccountBalance(accountNumber);
            return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Balance fetched", dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("ERROR", e.getMessage(), null));
        }
    }
}
