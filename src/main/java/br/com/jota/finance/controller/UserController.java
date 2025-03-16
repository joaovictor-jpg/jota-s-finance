package br.com.jota.finance.controller;

import br.com.jota.finance.DTOs.DadosLogin;
import br.com.jota.finance.DTOs.UserRegistrationData;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
}
