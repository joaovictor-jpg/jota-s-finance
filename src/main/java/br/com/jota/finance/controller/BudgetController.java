package br.com.jota.finance.controller;

import br.com.jota.finance.DTOs.budgetDTO.BudgetDetails;
import br.com.jota.finance.DTOs.budgetDTO.DataBudget;
import br.com.jota.finance.DTOs.budgetDTO.DataUpdateBudget;
import br.com.jota.finance.DTOs.budgetDTO.DataValueBudget;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.services.BudgetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<BudgetDetails> create(@Valid @RequestBody DataBudget data, @AuthenticationPrincipal User user, UriComponentsBuilder uriComponentsBuilder) {
        var budgetDetails = budgetService.createBudget(data, user);
        var uri = uriComponentsBuilder.path("/budgets/{id}").buildAndExpand(budgetDetails.idBudget()).toUri();
        return ResponseEntity.created(uri).body(budgetDetails);
    }

    @GetMapping
    public ResponseEntity<List<BudgetDetails>> listBudget(@AuthenticationPrincipal User user) {
        var budgetDetails = budgetService.findByUser(user);
        return ResponseEntity.ok().body(budgetDetails);
    }

    @PatchMapping("/{idBudget}")
    public ResponseEntity<BudgetDetails> updateValueBudget(@PathVariable Long idBudget, @AuthenticationPrincipal User user, @Valid @RequestBody DataValueBudget data) {
        return ResponseEntity.ok().body(budgetService.updateValue(idBudget, user, data));
    }

    @PutMapping("/{idBudget}")
    public ResponseEntity<BudgetDetails> updateBudget(@PathVariable Long idBudget, @AuthenticationPrincipal User user, @Valid @RequestBody DataUpdateBudget data) {
        return ResponseEntity.ok().body(budgetService.updateBudget(idBudget, user, data));
    }

    @DeleteMapping("/{idBudget}")
    public ResponseEntity<Void> delete(@PathVariable Long idBudget, @AuthenticationPrincipal User user) {
        budgetService.delete(idBudget, user);
        return ResponseEntity.noContent().build();
    }
}
