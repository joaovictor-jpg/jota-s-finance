package br.com.jota.finance.controller;

import br.com.jota.finance.DTOs.goalDTOS.GoalData;
import br.com.jota.finance.DTOs.goalDTOS.GoalDatails;
import br.com.jota.finance.DTOs.goalDTOS.GoalUpdateData;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.services.GoalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public ResponseEntity<GoalDatails> createGoal(@RequestBody @Valid GoalData data, @AuthenticationPrincipal User user, UriComponentsBuilder uriComponentsBuilder) {
        var goal = goalService.createGoal(data, user);

        var uri = uriComponentsBuilder.path("/goals/{id}").buildAndExpand(goal.idGoal()).toUri();

        return ResponseEntity.created(uri).body(goal);
    }

    @PutMapping("/{idGoal}")
    public ResponseEntity<GoalDatails> updateGoal(@PathVariable Long idGoal, @RequestBody @Valid GoalUpdateData data, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(goalService.updateGoal(idGoal, data, user));
    }

    @DeleteMapping("/{idGoal}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long idGoal, @AuthenticationPrincipal User user) {
        goalService.deleteGoal(idGoal, user);
        return ResponseEntity.noContent().build();
    }

}
