package br.com.jota.finance.controller;

import br.com.jota.finance.DTOs.goalDTOS.GoalData;
import br.com.jota.finance.DTOs.goalDTOS.GoalDatails;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.services.GoalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
