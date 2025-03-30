package br.com.jota.finance.controller;

import br.com.jota.finance.DTOs.transactionDTO.DataTransaction;
import br.com.jota.finance.DTOs.transactionDTO.DetailTransaction;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<DetailTransaction> transaction(@Valid @RequestBody DataTransaction data, @AuthenticationPrincipal User user, UriComponentsBuilder uriComponentsBuilder) {
        DetailTransaction detailTransaction = transactionService.registerTransaction(data, user);
        var uri = uriComponentsBuilder.path("/transactions/{id}").buildAndExpand(detailTransaction.idTransaction()).toUri();
        return ResponseEntity.created(uri).body(detailTransaction);
    }
}
