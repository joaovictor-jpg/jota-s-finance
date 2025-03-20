package br.com.jota.finance.controller;

import br.com.jota.finance.DTOs.DadosLogin;
import br.com.jota.finance.DTOs.UserRegistrationData;
import br.com.jota.finance.DTOs.bankAccountDTOs.BankAccountDetails;
import br.com.jota.finance.DTOs.goalDTOS.GoalDatails;
import br.com.jota.finance.entities.BankAccount;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody DadosLogin dados) {
        return ResponseEntity.ok().body(userServices.login(dados));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody UserRegistrationData data, UriComponentsBuilder uriComponentsBuilder) {
        User user = userServices.signup(data);
        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/goals")
    public ResponseEntity<List<GoalDatails>> getGoals(@AuthenticationPrincipal User user) {
        List<GoalDatails> goalDatails = userServices.getGoals(user.getId());
        return ResponseEntity.ok().body(goalDatails);
    }

    @GetMapping("/bankAccount")
    public ResponseEntity<List<BankAccountDetails>> getBankAccount(@AuthenticationPrincipal User user) {
        List<BankAccountDetails> bankAccountDetails = userServices.getBankAccount(user.getId());
        return ResponseEntity.ok().body(bankAccountDetails);
    }
}
