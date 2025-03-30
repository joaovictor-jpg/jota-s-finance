package br.com.jota.finance.controller;

import br.com.jota.finance.DTOs.transactionDTO.DataTransaction;
import br.com.jota.finance.DTOs.transactionDTO.DetailTransaction;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<DetailTransaction>> listTransaction(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(transactionService.listTransaction(user));
    }

    @DeleteMapping("/{idTransaction}")
    public ResponseEntity<Void> delete(@PathVariable Long idTransaction, @AuthenticationPrincipal User user) {
        transactionService.delete(idTransaction, user);
        return ResponseEntity.noContent().build();
    }
}
