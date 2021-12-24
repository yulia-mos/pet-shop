package edu.kpi.iasa.mmsa.petshop.controller;

import edu.kpi.iasa.mmsa.petshop.dto.AccountDto;
import edu.kpi.iasa.mmsa.petshop.model.Account;
import edu.kpi.iasa.mmsa.petshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }
    @GetMapping(value="/account")
    public ResponseEntity<List<Account>> getAccounts(){
        return ResponseEntity.ok(accountService.getAccounts());
    }
    @PostMapping(value = "/account")
    public ResponseEntity<Account> postAccount(@Valid @RequestBody AccountDto newAccount) {
        return ResponseEntity.ok(accountService.saveAccount(newAccount));
    }

    @GetMapping(value = "/account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PutMapping(value = "/account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDto updatedAccount) {
        return ResponseEntity.ok(accountService.updateAccountById(id, updatedAccount));
    }

    @DeleteMapping(value = "/account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.deleteAccountById(id));
    }
}
