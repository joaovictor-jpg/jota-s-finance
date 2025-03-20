package br.com.jota.finance.controller;

import br.com.jota.finance.DTOs.bankAccountDTOs.BankAccountData;
import br.com.jota.finance.DTOs.bankAccountDTOs.BankAccountDetails;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.services.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccountDetails> createBankAccount(@Valid @RequestBody BankAccountData bankAccountData, @AuthenticationPrincipal User user, UriComponentsBuilder uriComponentsBuilder) {
        var bankAccount = bankAccountService.createBankAccount(bankAccountData, user);
        var uri = uriComponentsBuilder.path("/bankAccounts/{id}").buildAndExpand(bankAccount.id()).toUri();
        return ResponseEntity.created(uri).body(bankAccount);
    }
}
